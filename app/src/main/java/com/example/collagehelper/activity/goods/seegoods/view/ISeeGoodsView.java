package com.example.collagehelper.activity.goods.seegoods.view;

import com.example.collagehelper.bean.GoodsInfo;

import java.util.List;

public interface ISeeGoodsView {
    void getGoodsSuccess(List<GoodsInfo> list);
    void getGoodsFailure();
    void noGoods();
}
