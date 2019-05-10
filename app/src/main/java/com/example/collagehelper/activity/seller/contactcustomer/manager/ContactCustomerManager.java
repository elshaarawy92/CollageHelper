package com.example.collagehelper.activity.seller.contactcustomer.manager;

import com.example.collagehelper.activity.seller.contactcustomer.presenter.ContactCustomerPresenter;
import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.bean.ChatDO;
import com.example.collagehelper.bean.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactCustomerManager extends BaseManager {
    private ContactCustomerPresenter presenter;

    public ContactCustomerManager(ContactCustomerPresenter presenter){
        this.presenter = presenter;
    }

    public void add(String customerPhone,String sellerPhone,String message,String time,String start){
        Call<ResponseBody> call = chatAsk.addToChat(customerPhone,sellerPhone,message,time,start);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                presenter.addSuccess();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                presenter.addFailure();
            }
        });
    }

    public void get(String customerPhone,String sellerPhone){
        Call<List<ChatDO>> call = chatAsk.getFromChat(customerPhone,sellerPhone);
        call.enqueue(new Callback<List<ChatDO>>() {
            @Override
            public void onResponse(Call<List<ChatDO>> call, Response<List<ChatDO>> response) {
                presenter.getSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<ChatDO>> call, Throwable t) {
                presenter.getFailure();
            }
        });
    }

    public void getSellerByPhone(String phone){
        Call<User> call = ask.getUser(phone);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                presenter.getSellerSuccess(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
