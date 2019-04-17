package com.example.collagehelper.activity.goods.managegoods;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.collagehelper.MyClickListener;
import com.example.collagehelper.R;
import com.example.collagehelper.activity.goods.addgoods.view.AddGoodsActivity;
import com.example.collagehelper.activity.goods.seegoods.view.SeeGoodsActivity;
import com.example.collagehelper.base.BaseActivity;

/**
 * Created by liang on 2019/04/16
 */
public class ManageGoodsActivity extends BaseActivity {

    private LinearLayout llAddGoods;
    private LinearLayout llSeeGoods;

    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_goods);
        hideActionBar();
        setTittle("商品管理");
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
        initView();
        clickEvent();
    }

    public void initView(){
        llAddGoods = findViewById(R.id.ll_add_goods);
        llSeeGoods = findViewById(R.id.ll_see_goods);
    }

    public void clickEvent(){
        proxyOnClickListener(2, llAddGoods, new MyClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageGoodsActivity.this,AddGoodsActivity.class);
                intent.putExtra("phone",phone);
                startActivity(intent);
            }
        });

        proxyOnClickListener(2, llSeeGoods, new MyClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageGoodsActivity.this,SeeGoodsActivity.class);
                startActivity(intent);
            }
        });
    }

}
