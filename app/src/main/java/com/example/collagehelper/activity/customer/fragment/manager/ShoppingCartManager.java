package com.example.collagehelper.activity.customer.fragment.manager;

import com.example.collagehelper.activity.customer.fragment.presenter.ShoppingCartPresenter;
import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.bean.GoodsAllInfo;
import com.example.collagehelper.bean.ShoppingCart;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingCartManager extends BaseManager {
    private ShoppingCartPresenter presenter;

    public ShoppingCartManager(ShoppingCartPresenter presenter){
        this.presenter = presenter;
    }

    public void getFromCart(String phone){
        Call<ShoppingCart> call = cartAsk.getFromCart(phone);
        call.enqueue(new Callback<ShoppingCart>() {
            @Override
            public void onResponse(Call<ShoppingCart> call, Response<ShoppingCart> response) {
                if (response.body().getStatus().equals("failure")){
                    presenter.getFromCartNull();
                }else {
                    presenter.getFromCartSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<ShoppingCart> call, Throwable t) {
                presenter.getFromCartFailure();
            }
        });
    }

    public void getGoodsDetail(int id) {
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

    public void delete(int id){
        Call<ResponseBody> call = cartAsk.delete(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                presenter.deleteSuccess();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                presenter.deleteFailure();
            }
        });
    }
}
