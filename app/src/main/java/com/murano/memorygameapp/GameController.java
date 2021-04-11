package com.murano.memorygameapp;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.transition.Explode;
import android.util.Log;
import android.util.Pair;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static com.murano.memorygameapp.R.*;
import static com.murano.memorygameapp.R.drawable.*;
import static com.murano.memorygameapp.R.mipmap.ic_delete;
import static com.murano.memorygameapp.R.mipmap.ic_launcher_round;
import static com.murano.memorygameapp.R.mipmap.ic_sound;

public class GameController extends AppCompatActivity {
    private Button back;
    private Button sound;
    private FrameLayout btn1;
    private FrameLayout btn2;
    private FrameLayout btn3;
    private FrameLayout btn4;
    private FrameLayout btn5;
    private FrameLayout btn6;
    private FrameLayout btn7;
    private FrameLayout btn8;
    private FrameLayout btn9;
    private FrameLayout btn10;
    private FrameLayout btn11;
    private FrameLayout btn12;

    private FrameLayout selected_1;
    private Integer save_image;
    private int max_click;
    static  Chronometer time;
    private ArrayList<Integer> count_image;
    private boolean[] clicked;
    private int click;
    private TextView text;
    private int correct;
    private boolean soundOn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        soundOn = true;
        setContentView(layout.start);
        save_image = 0;
        max_click = 0;
        correct = 0;
        shuffle();
        clicked = new boolean[12];
        for (boolean it : clicked)
            it = false;

        click = 0;
        time = findViewById(id.timer);
        time.setBase(SystemClock.elapsedRealtime());
        time.start();

