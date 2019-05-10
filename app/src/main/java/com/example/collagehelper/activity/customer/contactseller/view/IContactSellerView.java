package com.example.collagehelper.activity.customer.contactseller.view;

import com.example.collagehelper.bean.ChatDO;
import com.example.collagehelper.bean.User;

import java.util.List;

public interface IContactSellerView {
    void addSuccess();
    void addFailure();
    void getSuccess(List<ChatDO> list);
    void getFailure();
    void getSellerSuccess(User user);
}
