package com.example.collagehelper.activity.customer.goodsdetails.view;

import com.example.collagehelper.bean.CGDO;
import com.example.collagehelper.bean.GoodsAllInfo;

import java.util.List;

public interface IGoodsDetailView {
    void getGoodsSuccess(GoodsAllInfo goodsAllInfo);
    void getGoodsFailure();
    void addToCatSuccess();
    void addToCartFailure();
    void addOrderSuccess();
    void addOrderFailure();
    void addAssembleSuccess();
    void addAssembleFailure();
    void addCgSuccess();
    void addCgFailure();
    void getCgSuccess(List<CGDO> list);
    void getCgFailure();
}
