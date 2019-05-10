package com.example.collagehelper.activity.customer.fragment.presenter;

import com.example.collagehelper.activity.customer.fragment.manager.MessageManager;
import com.example.collagehelper.activity.customer.fragment.view.IMessageView;
import com.example.collagehelper.bean.ChatDO;
import com.example.collagehelper.bean.User;

import java.util.List;

public class MessagePresenter {
    private IMessageView view;
    private MessageManager manager;

    public MessagePresenter(IMessageView view){
        this.view = view;
        manager = new MessageManager(this);
    }

    public void getSuccess(List<ChatDO> list){
        view.getSuccess(list);
    }

    public void getFailure(){
        view.getFailure();
    }

    public void get(String customerPhone){
        manager.get(customerPhone);
    }

    public void getSellerByPhone(String phone){
        manager.getSellerByPhone(phone);
    }

    public void getSellerSuccess(User user){
        view.getSellerSuccess(user);
    }
}
