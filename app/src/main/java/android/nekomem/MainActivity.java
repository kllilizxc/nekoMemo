package android.nekomem;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nekomem.nekomemo.atsume_1;
import android.nekomem.nekoshopping.Store_first;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.nekomem.actions.ActionsCreator;
import android.nekomem.dispatcher.Dispatcher;
import android.nekomem.model.TodoModel;
import android.nekomem.stores.ScoreStore;
import android.nekomem.stores.TodoStore;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.yalantis.beamazingtoday.interfaces.AnimationType;
import com.yalantis.beamazingtoday.interfaces.BatModel;
import com.yalantis.beamazingtoday.listeners.BatListener;
import com.yalantis.beamazingtoday.listeners.OnItemClickListener;
import com.yalantis.beamazingtoday.listeners.OnOutsideClickedListener;
import com.yalantis.beamazingtoday.ui.adapter.BatAdapter;
import com.yalantis.beamazingtoday.ui.animator.BatItemAnimator;
import com.yalantis.beamazingtoday.ui.callback.BatCallback;
import com.yalantis.beamazingtoday.ui.widget.BatRecyclerView;
import com.yalantis.beamazingtoday.util.TypefaceUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BatListener, OnItemClickListener, OnOutsideClickedListener {
    private BatRecyclerView mRecyclerView;
    private BatAdapter mAdapter;
    private List<BatModel> mTodos;
    private BatItemAnimator mAnimator;
    private LinearLayout root;
    private long animDuration;

    private Dispatcher dispatcher;
    private ActionsCreator actionsCreator;
    private TodoStore todoStore;
    private ScoreStore scoreStore;

    private SQLiteDatabase db;
    private String dataBaseName = "Todo";
    private String tableName = "todos";

    private long scores = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDependencies();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        TextView title = (TextView) findViewById(R.id.title);
        title.setTypeface(TypefaceUtil.getAvenirTypeface(this));

        mRecyclerView = (BatRecyclerView) findViewById(R.id.bat_recycler_view);
        mAnimator = new BatItemAnimator();
        mTodos = todoStore.getTodoModels();
        mAdapter = new BatAdapter(mTodos, this, mAnimator);

        mRecyclerView.getView().setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setOnItemClickListener(this).setOnOutsideClickListener(this);
        mRecyclerView.getView().setAdapter(mAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new BatCallback(this));
        itemTouchHelper.attachToRecyclerView(mRecyclerView.getView());

        mRecyclerView.getView().setItemAnimator(mAnimator);
        mRecyclerView.setAddItemListener(this);

        root = (LinearLayout) findViewById(R.id.activity_todo);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.revertAnimation();
            }
        });

        ImageView toHomeButton = (ImageView) findViewById(R.id.toHomeButton);
        toHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(MainActivity.this, atsume_1.class);
                home.putExtra("score", scores);
                startActivity(home);
            }
        });

        //database
        initDataBase();

        setTitle();
    }

    private void initDataBase() {
        db = openOrCreateDatabase(dataBaseName + ".db", Context.MODE_PRIVATE, null);

        Cursor cursor = db.rawQuery("SELECT DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
        if (cursor != null && cursor.getCount() == 0) {
            //todos table not exist
            db.execSQL("CREATE TABLE " + tableName + " (_id INTEGER PRIMARY KEY, todo VARCHAR, isChecked BOOLEAN)");
            cursor.close();
        }

        cursor = db.rawQuery("SELECT DISTINCT tbl_name from sqlite_master where tbl_name = 'scores'", null);
        if (cursor != null && cursor.getCount() == 0) {
            //score table not exist
            db.execSQL("CREATE TABLE scores (_id INTEGER PRIMARY KEY AUTOINCREMENT, score INTEGER)");
            cursor.close();
        }

        //todos read data
        mTodos.clear();
        cursor = db.rawQuery("SELECT * FROM " + tableName, null);
        while (cursor.moveToNext()) {
            long _id = cursor.getLong(cursor.getColumnIndex("_id"));
            String todo = cursor.getString(cursor.getColumnIndex("todo"));
            boolean isChecked = cursor.getInt(cursor.getColumnIndex("isChecked")) != 0;
            TodoModel todoModel = new TodoModel(_id, todo, isChecked);
            if(isChecked) mTodos.add(todoModel);
            else mTodos.add(0, todoModel);
        }
        cursor.close();

        //score read data
        cursor = db.rawQuery("SELECT * FROM scores", null);
        while (cursor.moveToNext()) {
            long _score = cursor.getLong(cursor.getColumnIndex("score"));
            if(_score > scores) {
                scoreStore.setScore(_score);
                scores = _score;
            }
        }
        cursor.close();

    }

    private void writeBackToDataBase() {
        Cursor cursor = null;

        for (BatModel todoModel : mTodos) {
            long _id = ((TodoModel) todoModel).getId();
            String todo = ((TodoModel) todoModel).getText();
            boolean isChecked = ((TodoModel) todoModel).isChecked();

            cursor = db.rawQuery("SELECT * FROM " + tableName + " WHERE _id = '" + _id + "'", null);
            if (cursor != null && cursor.getCount() == 0) {
                db.execSQL("INSERT INTO " + tableName + " VALUES(?,?,?)", new Object[]{_id, todo, isChecked});
            } else {
                db.execSQL("UPDATE " + tableName + " SET todo = ?, isChecked = ? WHERE _id = ?", new Object[]{todo, isChecked, _id});
            }

            if(cursor != null) cursor.close();
        }

        cursor = db.rawQuery("SELECT * FROM scores", null);
        if (cursor.getCount() == 0) {
            db.execSQL("INSERT INTO scores VALUES(NULL,?)", new Object[]{scores});
        } else {
            db.execSQL("UPDATE scores SET score = ?", new Object[]{scores});
        }
    }

    private void setTitle() {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText("" + scores);
    }

    private void initDependencies() {
        dispatcher = Dispatcher.get(new Bus());
        actionsCreator = ActionsCreator.get(dispatcher);
        todoStore = TodoStore.get(dispatcher);
        scoreStore = ScoreStore.get(dispatcher);
    }

    @Override
    public void add(String data) {
        animDuration = mAnimator.getAddDuration();
        if (!TextUtils.isEmpty(data)) {
            actionsCreator.create(data);
        }
        mAdapter.notify(AnimationType.ADD, 0);
    }

    @Override
    public void delete(int position) {
        animDuration = mAnimator.getRemoveDuration();
        actionsCreator.destroy(((TodoModel) (mTodos.get(position))).getId());
        mAdapter.notify(AnimationType.REMOVE, position);

        if (todoStore.canUndo()) {
            Snackbar snackbar = Snackbar.make(root, "Element deleted", Snackbar.LENGTH_LONG);
            snackbar.setAction("Undo", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actionsCreator.undoDestroy();
                    mAdapter.notify(AnimationType.ADD, 0);
                }
            });
            snackbar.show();
        }
    }

    @Override
    public void move(int from, int to) {
        if (from >= 0 && to >= 0) {
            animDuration = mAnimator.getMoveDuration();

            TodoModel todoModel = (TodoModel) mTodos.get(from);
            if(todoModel.isChecked()) {
                actionsCreator.decScore();
                Toast.makeText(this, "Score -" + ScoreStore.getStep(), Toast.LENGTH_SHORT).show();
            }
            else {
                actionsCreator.incScore();
                Toast.makeText(this, "Score +" + ScoreStore.getStep(), Toast.LENGTH_SHORT).show();
            }

            mAnimator.setPosition(to);
            actionsCreator.move(from, to);
            mAdapter.notify(AnimationType.MOVE, from, to);

            if (from == 0 || to == 0) {
                mRecyclerView.getView().scrollToPosition(Math.min(from, to));
            }
        }
    }

    @Override
    public void onClick(BatModel item, int position) {
        Toast.makeText(this, item.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOutsideClicked() {
        mRecyclerView.revertAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ;
        dispatcher.register(this);
        dispatcher.register(todoStore);
        dispatcher.register(scoreStore);
        db = openOrCreateDatabase(dataBaseName + ".db", Context.MODE_PRIVATE, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        dispatcher.unregister(this);
        dispatcher.unregister(todoStore);
        dispatcher.unregister(scoreStore);
        writeBackToDataBase();
        db.close();
    }

    @Subscribe
    public void onTodoStoreChanged(TodoStore.TodoStoreChangeEvent event) {
        updateUI();
    }

    @Subscribe
    public void onScoreStoreChanged(ScoreStore.ScoreStoreChangeEvent event) {
        scores = scoreStore.getScore();
        setTitle();
    }

    private void updateUI() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                mAdapter.notifyDataSetChanged();
            }
        }, animDuration * 10);
    }
}
