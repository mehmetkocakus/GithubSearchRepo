package com.example.mehmetkocakus.challenge.Activities.Login.View;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.mehmetkocakus.challenge.Activities.Login.Interfaces.ILoginContract;
import com.example.mehmetkocakus.challenge.Activities.Login.Presenter.LoginPresenters;
import com.example.mehmetkocakus.challenge.Classes.Tools;
import com.example.mehmetkocakus.challenge.Activities.Orders.View.OrderActivity;
import com.example.mehmetkocakus.challenge.R;

/**
 * Developer:Mehmet Kocakuş
 * Mobile Phone:0543 915 2315
 * E Mail:mehmetkocakus@gmail.com
 */
public class LoginActivity extends AppCompatActivity implements ILoginContract.IUserInterface {

    @BindView(R.id.login_username_edittext)
    EditText username;

    @BindView(R.id.login_password_edittext)
    EditText password;

    @BindView(R.id.login_remember_me_switch)
    Switch rememberMe;

    @BindView(R.id.login)
    Button login;

    Tools tool;

    LoginPresenters loginPresenters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.login_activity);

        ButterKnife.bind(this);
        tool=new Tools();
        loginPresenters=new LoginPresenters(LoginActivity.this);
        loginPresenters.checkLogin();
    }

    @Optional
    @OnClick({R.id.login})
    public void onClick(View v) {
        tool.preventTwoClick(v);
        switch (v.getId()) {
            case R.id.login:
                setLogin();
                break;
        }
    }

    /**
     * Bu metot kullanıcı adı ve şifresini giren kullanıcının
     * veritabanından kontrolünü yaparak uygulamaya giriş yapar
     */
    void setLogin()
    {
        if(!tool.isNullOrEmpty(password.getText().toString()))
        {
            loginPresenters.isLogin(password.getText().toString(),rememberMe.isChecked());
        }
        else
        {
            tool.snackbarErrorMessage(login,R.string.please_select_your_password);
        }
    }

    /**
     * Bu metot uygulamaya giriş yapmak için kullanıcının durumunu sorgular
     * Eğer
     * type-->1 ise kulllanıcı butona basıp login işlemi yapmıştır
     * type-->2 ise kullanıcı uygulamaya daha önceden girmiş mi kontrol edilir
     * isLogin true gelirse artık login sayfasına giriş yapılmadan order activity'e geçilir
     * @param isLogin
     * @param type
     */
    @Override
    public void resultLogin(boolean isLogin,int type) {

        if(isLogin)
        {
            Intent intent = new Intent(LoginActivity.this, OrderActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            if(type==1)
            {
                tool.snackbarErrorMessage(login,R.string.invalid_password_string);
            }
        }
    }

    @Override
    public void resultDelete(Activity activity) {

    }

}
