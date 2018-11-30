package com.example.collagehelper.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.collagehelper.R;
import com.example.collagehelper.adapter.MyAdapter;
import com.example.collagehelper.base.BaseFragment;
import com.example.collagehelper.bean.Function;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liang on 2018/10/28
 * 我的界面
 */
public class MyFragment extends BaseFragment {

    private List<Function> list;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private LinearLayoutManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my,container,false);
        list = new ArrayList<>();
        initList();
        recyclerView = view.findViewById(R.id.my_recyclerview);
        adapter = new MyAdapter(list);
        manager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        ClickEvent();
        return view;
    }

    //recyclerview的item的点击事件
    private void ClickEvent() {
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    //初始化list
    private void initList() {
        list.add(new Function(R.drawable.customer_manage,"客户管理"));
        list.add(new Function(R.drawable.thing_manage,"商品管理"));
        list.add(new Function(R.drawable.form_manage,"订单管理"));
        list.add(new Function(R.drawable.statistics_manage,"统计管理"));
        list.add(new Function(R.drawable.head_setting,"头像设置"));
    }
}
