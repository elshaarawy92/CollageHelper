package com.example.collagehelper.activity.seller.goods.seegoods.presenter;

import com.example.collagehelper.activity.seller.goods.seegoods.manager.SeeGoodsManager;
import com.example.collagehelper.activity.seller.goods.seegoods.view.ISeeGoodsView;
import com.example.collagehelper.bean.GoodsInfo;
import com.example.collagehelper.bean.GoodsInfoFromServer;

import java.util.ArrayList;
import java.util.List;

public class SeeGoodsPresenter {
    private SeeGoodsManager manager;
    private ISeeGoodsView view;

    public SeeGoodsPresenter(ISeeGoodsView view){
        this.view = view;
        manager = new SeeGoodsManager(this);
    }

    public void getGoods(String phone){
        manager.getGoods(phone);
    }

    public void getGoodsSuccess(GoodsInfoFromServer goodsInfoFromServer){
        List<GoodsInfo> list = new ArrayList<>();
        for (int i = 0; i < goodsInfoFromServer.getData().size(); i++){
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setId(goodsInfoFromServer.getData().get(i).getGoodsId());
            goodsInfo.setName(goodsInfoFromServer.getData().get(i).getGoodsName());
            goodsInfo.setDes(goodsInfoFromServer.getData().get(i).getGoodsDes());
            goodsInfo.setImg(goodsInfoFromServer.getData().get(i).getGoodsImg());
            goodsInfo.setPrice(goodsInfoFromServer.getData().get(i).getGoodsPrice());
            list.add(goodsInfo);
        }
        view.getGoodsSuccess(list);
    }

    public void getGoodsFailure(){
        view.getGoodsFailure();
    }

    public void noGoods(){
        view.noGoods();
    }

    public void deleteSuccess(){
        view.deleteSuccess();
    }

    public void deleteFailure(){
        view.deleteFailure();
    }

    public void deleteGoods(int id){
        manager.deleteGoods(id);
    }
}
