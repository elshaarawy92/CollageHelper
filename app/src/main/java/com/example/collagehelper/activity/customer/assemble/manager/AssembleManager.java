package com.example.collagehelper.activity.customer.assemble.manager;

import com.example.collagehelper.activity.customer.assemble.presenter.AssemblePresenter;
import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.bean.APDO;
import com.example.collagehelper.bean.Assemble;
import com.example.collagehelper.bean.GoodsAllInfo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssembleManager extends BaseManager {
    private AssemblePresenter presenter;

    public AssembleManager(AssemblePresenter presenter){
        this.presenter = presenter;
    }

    public void getByPhone(String phone){
        Call<List<APDO>> call = apAsk.getByPhone(phone);
        call.enqueue(new Callback<List<APDO>>() {
            @Override
            public void onResponse(Call<List<APDO>> call, Response<List<APDO>> response) {
                presenter.getAssembleSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<APDO>> call, Throwable t) {
                presenter.getAssembleFailure();
            }
        });
    }

    public void getByAssembleId(String assembleId){
        Call<List<Assemble>> call = assembleAsk.getByAssembleId(assembleId);
        call.enqueue(new Callback<List<Assemble>>() {
            @Override
            public void onResponse(Call<List<Assemble>> call, Response<List<Assemble>> response) {
                presenter.getByAssembleIdSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Assemble>> call, Throwable t) {
                presenter.getByAssembleIdFailure();
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

    public void updateApById(String assembleId,String phone,String status){
        Call<ResponseBody> call = apAsk.updateAPById(status,assembleId,phone);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                presenter.updateApSuccess();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                presenter.updateApFailure();
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
