package com.example.collagehelper.face.manager;

import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.bean.User;
import com.example.collagehelper.bean.UserDO;
import com.example.collagehelper.face.presenter.RARAPresenter;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RARAManager extends BaseManager {
    private RARAPresenter presenter;

    public RARAManager(RARAPresenter presenter){
        this.presenter = presenter;
    }

    public void insert(String phone,String face){
        Call<ResponseBody> call = ask.insertFace(phone,face);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                presenter.insertSuccess();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void selectByPhoneAndFace(String phone,String face){
        Call<UserDO> call = ask.selectByPhoneAndFace(phone,face);
        call.enqueue(new Callback<UserDO>() {
            @Override
            public void onResponse(Call<UserDO> call, Response<UserDO> response) {
                presenter.selectSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UserDO> call, Throwable t) {

            }
        });
    }

    public void selectByPhone(String phone){
        Call<User> call = ask.getUser(phone);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getStatus().equals("failure")){
                    presenter.getNull();
                }else {
                    presenter.getSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
