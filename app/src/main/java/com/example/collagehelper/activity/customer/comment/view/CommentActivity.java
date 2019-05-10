package com.example.collagehelper.activity.customer.comment.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.collagehelper.MyClickListener;
import com.example.collagehelper.R;
import com.example.collagehelper.activity.customer.comment.presenter.CommentPresenter;
import com.example.collagehelper.adapter.CommentAdapter;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.bean.Comment;
import com.example.collagehelper.bean.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentActivity extends BaseActivity implements ICommentView{
    private int goodsId;
    private String comment;
    private RecyclerView rvComment;
    private EditText etComment;
    private Button btnComment;
    private CommentPresenter presenter;
    private List<Comment> commentList = new ArrayList<>();
    private List<Comment> commentList2 = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private List<User> userList2 = new ArrayList<>();
    private List<String> phoneList = new ArrayList<>();
    private int i = 0;
    private CommentAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        hideActionBar();
        setTittle("评论");
        Intent intent = getIntent();
        goodsId = intent.getIntExtra("goods_id",-1);
        rvComment = findViewById(R.id.rv_comment);
        etComment = findViewById(R.id.et_comment);
        btnComment = findViewById(R.id.btn_comment);
        presenter = new CommentPresenter(this);
        presenter.get(goodsId);
        proxyOnClickListener(2, btnComment, new MyClickListener() {
            @Override
            public void onClick(View view) {
                comment = etComment.getText().toString();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String time = df.format(new Date());
                presenter.add(comment,phone,goodsId,time);
                etComment.setText(null);
                refresh();
            }
        });
    }

    private void refresh(){
        i = 0;
        commentList.clear();
        userList.clear();
        phoneList.clear();
        presenter.get(goodsId);
    }

    @Override
    public void addSuccess() {
        Toast.makeText(CommentActivity.this,"发布评论成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addFailure() {

    }

    @Override
    public void getSuccess(List<Comment> list) {
        if (list.size() == 0){
            return;
        }
        commentList.addAll(list);
        for (int i = 0; i < commentList.size(); i++){
            phoneList.add(commentList.get(i).getPhone());
        }
        getUser(phoneList);
    }

    private void getUser(List<String> list){
        presenter.getUserByPhone(list.get(i));
        i++;
    }

    @Override
    public void getFailure() {

    }

    @Override
    public void getUserSuccess(User user) {
        userList.add(user);
        if (i != commentList.size()){
            getUser(phoneList);
            return;
        }
        commentList2.clear();
        userList2.clear();
        commentList2.addAll(commentList);
        userList2.addAll(userList);
        adapter = new CommentAdapter(commentList2,userList2,CommentActivity.this);
        manager = new LinearLayoutManager(this);
        rvComment.setAdapter(adapter);
        rvComment.setLayoutManager(manager);
        rvComment.smoothScrollToPosition(adapter.getItemCount() - 1);
    }
}
