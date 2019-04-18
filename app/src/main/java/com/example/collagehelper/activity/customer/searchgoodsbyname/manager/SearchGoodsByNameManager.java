package com.example.collagehelper.activity.customer.searchgoodsbyname.manager;

import com.example.collagehelper.activity.customer.searchgoodsbyname.presenter.SearchGoodsByNamePresenter;
import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.bean.GoodsInfoFromServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchGoodsByNameManager extends BaseManager {
    private SearchGoodsByNamePresenter presenter;

    public SearchGoodsByNameManager(SearchGoodsByNamePresenter presenter){
        this.presenter = presenter;
    }

    public void getGoodsByName(String name){
        Call<GoodsInfoFromServer> call = goodsAsk.getGoodsByName(name);
        call.enqueue(new Callback<GoodsInfoFromServer>() {
            @Override
            public void onResponse(Call<GoodsInfoFromServer> call, Response<GoodsInfoFromServer> response) {
                if (response.body().getStatus().equals("failure")){
                    presenter.getGoodsNull();
                }else {
                    presenter.getGoodsSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<GoodsInfoFromServer> call, Throwable t) {
                presenter.getGoodsFailure();
            }
        });
    }
}
