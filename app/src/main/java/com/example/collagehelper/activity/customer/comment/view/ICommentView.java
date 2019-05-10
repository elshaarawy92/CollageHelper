package com.example.collagehelper.activity.customer.comment.view;

import com.example.collagehelper.bean.Comment;
import com.example.collagehelper.bean.User;

import java.util.List;

public interface ICommentView {
    void addSuccess();
    void addFailure();
    void getSuccess(List<Comment> list);
    void getFailure();
    void getUserSuccess(User user);
}
