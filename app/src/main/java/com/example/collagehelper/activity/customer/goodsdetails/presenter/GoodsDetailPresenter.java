package com.example.collagehelper.activity.customer.goodsdetails.presenter;

import com.example.collagehelper.activity.customer.goodsdetails.manager.GoodsDetailManager;
import com.example.collagehelper.activity.customer.goodsdetails.view.IGoodsDetailView;
import com.example.collagehelper.bean.GoodsAllInfo;

public class GoodsDetailPresenter {
    private GoodsDetailManager manager;
    private IGoodsDetailView view;

    public GoodsDetailPresenter(IGoodsDetailView view){
        this.view = view;
        manager = new GoodsDetailManager(this);
    }

    public void getGoodsSuccess(GoodsAllInfo goodsAllInfo){
        view.getGoodsSuccess(goodsAllInfo);
    }

    public void getGoodsFailure(){
        view.getGoodsFailure();
    }

    public void getGoodsDetail(int id){
        manager.getGoodsDetail(id);
    }

    public void addToCart(String phone,int goodsId,int goodsCount){
        manager.addToCart(phone,goodsId,goodsCount);
    }

    public void addToCartSuccess(){
        view.addToCatSuccess();
    }

    public void addToCartFailure(){
        view.addToCartFailure();
    }

    public void addOrder(String customerPhone,String sellerPhone,String orderId,String time,String money,int goodsId,int goodsCount){
        manager.addOrder(customerPhone,sellerPhone,orderId,time,money,goodsId,goodsCount);
    }

    public void addOrderSuccess(){
        view.addOrderSuccess();
    }

    public void addOrderFailure(){
        view.addOrderFailure();
    }

    public void addAssembleSuccess(){
        view.addAssembleSuccess();
    }

    public void addAssembleFailure(){
        view.addAssembleFailure();
    }

    public void addAssemble(String customerPhone,String sellerPhone,String assembleId,String time,String money,int goodsId,int goodsCount){
        manager.addAssemble(customerPhone,sellerPhone,assembleId,time,money,goodsId,goodsCount);
    }
}
