package com.example.collagehelper.activity.customer.assemble.presenter;

import com.example.collagehelper.activity.customer.assemble.manager.AssembleManager;
import com.example.collagehelper.activity.customer.assemble.view.IAssembleView;
import com.example.collagehelper.bean.APDO;
import com.example.collagehelper.bean.Assemble;
import com.example.collagehelper.bean.GoodsAllInfo;

import java.util.List;

public class AssemblePresenter {
    private IAssembleView view;
    private AssembleManager manager;

    public AssemblePresenter(IAssembleView view){
        this.view = view;
        manager = new AssembleManager(this);
    }

    public void getAssembleSuccess(List<APDO> list){
        view.getAssembleSuccess(list);
    }

    public void getAssembleFailure(){
        view.getAssembleFailure();
    }

    public void getAssemble(String phone){
        manager.getByPhone(phone);
    }

    public void getByAssembleIdSuccess(List<Assemble> list){
        view.getByAssembleIdSuccess(list);
    }

    public void getByAssembleIdFailure(){
        view.getByAssembleIdFailure();
    }

    public void getByAssembleId(String assembleId){
        manager.getByAssembleId(assembleId);
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

    public void updateApSuccess(){
        view.updateApSuccess();
    }

    public void updateApFailure(){
        view.updateApFailure();
    }

    public void updateApById(String assembleId,String phone,String status){
        manager.updateApById(assembleId,phone,status);
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
}
