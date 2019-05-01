package com.example.collagehelper.activity.customer.fragment.presenter;

import com.example.collagehelper.activity.customer.fragment.manager.AssembleManager;
import com.example.collagehelper.activity.customer.fragment.view.IAssembleView;
import com.example.collagehelper.bean.APDO;
import com.example.collagehelper.bean.Assemble;
import com.example.collagehelper.bean.GoodsAllInfo;

import java.util.List;

public class AssemblePresenter {
    private AssembleManager manager;
    private IAssembleView view;

    public AssemblePresenter(IAssembleView view){
        manager = new AssembleManager(this);
        this.view = view;
    }

    public void getAssembleSuccess(List<Assemble> list){
        view.getAssembleSuccess(list);
    }

    public void getAssembleFailure(){
        view.getAssembleFailure();
    }

    public void getAssemble(){
        manager.getAssemble();
    }

    public void getGodsById(int id){
        manager.getGoodsDetail(id);
    }

    public void getGoodsSuccess(GoodsAllInfo goodsAllInfo){
        view.getGoodsSuccess(goodsAllInfo);
    }

    public void getGoodsFailure(){
        view.getGoodsFailure();
    }

    public void getAPSuccess(List<APDO> list){
        view.getAPSuccess(list);
    }

    public void getAPFailure(){
        view.getAPFailure();
    }

    public void getAp(String assembleId){
        manager.getAP(assembleId);
    }
}
