package com.example.collagehelper.activity.customer.goodsdetails.view;

import com.example.collagehelper.bean.GoodsAllInfo;

public interface IGoodsDetailView {
    void getGoodsSuccess(GoodsAllInfo goodsAllInfo);
    void getGoodsFailure();
}
