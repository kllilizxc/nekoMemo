package android.nekomem.nekomemo;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.nekomem.MainActivity;
import android.nekomem.R;
import android.nekomem.nekoshopping.Store_first;
import android.nekomem.nekoshopping.Store_fourth;
import android.nekomem.nekoshopping.Store_second;
import android.nekomem.nekoshopping.Store_third;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.Calendar;
import java.util.Random;

import static com.oguzdev.circularfloatingactionmenu.library.SubActionButton.THEME_DARKER;

public class atsume_1 extends AppCompatActivity {

    public final static int SLEEP = 100;
    public final static int SLEEP_WELL = 101;
    public final static int SLEEP_UNICORN = 102;
    public boolean[] SLEEP_ARRAY = { true, Store_third.choose_sleepwell == 1, Store_fourth.choose_unicorn == 1 };

    public final static int NORMAL_WHAT = 200;
    public final static int NORMAL_CAR = 201;
    public final static int NORMAL_CUTE = 202;
    public final static int NORMAL_LAP = 203;
    public final static int NORMAL_LYING = 204;
    public final static int NORMAL_PLANKET = 205;
    public final static int NORMAL_UNHAPPY = 206;
    public final static int NORMAL_WOOL = 207;
    public boolean[] NORMAL_ARRAY = { true,
            Store_first.choose_car == 1,
            Store_first.choose_cute == 1,
            Store_second.choose_lap == 1,
            Store_second.choose_lying == 1,
            Store_third.choose_planket == 1,
            Store_fourth.choose_unhappy == 1,
            Store_fourth.choose_wool == 1};

    public final static int MEAL_DOOSE = 300;
    public final static int MEAL_DONUT = 301;
    public final static int MEAL_HAPPYBIRTHDAY = 302;
    public final static int MEAL_POPCORN = 303;
    public final static int MEAL_NOODLE = 304;
    public final static int MEAL_SUSHI = 305;
    public boolean[] MEAL_ARRAY = {
            Store_first.choose_doose == 1,
            Store_first.choose_donut == 1,
            Store_second.choose_happybirthday == 1,
            Store_third.choose_popcorn == 1,
            Store_second.choose_noodle == 1,
            Store_third.choose_sushi == 1
    };

    public final static int CLICK_HENG = 401;
    public final static int CLICK_SHY = 402;
    public final static int CLICK_WINK = 403;
    public final static int CLICK_LIKE = 404;

    public final static int YARD_BOOKWRITING = 501;
    public final static int YARD_COOL = 502;
    public final static int YARD_CRY = 503;
    public final static int YARD_HEART = 504;
    public final static int YARD_PIANO = 505;
    public final static int YARD_SMIRK = 506;
    public final static int YARD_WEEP = 507;
    public final static int YARD_WORDLESS = 508;
    public final static int YARD_DJ = 509;
    public final static int YARD_HAMBURGER = 510;
    public final static int YARD_POPCORN = 511;
    public final static int YARD_WATSON = 512;

    public final static int TEST = 10086;


    private ImageView animationI;
    private ImageView animationII;
    private AnimationDrawable AniDrawI;
    private AnimationDrawable AniDrawII;


    public static long current_score = 0;
    private int currentAni;
    private int yardAni;


    private int lastx;
    private int lasty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
        Read the current array from store:
        This is all for debugging.
         */
        Intent intent = getIntent();
        current_score = intent.getLongExtra("score", current_score);

