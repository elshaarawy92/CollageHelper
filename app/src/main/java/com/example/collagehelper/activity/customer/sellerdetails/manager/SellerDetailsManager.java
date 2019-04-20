package com.example.collagehelper.activity.customer.sellerdetails.manager;

import com.example.collagehelper.activity.customer.sellerdetails.presenter.SellerDetailsPresenter;
import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.bean.CTSDO;
import com.example.collagehelper.bean.GoodsInfoFromServer;
import com.example.collagehelper.bean.User;

import okhttp3.ResponseBody;
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

    public void collectSeller(String cPhone,String sPhone){
        Call<ResponseBody> call = ctsAsk.collectSeller(cPhone,sPhone);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                presenter.collectSuccess();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                presenter.collectFailure();
            }
        });
    }

    public void getCollectedSeller(String cPhone){
        Call<CTSDO> ctsdoCall = ctsAsk.getCollectedSeller(cPhone);
        ctsdoCall.enqueue(new Callback<CTSDO>() {
            @Override
            public void onResponse(Call<CTSDO> call, Response<CTSDO> response) {
                if (response.body().getStatus().equals("failure")){
                    presenter.getCollectedSellerNull();
                }else {
                    presenter.getCollectedSellerSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<CTSDO> call, Throwable t) {
                presenter.getCollectedSellerFailure();
            }
        });
    }

    public void deleteSellerByPrimaryKey(int id){
        Call<ResponseBody> call = ctsAsk.deleteSellerById(id);
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
