package com.example.collagehelper.activity.customer.comment.manager;

import com.example.collagehelper.activity.customer.comment.presenter.CommentPresenter;
import com.example.collagehelper.base.BaseManager;
import com.example.collagehelper.bean.Comment;
import com.example.collagehelper.bean.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentManager extends BaseManager {
    private CommentPresenter presenter;

    public CommentManager(CommentPresenter presenter){
        this.presenter = presenter;
    }

    public void getComment(int goodsId){
        Call<List<Comment>> call = commentAsk.getFromComment(goodsId);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                presenter.getSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                presenter.getFailure();
            }
        });
    }

    public void addToComment(String comment,String phone,int goodsId,String time){
        Call<ResponseBody> call = commentAsk.addToComment(comment,phone,goodsId,time);
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

    public void getUser(String phone){
        Call<User> call = ask.getUser(phone);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                presenter.getUserSuccess(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
