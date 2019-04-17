package com.example.collagehelper.activity.regist.manager;

import android.util.Log;

import com.example.collagehelper.activity.regist.presenter.RegistPresenter;
import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.activity.seller.bean.User;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistManager extends BaseManager {

    public RegistPresenter registPresenter;

    private boolean label = false;

    public RegistManager(RegistPresenter registPresenter){
        this.registPresenter = registPresenter;
    }

    public boolean regist(String username, String phone, String pwd, String type, MultipartBody.Part head){
        Call<Integer> call = ask.regist(toRequestBody(phone),toRequestBody(username),toRequestBody(pwd),toRequestBody(type),head);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.d("onClick", "onResponse: ");
                label = true;
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                label = false;
            }
        });
        return label;
    }

    public void checkAccountExsists(String phone){
        Call<User> call = ask.getUser(phone);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getStatus().equals("success")){
                    registPresenter.pwdExist();
                }else {
                    registPresenter.pwdNotExist();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }
}
