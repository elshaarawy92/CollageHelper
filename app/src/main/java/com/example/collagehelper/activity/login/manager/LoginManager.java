package com.example.collagehelper.activity.login.manager;

import com.example.collagehelper.activity.login.presenter.LoginPresenter;
import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.bean.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginManager extends BaseManager {
    private LoginPresenter loginPresenter;

    public LoginManager(LoginPresenter loginPresenter){
        this.loginPresenter = loginPresenter;
    }

    public void login(String phone, final String pwd, final String type){
        Call<User> call = ask.getUser(phone);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getStatus().equals("failure")){
                    loginPresenter.userNotExist();
                }else {
                    if (!pwd.equals(response.body().getData().getPassword())){
                        loginPresenter.pwdWrong();
                    }else if (!type.equals(response.body().getData().getType())){
                        loginPresenter.typeWrong();
                    }else {
                        if (type == "1"){
                            loginPresenter.loginSuccessBySeller();
                        }else {
                            loginPresenter.loginSuccessByCustomer();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loginPresenter.loginFailure();
            }
        });
    }
}
