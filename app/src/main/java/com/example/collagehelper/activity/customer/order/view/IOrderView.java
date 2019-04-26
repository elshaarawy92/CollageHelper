package com.example.collagehelper.activity.customer.order.view;

import com.example.collagehelper.bean.GoodsAllInfo;
import com.example.collagehelper.bean.Order;

import java.util.List;

public interface IOrderView {
    void getOrderSuccess(List<Order> list);
    void getOrderFailure();
    void getGoodsSuccess(GoodsAllInfo goodsAllInfo);
    void getGoodsFailure();
}
