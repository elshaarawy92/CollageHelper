package com.example.collagehelper.activity.customer.main.presenter;

import com.example.collagehelper.activity.customer.main.manager.Main2Manager;
import com.example.collagehelper.activity.customer.main.view.IMainView2;
import com.example.collagehelper.bean.User;

public class Main2Presenter {
    private Main2Manager manager;
    private IMainView2 view;

    public Main2Presenter(IMainView2 view){
        this.view = view;
        manager = new Main2Manager(this);
    }

    public void getUser(String phone){
        manager.getUser(phone);
    }

    public void getUserInfoSuccess(User user){
        view.getUserInfoSuccess(user);
    }
}
