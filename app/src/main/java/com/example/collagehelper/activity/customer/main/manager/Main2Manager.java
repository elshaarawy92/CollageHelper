package com.example.collagehelper.activity.customer.main.manager;

import com.example.collagehelper.activity.customer.main.presenter.Main2Presenter;
import com.example.collagehelper.bean.CTSDO;
import com.example.collagehelper.bean.User;
import com.example.collagehelper.base.BaseManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Manager extends BaseManager {
    private Main2Presenter presenter;

    public Main2Manager(Main2Presenter presenter){
        this.presenter = presenter;
    }

    public void getUser(String phone){
        Call<User> call = ask.getUser(phone);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                presenter.getUserInfoSuccess(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void getCollectedSeller(String cPhone){
        Call<CTSDO> ctsdoCall = ctsAsk.getCollectedSeller(cPhone);
        ctsdoCall.enqueue(new Callback<CTSDO>() {
            @Override
            public void onResponse(Call<CTSDO> call, Response<CTSDO> response) {
                if (response.body().getStatus().equals("failure")){
                    presenter.getCollectedSellerNull();
                }else {
                    presenter.getCollectedSellerSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<CTSDO> call, Throwable t) {
                presenter.getCollectedSellerFailure();
            }
        });
    }

    public void getSellerByPhone(String phone){
        Call<User> call = ask.getUser(phone);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                presenter.getSellerSuccess(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
