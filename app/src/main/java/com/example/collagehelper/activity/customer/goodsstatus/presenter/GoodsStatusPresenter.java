package com.example.collagehelper.activity.customer.goodsstatus.presenter;

import com.example.collagehelper.activity.customer.goodsstatus.manager.GoodsStatusManager;
import com.example.collagehelper.activity.customer.goodsstatus.view.IGoodsStatusView;
import com.example.collagehelper.bean.GoodsAllInfo;
import com.example.collagehelper.bean.Order;

import java.util.List;

public class GoodsStatusPresenter {
    private IGoodsStatusView view;
    private GoodsStatusManager manager;

    public GoodsStatusPresenter(IGoodsStatusView view){
        this.view = view;
        manager = new GoodsStatusManager(this);
    }

    public void getSuccess(List<Order> list){
        view.getSuccess(list);
    }

    public void getGoodsSuccess(GoodsAllInfo goodsAllInfo){
        view.getGoodsSuccess(goodsAllInfo);
    }

    public void updateSuccess(){
        view.updateSuccess();
    }

    public void getOrder(String status,String customerPhone){
        manager.getOrderByStatus(status, customerPhone);
    }

    public void getGodsById(int id){
        manager.getGoodsDetail(id);
    }

    public void updateByOrderId(String orderId,String status){
        manager.updateByOrderId(orderId, status);
    }
}
