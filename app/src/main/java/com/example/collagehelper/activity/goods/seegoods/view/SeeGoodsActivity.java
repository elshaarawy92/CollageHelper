package com.example.collagehelper.activity.goods.seegoods.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.collagehelper.R;
import com.example.collagehelper.activity.goods.seegoods.presenter.SeeGoodsPresenter;
import com.example.collagehelper.adapter.GoodsInfoAdapter;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.bean.GoodsInfo;

import java.util.ArrayList;
import java.util.List;

public class SeeGoodsActivity extends BaseActivity implements ISeeGoodsView {

    private RecyclerView rvSeeGoods;
    private GoodsInfoAdapter adapter;
    private LinearLayoutManager manager;

    private SeeGoodsPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_goods);
        hideActionBar();
        setTittle("管理已上架商品");
        init();
    }

    private void init(){
        rvSeeGoods = findViewById(R.id.rv_see_goods);
        presenter = new SeeGoodsPresenter(this);
        presenter.getGoods(phone);
    }

    @Override
    public void getGoodsSuccess(List<GoodsInfo> list) {
        adapter = new GoodsInfoAdapter(list,SeeGoodsActivity.this);
        manager = new LinearLayoutManager(this);
        rvSeeGoods.setAdapter(adapter);
        rvSeeGoods.setLayoutManager(manager);
    }

    @Override
    public void getGoodsFailure() {
        Toast.makeText(SeeGoodsActivity.this,"获取商品列表失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noGoods() {
        Toast.makeText(SeeGoodsActivity.this,"列表中没有商品",Toast.LENGTH_SHORT).show();
    }
}