        int i;
        for (i = 0; i <3; i++) SLEEP_ARRAY[i] = intent.getIntExtra("choose_infor"+i, SLEEP_ARRAY[i] ? 1 : 0) == 1;
        for (i = 0; i <8; i++) NORMAL_ARRAY[i] = intent.getIntExtra("choose_infor"+(i+3), NORMAL_ARRAY[i] ? 1 : 0) == 1;
        for (i = 0; i <6; i++) MEAL_ARRAY[i] = intent.getIntExtra("choose_infor"+(i+11), MEAL_ARRAY[i] ? 1 : 0) == 1;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atsume_1);
        /*
        Home instance
         */
        goToHome();
    }

    /*
    Get the random drawable according to the current time
     */
    private int getRandom(int hour) {
        Random r = new Random();
        int randomNum = 0;
        int returnNum = 100;
        Log.d("TAG", "hour " + hour);
        // sleep time
        if ((hour >= 22 && hour < 24) || (hour >= 0 && hour < 6)) {
            randomNum = r.nextInt(3);
            while (!SLEEP_ARRAY[randomNum]) {
                randomNum = r.nextInt(3);
            }
            return 100 + randomNum;
        } else if ((hour >= 11 && hour < 13) || (hour >= 16 && hour < 18)) {
            randomNum = r.nextInt(6);
            while (!MEAL_ARRAY[randomNum]) {
                randomNum = r.nextInt(6);
            }
            return 300 + randomNum;
        } else {
            randomNum = r.nextInt(8);
            while (!NORMAL_ARRAY[randomNum]) {
                randomNum = r.nextInt(8);
            }
            return 200 + randomNum;
        }
    }

    /*
    For yard viewing
     */
    private void goToYard() {
        /*
        Initialize sound meow
         */
        final SoundPool sp;
        sp = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        final int music = sp.load(this, R.raw.meow, 1);
        setContentView(R.layout.activity_atsume_2);

        // 全局布局
        final FrameLayout mainLayout = (FrameLayout) this.getWindow().getDecorView();
        // 创建猫View
        animationII = new ImageView(this);
        // 重新调整布局
        FrameLayout.LayoutParams lpFeedback = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        lpFeedback.leftMargin = animationII.getLeft();
        lpFeedback.topMargin = animationII.getTop();
        lpFeedback.setMargins(animationII.getLeft(), animationII.getTop(), 0, 0);
        animationII.setLayoutParams(lpFeedback);
        // 根据随机函数获取yardAni并且load drawable
        yardAni = getYardRandom();
        switch (yardAni) {
            //switch (YARD_WORDLESS){
            case YARD_BOOKWRITING:
                animationII.setImageResource(R.drawable.bookwriting);
                lpFeedback.leftMargin = 406;
                lpFeedback.topMargin = 1216;
                animationII.setLayoutParams(lpFeedback);
                break;
            case YARD_COOL:
                animationII.setImageResource(R.drawable.cool);
                lpFeedback.leftMargin = 539;
                lpFeedback.topMargin = 94;
                animationII.setLayoutParams(lpFeedback);
                break;
            case YARD_CRY:
                animationII.setImageResource(R.drawable.cry);
                lpFeedback.leftMargin = -18;
                lpFeedback.topMargin = 805;
                animationII.setLayoutParams(lpFeedback);
                break;
            case YARD_HEART:
                animationII.setImageResource(R.drawable.heart);
                lpFeedback.leftMargin = 390;
                lpFeedback.topMargin = 1182;
                animationII.setLayoutParams(lpFeedback);
                break;
            case YARD_PIANO:
                animationII.setImageResource(R.drawable.piano);
                lpFeedback.leftMargin = 7;
                lpFeedback.topMargin = 1324;
                animationII.setLayoutParams(lpFeedback);
                break;
            case YARD_SMIRK:
                animationII.setImageResource(R.drawable.smirk);
                lpFeedback.leftMargin = 665;
                lpFeedback.topMargin = 942;
                animationII.setLayoutParams(lpFeedback);
                break;
            case YARD_WEEP:
                animationII.setImageResource(R.drawable.weep);
                lpFeedback.leftMargin = 559;
                lpFeedback.topMargin = 407;
                animationII.setLayoutParams(lpFeedback);
                break;
            case YARD_WORDLESS:
                animationII.setImageResource(R.drawable.wordless);
                lpFeedback.leftMargin = 195;
                lpFeedback.topMargin = 1497;
                animationII.setLayoutParams(lpFeedback);
                break;
            case TEST:
                animationII.setImageResource(R.drawable.test);
                lpFeedback.leftMargin = 0;
                lpFeedback.topMargin = 0;
                animationII.setLayoutParams(lpFeedback);
                break;
            default:
                animationII.setImageResource(R.drawable.smirk);
                lpFeedback.leftMargin = 665;
                lpFeedback.topMargin = 942;
                animationII.setLayoutParams(lpFeedback);
                break;
        }
        // 修改其他Imageview参数
        animationII.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        // 启动帧动画
        AniDrawII = (AnimationDrawable) animationII.getDrawable();
        AniDrawII.start();
        // 在Layout里添加ImageView
        mainLayout.addView(animationII);
        // 设定触摸监听
        animationII.setOnTouchListener(new View.OnTouchListener() {
            // 记录开始坐标
            int startx;
            int starty;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v == animationII) {
                    // 获取触碰动作
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:// 获取手指第一次接触屏幕
                            startx = (int) event.getRawX();
                            starty = (int) event.getRawY();
                            break;
                        case MotionEvent.ACTION_MOVE:// 手指在屏幕上移动对应的事件
                            int x = (int) event.getRawX();
                            int y = (int) event.getRawY();
                            // 获取手指移动的距离
                            int dx = x - startx;
                            int dy = y - starty;
                            // 得到imageView最开始的各顶点的坐标
                            int l = v.getLeft();
                            int r = v.getRight();
                            int t = v.getTop();
                            int b = v.getBottom();
                            // 更改imageView在窗体的位置
                            v.layout(l + dx, t + dy, r + dx, b + dy);
                            // 获取移动后的位置
                            startx = (int) event.getRawX();
                            starty = (int) event.getRawY();
                            break;
                        case MotionEvent.ACTION_UP:// 手指离开屏幕对应事件
                            // 播放声音
                            sp.play(music, 1, 1, 0, 0, 1);
                            Log.i("TAG", "手指离开屏幕");
                            // 记录最后图片在窗体的位置
                            lasty = v.getTop();
                            lastx = v.getLeft();

                            // 重新布局
                            FrameLayout.LayoutParams lpFeedback = new FrameLayout.LayoutParams(
                                    FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                            lpFeedback.leftMargin = v.getLeft();
                            lpFeedback.topMargin = v.getTop();
                            lpFeedback.setMargins(v.getLeft(), v.getTop(), 0, 0);
                            v.setLayoutParams(lpFeedback);
                            // 停止特殊位置,变换动画
                            if (lastx >= 300 && lastx <= 400 && lasty >= 1400 && lasty <= 1500) {
                                ImageView temp = (ImageView) v;
                                yardAni = YARD_HAMBURGER;
                                temp.setImageResource(R.drawable.hamburger);
                                AniDrawI = (AnimationDrawable) temp.getDrawable();
                                AniDrawI.start();
                            }
                            if (lastx >= 500 && lastx <= 620 && lasty >= 600 && lasty <= 750) {
                                ImageView temp = (ImageView) v;
                                yardAni = NORMAL_WOOL;
                                temp.setImageResource(R.drawable.wool);
                                AniDrawI = (AnimationDrawable) temp.getDrawable();
                                AniDrawI.start();
                            }
                            if (lastx >= 240 && lastx <= 340 && lasty >= 450 && lasty <= 620) {
                                ImageView temp = (ImageView) v;
                                yardAni = YARD_DJ;
                                temp.setImageResource(R.drawable.dj);
                                AniDrawI = (AnimationDrawable) temp.getDrawable();
                                AniDrawI.start();
                            }
                            if (lastx >= 320 && lastx <= 450 && lasty >= 1050 && lasty <= 1200) {
                                ImageView temp = (ImageView) v;
                                yardAni = YARD_WATSON;
                                temp.setImageResource(R.drawable.watson);
                                AniDrawI = (AnimationDrawable) temp.getDrawable();
                                AniDrawI.start();
                            }
                            if (lastx >= 30 && lastx <= 170 && lasty >= 600 && lasty <= 750) {
                                ImageView temp = (ImageView) v;
                                yardAni = YARD_POPCORN;
                                temp.setImageResource(R.drawable.popcorn);
                                AniDrawI = (AnimationDrawable) temp.getDrawable();
                                AniDrawI.start();
                            }
                            break;
                    }
                    Log.d("TAG", "lastx" + lastx);
                    Log.d("TAG", "lasty" + lasty);
                }
                return true;// 不会中断触摸事件的返回
            }
        });
        /**************************************************************************
         * 菜单绘制
         */
        final ImageView fabIconNew = new ImageView(this);
        // 菜单图标
        fabIconNew.setImageDrawable(getResources().getDrawable(R.drawable.main_ic_paw));
        fabIconNew.setScaleType(ImageView.ScaleType.FIT_CENTER);
        fabIconNew.bringToFront();
        // 调整大小
        int mainIconSize = getResources().getDimensionPixelSize(R.dimen.btn_width);
        FloatingActionButton.LayoutParams mainParams = new FloatingActionButton.LayoutParams(mainIconSize,
                mainIconSize);
        int mainActionButtonMargin = getResources().getDimensionPixelOffset(R.dimen.action_button_margin);
        mainParams.setMargins(mainActionButtonMargin, mainActionButtonMargin, mainActionButtonMargin,
                mainActionButtonMargin);
        fabIconNew.setLayoutParams(mainParams);
        // 更新浮动菜单条
        final FloatingActionButton rightLowerButton = new FloatingActionButton.Builder(this)
                .setContentView(fabIconNew)
                .setBackgroundDrawable(R.drawable.main_ic_background)
                .build();

        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this);
        rLSubBuilder.setTheme(THEME_DARKER);
        // 设定子菜单
        ImageView yardIcon = new ImageView(this);
        ImageView memoIcon = new ImageView(this);
        ImageView storeIcon = new ImageView(this);
        // 设定子菜单形状
        yardIcon.setImageDrawable(getResources().getDrawable(R.drawable.button1_sushi));
        memoIcon.setImageDrawable(getResources().getDrawable(R.drawable.button2_sushi));
        storeIcon.setImageDrawable(getResources().getDrawable(R.drawable.button3_sushi));

        // Build the menu with default options: light theme, 90 degrees, 72dp
        // radius.
        // Set 4 default SubActionButtons
        // FloatingActionMenu通过attachTo(rightLowerButton)附着到FloatingActionButton
        final FloatingActionMenu rightLowerMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(rLSubBuilder.setContentView(yardIcon).build())
                .addSubActionView(rLSubBuilder.setContentView(memoIcon).build())
                .addSubActionView(rLSubBuilder.setContentView(storeIcon).build())
                .setStartAngle(-180).setEndAngle(-90)
                .attachTo(rightLowerButton)
                .build();

        yardIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("TAG", "CLicked00");
                mainLayout.removeView(animationII);
                goToHome();
            }
        });

        // Listen menu open and close events to animate the button content view
        rightLowerMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu menu) {
                // 增加按钮中的+号图标顺时针旋转45度
                // Rotate the icon of rightLowerButton 45 degrees clockwise
                fabIconNew.bringToFront();
                fabIconNew.setRotation(0);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu menu) {
                // 增加按钮中的+号图标逆时针旋转45度
                // Rotate the icon of rightLowerButton 45 degrees
                // counter-clockwise
                fabIconNew.bringToFront();
                fabIconNew.setRotation(45);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }
        });
    }

    /*
    Home界面绘制函数
     */
    private void goToHome() {
        // 设置view
        setContentView(R.layout.activity_atsume_1);
        // 获取当前时间
        Calendar c = Calendar.getInstance();
        final int hour = c.get(Calendar.HOUR_OF_DAY);
        // 获取声音池
        final SoundPool sp;
        sp = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        final int music = sp.load(this, R.raw.meow, 1);

        // 全局布局
        final FrameLayout mainLayout = (FrameLayout) this.getWindow().getDecorView();
        // 创建猫View
        animationI = new ImageView(this);
        // 重新调整布局
        FrameLayout.LayoutParams lpFeedback = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        lpFeedback.leftMargin = animationI.getLeft();
        lpFeedback.topMargin = animationI.getTop();
        lpFeedback.setMargins(animationI.getLeft(), animationI.getTop(), 0, 0);
        animationI.setLayoutParams(lpFeedback);
        // 根据随机函数获取yardAni并且load drawable
        currentAni = getRandom(hour);
        switch (currentAni) {
            //switch (MEAL_SUSHI){
            case SLEEP:
                animationI.setImageResource(R.drawable.sleep);
                lpFeedback.leftMargin = 667;
                lpFeedback.topMargin = 1105;
                animationI.setLayoutParams(lpFeedback);
                break;
            case SLEEP_WELL:
                animationI.setImageResource(R.drawable.sleepwell);
                lpFeedback.leftMargin = 189;
                lpFeedback.topMargin = 927;
                animationI.setLayoutParams(lpFeedback);
                break;
            case SLEEP_UNICORN:
                animationI.setImageResource(R.drawable.unicorn);
                lpFeedback.leftMargin = 28;
                lpFeedback.topMargin = 1221;
                animationI.setLayoutParams(lpFeedback);
                break;
            case NORMAL_WHAT:
                animationI.setImageResource(R.drawable.what);
                lpFeedback.leftMargin = 323;
                lpFeedback.topMargin = 1001;
                animationI.setLayoutParams(lpFeedback);
                break;
            case NORMAL_CAR:
                animationI.setImageResource(R.drawable.car);
                lpFeedback.leftMargin = 543;
                lpFeedback.topMargin = 1174;
                animationI.setLayoutParams(lpFeedback);
                break;
            case NORMAL_CUTE:
                animationI.setImageResource(R.drawable.cute);
                lpFeedback.leftMargin = 282;
                lpFeedback.topMargin = 1000;
                animationI.setLayoutParams(lpFeedback);
                break;
            case NORMAL_LAP:
                animationI.setImageResource(R.drawable.lap);
                lpFeedback.leftMargin = 319;
                lpFeedback.topMargin = 1142;
                animationI.setLayoutParams(lpFeedback);
                break;
            case NORMAL_LYING:
                animationI.setImageResource(R.drawable.lying);
                lpFeedback.leftMargin = 656;
                lpFeedback.topMargin = 1232;
                animationI.setLayoutParams(lpFeedback);
                break;
            case NORMAL_PLANKET:
                animationI.setImageResource(R.drawable.planket);
                lpFeedback.leftMargin = 482;
                lpFeedback.topMargin = 1282;
                animationI.setLayoutParams(lpFeedback);
                break;
            case NORMAL_UNHAPPY:
                animationI.setImageResource(R.drawable.unhappy);
                lpFeedback.leftMargin = 43;
                lpFeedback.topMargin = 620;
                animationI.setLayoutParams(lpFeedback);
                break;
            case NORMAL_WOOL:
                animationI.setImageResource(R.drawable.wool);
                lpFeedback.leftMargin = 278;
                lpFeedback.topMargin = 1132;
                animationI.setLayoutParams(lpFeedback);
                break;
            case MEAL_DOOSE:
                animationI.setImageResource(R.drawable.doose);
                lpFeedback.leftMargin = 352;
                lpFeedback.topMargin = 1097;
                animationI.setLayoutParams(lpFeedback);
                break;
            case MEAL_DONUT:
                animationI.setImageResource(R.drawable.donut);
                lpFeedback.leftMargin = -80;
                lpFeedback.topMargin = 1288;
                animationI.setLayoutParams(lpFeedback);
                break;
            case MEAL_HAPPYBIRTHDAY:
                animationI.setImageResource(R.drawable.happybirthday);
                lpFeedback.leftMargin = 221;
                lpFeedback.topMargin = 965;
                animationI.setLayoutParams(lpFeedback);
                break;
            case MEAL_POPCORN:
                animationI.setImageResource(R.drawable.popcorn);
                lpFeedback.leftMargin = 635;
                lpFeedback.topMargin = 1093;
                animationI.setLayoutParams(lpFeedback);
                break;
            case MEAL_NOODLE:
                animationI.setImageResource(R.drawable.noddle2);
                lpFeedback.leftMargin = 211;
                lpFeedback.topMargin = 977;
                animationI.setLayoutParams(lpFeedback);
                break;
            case MEAL_SUSHI:
                animationI.setImageResource(R.drawable.sushi);
                lpFeedback.leftMargin = 351;
                lpFeedback.topMargin = 991;
                animationI.setLayoutParams(lpFeedback);
                break;
            default:
                animationI.setImageResource(R.drawable.lying);
                lpFeedback.leftMargin = 656;
                lpFeedback.topMargin = 1232;
                animationI.setLayoutParams(lpFeedback);
                break;
        }
        // 修改其他Imageview参数
        animationI.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        // 启动帧动画
        AniDrawI = (AnimationDrawable) animationI.getDrawable();
        AniDrawI.start();
        // 在Layout里添加ImageView
        mainLayout.addView(animationI);

        final int[] Click = {0};

        // 设定触摸监听
        animationI.setOnTouchListener(new View.OnTouchListener() {
            // 记录开始坐标
            int startx;
            int starty;
            // 可否触摸
            boolean touchable = true;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (currentAni == SLEEP || currentAni == SLEEP_WELL || currentAni == SLEEP_UNICORN) {
                    touchable = false;
                    Toast.makeText(getApplicationContext(), "嘘,猫咪在睡觉!",
                            Toast.LENGTH_LONG).show();
                } else if (currentAni == CLICK_HENG) {
                    touchable = false;
                    Toast.makeText(getApplicationContext(), "啊呀呀,看来猫咪还不够喜欢你",
                            Toast.LENGTH_LONG).show();
                } else touchable = true;
                if (touchable == true) {
                    // 获取触碰动作
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:// 获取手指第一次接触屏幕
                            startx = (int) event.getRawX();
                            starty = (int) event.getRawY();
                            break;
                        case MotionEvent.ACTION_MOVE:// 手指在屏幕上移动对应的事件
                            int x = (int) event.getRawX();
                            int y = (int) event.getRawY();
                            // 获取手指移动的距离
                            int dx = x - startx;
                            int dy = y - starty;
                            // 得到imageView最开始的各顶点的坐标
                            int l = v.getLeft();
                            int r = v.getRight();
                            int t = v.getTop();
                            int b = v.getBottom();
                            // 更改imageView在窗体的位置
                            v.layout(l + dx, t + dy, r + dx, b + dy);
                            // 获取移动后的位置
                            startx = (int) event.getRawX();
                            starty = (int) event.getRawY();
                            break;
                        case MotionEvent.ACTION_UP:// 手指离开屏幕对应事件
                            // 播放声音
                            sp.play(music, 1, 1, 0, 0, 1);
                            Click[0]++;
                            Log.i("TAG", "手指离开屏幕");
                            // 记录最后图片在窗体的位置
                            lasty = v.getTop();
                            lastx = v.getLeft();

                            // 重新布局
                            FrameLayout.LayoutParams lpFeedback = new FrameLayout.LayoutParams(
                                    FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                            lpFeedback.leftMargin = v.getLeft();
                            lpFeedback.topMargin = v.getTop();
                            lpFeedback.setMargins(v.getLeft(), v.getTop(), 0, 0);
                            v.setLayoutParams(lpFeedback);
                            // 停止特殊位置,变换动画
                            // 根据积分确定点击
                            if (current_score >= 0 && current_score < 1000) {
                                if (Click[0] >= 5) {
                                    ImageView temp = (ImageView) v;
                                    currentAni = CLICK_HENG;
                                    temp.setImageResource(R.drawable.heng);
                                    AniDrawI = (AnimationDrawable) temp.getDrawable();
                                    AniDrawI.start();
                                }
                            } else if (current_score >= 1000 && current_score < 2000) {
                                if (Click[0] == 5) {
                                    ImageView temp = (ImageView) v;
                                    currentAni = CLICK_SHY;
                                    temp.setImageResource(R.drawable.shy);
                                    AniDrawI = (AnimationDrawable) temp.getDrawable();
                                    AniDrawI.start();
                                }
                                if (Click[0] == 20) {
                                    ImageView temp = (ImageView) v;
                                    currentAni = CLICK_WINK;
                                    temp.setImageResource(R.drawable.wink);
                                    AniDrawI = (AnimationDrawable) temp.getDrawable();
                                    AniDrawI.start();
                                }
                            } else if (current_score >= 2000) {
                                if (Click[0] == 3) {
                                    ImageView temp = (ImageView) v;
                                    currentAni = CLICK_SHY;
                                    temp.setImageResource(R.drawable.shy);
                                    AniDrawI = (AnimationDrawable) temp.getDrawable();
                                    AniDrawI.start();
                                }
                                if (Click[0] == 10) {
                                    ImageView temp = (ImageView) v;
                                    currentAni = CLICK_LIKE;
                                    temp.setImageResource(R.drawable.like);
                                    AniDrawI = (AnimationDrawable) temp.getDrawable();
                                    AniDrawI.start();
                                }
                            }
                            if (lastx >= 0 && lastx <= 200 && lasty >= 130 && lasty <= 320) {
                                ImageView temp = (ImageView) v;
                                currentAni = YARD_HEART;
                                temp.setImageResource(R.drawable.heart);
                                AniDrawI = (AnimationDrawable) temp.getDrawable();
                                AniDrawI.start();
                            }
                            if (lastx >= 650 && lastx <= 750 && lasty >= 550 && lasty <= 760) {
                                ImageView temp = (ImageView) v;
                                currentAni = MEAL_POPCORN;
                                temp.setImageResource(R.drawable.popcorn);
                                AniDrawI = (AnimationDrawable) temp.getDrawable();
                                AniDrawI.start();
                            }
                            break;
                    }
                    Log.d("TAG", "lastx" + lastx);
                    Log.d("TAG", "lasty" + lasty);
                }
                return true;// 不会中断触摸事件的返回
            }
        });
        /**************************************************************************
         * 菜单绘制
         */
        final ImageView fabIconNew = new ImageView(this);
        // 菜单图标
        fabIconNew.setImageDrawable(getResources().getDrawable(R.drawable.main_ic_paw));
        fabIconNew.setScaleType(ImageView.ScaleType.FIT_CENTER);
        fabIconNew.bringToFront();
        // 调整大小
        int mainIconSize = getResources().getDimensionPixelSize(R.dimen.btn_width);
        FloatingActionButton.LayoutParams mainParams = new FloatingActionButton.LayoutParams(mainIconSize,
                mainIconSize);
        int mainActionButtonMargin = getResources().getDimensionPixelOffset(R.dimen.action_button_margin);
        mainParams.setMargins(mainActionButtonMargin, mainActionButtonMargin, mainActionButtonMargin,
                mainActionButtonMargin);
        fabIconNew.setLayoutParams(mainParams);
        // 更新浮动菜单条
        final FloatingActionButton rightLowerButton = new FloatingActionButton.Builder(this)
                .setContentView(fabIconNew)
                .setBackgroundDrawable(R.drawable.main_ic_background)
                .build();

        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this);
        rLSubBuilder.setTheme(THEME_DARKER);
        // 设定子菜单
        ImageView yardIcon = new ImageView(this);
        ImageView memoIcon = new ImageView(this);
        ImageView storeIcon = new ImageView(this);
        // 设定子菜单形状
        yardIcon.setImageDrawable(getResources().getDrawable(R.drawable.button1_sushi));
        memoIcon.setImageDrawable(getResources().getDrawable(R.drawable.button2_sushi));
        storeIcon.setImageDrawable(getResources().getDrawable(R.drawable.button3_sushi));

        // Build the menu with default options: light theme, 90 degrees, 72dp
        // radius.
        // Set 4 default SubActionButtons
        // FloatingActionMenu通过attachTo(rightLowerButton)附着到FloatingActionButton
        final FloatingActionMenu rightLowerMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(rLSubBuilder.setContentView(yardIcon).build())
                .addSubActionView(rLSubBuilder.setContentView(memoIcon).build())
                .addSubActionView(rLSubBuilder.setContentView(storeIcon).build())
                .setStartAngle(-180).setEndAngle(-90)
                .attachTo(rightLowerButton)
                .build();

        memoIcon.setOnClickListener(v -> {
            Intent memoIntent = new Intent(this, MainActivity.class);
            startActivity(memoIntent);
        });

        storeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shop = new Intent(atsume_1.this, Store_first.class);
                shop.putExtra("score", current_score);
                startActivity(shop);
            }
        });

        yardIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("TAG", "CLicked00");

                if (current_score < 3500) {
                    Toast.makeText(getApplicationContext(), "积分高于3500才能解锁庭院!",
                            Toast.LENGTH_LONG).show();
                } else {
                    mainLayout.removeView(animationI);
                    goToYard();
                }
            }
        });

        // Listen menu open and close events to animate the button content view
        rightLowerMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu menu) {
                // 增加按钮中的+号图标顺时针旋转45度
                // Rotate the icon of rightLowerButton 45 degrees clockwise
                fabIconNew.bringToFront();
                fabIconNew.setRotation(0);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu menu) {
                // 增加按钮中的+号图标逆时针旋转45度
                // Rotate the icon of rightLowerButton 45 degrees
                // counter-clockwise
                fabIconNew.bringToFront();
                fabIconNew.setRotation(45);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }
        });

    }

    private int getYardRandom() {
        Random r = new Random();
        int randomNum = 0;
        randomNum = r.nextInt(9);
        return randomNum + 500;
    }
}
