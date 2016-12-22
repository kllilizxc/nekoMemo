package android.nekomem.nekoshopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.nekomem.R;
import android.nekomem.nekomemo.atsume_1;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static android.nekomem.nekoshopping.Store_fourth.choose_unhappy;
import static android.nekomem.nekoshopping.Store_fourth.choose_unicorn;
import static android.nekomem.nekoshopping.Store_fourth.choose_wool;
import static android.nekomem.nekoshopping.Store_second.choose_happybirthday;
import static android.nekomem.nekoshopping.Store_second.choose_lap;
import static android.nekomem.nekoshopping.Store_second.choose_lying;
import static android.nekomem.nekoshopping.Store_second.choose_noodle;
import static android.nekomem.nekoshopping.Store_third.choose_planket;
import static android.nekomem.nekoshopping.Store_third.choose_popcorn;
import static android.nekomem.nekoshopping.Store_third.choose_sleepwell;
import static android.nekomem.nekoshopping.Store_third.choose_sushi;

public class Store_first extends AppCompatActivity {

    public static long money_set = 777;
    public static int stop_music = 0;
    public static int choose_car = 0;
    public static int choose_cute = 0;
    public static int choose_donut = 0;
    public static int choose_doose = 0;

    private MediaPlayer player = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_first);

        money_set = getIntent().getLongExtra("score", 0);

        SharedPreferences get_preferences = getSharedPreferences("group_one", Context.MODE_PRIVATE);
        choose_car = get_preferences.getInt("flag1",0);
        choose_cute = get_preferences.getInt("flag2",0);
        choose_donut = get_preferences.getInt("flag3",0);
        choose_doose = get_preferences.getInt("flag4",0);


        final MediaPlayer player = MediaPlayer.create(this, R.raw.cat_voice);
        final ImageView paw_music = (ImageView) findViewById(R.id.paw_music);
        player.start();

        paw_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(stop_music == 0){
                    stop_music = 1;
                    paw_music.setAlpha(0.3f);
                    player.pause();
                }
                else {
                    stop_music = 0;
                    paw_music.setAlpha(1.0f);
                    player.start();
                }
            }
        });

        final ImageView next_page = (ImageView) findViewById(R.id.right);
        next_page.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                player.stop();
                Intent next_intent = new Intent(Store_first.this, Store_second.class);
                next_intent.putExtra("data",""+money_set);
                startActivity(next_intent);
            }
        });

        final ImageView house_home = (ImageView) findViewById(R.id.house_home);
        house_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                player.stop();
                Intent house_home_intent = new Intent(Store_first.this, atsume_1.class);
                house_home_intent.putExtra("choose_infor0",true); //sleep
                house_home_intent.putExtra("choose_infor1",choose_sleepwell);
                house_home_intent.putExtra("choose_infor2",choose_unicorn);
                house_home_intent.putExtra("choose_infor3",true); //what
                house_home_intent.putExtra("choose_infor4",choose_car);
                house_home_intent.putExtra("choose_infor5",choose_cute);
                house_home_intent.putExtra("choose_infor6",choose_lap);
                house_home_intent.putExtra("choose_infor7",choose_lying);
                house_home_intent.putExtra("choose_infor8",choose_planket);
                house_home_intent.putExtra("choose_infor9",choose_unhappy);
                house_home_intent.putExtra("choose_infor10",choose_wool);
                house_home_intent.putExtra("choose_infor11",choose_doose);
                house_home_intent.putExtra("choose_infor12",choose_donut);
                house_home_intent.putExtra("choose_infor13",choose_happybirthday);
                house_home_intent.putExtra("choose_infor14",choose_popcorn);
                house_home_intent.putExtra("choose_infor15",choose_noodle);
                house_home_intent.putExtra("choose_infor16",choose_sushi);
                startActivity(house_home_intent);
            }
        });

        final TextView money_left = (TextView) findViewById(R.id.money_left);
        money_left.setText(""+money_set);

        final ImageView car = (ImageView) findViewById(R.id.car);
        final ImageView cute = (ImageView) findViewById(R.id.cute);
        final ImageView donut = (ImageView) findViewById(R.id.donut);
        final ImageView doose = (ImageView) findViewById(R.id.doose);

        final TextView value_pic_1 = (TextView) findViewById(R.id.textView3);
        final TextView value_pic_2 = (TextView) findViewById(R.id.textView4);
        final TextView value_pic_3 = (TextView) findViewById(R.id.textView7);
        final TextView value_pic_4 = (TextView) findViewById(R.id.textView8);

        car.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                if(money_set >= 10 && choose_car == 0){
                    money_set = money_set - 10;
                    money_left.setText(""+money_set);
                    choose_car = 1;
                    car.setAlpha(0.3f);
                    value_pic_1.setText("bought");
                    value_pic_1.setTextColor(Color.BLACK);
                    value_pic_1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
                    SharedPreferences preferences = getSharedPreferences("group_one", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("flag1", 1);
                    editor.commit();
                }
            }
        });

        cute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(money_set >= 20 && choose_cute == 0){
                    money_set = money_set - 20;
                    money_left.setText(""+money_set);
                    choose_cute = 1;
                    cute.setAlpha(0.3f);
                    value_pic_2.setText("bought");
                    value_pic_2.setTextColor(Color.BLACK);
                    value_pic_2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
                    SharedPreferences preferences = getSharedPreferences("group_one", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("flag2", 1);
                    editor.commit();
                }
            }
        });

        donut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(money_set >= 30 && choose_donut == 0){
                    money_set = money_set - 30;
                    money_left.setText(""+money_set);
                    choose_donut = 1;
                    donut.setAlpha(0.3f);
                    value_pic_3.setText("bought");
                    value_pic_3.setTextColor(Color.BLACK);
                    value_pic_3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
                    SharedPreferences preferences = getSharedPreferences("group_one", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("flag3", 1);
                    editor.commit();
                }
            }
        });

        doose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(money_set >= 40 && choose_doose == 0){
                    money_set = money_set - 40;
                    money_left.setText(""+money_set);
                    choose_doose = 1;
                    doose.setAlpha(0.3f);
                    value_pic_4.setText("bought");
                    value_pic_4.setTextColor(Color.BLACK);
                    value_pic_4.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
                    SharedPreferences preferences = getSharedPreferences("group_one", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("flag4", 1);
                    editor.commit();
                }
            }
        });



        if(choose_car == 1){
            car.setAlpha(0.3f);
            value_pic_1.setText("bought");
            value_pic_1.setTextColor(Color.BLACK);
            value_pic_1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
        }
        if(choose_cute == 1){
            cute.setAlpha(0.3f);
            value_pic_2.setText("bought");
            value_pic_2.setTextColor(Color.BLACK);
            value_pic_2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
        }
        if(choose_donut == 1){
            donut.setAlpha(0.3f);
            value_pic_3.setText("bought");
            value_pic_3.setTextColor(Color.BLACK);
            value_pic_3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
        }
        if(choose_doose == 1){
            doose.setAlpha(0.3f);
            value_pic_4.setText("bought");
            value_pic_4.setTextColor(Color.BLACK);
            value_pic_4.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
        }


    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(player != null){
            player.stop();
            player.release();
        }
    }
}
