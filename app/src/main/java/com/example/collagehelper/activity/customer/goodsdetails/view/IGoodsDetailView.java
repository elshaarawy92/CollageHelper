package com.example.collagehelper.activity.customer.goodsdetails.view;

import com.example.collagehelper.bean.GoodsAllInfo;

public interface IGoodsDetailView {
    void getGoodsSuccess(GoodsAllInfo goodsAllInfo);
    void getGoodsFailure();
    void addToCatSuccess();
    void addToCartFailure();
    void addOrderSuccess();
    void addOrderFailure();
    void addAssembleSuccess();
    void addAssembleFailure();
}
