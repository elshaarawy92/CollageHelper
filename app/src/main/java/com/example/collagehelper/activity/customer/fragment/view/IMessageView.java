package com.example.collagehelper.activity.customer.fragment.view;

import com.example.collagehelper.bean.ChatDO;
import com.example.collagehelper.bean.User;

import java.util.List;

public interface IMessageView {
    void getSuccess(List<ChatDO> list);
    void getFailure();
    void getSellerSuccess(User user);
}
