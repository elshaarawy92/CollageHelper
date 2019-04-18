package com.example.collagehelper.activity.customer.searchgoodsbyname.view;

import com.example.collagehelper.bean.GoodsInfo2;
import com.example.collagehelper.bean.GoodsInfoFromServer;

import java.util.List;

public interface ISearchGoodsByNameView {
    void getGoodsSuccess(List<GoodsInfo2> list);
    void getGoodsNull();
    void getGoodsFailure();
}
