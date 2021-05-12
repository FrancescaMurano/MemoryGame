package com.murano.memorygameapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class LoginController extends AppCompatActivity {
    private Button login;
    private Button back;
    private TextView username;
    private TextView password;
    private TextView registrati;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login = (Button) findViewById(R.id.btn_register);
        username = (TextView) findViewById(R.id.username_login);
        password = (TextView) findViewById(R.id.password_login);
        registrati = (TextView) findViewById(R.id.registrati_textview);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("username : " + username.getText());
                System.out.println("password : " + password.getText());

            }
        });
        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }

    public void register() {
        Intent intent = new Intent(this, RegisterController.class);
        startActivity(intent);
    }
}
