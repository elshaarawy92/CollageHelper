package com.example.collagehelper.activity.customer.sellerdetails.view;

import com.example.collagehelper.bean.GoodsInfo2;
import com.example.collagehelper.bean.User;

import java.util.List;

public interface ISellerDetailsView {
    void getGoodsSuccess(List<GoodsInfo2> list);
    void getGoodsFailure();
    void getSellerSuccess(User user);
}
