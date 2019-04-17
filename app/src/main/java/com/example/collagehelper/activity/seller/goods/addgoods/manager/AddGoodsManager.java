package com.example.collagehelper.activity.seller.goods.addgoods.manager;

import com.example.collagehelper.activity.seller.goods.addgoods.presenter.AddGoodsPresenter;
import com.example.collagehelper.base.BaseManager;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddGoodsManager extends BaseManager {
    private AddGoodsPresenter presenter;

    public AddGoodsManager(AddGoodsPresenter presenter){
        this.presenter = presenter;
    }

    public void addGoods(String phone,String name, String des, String price, MultipartBody.Part image){
        Call<ResponseBody> call = goodsAsk.addGoods(toRequestBody(phone),toRequestBody(name),toRequestBody(price),toRequestBody(des),image);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                presenter.addSuccess();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                presenter.addFailure();
            }
        });
    }

}
