package com.example.collagehelper.activity.seller.goods.updategoods.manager;

import com.example.collagehelper.activity.seller.goods.updategoods.presenter.UpdateGoodsPresenter;
import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.activity.seller.bean.GoodsAllInfo;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateGoodsManager extends BaseManager {
    private UpdateGoodsPresenter presenter;

    public UpdateGoodsManager(UpdateGoodsPresenter presenter){
        this.presenter = presenter;
    }

    public void getGoods(int id){
        Call<GoodsAllInfo> call = goodsAsk.getGoodsById(id);
        call.enqueue(new Callback<GoodsAllInfo>() {
            @Override
            public void onResponse(Call<GoodsAllInfo> call, Response<GoodsAllInfo> response) {
                if (response.body().getStatus().equals("success")){
                    presenter.getGoodsSuccess(response.body());
                }else {
                    presenter.getGoodsFailure();
                }
            }

            @Override
            public void onFailure(Call<GoodsAllInfo> call, Throwable t) {
                presenter.getGoodsFailure();
            }
        });
    }

    public void updateGoods(int id, String phone, String name, String price, String des, MultipartBody.Part img){
        Call<ResponseBody> call = goodsAsk.updateGoods(toRequestBody(String.valueOf(id)),toRequestBody(phone),toRequestBody(name),toRequestBody(price),toRequestBody(des),img);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                presenter.updateGoodsSuccess();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                presenter.getGoodsFailure();
            }
        });
    }
}
