package com.example.mehmetkocakus.challenge.Database;

import com.example.mehmetkocakus.challenge.Activities.Login.Model.User;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

//Bu interface user tablosu işlemleri için kullanılmıştır.
@Dao
public interface IDAO {

    @Insert
    void addNewUser(User user);

    @Query("DELETE FROM USER")
    void deleteUSER();

    @Query("SELECT * FROM USER WHERE PASSWORD LIKE:password")
    User getUSER(String password);

    @Query("SELECT ISLOGIN FROM USER")
    Boolean isLogin();

    @Query("UPDATE USER SET ISLOGIN = :isLogin")
    void updateUSER(boolean isLogin);

}
