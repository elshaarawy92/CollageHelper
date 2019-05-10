package com.example.collagehelper.activity.customer.comment.presenter;

import com.example.collagehelper.activity.customer.comment.manager.CommentManager;
import com.example.collagehelper.activity.customer.comment.view.ICommentView;
import com.example.collagehelper.bean.Comment;
import com.example.collagehelper.bean.User;

import java.util.List;

public class CommentPresenter {
    private CommentManager manager;
    private ICommentView view;

    public CommentPresenter(ICommentView view){
        this.view = view;
        manager = new CommentManager(this);
    }

    public void getSuccess(List<Comment> list){
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

    public void get(int goodsId){
        manager.getComment(goodsId);
    }

    public void add(String comment,String phone,int goodsId,String time){
        manager.addToComment(comment,phone,goodsId,time);
    }

    public void getUserByPhone(String phone){
        manager.getUser(phone);
    }

    public void getUserSuccess(User user){
        view.getUserSuccess(user);
    }
}
