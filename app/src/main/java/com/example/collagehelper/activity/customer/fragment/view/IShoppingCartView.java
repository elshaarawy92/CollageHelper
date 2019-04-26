package com.example.collagehelper.activity.customer.fragment.view;

import com.example.collagehelper.bean.GoodsAllInfo;
import com.example.collagehelper.bean.ShoppingCartInfo;

import java.util.List;

public interface IShoppingCartView {
    void getFromCartSuccess(List<ShoppingCartInfo> info);
    void getFromCartNull();
    void getFromCartFailure();
    void getGoodsSuccess(GoodsAllInfo goodsAllInfo);
    void getGoodsFailure();
    void deleteSuccess();
    void deleteFailure();
}
