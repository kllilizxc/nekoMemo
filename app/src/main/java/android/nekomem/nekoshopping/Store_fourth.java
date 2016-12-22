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
import static android.nekomem.nekoshopping.Store_third.choose_planket;
import static android.nekomem.nekoshopping.Store_third.choose_popcorn;
import static android.nekomem.nekoshopping.Store_third.choose_sleepwell;

public class Store_fourth extends AppCompatActivity {

    public static int choose_unhappy = 0;
    public static int choose_unicorn = 0;
    public static int choose_wool = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_fourth);

        SharedPreferences get_preferences = getSharedPreferences("group_four", Context.MODE_PRIVATE);
        choose_unhappy = get_preferences.getInt("flag13",0);
        choose_unicorn = get_preferences.getInt("flag14",0);
        choose_wool = get_preferences.getInt("flag15",0);

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
                Intent last_intent = new Intent(Store_fourth.this, Store_third.class);
                last_intent.putExtra("data",""+money_set);
                startActivity(last_intent);
            }
        });

        final ImageView unhappy = (ImageView) findViewById(R.id.unhappy);
        final ImageView unicorn = (ImageView) findViewById(R.id.unicorn);
        final ImageView wool = (ImageView) findViewById(R.id.wool);

        final TextView value_pic_1 = (TextView) findViewById(R.id.textView3);
        final TextView value_pic_2 = (TextView) findViewById(R.id.textView4);
        final TextView value_pic_3 = (TextView) findViewById(R.id.textView7);




        unhappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(money_set >= 10 && choose_unhappy == 0){
                    money_set = money_set - 10;
                    money_left.setText(""+money_set);
                    choose_unhappy = 1;
                    unhappy.setAlpha(0.3f);
                    value_pic_1.setText("bought");
                    value_pic_1.setTextColor(Color.BLACK);
                    value_pic_1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
                    SharedPreferences preferences = getSharedPreferences("group_four", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("flag13", 1);
                    editor.commit();
                }
            }
        });

        unicorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(money_set >= 20 && choose_unicorn == 0){
                    money_set = money_set - 20;
                    money_left.setText(""+money_set);
                    choose_unicorn = 1;
                    unicorn.setAlpha(0.3f);
                    value_pic_2.setText("bought");
                    value_pic_2.setTextColor(Color.BLACK);
                    value_pic_2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
                    SharedPreferences preferences = getSharedPreferences("group_four", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("flag14", 1);
                    editor.commit();
                }
            }
        });

        wool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(money_set >= 30 && choose_wool == 0){
                    money_set = money_set - 30;
                    money_left.setText(""+money_set);
                    choose_wool = 1;
                    wool.setAlpha(0.3f);
                    value_pic_3.setText("bought");
                    value_pic_3.setTextColor(Color.BLACK);
                    value_pic_3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
                    SharedPreferences preferences = getSharedPreferences("group_four", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("flag15", 1);
                    editor.commit();
                }
            }
        });


        if(choose_unhappy == 1){
            unhappy.setAlpha(0.3f);
            value_pic_1.setText("bought");
            value_pic_1.setTextColor(Color.BLACK);
            value_pic_1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
        }
        if(choose_unicorn == 1){
            unicorn.setAlpha(0.3f);
            value_pic_2.setText("bought");
            value_pic_2.setTextColor(Color.BLACK);
            value_pic_2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
        }
        if(choose_wool == 1){
            wool.setAlpha(0.3f);
            value_pic_3.setText("bought");
            value_pic_3.setTextColor(Color.BLACK);
            value_pic_3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.FAKE_BOLD_TEXT_FLAG);
        }






    }
}
