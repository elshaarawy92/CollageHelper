package com.example.collagehelper.activity.login.presenter;

import com.example.collagehelper.activity.login.manager.LoginManager;
import com.example.collagehelper.activity.login.view.ILoginView;

public class LoginPresenter {
    private ILoginView loginView;
    private LoginManager loginManager;

    public LoginPresenter(ILoginView loginView){
        this.loginView = loginView;
        loginManager = new LoginManager(this);
    }

    public void login(String phone, String pwd, String type){
        loginManager.login(phone,pwd,type);
    }

    public void pwdWrong(){
        loginView.pwdWrong();
    }

    public void typeWrong(){
        loginView.typeWrong();
    }

    public void loginSuccess(){
        loginView.loginSuccess();
    }

    public void loginFailure(){
        loginView.loginFailure();
    }

    public void userNotExist(){
        loginView.userNotExist();
    }
}
