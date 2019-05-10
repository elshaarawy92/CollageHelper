package com.example.collagehelper.activity.seller.fragment.manager;

import com.example.collagehelper.activity.seller.fragment.presenter.MessagePresenter;
import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.bean.ChatDO;
import com.example.collagehelper.bean.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageManager extends BaseManager {
    private MessagePresenter presenter;

    public MessageManager(MessagePresenter presenter){
        this.presenter = presenter;
    }

    public void get(String sellerPhone){
        Call<List<ChatDO>> call = chatAsk.getFromChatBySPhone(sellerPhone);
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
