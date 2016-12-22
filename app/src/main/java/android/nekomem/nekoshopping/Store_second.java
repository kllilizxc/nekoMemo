package android.nekomem.nekoshopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.nekomem.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.nekomem.nekoshopping.Store_first.choose_car;
import static android.nekomem.nekoshopping.Store_first.choose_cute;
import static android.nekomem.nekoshopping.Store_first.choose_donut;
import static android.nekomem.nekoshopping.Store_first.choose_doose;
import static android.nekomem.nekoshopping.Store_first.money_set;
import static android.nekomem.nekoshopping.Store_first.stop_music;


public class Store_second extends AppCompatActivity {

    public static int choose_happybirthday = 0;
    public static int choose_lap = 0;
    public static int choose_lying = 0;
    public static int choose_noodle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_second);

        SharedPreferences get_preferences = getSharedPreferences("group_two", Context.MODE_PRIVATE);
        choose_happybirthday = get_preferences.getInt("flag5",0);
        choose_lap = get_preferences.getInt("flag6",0);
        choose_lying = get_preferences.getInt("flag7",0);
        choose_noodle = get_preferences.getInt("flag8",0);

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

        final TextView money_left = (TextView) findViewById(R.id.money_left);
        Intent get_money_left = getIntent();
        money_left.setText(get_money_left.getStringExtra("data"));

        final ImageView last_page = (ImageView) findViewById(R.id.left);
        last_page.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                player.stop();
                Intent last_intent = new Intent(Store_second.this, Store_first.class);
                last_intent.putExtra("data",""+money_set);
                startActivity(last_intent);
            }
        });


        final ImageView next_page = (ImageView) findViewById(R.id.right);
        next_page.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                player.stop();
                Intent next_intent = new Intent(Store_second.this, Store_third.class);
                next_intent.putExtra("data",""+money_set);
                startActivity(next_intent);
            }
        });

        final ImageView happybirthday = (ImageView) findViewById(R.id.happybirthday);
        final ImageView lap = (ImageView) findViewById(R.id.lap);
        final ImageView lying = (ImageView) findViewById(R.id.lying);
        final ImageView noodle = (ImageView) findViewById(R.id.noodle);

        final TextView value_pic_1 = (TextView) findViewById(R.id.textView3);
        final TextView value_pic_2 = (TextView) findViewById(R.id.textView4);
        final TextView value_pic_3 = (TextView) findViewById(R.id.textView7);
        final TextView value_pic_4 = (TextView) findViewById(R.id.textView8);


        happybirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(money_set >= 10 && choose_happybirthday == 0){
                    money_set = money_set - 10;
                    money_left.setText(""+money_set);
                    choose_happybirthday = 1;
                    happybirthday.setAlpha(0.3f);
                    value_pic_1.setText("bought");
                    value_pic_1.setTextColor(Color.BLACK);
                    value_pic_1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
                    SharedPreferences preferences = getSharedPreferences("group_two", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("flag5", 1);
                    editor.commit();
                }
            }
        });

        lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(money_set >= 20 && choose_lap == 0){
                    money_set = money_set - 20;
                    money_left.setText(""+money_set);
                    choose_lap = 1;
                    lap.setAlpha(0.3f);
                    value_pic_2.setText("bought");
                    value_pic_2.setTextColor(Color.BLACK);
                    value_pic_2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
                    SharedPreferences preferences = getSharedPreferences("group_two", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("flag6", 1);
                    editor.commit();
                }
            }
        });

        lying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(money_set >= 30 && choose_lying == 0){
                    money_set = money_set - 30;
                    money_left.setText(""+money_set);
                    choose_lying = 1;
                    lying.setAlpha(0.3f);
                    value_pic_3.setText("bought");
                    value_pic_3.setTextColor(Color.BLACK);
                    value_pic_3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
                    SharedPreferences preferences = getSharedPreferences("group_two", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("flag7", 1);
                    editor.commit();
                }
            }
        });

        noodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(money_set >= 40 && choose_noodle == 0){
                    money_set = money_set - 40;
                    money_left.setText(""+money_set);
                    choose_noodle = 1;
                    noodle.setAlpha(0.3f);
                    value_pic_4.setText("bought");
                    value_pic_4.setTextColor(Color.BLACK);
                    value_pic_4.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
                    SharedPreferences preferences = getSharedPreferences("group_two", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("flag8", 1);
                    editor.commit();
                }
            }
        });

        if(choose_happybirthday == 1){
            happybirthday.setAlpha(0.3f);
            value_pic_1.setText("bought");
            value_pic_1.setTextColor(Color.BLACK);
            value_pic_1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
        }
        if(choose_lap == 1){
            lap.setAlpha(0.3f);
            value_pic_2.setText("bought");
            value_pic_2.setTextColor(Color.BLACK);
            value_pic_2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
        }
        if(choose_lying == 1){
            lying.setAlpha(0.3f);
            value_pic_3.setText("bought");
            value_pic_3.setTextColor(Color.BLACK);
            value_pic_3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
        }
        if(choose_noodle == 1){
            noodle.setAlpha(0.3f);
            value_pic_4.setText("bought");
            value_pic_4.setTextColor(Color.BLACK);
            value_pic_4.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
        }


    }
}
