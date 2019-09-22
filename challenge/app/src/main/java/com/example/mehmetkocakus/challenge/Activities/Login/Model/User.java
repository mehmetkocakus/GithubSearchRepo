package com.example.mehmetkocakus.challenge.Activities.Login.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "USER")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int userID;

    @ColumnInfo(name = "USERNAME")
    private String username;

    @ColumnInfo(name = "PASSWORD")
    private String password;

    @ColumnInfo(name = "ISLOGIN")
    private boolean isLogin;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
