package android.nekomem.stores;

import android.nekomem.actions.Action;
import android.nekomem.actions.TodoActions;
import android.nekomem.dispatcher.Dispatcher;
import android.nekomem.model.TodoModel;
import com.squareup.otto.Subscribe;
import com.yalantis.beamazingtoday.interfaces.BatModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 54179 on 2016/12/8.
 */

public class TodoStore extends Store {
    private static TodoStore instance;
    private final List<BatModel> todoModels;
    private TodoModel lastDeleted;

    public TodoStore(Dispatcher dispatcher) {
        super(dispatcher);
        todoModels = new ArrayList<>();
    }

    public static TodoStore get(Dispatcher dispatcher) {
        if(instance == null) {
            instance = new TodoStore(dispatcher);
        }
        return instance;
    }

    public List<BatModel> getTodoModels() {
        return todoModels;
    }

    public boolean canUndo() {
        return lastDeleted != null;
    }

    private void addElement(TodoModel clone) {
        if(clone.isChecked()) todoModels.add(clone);
        else todoModels.add(0, clone);
    }

    private void create(String text) {
        long id = System.currentTimeMillis();
        TodoModel todoModel = new TodoModel(id, text);
        addElement(todoModel);
    }

    private void destroy(long id) {
        Iterator<BatModel> iter = todoModels.iterator();
        while (iter.hasNext()) {
            TodoModel todoModel = (TodoModel) iter.next();
            if(todoModel.getId() == id) {
                lastDeleted = todoModel.clone();
                iter.remove();
                break;
            }
        }
    }

    private void undoDestroy() {
        if(canUndo()) {
            addElement(lastDeleted.clone());
            lastDeleted = null;
        }
    }

    private TodoModel getById(long id) {
        Iterator<BatModel> iter = todoModels.iterator();
        while(iter.hasNext()) {
            TodoModel todoModel = (TodoModel) iter.next();
            if(todoModel.getId() == id)
                return todoModel;
        }
        return null;
    }

    private void updateComplete(long id, boolean complete) {
        TodoModel todoModel = getById(id);
    }

    private void updateAllComplete(boolean checked) {
        for (BatModel todomodel : todoModels) {
            todomodel.setChecked(checked);
        }
    }

    private boolean areAllComplete() {
        for (BatModel todoModel : todoModels) {
            if(!todoModel.isChecked()) return false;
        }
        return true;
    }

    private void toggleAllComplete() {
        if(areAllComplete()) updateAllComplete(false);
        else updateAllComplete(true);
    }

    private void destroyCompleted() {
        Iterator<BatModel> iter = todoModels.iterator();
        while (iter.hasNext()) {
            TodoModel todoModel = (TodoModel) iter.next();
            if(todoModel.isChecked())
                iter.remove();
        }
    }

    private void move(int from, int to) {
        BatModel model = todoModels.get(from);
        todoModels.remove(model);
        todoModels.add(to, model);
    }

    @Override
    @Subscribe
    @SuppressWarnings("unchecked")
    public void onAction(Action action) {
        long id;
        switch (action.getType()) {
            case TodoActions.TODO_CREATE:
                String text = ((String) action.getData().get(TodoActions.KEY_TEXT));
                create(text);
                break;
            case TodoActions.TODO_DESTROY:
                id = ((long) action.getData().get(TodoActions.KEY_ID));
                destroy(id);
                break;
            case TodoActions.TODO_UNDO_DESTROY:
                undoDestroy();
                break;
            case TodoActions.TODO_COMPLETE:
                id = ((long) action.getData().get(TodoActions.KEY_ID));
                updateComplete(id, true);
                break;
            case TodoActions.TODO_UNDO_COMPLETE:
                id = ((long) action.getData().get(TodoActions.KEY_ID));
                updateComplete(id, false);
                break;
            case TodoActions.TODO_DESTROY_COMPLETED:
                destroyCompleted();
                break;
            case TodoActions.TODO_TOGGLE_COMPLETE_ALL:
                toggleAllComplete();
                break;
            case TodoActions.TODO_MOVE:
                int from = ((int) action.getData().get(TodoActions.POS_FROM));
                int to = ((int) action.getData().get(TodoActions.POS_TO));
                move(from, to);
                break;
        }
        emitStoreChange();
    }

    @Override
    StoreChangeEvent changeEvent() {
        return new TodoStoreChangeEvent();
    }

    public class TodoStoreChangeEvent implements StoreChangeEvent {}
}
