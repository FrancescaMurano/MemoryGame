package com.murano.memorygameapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class LevelsController extends AppCompatActivity {

    private Button back;
    private FrameLayout btn1;
    private FrameLayout btn2;
    private FrameLayout btn3;
    private FrameLayout btn4;
    private FrameLayout btn5;
    private FrameLayout btn6;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);
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


