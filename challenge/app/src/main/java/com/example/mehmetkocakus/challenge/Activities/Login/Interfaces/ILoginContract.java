package com.example.mehmetkocakus.challenge.Activities.Login.Interfaces;

import android.app.Activity;

public interface ILoginContract {

    //Bu metot kullanıcıyı uygulamaya giriş yaptırmak için kullanılır
    void isLogin(String password,boolean isChecked);

    //bu metot kullanıcının uygulamaya giriş durumunu kontrol eder
    void checkLogin();

    //bu metot kullanıcıyı veritabanındam silmek için kullanılır
    void deleteUser();

    interface IUserInterface{
        //Bu metot kullanıcının login durumunun activity'e düşmesi için kullanılır
        void resultLogin(boolean isLogin,int type);

        //Bu metot kullanının uygulamadan çıkış durumunun activity'e düşmesi için kullanılır
        void resultDelete(Activity activity);
    }

}
