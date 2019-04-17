package com.example.collagehelper.activity.seller.main.presenter;

import com.example.collagehelper.activity.seller.main.manager.MainManager;
import com.example.collagehelper.activity.seller.main.view.IMainView;

public class MainPresenter {
    private IMainView mainView;
    private MainManager mainManager;

    public MainPresenter(IMainView iMainView){
        this.mainView = iMainView;
        mainManager = new MainManager(this);
    }

    public void getUserInfo(String phone){
        mainManager.getUserInfo(phone);
    }

    public void getUserInoSuccess(String name,String headUrl){
        mainView.getUserInfoSuccess(name,headUrl);
    }

    public void getUserInfoFailure(){
        mainView.getUserInfoFaiure();
    }
}
