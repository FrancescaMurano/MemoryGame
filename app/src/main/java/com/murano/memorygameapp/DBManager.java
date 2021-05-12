package com.murano.memorygameapp;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBManager {

    private Connection connection;
    private static DBManager instance;

    private DBManager() {
        connection = null;
        instance = null;
        createConnection();
    }

    public static DBManager getInstance() {
        if (instance == null)
            instance = new DBManager();
        return instance;
    }

    private void createConnection() {
        try {
            String url = "jdbc:sqlite:MemoryGame.db";
            connection = DriverManager.getConnection(url);
            System.out.println("db connesso");

        }

        catch (SQLException e) {
            connection = null;
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        closeConnection();
    }

    private void closeConnection() throws SQLException {
        if (connection == null)
            return;
        connection.close();
        connection = null;
    }
    public boolean checkUsername(String user) {

        if(connection == null)
            return false;
        try {
            String query = "SELECT username FROM User WHERE username=?;";
            PreparedStatement stmt = null;
            stmt = connection.prepareStatement(query);
            stmt.setString(1, user);
            ResultSet res = stmt.executeQuery();
            boolean result = res.next();
            stmt.close();
            return result;
        }

        catch (SQLException e) {
            e.printStackTrace();
            return false;

        }



    }
    public boolean checkPassword(String user, String password){

        if(connection == null)
            return false;

        String query = "SELECT password FROM User WHERE username=?;";
        PreparedStatement stmt = null;

        try {

            stmt = connection.prepareStatement(query);
            stmt.setString(1, user);

            ResultSet rs = stmt.executeQuery();

            String password_hash = null;
            password_hash = rs.getString(1);

            stmt.close();

            if(password_hash == null)
                return false;

            return BCrypt.checkpw(password, password_hash);
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean login(String username,String password) {
        if(connection == null)
            return false;

        return false;
    }

    public boolean changePassword(String Username,String Newpassword) {

        if(connection == null)
            return false;

        if(checkUsername(Username)) {
            String query = "UPDATE User SET Password=? WHERE Username=? ";
            try {
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setString(1, BCrypt.hashpw(Newpassword,BCrypt.gensalt(12)));
                stmt.setString(2,Username);
                stmt.executeUpdate();
                stmt.close();

                return true;
            }
            catch (SQLException e) {
                e.printStackTrace();;
            }
        }

        return false;
    }

    public boolean register(String Username,String Password) {

        if(connection == null)
            return false;

        if(checkUsername(Username))
            return false;

        PreparedStatement stmt = null;

        try {
            String query = "INSERT INTO User (Username,Password) VALUES (?,?)";
            stmt = connection.prepareStatement(query);

            String hashPass = null;
            hashPass = BCrypt.hashpw(Password,BCrypt.gensalt(12));

            stmt.setString(1, Username);
            stmt.setString(2, hashPass);
            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

}
