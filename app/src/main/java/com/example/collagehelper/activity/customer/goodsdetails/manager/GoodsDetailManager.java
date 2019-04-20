package com.example.collagehelper.activity.customer.goodsdetails.manager;

import com.example.collagehelper.activity.customer.goodsdetails.presenter.GoodsDetailPresenter;
import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.bean.GoodsAllInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoodsDetailManager extends BaseManager {
    private GoodsDetailPresenter presenter;

    public GoodsDetailManager(GoodsDetailPresenter presenter){
        this.presenter = presenter;
    }

    public void getGoodsDetail(int id){
        Call<GoodsAllInfo> call = goodsAsk.getGoodsById(id);
        call.enqueue(new Callback<GoodsAllInfo>() {
            @Override
            public void onResponse(Call<GoodsAllInfo> call, Response<GoodsAllInfo> response) {
                presenter.getGoodsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<GoodsAllInfo> call, Throwable t) {
                presenter.getGoodsFailure();
            }
        });
    }
}
