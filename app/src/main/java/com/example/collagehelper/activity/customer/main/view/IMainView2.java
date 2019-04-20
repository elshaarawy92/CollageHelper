package com.example.collagehelper.activity.customer.main.view;

import com.example.collagehelper.bean.CTSDO;
import com.example.collagehelper.bean.User;

public interface IMainView2 {
    void getUserInfoSuccess(User user);
    void getCollectedSellerSuccess(CTSDO ctsdo);
    void getCollectedSellerFailure();
    void getCollectedSellerNull();
    void getSellerSuccess(User user);
}
