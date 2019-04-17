package com.example.collagehelper.activity.seller.goods.seegoods.view;

import com.example.collagehelper.activity.seller.bean.GoodsInfo;

import java.util.List;

public interface ISeeGoodsView {
    void getGoodsSuccess(List<GoodsInfo> list);
    void getGoodsFailure();
    void noGoods();
    void deleteSuccess();
    void deleteFailure();
}
