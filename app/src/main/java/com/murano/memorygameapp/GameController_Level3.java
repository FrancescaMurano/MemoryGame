package com.murano.memorygameapp;


import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static com.murano.memorygameapp.R.anim;
import static com.murano.memorygameapp.R.drawable.cardstyle;
import static com.murano.memorygameapp.R.id;
import static com.murano.memorygameapp.R.layout;
import static com.murano.memorygameapp.R.mipmap;
import static com.murano.memorygameapp.R.mipmap.ic_sound;
import static com.murano.memorygameapp.R.raw;

public class GameController_Level3 extends AppCompatActivity {
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
    private int punteggio;

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
    private Animation animation_flip;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        soundOn = true;
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(layout.livel3);
        save_image = 0;
        max_click = 0;
        correct = 0;
        shuffle();
        clicked = new boolean[8];
        for (boolean it : clicked)
            it = false;

        click = 0;
        time = findViewById(id.timer_liv3);
        time.setBase(SystemClock.elapsedRealtime());
        time.start();
        text = (TextView) findViewById(id.mosse_field_liv3);
        back = (Button) findViewById(id.btn_back_liv3);
        sound = (Button) findViewById(id.btn_sound_liv4);
        animation_flip = AnimationUtils.loadAnimation(GameController_Level3.this, anim.flip);
        btn1 = (FrameLayout) findViewById(id.button1_l3);
        btn2 = (FrameLayout) findViewById(id.button2_l3);
        btn3 = (FrameLayout) findViewById(id.button3_l3);
        btn4 = (FrameLayout) findViewById(id.button4_l3);
        btn5 = (FrameLayout) findViewById(id.button5_l3);
        btn6 = (FrameLayout) findViewById(id.button6_l3);
        btn7 = (FrameLayout) findViewById(id.button7_l3);
        btn8 = (FrameLayout) findViewById(id.button8_l3);


        new Handler().postDelayed(new Runnable() {
            @Override
            public synchronized void run() {
                show_preview();
            }
        }, 500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public synchronized void run() {
                try {
                    hideAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 2000);



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
                    sound.setForeground(getDrawable(ic_sound));
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


    }

    public synchronized void show_preview(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {

                btn1.setForeground(getDrawable(count_image.get(0)));
                btn2.setForeground(getDrawable(count_image.get(1)));
                btn3.setForeground(getDrawable(count_image.get(2)));
                btn4.setForeground(getDrawable(count_image.get(3)));
                btn5.setForeground(getDrawable(count_image.get(4)));
                btn6.setForeground(getDrawable(count_image.get(5)));
                btn7.setForeground(getDrawable(count_image.get(6)));
                btn8.setForeground(getDrawable(count_image.get(7)));

                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
    public void show(FrameLayout btn, int n) {

        Animation animation = AnimationUtils.loadAnimation(GameController_Level3.this, anim.flip);
        btn.setAnimation(animation);
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
                    Animation anim1 = AnimationUtils.loadAnimation(GameController_Level3.this, anim.righttoleft);
                    btn.startAnimation(anim1);
                    selected_1.startAnimation(anim1);
                    btn.setVisibility(View.INVISIBLE);
                    selected_1.setVisibility(View.INVISIBLE);
                    max_click = 0;
                    correct++;

                    if(correct == 4){
                        time.stop();
                        String time_result = time.getText().toString();

                        if(soundOn) {
                            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), raw.win_sound);
                            mediaPlayer.start();
                        }
                        if(click == correct)
                            punteggio = 3;
                        else if(click <= correct+4)
                            punteggio = 2;
                        else
                            punteggio = 1;

                        DatabaseHelper.save_point("Level_3",punteggio);

                        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                        dialog.setCancelable(false);
                        setFinishOnTouchOutside(false);
                        dialog.setTitle( "Partita terminata" )
                                .setIcon(mipmap.logo_small_icon_only)
                                .setMessage("Hai vinto!!\nTempo Impiegato: "+ time_result+ "\nStelle ottenute: "+ punteggio)
                                .setNegativeButton("Esci", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialoginterface, int i) {
                                        stop();
                                    }
                                }).setPositiveButton("Prossimo Livello", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                next();
                            }
                        }).show();


                    }

                } else { // <<--- card non uguali
                    if(soundOn) {
                        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), raw.error_sound);
                        mediaPlayer.start();
                    }
                    Animation animation1 = AnimationUtils.loadAnimation(GameController_Level3.this, anim.shake);
                    Animation animation2 = AnimationUtils.loadAnimation(GameController_Level3.this, anim.shake);
                    btn.startAnimation(animation1);
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
        Intent intent = new Intent(this, LevelsController.class);
        startActivity(intent);
    }
    public void next() {
        Intent intent = new Intent(this, GameController_Level4.class);
        startActivity(intent);
    }

    public void shuffle() {
        count_image  = new ArrayList<Integer>(Arrays.asList(mipmap.tulip,mipmap.tulip,mipmap.blossom,mipmap.blossom, mipmap.yellowblossom,mipmap.yellowblossom
        ,mipmap.fourleafclover,mipmap.fourleafclover));
        Collections.shuffle(count_image);
    }

    public void add() {
        Random r = new Random();
        String n = String.valueOf(click);
        text.setText(n);
    }

    public View.OnClickListener getListener(FrameLayout f, int n) {
        f.startAnimation(animation_flip);
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



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public synchronized void hideAll() throws InterruptedException {
        // Aspetta il suo turno

        btn1.setForeground(getDrawable(cardstyle));
        btn2.setForeground(getDrawable(cardstyle));
        btn3.setForeground(getDrawable(cardstyle));
        btn4.setForeground(getDrawable(cardstyle));
        btn5.setForeground(getDrawable(cardstyle));
        btn6.setForeground(getDrawable(cardstyle));
        btn7.setForeground(getDrawable(cardstyle));
        btn8.setForeground(getDrawable(cardstyle));


        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

}