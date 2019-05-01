package com.example.collagehelper.activity.customer.fragment.manager;

import com.example.collagehelper.activity.customer.fragment.presenter.AssemblePresenter;
import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.bean.APDO;
import com.example.collagehelper.bean.Assemble;
import com.example.collagehelper.bean.GoodsAllInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssembleManager extends BaseManager {
    private AssemblePresenter presenter;

    public AssembleManager(AssemblePresenter presenter){
        this.presenter = presenter;
    }

    public void getAssemble(){
        Call<List<Assemble>> call = assembleAsk.getAssemble();
        call.enqueue(new Callback<List<Assemble>>() {
            @Override
            public void onResponse(Call<List<Assemble>> call, Response<List<Assemble>> response) {
                presenter.getAssembleSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Assemble>> call, Throwable t) {
                presenter.getAssembleFailure();
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

    public void getAP(String assembleId){
        Call<List<APDO>> call = apAsk.getAP(assembleId);
        call.enqueue(new Callback<List<APDO>>() {
            @Override
            public void onResponse(Call<List<APDO>> call, Response<List<APDO>> response) {
                presenter.getAPSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<APDO>> call, Throwable t) {
                presenter.getAPFailure();
            }
        });
    }
}
