package com.example.collagehelper.activity.customer.order.manager;

import com.example.collagehelper.activity.customer.order.presenter.OrderPresenter;
import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.bean.GoodsAllInfo;
import com.example.collagehelper.bean.Order;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderManager extends BaseManager {
    private OrderPresenter presenter;

    public OrderManager(OrderPresenter presenter){
        this.presenter = presenter;
    }

    public void getOrder(String customerPhone){
        Call<List<Order>> call = orderAsk.selectByCustomerPhone(customerPhone);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                presenter.getOrderSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                presenter.getOrderFailure();
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

    public void updateByOrderId(String orderId,String status){
        Call<ResponseBody> call = orderAsk.updateByOrderId(orderId,status);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                presenter.updateSuccess();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
