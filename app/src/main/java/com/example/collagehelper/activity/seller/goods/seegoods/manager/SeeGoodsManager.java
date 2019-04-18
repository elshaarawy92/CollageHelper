package com.example.collagehelper.activity.seller.goods.seegoods.manager;

import com.example.collagehelper.activity.seller.goods.seegoods.presenter.SeeGoodsPresenter;
import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.bean.GoodsInfoFromServer;

import okhttp3.ResponseBody;
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
        call.enqueue(new Callback<GoodsInfoFromServer>() {
            @Override
            public void onResponse(Call<GoodsInfoFromServer> call, Response<GoodsInfoFromServer> response) {
                if (response.body().getStatus().equals("success")){
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

    public void deleteGoods(int id){
        Call<ResponseBody> call = goodsAsk.deleteGoods(id);
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
