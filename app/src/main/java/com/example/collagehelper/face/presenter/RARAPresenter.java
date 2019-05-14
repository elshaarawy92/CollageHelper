package com.example.collagehelper.face.presenter;

import com.example.collagehelper.bean.User;
import com.example.collagehelper.bean.UserDO;
import com.example.collagehelper.face.manager.RARAManager;
import com.example.collagehelper.face.view.IRARAView;

public class RARAPresenter {
    private IRARAView view;
    private RARAManager manager;

    public RARAPresenter(IRARAView view){
        this.view = view;
        manager = new RARAManager(this);
    }

    public void insertSuccess(){
        view.insertSuccess();
    }

    public void selectSuccess(UserDO userDO){
        view.selectSuccess(userDO);
    }

    public void insertFace(String phone,String face){
        manager.insert(phone,face);
    }

    public void select(String phone,String face){
        manager.selectByPhoneAndFace(phone,face);
    }

    public void getSuccess(User user){
        view.getSuccess(user);
    }

    public void getNull(){
        view.getNull();
    }

    public void getUser(String phone){
        manager.selectByPhone(phone);
    }
}
