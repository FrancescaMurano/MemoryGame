package com.murano.memorygameapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class DatabaseHelper extends SQLiteOpenHelper {


        public static final String DATABASE_NAME = "MemoryGame.db";
        public static  String user = "";

        public static final String TABLE_USER = "User";
        public static final String TABLE_LEVELS = "Levels";
        public static final String COL_1_USER = "Username";
        public static final String COL_2_USER = "Password";
        public static final String COL_1_LEVELS = "Level_1";
        public static final String COL_2_LEVELS= "Level_2";
        public static final String COL_3_LEVELS = "Level_3";
        public static final String COL_4_LEVELS = "Level_4";
        public static final String COL_5_LEVELS = "Level_5";
        public static final String COL_6_LEVELS = "Level_6";
        private static Context context;


        public static SQLiteDatabase db;

        public DatabaseHelper(Context context) {
            super(context,DATABASE_NAME,null,1);
            db = this.getWritableDatabase();
            this.context = context;

        }

    @Override
        public void onCreate(SQLiteDatabase db) {
            this.db = db;

            String QUERY_1 = String.format("CREATE TABLE IF NOT EXISTS " + TABLE_USER + "( " + COL_1_USER +
                    " TEXT NOT NULL UNIQUE, " + COL_2_USER +
                    " TEXT NOT NULL,PRIMARY KEY " + "(" + COL_1_USER + ") )");
            String QUERY_2 = String.format("CREATE TABLE IF NOT EXISTS  " + TABLE_LEVELS + "( " + COL_1_USER +
                    " TEXT NOT NULL UNIQUE, " +
                    COL_1_LEVELS +" NUMERIC,"+
                    COL_2_LEVELS+" NUMERIC,"+
                    COL_3_LEVELS+" NUMERIC,"+
                    COL_4_LEVELS+" NUMERIC,"+
                    COL_5_LEVELS+" NUMERIC,"+
                    COL_6_LEVELS+" NUMERIC, PRIMARY KEY " + "(" + COL_1_USER + ") )");

            db.execSQL(QUERY_1);
            db.execSQL(QUERY_2);

        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        public static boolean save_user(String username,String password){
            ContentValues values = new ContentValues();
            values.put(COL_1_USER,username);
            values.put(COL_2_USER,  BCrypt.hashpw(password,BCrypt.gensalt(12)));

            long id = db.insert(TABLE_USER, null, values);

            values.clear();
            values.put(COL_1_USER,username);
            values.put(COL_1_LEVELS,  0);
            values.put(COL_2_LEVELS,  0);
            values.put(COL_3_LEVELS,  0);
            values.put(COL_4_LEVELS,  0);
            values.put(COL_5_LEVELS,  0);
            values.put(COL_6_LEVELS,  0);

            long id2 = db.insert(TABLE_LEVELS, null, values);

            if(id != -1 && id2 != -1) {
                user = username;
                return true;
            }
            return false;

        }

        public static boolean login(String username,String password)
        {

            if(checkUsername(username) && checkPassword(username,password)){
                Toast toast = Toast.makeText(context, "Login effettuato con successo!", Toast.LENGTH_SHORT);
                toast.show();
                user = username;
                return true;
            }

            return false;

        }

        private static boolean checkUsername(String username) {

                String query = String.format("SELECT Username FROM " + TABLE_USER + " WHERE Username = '"+ username+ "'" );
                Cursor c = db.rawQuery(query, null);
                boolean result = c.moveToFirst();

                c.close();
                return result;

        }
        private static boolean checkPassword(String username,String password) {

            String query = String.format("SELECT * FROM " + TABLE_USER + " WHERE Username = '"+ username+ "'" );
            Cursor c = db.rawQuery(query, null);
            boolean result = c.moveToFirst();

            String password_hash = c.getString(1);
            if(BCrypt.checkpw(password, password_hash) && result)
                return true;
            return false;
        }

        public static boolean save_point(String level, int punteggio) {

            String query = String.format("SELECT * FROM " + TABLE_LEVELS + " WHERE Username = '"+ user+ "'" );
            Cursor c = db.rawQuery(query, null);
            boolean result = c.moveToFirst();
            if(result)
                return update_point(level,punteggio);
            else {
                ContentValues values = new ContentValues();
                values.put("Username", user);
                values.put(level, punteggio);

                long id = db.insert(TABLE_LEVELS, null, values);
                if (id != -1) {
                    return true;
                }
                return false;
            }

        }

        private static boolean update_point(String level, int punteggio) {
            System.out.println("sto facendo update..");
            ContentValues data = new ContentValues();
            data.put(level,punteggio);
            long l = db.update(TABLE_LEVELS,data, "username= '" + user+"'", null);
            if(l != -1)
                return true;
            return false;

        }

        public static ArrayList<Integer> getTotalScores() {

            ArrayList <Integer> scores = new ArrayList<Integer>();
            String query = String.format("SELECT * FROM " + TABLE_LEVELS + " WHERE Username = '"+ user + "'" );
            Cursor c = db.rawQuery(query, null);
            while(c.moveToNext()){
                scores.add(c.getInt(1));
                scores.add(c.getInt(2));
                scores.add(c.getInt(3));
                scores.add(c.getInt(4));
                scores.add(c.getInt(5));
                scores.add(c.getInt(6));
            }

            return scores;
        }

}


