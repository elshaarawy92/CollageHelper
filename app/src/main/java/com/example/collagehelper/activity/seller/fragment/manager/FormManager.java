package com.example.collagehelper.activity.seller.fragment.manager;

import com.example.collagehelper.activity.seller.fragment.presenter.FormPresenter;
import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.bean.GoodsAllInfo;
import com.example.collagehelper.bean.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormManager extends BaseManager {
    private FormPresenter presenter;

    public FormManager(FormPresenter presenter){
        this.presenter = presenter;
    }

    public void getForm(String sellerPhone){
        Call<List<Order>> call = orderAsk.selectBySellerPhone(sellerPhone);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                presenter.getOrderSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                presenter.getOrderFailure();
            }
        });
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
