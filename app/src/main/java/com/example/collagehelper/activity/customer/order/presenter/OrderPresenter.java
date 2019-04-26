package com.example.collagehelper.activity.customer.order.presenter;

import com.example.collagehelper.activity.customer.order.manager.OrderManager;
import com.example.collagehelper.activity.customer.order.view.IOrderView;
import com.example.collagehelper.bean.GoodsAllInfo;
import com.example.collagehelper.bean.Order;

import java.util.List;

public class OrderPresenter {
    private IOrderView view;
    private OrderManager manager;

    public OrderPresenter(IOrderView view){
        this.view = view;
        manager = new OrderManager(this);
    }

    public void getOrderSuccess(List<Order> list){
        view.getOrderSuccess(list);
    }

    public void getOrderFailure(){
        view.getOrderFailure();
    }

    public void getOrder(String customerPhone){
        manager.getOrder(customerPhone);
    }

    public void getGodsById(int id){
        manager.getGoodsDetail(id);
    }

    public void getGoodsSuccess(GoodsAllInfo goodsAllInfo){
        view.getGoodsSuccess(goodsAllInfo);
    }

    public void getGoodsFailure(){
        view.getOrderFailure();
    }
}
