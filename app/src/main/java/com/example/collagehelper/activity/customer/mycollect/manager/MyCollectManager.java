package com.example.collagehelper.activity.customer.mycollect.manager;

import com.example.collagehelper.activity.customer.mycollect.presenter.MyCollectPresenter;
import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.bean.CGDO;
import com.example.collagehelper.bean.GoodsAllInfo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCollectManager extends BaseManager {
    private MyCollectPresenter presenter;

    public MyCollectManager(MyCollectPresenter presenter){
        this.presenter = presenter;
    }

    public void deleteCg(int id){
        Call<ResponseBody> call = cgAsk.deleteFromCg(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                presenter.deleteCgSuccess();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                presenter.deleteCgFailure();
            }
        });
    }

    public void getCg(String phone){
        Call<List<CGDO>> call = cgAsk.getFromCg(phone);
        call.enqueue(new Callback<List<CGDO>>() {
            @Override
            public void onResponse(Call<List<CGDO>> call, Response<List<CGDO>> response) {
                if (response.body() != null){
                    presenter.getCgSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CGDO>> call, Throwable t) {
                presenter.getCgFailure();
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
