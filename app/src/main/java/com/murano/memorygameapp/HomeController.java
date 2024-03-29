package com.murano.memorygameapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class HomeController extends AppCompatActivity {

    private Button start_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start_game = (Button) findViewById(R.id.btn_register);
        start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        DatabaseHelper db = new DatabaseHelper(this);

           /* try {
                db = new Database(this);
                DatabaseOpenHelper open = new DatabaseOpenHelper(this);

            } catch (Exception e) {
                e.printStackTrace();
            }


        db.open();
            db.test();
        db.close();*/
    }
    public void startGame() {
        Intent intent = new Intent(this, LoginController.class);
        startActivity(intent);
    }
}
