package com.murano.memorygameapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterController extends AppCompatActivity {
    private Button register;
    private Button back;
    private TextView username;
    private TextView password;
    private TextView login;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        register = (Button) findViewById(R.id.btn_register);
        username = (TextView) findViewById(R.id.username_register);
        password = (TextView) findViewById(R.id.password_register);
        login = (TextView) findViewById(R.id.accedi_textview);
        getSupportActionBar().hide();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(username.getText()+" "+password.getText());
                if(DatabaseHelper.save_user(username.getText().toString(),password.getText().toString()))
                    show_alert(true);
                else
                    show_alert(false);
            }
        });

    }

    public void login() {
        Intent intent = new Intent(this, LoginController.class);
        startActivity(intent);
    }

    public void show_alert(boolean condition){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);
        setFinishOnTouchOutside(false);
        Intent intent ;
        if(condition){
            intent = new Intent(this, LevelsController.class);
            dialog.setTitle( "Registrazione" )
                    .setIcon(R.mipmap.logo_small_icon_only)
                    .setMessage("Registrazione avvenuta con successo")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        startActivity(intent);
                    }
            }).show();
        }
        else{
            dialog.setTitle( "Attenzione!" )
            .setIcon(R.mipmap.logo_small_icon_only)
            .setMessage("Registrazione fallita, riprova.")
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialoginterface, int i) {
                    //startActivity(intent);
                }
            }).show();
        }
    }
}