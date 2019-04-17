package com.example.collagehelper.activity.seller.goods.addgoods.presenter;

import com.example.collagehelper.activity.seller.goods.addgoods.manager.AddGoodsManager;
import com.example.collagehelper.activity.seller.goods.addgoods.view.IAddGoodsView;

import okhttp3.MultipartBody;

public class AddGoodsPresenter {
    private IAddGoodsView view;
    private AddGoodsManager manager;

    public AddGoodsPresenter(IAddGoodsView view){
        this.view = view;
        manager = new AddGoodsManager(this);
    }

    public void addGoods(String phone, String name, String price, String des, MultipartBody.Part image){
        manager.addGoods(phone, name, des, price, image);
    }

    public void addSuccess(){
        view.addSuccess();
    }

    public void addFailure(){
        view.addFailure();
    }
}
