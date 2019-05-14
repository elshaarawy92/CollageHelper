package com.example.collagehelper.face.view;

import com.example.collagehelper.bean.User;
import com.example.collagehelper.bean.UserDO;

public interface IRARAView {
    void insertSuccess();
    void selectSuccess(UserDO userDO);
    void getSuccess(User user);
    void getNull();
}
