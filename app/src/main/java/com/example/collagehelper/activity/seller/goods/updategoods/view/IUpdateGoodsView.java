package com.example.collagehelper.activity.seller.goods.updategoods.view;

import com.example.collagehelper.bean.GoodsAllInfo;

public interface IUpdateGoodsView {
    void getGoodsSuccess(GoodsAllInfo goodsAllInfo);
    void getGoodsFailure();
    void updateGoodsSuccess();
    void updateGoodsFailure();
}
