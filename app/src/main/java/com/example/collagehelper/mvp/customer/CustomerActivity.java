package com.example.collagehelper.mvp.customer;

import android.content.Intent;
import android.os.Bundle;

import com.example.collagehelper.R;
import com.example.collagehelper.base.BaseActivity;

/**
 * 客户个人信息界面
 * Created by liang on 2018/10/30
 */
public class CustomerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        hideActionBar();
        Intent intent = getIntent();
        setTittle(intent.getStringExtra("name"));
    }
}
