package com.example.collagehelper.activity.customer.goodsdetails.manager;

import com.example.collagehelper.activity.customer.goodsdetails.presenter.GoodsDetailPresenter;
import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.bean.GoodsAllInfo;

import okhttp3.ResponseBody;
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

    public void addToCart(String phone,int goodsId,int goodsCount){
        Call<ResponseBody> call = cartAsk.addToCart(phone,goodsId,goodsCount);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                presenter.addToCartSuccess();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                presenter.addToCartFailure();
            }
        });
    }

    public void addOrder(String customerPhone,String sellerPhone,String orderId,String time,String money,int goodsId,int goodsCount){
        Call<ResponseBody> call = orderAsk.addOrder(customerPhone,sellerPhone,orderId,time,money,goodsId,goodsCount);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                presenter.addOrderSuccess();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                presenter.addOrderFailure();
            }
        });
    }
}
