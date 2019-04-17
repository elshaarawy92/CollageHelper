package com.example.collagehelper.activity.goods.seegoods.manager;

import android.util.Log;

import com.example.collagehelper.activity.goods.seegoods.presenter.SeeGoodsPresenter;
import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.bean.GoodsInfoFromServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeGoodsManager extends BaseManager {
    private SeeGoodsPresenter presenter;

    public SeeGoodsManager(SeeGoodsPresenter presenter){
        this.presenter = presenter;
    }

    public void getGoods(String phone){
        Call<GoodsInfoFromServer> call = goodsAsk.getGoods(phone);
        Log.d("getGoods", "getGoods: " + phone);
        call.enqueue(new Callback<GoodsInfoFromServer>() {
            @Override
            public void onResponse(Call<GoodsInfoFromServer> call, Response<GoodsInfoFromServer> response) {
                if (response.body().getStatus().equals("success")){
                    Log.d("getGoods", "getGoods: " + response.body().getData().get(0).getGoodsName());
                    presenter.getGoodsSuccess(response.body());
                }else {
                    presenter.noGoods();
                }
            }

            @Override
            public void onFailure(Call<GoodsInfoFromServer> call, Throwable t) {
                presenter.getGoodsFailure();
            }
        });
    }
}
