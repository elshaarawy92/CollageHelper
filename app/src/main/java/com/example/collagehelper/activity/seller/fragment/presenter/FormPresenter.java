package com.example.collagehelper.activity.seller.fragment.presenter;

import com.example.collagehelper.activity.seller.fragment.manager.FormManager;
import com.example.collagehelper.activity.seller.fragment.view.IFormView;
import com.example.collagehelper.bean.GoodsAllInfo;
import com.example.collagehelper.bean.Order;

import java.util.List;

public class FormPresenter {
    private IFormView view;
    private FormManager manager;

    public FormPresenter(IFormView view){
        this.view = view;
        manager = new FormManager(this);
    }

    public void getForm(String sellerPhone){
        manager.getForm(sellerPhone);
    }

    public void getGoodsDetail(int id){
        manager.getGoodsDetail(id);
    }

    public void getOrderSuccess(List<Order> list){
        view.getOrderSuccess(list);
    }

    public void getOrderFailure(){
        view.getOrderFailure();
    }

    public void getGoodsSuccess(GoodsAllInfo goodsAllInfo){
        view.getGoodsSuccess(goodsAllInfo);
    }

    public void getGoodsFailure(){
        view.getOrderFailure();
    }

    public void updateSuccess(){
        view.updateSuccess();
    }

    public void updateFailure(){
        view.updateFailure();
    }

    public void updateByOrderId(String orderId,String status){
        manager.updateByOrderId(orderId,status);
    }
}
