package com.example.collagehelper.activity.customer.searchgoodsbyname.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.collagehelper.R;
import com.example.collagehelper.activity.customer.goodsdetails.view.GoodsDetailActivity;
import com.example.collagehelper.activity.customer.searchgoodsbyname.presenter.SearchGoodsByNamePresenter;
import com.example.collagehelper.adapter.GoodsByNameAdapter;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.bean.GoodsInfo2;

import java.util.List;

public class SearchGoodsByNameActivity extends BaseActivity implements ISearchGoodsByNameView{

    private SearchGoodsByNamePresenter presenter;
    private String name;
    private RecyclerView recyclerView;
    private GoodsByNameAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods_by_name);
        hideActionBar();
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        setTittle(name);
        recyclerView = findViewById(R.id.rv_goods_by_name);
        presenter = new SearchGoodsByNamePresenter(this);
        presenter.getGoodsByName(name);
    }

    @Override
    public void getGoodsSuccess(List<GoodsInfo2> list) {
        adapter = new GoodsByNameAdapter(list,SearchGoodsByNameActivity.this);
        manager = new LinearLayoutManager(SearchGoodsByNameActivity.this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GoodsByNameAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int id) {
                Intent intent = new Intent(SearchGoodsByNameActivity.this,GoodsDetailActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position, int id) {

            }
        });
    }

    @Override
    public void getGoodsNull() {

    }

    @Override
    public void getGoodsFailure() {

    }
}
