package com.example.collagehelper.activity.customer.mycollect.presenter;

import com.example.collagehelper.activity.customer.mycollect.manager.MyCollectManager;
import com.example.collagehelper.activity.customer.mycollect.view.IMyCollectView;
import com.example.collagehelper.bean.CGDO;
import com.example.collagehelper.bean.GoodsAllInfo;

import java.util.List;

public class MyCollectPresenter {
    private IMyCollectView view;
    private MyCollectManager manager;

    public MyCollectPresenter(IMyCollectView view){
        this.view = view;
        manager = new MyCollectManager(this);
    }

    public void getCgSuccess(List<CGDO> list){
        view.getCgSuccess(list);
    }

    public void getCgFailure(){
        view.getCgFailure();
    }

    public void deleteCgSuccess(){
        view.deleteCgSuccess();
    }

    public void deleteCgFailure(){
        view.deleteCgFailure();
    }

    public void getCg(String phone){
        manager.getCg(phone);
    }

    public void deleteCg(int id){
        manager.deleteCg(id);
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
