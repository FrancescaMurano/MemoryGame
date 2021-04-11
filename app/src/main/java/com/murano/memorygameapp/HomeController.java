package com.murano.memorygameapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.murano.memorygameapp.GameController;
import com.murano.memorygameapp.R;

public class HomeController extends AppCompatActivity {

    private Button start_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start_game = (Button) findViewById(R.id.btn_start);
        start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
    }

    public void startGame() {
        Intent intent = new Intent(this, GameController.class);
        startActivity(intent);
    }
}
