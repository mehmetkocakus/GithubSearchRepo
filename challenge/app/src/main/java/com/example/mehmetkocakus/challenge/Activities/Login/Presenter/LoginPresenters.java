package com.example.mehmetkocakus.challenge.Activities.Login.Presenter;


import android.app.Activity;

import com.example.mehmetkocakus.challenge.Activities.Login.Interfaces.ILoginContract;
import com.example.mehmetkocakus.challenge.Database.CDAO;
import com.example.mehmetkocakus.challenge.Activities.Login.Model.User;
import com.example.mehmetkocakus.challenge.Database.IDAO;

public class LoginPresenters implements ILoginContract {

    ILoginContract.IUserInterface userInterface;
    Activity activity;
    CDAO database;
    User getUser;
    IDAO idao;

    public LoginPresenters(Activity activity){
        this.userInterface=(ILoginContract.IUserInterface) activity;
        this.activity=activity;

        //Eğerki oluşturulan şifreli user veritabanında yoksa yenisini oluşturur
        User user = new User();
        user.setUsername("kariyer");
        user.setPassword("2019ADev");
        user.setLogin(false);
        database=CDAO.getCDAO(activity);
        idao=database.idaoUSER();
        User getUSER=idao.getUSER("2019ADev");
        if(getUSER==null) {
            database.idaoUSER().addNewUser(user);
        }
    }

    //Bu metot kullanıcının login yapması için kullanılır
    @Override
    public void isLogin(String password,boolean isChecked) {
        getUser=idao.getUSER(password);
        //eğer gelen şifre ile eşleşen kullanıcı  varsa true yoksa false döner
        if(getUser!=null)
        {
            //beni hatırla durumuna göre kullanıcı günceller
            idao.updateUSER(isChecked);

            userInterface.resultLogin(true,1);
        }
        else
        {
            userInterface.resultLogin(false,1);
        }

    }

    /**
     *     Bu metot kullanıcının loginde beni hatırla durumunu kontrol eder
     *     activity'e dönen cevaba göre eğer beni hatırla seçili ise bir dahaki girişlerde
     *     login ekranına düşmez
     */
    @Override
    public void checkLogin() {
        userInterface.resultLogin(idao.isLogin(),2);
    }

    //bu metot kullanıcıyı veritabanından siler.
    @Override
    public void deleteUser() {
        idao.deleteUSER();
        userInterface.resultDelete(activity);
    }
}
