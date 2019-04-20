package com.example.collagehelper.activity.customer.sellerdetails.view;

import com.example.collagehelper.bean.CTSDO;
import com.example.collagehelper.bean.GoodsInfo2;
import com.example.collagehelper.bean.User;

import java.util.List;

public interface ISellerDetailsView {
    void getGoodsSuccess(List<GoodsInfo2> list);
    void getGoodsFailure();
    void getSellerSuccess(User user);
    void collectSuccess();
    void collectFailure();
    void deleteSuccess();
    void deleteFailure();
    void getCollectedSellerSuccess(CTSDO ctsdo);
    void getCollectedSellerFailure();
    void getCollectedSellerNull();
}