        back = (Button) findViewById(id.btn_back);
        sound = (Button) findViewById(id.btn_sound);
        btn1 = (FrameLayout) findViewById(id.button1);
        btn2 = (FrameLayout) findViewById(id.button2);
        btn3 = (FrameLayout) findViewById(id.button3);
        btn4 = (FrameLayout) findViewById(id.button4);
        btn5 = (FrameLayout) findViewById(id.button5);
        btn6 = (FrameLayout) findViewById(id.button6);
        btn7 = (FrameLayout) findViewById(id.button7);
        btn8 = (FrameLayout) findViewById(id.button8);
        btn9 = (FrameLayout) findViewById(id.button9);
        btn10 = (FrameLayout) findViewById(id.button10);
        btn11 = (FrameLayout) findViewById(id.button11);
        btn12 = (FrameLayout) findViewById(id.button12);
        text = (TextView) findViewById(id.mosse_field);


        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        back.setOnClickListener(new View.OnClickListener() {
            long time_stopped  = SystemClock.elapsedRealtime();
            @Override
            public void onClick(View v) {
                time.stop();
                time_stopped =  SystemClock.elapsedRealtime();
                dialog.setTitle( "Pausa" )
                        .setIcon(mipmap.logo_small_icon_only)
                        .setMessage("Il gioco è in pausa")

                        .setNegativeButton("Abbandona", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                stop();

                            }
                        }).setPositiveButton("Riprendi il gioco", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        time.setBase(time.getBase() + SystemClock.elapsedRealtime() - time_stopped);
                        time.start();
                        dialog.dismiss();


                    }
                }).show();
            }
        });
        sound.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
               if(soundOn) {
                   soundOn = false;
                   sound.setForeground(getDrawable(mipmap.ic_stop));
               }
               else {
                   soundOn = true;
                   sound.setForeground(getDrawable(mipmap.ic_sound));
               }
            }
        });
        btn1.setOnClickListener(getListener(btn1, 0));
        btn2.setOnClickListener(getListener(btn2, 1));
        btn3.setOnClickListener(getListener(btn3, 2));
        btn4.setOnClickListener(getListener(btn4, 3));
        btn5.setOnClickListener(getListener(btn5, 4));
        btn6.setOnClickListener(getListener(btn6, 5));
        btn7.setOnClickListener(getListener(btn7, 6));
        btn8.setOnClickListener(getListener(btn8, 7));
        btn9.setOnClickListener(getListener(btn9, 8));
        btn10.setOnClickListener(getListener(btn10, 9));
        btn11.setOnClickListener(getListener(btn11, 10));
        btn12.setOnClickListener(getListener(btn12, 11));

    }


    public synchronized void show(FrameLayout btn, int n) {

        max_click++;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (max_click == 2) {
                cancel();
                btn.setForeground(getDrawable(count_image.get(n)));
                if (save_image.equals(count_image.get(n)) && !selected_1.equals(btn)) { //<-- coppia trovata
                    if(soundOn) {
                        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), raw.success);
                        mediaPlayer.start();
                    }
                    Animation animation = AnimationUtils.loadAnimation(GameController.this, anim.righttoleft);
                    btn.startAnimation(animation);
                    selected_1.startAnimation(animation);
                    btn.setVisibility(View.INVISIBLE);
                    selected_1.setVisibility(View.INVISIBLE);
                    max_click = 0;
                    correct++;

                    if(correct == 6){
                        time.stop();
                        String time_result = time.getText().toString();

                        if(soundOn) {
                            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), raw.win_sound);
                            mediaPlayer.start();
                        }
                        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                        dialog.setCancelable(false);
                        setFinishOnTouchOutside(false);
                        dialog.setTitle( "Partita terminata" )
                                .setIcon(mipmap.logo_small_icon_only)
                                .setMessage("Hai vinto!!\nTempo Impiegato: "+ time_result)
                                .setNegativeButton("Esci", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialoginterface, int i) {
                                        stop();
                                    }
                                }).setPositiveButton("Ricomincia", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialoginterface, int i) {
                                        restart();
                                    }
                                }).show();


                    }

                } else { // <<--- card non uguali
                    if(soundOn) {
                        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.error_sound);
                        mediaPlayer.start();
                    }
                    Animation animation = AnimationUtils.loadAnimation(GameController.this, anim.shake);
                    Animation animation2 = AnimationUtils.loadAnimation(GameController.this, anim.shake);
                    btn.startAnimation(animation);
                    selected_1.startAnimation(animation2);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public synchronized void run() {
                            max_click = 0;
                            System.out.println("in run: "+ max_click);
                            clean(btn,selected_1);
                            selected_1 = btn;
                            save_image = count_image.get(n);
                        }
                    }, 1000);

                }

            }else {
                click++;
                btn.setForeground(getDrawable(count_image.get(n)));
                selected_1 = btn;
                save_image = count_image.get(n);
            }

        }

    }

    public synchronized void clean(FrameLayout btn,FrameLayout selected_1) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btn.setForeground(getDrawable(cardstyle));
            selected_1.setForeground(getDrawable(cardstyle));
        }
    }

    public synchronized void notshow(FrameLayout btn) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btn.setForeground(getDrawable(cardstyle));
            System.out.println("NOT SHOW");

        }
    }

    public void stop() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void restart() {
        Intent intent = new Intent(this, GameController.class);
        startActivity(intent);
    }

    public void shuffle() {
        count_image  = new ArrayList<Integer>(Arrays.asList(mipmap.beaver, mipmap.beaver, mipmap.bird, mipmap.bird,
                mipmap.cat, mipmap.cat, mipmap.dog, mipmap.dog, mipmap.dolphin, mipmap.dolphin, mipmap.duck, mipmap.duck));
        Collections.shuffle(count_image);
    }

    public void add() {
        Random r = new Random();
        String n = String.valueOf(click);
        text.setText(n);
    }

    public View.OnClickListener getListener(FrameLayout f, int n) {
        return (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("click"+ max_click);
                if (clicked[n] == false && max_click!=2) {
                    System.out.println("LISTENER ");
                    show(f, n);
                    add();
                } else {
                    notshow(f);
                    clicked[n] = false;
                }
            }
        });
    }

    public int count(int n) {
        int c = 0;
        for (Integer it : count_image) {
            if (it == null || it == -1)
                if (it.equals(n))
                    c++;
        }
        return c;
    }

    public void cancel() {
        for (boolean it : clicked)
            it = false;
    }

}