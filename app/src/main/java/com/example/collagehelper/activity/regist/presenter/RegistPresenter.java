package com.example.collagehelper.activity.regist.presenter;

import com.example.collagehelper.activity.regist.manager.RegistManager;
import com.example.collagehelper.activity.regist.view.IRegistView;

import okhttp3.MultipartBody;

/**
 * Created by liang on 2019/04/13
 */
public class RegistPresenter {
    private IRegistView registView;
    private RegistManager registManager;

    public RegistPresenter(IRegistView registView){
        this.registView = registView;
        registManager = new RegistManager(this);
    }

    public void regist(String username, String phone, String pwd, String type, MultipartBody.Part head){
        registManager.regist(username,phone,pwd,type,head);
    }

    public void checkAccountExists(String phone){
        registManager.checkAccountExsists(phone);
    }

    public void pwdExist(){
        registView.pwdExist();
    }

    public void pwdNotExist(){
        registView.pwdNotExist();
    }

    public void registSuccess(){
        registView.registSuccess();
    }

    public void registFailure(){
        registView.registFailure();
    }
}
