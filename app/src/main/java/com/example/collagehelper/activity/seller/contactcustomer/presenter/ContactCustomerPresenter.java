package com.example.collagehelper.activity.seller.contactcustomer.presenter;

import com.example.collagehelper.activity.seller.contactcustomer.manager.ContactCustomerManager;
import com.example.collagehelper.activity.seller.contactcustomer.view.IContactCustomerView;
import com.example.collagehelper.bean.ChatDO;
import com.example.collagehelper.bean.User;

import java.util.List;

public class ContactCustomerPresenter {
    private IContactCustomerView view;
    private ContactCustomerManager manager;

    public ContactCustomerPresenter(IContactCustomerView view){
        this.view = view;
        manager = new ContactCustomerManager(this);
    }

    public void getSuccess(List<ChatDO> list){
        view.getSuccess(list);
    }

    public void getFailure(){
        view.getFailure();
    }

    public void addSuccess(){
        view.addSuccess();
    }

    public void addFailure(){
        view.addFailure();
    }

    public void get(String customerPhone,String sellerPhone){
        manager.get(customerPhone,sellerPhone);
    }

    public void add(String customerPhone,String sellerPhone,String message,String time,String start){
        manager.add(customerPhone,sellerPhone,message,time,start);
    }

    public void getSellerByPhone(String phone){
        manager.getSellerByPhone(phone);
    }

    public void getSellerSuccess(User user){
        view.getSellerSuccess(user);
    }
}
