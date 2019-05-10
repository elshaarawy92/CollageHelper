package com.example.collagehelper.activity.customer.goodsdetails.presenter;

import com.example.collagehelper.activity.customer.goodsdetails.manager.GoodsDetailManager;
import com.example.collagehelper.activity.customer.goodsdetails.view.IGoodsDetailView;
import com.example.collagehelper.bean.CGDO;
import com.example.collagehelper.bean.GoodsAllInfo;

import java.util.List;

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

    public void addOrder(String customerPhone,String sellerPhone,String orderId,String time,String money,int goodsId,int goodsCount,String status){
        manager.addOrder(customerPhone,sellerPhone,orderId,time,money,goodsId,goodsCount,status);
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

    public void addCgSuccess(){
        view.addCgSuccess();
    }

    public void addCgFailure(){
        view.addCgFailure();
    }

    public void addCg(String phone,int goodsId){
        manager.addCg(phone,goodsId);
    }

    public void getCg(String phone){
        manager.getCg(phone);
    }

    public void getCgSuccess(List<CGDO> list){
        view.getCgSuccess(list);
    }

    public void getCgFailure(){
        view.getCgFailure();
    }
}
