package com.example.collagehelper.activity.customer.sellerdetails.manager;

import com.example.collagehelper.activity.customer.sellerdetails.presenter.SellerDetailsPresenter;
import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.bean.GoodsInfoFromServer;
import com.example.collagehelper.bean.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerDetailsManager extends BaseManager {
    private SellerDetailsPresenter presenter;

    public SellerDetailsManager(SellerDetailsPresenter presenter){
        this.presenter = presenter;
    }

    public void getGoodsByPhone(String phone){
        Call<GoodsInfoFromServer> call = goodsAsk.getGoods(phone);
        call.enqueue(new Callback<GoodsInfoFromServer>() {
            @Override
            public void onResponse(Call<GoodsInfoFromServer> call, Response<GoodsInfoFromServer> response) {
                presenter.getGoodsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<GoodsInfoFromServer> call, Throwable t) {
                presenter.getGoodsFailure();
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
