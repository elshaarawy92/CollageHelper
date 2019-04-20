package com.example.collagehelper.activity.customer.sellerdetails.presenter;

import com.example.collagehelper.activity.customer.sellerdetails.manager.SellerDetailsManager;
import com.example.collagehelper.activity.customer.sellerdetails.view.ISellerDetailsView;
import com.example.collagehelper.bean.CTSDO;
import com.example.collagehelper.bean.GoodsInfo2;
import com.example.collagehelper.bean.GoodsInfoFromServer;
import com.example.collagehelper.bean.User;

import java.util.ArrayList;
import java.util.List;

public class SellerDetailsPresenter {
    private SellerDetailsManager manager;
    private ISellerDetailsView view;

    public SellerDetailsPresenter(ISellerDetailsView view){
        this.view = view;
        manager = new SellerDetailsManager(this);
    }

    public void getGoodsSuccess(GoodsInfoFromServer goodsInfoFromServer){
        List<GoodsInfo2> list = new ArrayList<>();
        for (int i = 0; i < goodsInfoFromServer.getData().size(); i++){
            GoodsInfo2 goodsInfo = new GoodsInfo2();
            goodsInfo.setGoodsId(goodsInfoFromServer.getData().get(i).getGoodsId());
            goodsInfo.setGoodsName(goodsInfoFromServer.getData().get(i).getGoodsName());
            goodsInfo.setGoodsDes(goodsInfoFromServer.getData().get(i).getGoodsDes());
            goodsInfo.setGoodsImg(goodsInfoFromServer.getData().get(i).getGoodsImg());
            goodsInfo.setGoodsPrice(goodsInfoFromServer.getData().get(i).getGoodsPrice());
            goodsInfo.setPhone(goodsInfoFromServer.getData().get(i).getPhone());
            list.add(goodsInfo);
        }
        view.getGoodsSuccess(list);
    }

    public void getGoodsFailure(){
        view.getGoodsFailure();
    }

    public void getGoodsByPhone(String phone){
        manager.getGoodsByPhone(phone);
    }

    public void getSellerByPhone(String phone){
        manager.getSellerByPhone(phone);
    }

    public void getSellerSuccess(User user){
        view.getSellerSuccess(user);
    }

    public void collectSuccess(){
        view.collectSuccess();
    }

    public void collectFailure(){
        view.collectFailure();
    }

    public void collectSeller(String cPhone,String sPhone){
        manager.collectSeller(cPhone,sPhone);
    }

    public void getCollectedSeller(String cPhone){
        manager.getCollectedSeller(cPhone);
    }

    public void deleteSellerById(int id){
        manager.deleteSellerByPrimaryKey(id);
    }

    public void getCollectedSellerSuccess(CTSDO ctsdo){
        view.getCollectedSellerSuccess(ctsdo);
    }

    public void getCollectedSellerFailure(){
        view.getCollectedSellerFailure();
    }

    public void getCollectedSellerNull(){
        view.getCollectedSellerNull();
    }

    public void deleteSuccess(){
        view.deleteSuccess();
    }

    public void deleteFailure(){
        view.deleteFailure();
    }
}
