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
}
