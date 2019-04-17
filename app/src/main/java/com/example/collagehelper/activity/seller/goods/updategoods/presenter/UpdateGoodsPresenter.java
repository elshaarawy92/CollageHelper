package com.example.collagehelper.activity.seller.goods.updategoods.presenter;

import com.example.collagehelper.activity.seller.goods.updategoods.manager.UpdateGoodsManager;
import com.example.collagehelper.activity.seller.goods.updategoods.view.IUpdateGoodsView;
import com.example.collagehelper.activity.seller.bean.GoodsAllInfo;

import okhttp3.MultipartBody;

public class UpdateGoodsPresenter {
    private UpdateGoodsManager manager;
    private IUpdateGoodsView view;

    public UpdateGoodsPresenter(IUpdateGoodsView view){
        this.view = view;
        manager = new UpdateGoodsManager(this);
    }

    public void getGoods(int id){
        manager.getGoods(id);
    }

    public void updateGoods(int id, String phone, String name, String price, String des, MultipartBody.Part img){
        manager.updateGoods(id,phone,name,price,des,img);
    }

    public void getGoodsSuccess(GoodsAllInfo goodsAllInfo){
        view.getGoodsSuccess(goodsAllInfo);
    }

    public void getGoodsFailure(){
        view.getGoodsFailure();
    }

    public void updateGoodsSuccess(){
        view.updateGoodsSuccess();
    }

    public void updateGoodsFailure(){
        view.updateGoodsFailure();
    }
}
