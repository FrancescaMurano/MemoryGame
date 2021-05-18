package com.murano.memorygameapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class LevelsController extends AppCompatActivity {

    private Button back;
    private FrameLayout btn1;
    private FrameLayout btn2;
    private FrameLayout btn3;
    private FrameLayout btn4;
    private FrameLayout btn5;
    private FrameLayout btn6;
    ArrayList<Integer> level1;
    ArrayList<Integer> level2;
    ArrayList<Integer> level3;
    ArrayList<Integer> level4;
    ArrayList<Integer> level5;
    ArrayList<Integer> level6;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        animation =  AnimationUtils.loadAnimation(LevelsController.this, R.anim.fadein);
       // back = (Button) findViewById(R.id.btn_back);
        btn1 = (FrameLayout) findViewById(R.id.button1_menu_level1);
        btn2 = (FrameLayout) findViewById(R.id.button2_menu_level2);
        btn3 = (FrameLayout) findViewById(R.id.button3_menu_level3);
        btn4 = (FrameLayout) findViewById(R.id.button4_menu_level4);
        btn5 = (FrameLayout) findViewById(R.id.button5_menu_level5);
        btn6 = (FrameLayout) findViewById(R.id.button6_menu_level6);

        btn1.setOnClickListener(getListener(btn1,GameController_Level1.class));
        btn2.setOnClickListener(getListener(btn2, GameController_Level2.class));
        btn3.setOnClickListener(getListener(btn3, GameController_Level3.class));
        btn4.setOnClickListener(getListener(btn4, GameController_Level4.class));
        btn5.setOnClickListener(getListener(btn5, GameController_Level5.class));
        btn6.setOnClickListener(getListener(btn6, GameController_Level6.class));

        level1 = new ArrayList<Integer>();
        level1.addAll(Arrays.asList(R.id.star1_level1, R.id.star2_level1, R.id.star3_level1));
        level2 = new ArrayList<Integer>();
        level2.addAll(Arrays.asList(R.id.star1_level2, R.id.star2_level2, R.id.star3_level2));
        level3 = new ArrayList<Integer>();
        level3.addAll(Arrays.asList(R.id.star1_level3, R.id.star2_level3, R.id.star3_level3));
        level4 = new ArrayList<Integer>();
        level4.addAll(Arrays.asList(R.id.star1_level4, R.id.star2_level4, R.id.star3_level4));
        level5 = new ArrayList<Integer>();
        level5.addAll(Arrays.asList(R.id.star1_level5, R.id.star2_level5, R.id.star3_level5));
        level6 = new ArrayList<Integer>();
        level6.addAll(Arrays.asList(R.id.star1_level6, R.id.star2_level6, R.id.star3_level6));
    /*    System.out.println("level 1 size: "+ level1.size());
        System.out.println("level 2 size: "+ level2.size());
        System.out.println("level 3 size: "+ level3.size());
        System.out.println("level 4 size: "+ level4.size());
        System.out.println("level 5 size: "+ level5.size());
        System.out.println("level 6 size: "+ level6.size());*/

        loadScore();

    }

    private void loadScore() {
        ImageView star1;
        ImageView star2;
        ImageView star3;

        ArrayList<Integer> scores = DatabaseHelper.getTotalScores();

        for(int i = 0; i < scores.size() ; i++ ){

            System.out.println("score "+ i + " "+ scores.get(i));
           if( i == 0){
               star1 = ((ImageView) findViewById(level1.get(0)));
               star2 = ((ImageView) findViewById(level1.get(1)));
               star3 = ((ImageView) findViewById(level1.get(2)));
               if( scores.get(i) == 1)
                   loadStars(star1,star2,star3,View.VISIBLE,View.INVISIBLE,View.INVISIBLE);
               else if ( scores.get(i) == 2)
                   loadStars(star1,star2,star3,View.VISIBLE,View.VISIBLE,View.INVISIBLE);
               else if ( scores.get(i) == 3)
                   loadStars(star1,star2,star3,View.VISIBLE,View.VISIBLE,View.VISIBLE);
               else if ( scores.get(i) == 0)
                   loadStars(star1,star2,star3,View.INVISIBLE,View.INVISIBLE,View.INVISIBLE);
           }
           else if(i == 1){
               System.out.println("level 2 size: "+ level2.size());
               star1 = ((ImageView) findViewById(level2.get(0)));
               star2 = ((ImageView) findViewById(level2.get(1)));
               star3 = ((ImageView) findViewById(level2.get(2)));
               if( scores.get(i) == 1)
                   loadStars(star1,star2,star3,View.VISIBLE,View.INVISIBLE,View.INVISIBLE);
               else if ( scores.get(i) == 2)
                   loadStars(star1,star2,star3,View.VISIBLE,View.VISIBLE,View.INVISIBLE);
               else if ( scores.get(i) == 3)
                   loadStars(star1,star2,star3,View.VISIBLE,View.VISIBLE,View.VISIBLE);
               else if ( scores.get(i) == 0)
                   loadStars(star1,star2,star3,View.INVISIBLE,View.INVISIBLE,View.INVISIBLE);
           }
           else if( i == 2){
               star1 = ((ImageView) findViewById(level3.get(0)));
               star2 = ((ImageView) findViewById(level3.get(1)));
               star3 = ((ImageView) findViewById(level3.get(2)));

               if( scores.get(i) == 1)
                   loadStars(star1,star2,star3,View.VISIBLE,View.INVISIBLE,View.INVISIBLE);
               else if ( scores.get(i) == 2)
                   loadStars(star1,star2,star3,View.VISIBLE,View.VISIBLE,View.INVISIBLE);
               else if ( scores.get(i) == 3)
                   loadStars(star1,star2,star3,View.VISIBLE,View.VISIBLE,View.VISIBLE);
               else if ( scores.get(i) == 0)
                   loadStars(star1,star2,star3,View.INVISIBLE,View.INVISIBLE,View.INVISIBLE);

           }
           else if(i == 3){
               star1 = ((ImageView) findViewById(level4.get(0)));
               star2 = ((ImageView) findViewById(level4.get(1)));
               star3 = ((ImageView) findViewById(level4.get(2)));

               if( scores.get(i) == 1)
                   loadStars(star1,star2,star3,View.INVISIBLE,View.INVISIBLE,View.INVISIBLE);
               else if ( scores.get(i) == 2)
                   loadStars(star1,star2,star3,View.INVISIBLE,View.INVISIBLE,View.INVISIBLE);
               else if ( scores.get(i) == 3)
                   loadStars(star1,star2,star3,View.INVISIBLE,View.INVISIBLE,View.INVISIBLE);
               else if ( scores.get(i) == 0)
                   loadStars(star1,star2,star3,View.INVISIBLE,View.INVISIBLE,View.INVISIBLE);

           }
           else if( i == 4){
               star1 = ((ImageView) findViewById(level5.get(0)));
               star2 = ((ImageView) findViewById(level5.get(1)));
               star3 = ((ImageView) findViewById(level5.get(2)));

               if( scores.get(i) == 1)
                   loadStars(star1,star2,star3, View.VISIBLE,View.INVISIBLE,View.INVISIBLE);
               else if ( scores.get(i) == 2)
                   loadStars(star1,star2,star3,View.VISIBLE,View.VISIBLE,View.INVISIBLE);
               else if ( scores.get(i) == 3)
                   loadStars(star1,star2,star3,View.VISIBLE,View.VISIBLE,View.VISIBLE);
               else if ( scores.get(i) == 0)
                   loadStars(star1,star2,star3,View.INVISIBLE,View.INVISIBLE,View.INVISIBLE);
           }
           else if( i == 5){
               star1 = ((ImageView) findViewById(level6.get(0)));
               star2 = ((ImageView) findViewById(level6.get(1)));
               star3 = ((ImageView) findViewById(level6.get(2)));

               if( scores.get(i) == 1)
                   loadStars(star1,star2,star3, View.VISIBLE,View.INVISIBLE,View.INVISIBLE);
               else if ( scores.get(i) == 2)
                   loadStars(star1,star2,star3,View.VISIBLE,View.VISIBLE,View.INVISIBLE);
               else if ( scores.get(i) == 3)
                   loadStars(star1,star2,star3,View.VISIBLE,View.VISIBLE,View.VISIBLE);
               else if ( scores.get(i) == 0)
                   loadStars(star1,star2,star3,View.INVISIBLE,View.INVISIBLE,View.INVISIBLE);
           }

        }

    }

    private void loadStars(ImageView star1, ImageView star2, ImageView star3, int res1,int res2, int res3) {

        star1.setVisibility(res1);
        star2.setVisibility(res2);
        star3.setVisibility(res3);

    }

    public View.OnClickListener getListener(FrameLayout f,Class<?> cls){
        f.startAnimation(animation);
        return (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(cls);
                f.clearAnimation();
            }
        });

    }

    public void startGame(Class<?> cls) {
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }
}


