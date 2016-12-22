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

import static android.nekomem.nekoshopping.Store_first.money_set;
import static android.nekomem.nekoshopping.Store_first.stop_music;
import static android.nekomem.nekoshopping.Store_second.choose_happybirthday;
import static android.nekomem.nekoshopping.Store_second.choose_lap;
import static android.nekomem.nekoshopping.Store_second.choose_lying;
import static android.nekomem.nekoshopping.Store_second.choose_noodle;


public class Store_third extends AppCompatActivity {

    public static int choose_planket = 0;
    public static int choose_popcorn = 0;
    public static int choose_sleepwell = 0;
    public static int choose_sushi = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_third);

        SharedPreferences get_preferences = getSharedPreferences("group_three", Context.MODE_PRIVATE);
        choose_planket = get_preferences.getInt("flag9",0);
        choose_popcorn = get_preferences.getInt("flag10",0);
        choose_sleepwell = get_preferences.getInt("flag11",0);
        choose_sushi = get_preferences.getInt("flag12",0);

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
                Intent last_intent = new Intent(Store_third.this, Store_second.class);
                last_intent.putExtra("data",""+money_set);
                startActivity(last_intent);
            }
        });

        final ImageView next_page = (ImageView) findViewById(R.id.right);
        next_page.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                player.stop();
                Intent next_intent = new Intent(Store_third.this, Store_fourth.class);
                next_intent.putExtra("data",""+money_set);
                startActivity(next_intent);
            }
        });

        final ImageView planket = (ImageView) findViewById(R.id.planket);
        final ImageView popcorn = (ImageView) findViewById(R.id.popcorn);
        final ImageView sleepwell = (ImageView) findViewById(R.id.sleepwell);
        final ImageView sushi = (ImageView) findViewById(R.id.sushi);

        final TextView value_pic_1 = (TextView) findViewById(R.id.textView3);
        final TextView value_pic_2 = (TextView) findViewById(R.id.textView4);
        final TextView value_pic_3 = (TextView) findViewById(R.id.textView7);
        final TextView value_pic_4 = (TextView) findViewById(R.id.textView8);

        planket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(money_set >= 10 && choose_planket == 0){
                    money_set = money_set - 10;
                    money_left.setText(""+money_set);
                    choose_planket = 1;
                    planket.setAlpha(0.3f);
                    value_pic_1.setText("bought");
                    value_pic_1.setTextColor(Color.BLACK);
                    value_pic_1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
                    SharedPreferences preferences = getSharedPreferences("group_three", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("flag9", 1);
                    editor.commit();
                }
            }
        });

        popcorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(money_set >= 20 && choose_popcorn == 0){
                    money_set = money_set - 20;
                    money_left.setText(""+money_set);
                    choose_popcorn = 1;
                    popcorn.setAlpha(0.3f);
                    value_pic_2.setText("bought");
                    value_pic_2.setTextColor(Color.BLACK);
                    value_pic_2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
                    SharedPreferences preferences = getSharedPreferences("group_three", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("flag10", 1);
                    editor.commit();
                }
            }
        });

        sleepwell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(money_set >= 30 && choose_sleepwell == 0){
                    money_set = money_set - 30;
                    money_left.setText(""+money_set);
                    choose_sleepwell = 1;
                    sleepwell.setAlpha(0.3f);
                    value_pic_3.setText("bought");
                    value_pic_3.setTextColor(Color.BLACK);
                    value_pic_3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
                    SharedPreferences preferences = getSharedPreferences("group_three", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("flag11", 1);
                    editor.commit();
                }
            }
        });

        sushi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(money_set >= 40 && choose_sushi == 0){
                    money_set = money_set - 40;
                    money_left.setText(""+money_set);
                    choose_sushi = 1;
                    sushi.setAlpha(0.3f);
                    value_pic_4.setText("bought");
                    value_pic_4.setTextColor(Color.BLACK);
                    value_pic_4.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
                    SharedPreferences preferences = getSharedPreferences("group_three", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("flag12", 1);
                    editor.commit();
                }
            }
        });

        if(choose_planket == 1){
            planket.setAlpha(0.3f);
            value_pic_1.setText("bought");
            value_pic_1.setTextColor(Color.BLACK);
            value_pic_1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
        }
        if(choose_popcorn == 1){
            popcorn.setAlpha(0.3f);
            value_pic_2.setText("bought");
            value_pic_2.setTextColor(Color.BLACK);
            value_pic_2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
        }
        if(choose_sleepwell == 1){
            sleepwell.setAlpha(0.3f);
            value_pic_3.setText("bought");
            value_pic_3.setTextColor(Color.BLACK);
            value_pic_3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
        }
        if(choose_sushi == 1){
            sushi.setAlpha(0.3f);
            value_pic_4.setText("bought");
            value_pic_4.setTextColor(Color.BLACK);
            value_pic_4.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
        }


    }
}
