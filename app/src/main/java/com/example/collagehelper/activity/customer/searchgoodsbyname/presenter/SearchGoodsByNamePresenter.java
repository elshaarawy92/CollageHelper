package com.example.collagehelper.activity.customer.searchgoodsbyname.presenter;

import com.example.collagehelper.activity.customer.searchgoodsbyname.manager.SearchGoodsByNameManager;
import com.example.collagehelper.activity.customer.searchgoodsbyname.view.ISearchGoodsByNameView;
import com.example.collagehelper.bean.GoodsInfo2;
import com.example.collagehelper.bean.GoodsInfoFromServer;

import java.util.ArrayList;
import java.util.List;


public class SearchGoodsByNamePresenter {
    private ISearchGoodsByNameView view;
    private SearchGoodsByNameManager manager;

    public SearchGoodsByNamePresenter(ISearchGoodsByNameView view){
        this.view = view;
        manager = new SearchGoodsByNameManager(this);
    }

    public void getGoodsByName(String name){
        manager.getGoodsByName(name);
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

    public void getGoodsNull(){
        view.getGoodsNull();
    }

    public void getGoodsFailure(){
        view.getGoodsFailure();
    }
}
