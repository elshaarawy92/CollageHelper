package com.example.collagehelper.activity.main.manager;

import com.example.collagehelper.activity.main.presenter.MainPresenter;
import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.bean.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainManager extends BaseManager {
    private MainPresenter mainPresenter;

    public MainManager(MainPresenter mainPresenter){
        this.mainPresenter = mainPresenter;
    }

    public void getUserInfo(String phone){
        Call<User> call = ask.getUser(phone);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getStatus().equals("success")){
                    mainPresenter.getUserInoSuccess(response.body().getData().getName(),response.body().getData().getHead());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mainPresenter.getUserInfoFailure();
            }
        });
    }

}
