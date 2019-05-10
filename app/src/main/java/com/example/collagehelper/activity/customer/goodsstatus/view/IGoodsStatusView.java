package com.example.collagehelper.activity.customer.goodsstatus.view;

import com.example.collagehelper.bean.GoodsAllInfo;
import com.example.collagehelper.bean.Order;

import java.util.List;

public interface IGoodsStatusView {
    void getSuccess(List<Order> list);
    void getGoodsSuccess(GoodsAllInfo goodsAllInfo);
    void updateSuccess();
}
