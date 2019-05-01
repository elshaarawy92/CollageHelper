package com.example.collagehelper.activity.seller.fragment.view;

import com.example.collagehelper.bean.GoodsAllInfo;
import com.example.collagehelper.bean.Order;

import java.util.List;

public interface IFormView {
    void getOrderSuccess(List<Order> list);
    void getOrderFailure();
    void getGoodsSuccess(GoodsAllInfo goodsAllInfo);
    void getGoodsFailure();
}
