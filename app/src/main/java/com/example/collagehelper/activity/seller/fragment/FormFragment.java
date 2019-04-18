package com.example.collagehelper.activity.seller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.collagehelper.MyClickListener;
import com.example.collagehelper.R;
import com.example.collagehelper.adapter.DepthPageTransformer;
import com.example.collagehelper.adapter.MyPager;
import com.example.collagehelper.base.BaseFragment;
import com.example.collagehelper.activity.seller.order.OrderActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liang on 2018/10/28
 * 订单页面
 */
public class FormFragment extends BaseFragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<View> viewList;
    private TextView tvAddOrder;
    private TextView tvChangeOrder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form,container,false);
        initView(view);
        viewList = new ArrayList<>();
        initList();
        initViewPager();
        clickEvent();
        return view;
    }

    //点击事件
    private void clickEvent() {
        proxyOnClickListener(2, tvAddOrder, new MyClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),OrderActivity.class);
                startActivity(intent);
            }
        });
        proxyOnClickListener(2, tvChangeOrder, new MyClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    //初始化Viewpager
    private void initViewPager() {
        viewPager.setAdapter(new MyPager(viewList));
        viewPager.setPageTransformer(true,new DepthPageTransformer());
        tabLayout.setupWithViewPager(viewPager);
    }

    //初始化list
    private void initList() {
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.layout_viewpager1,null);
        View view2 = LayoutInflater.from(getContext()).inflate(R.layout.layout_viewpager2,null);
        viewList.add(view1);
        viewList.add(view2);
    }

    //实例化控件
    private void initView(View view){
        viewPager = view.findViewById(R.id.viewpager);
        tabLayout = view.findViewById(R.id.tablayout);
        tvAddOrder = view.findViewById(R.id.tv_add_form);
        tvChangeOrder = view.findViewById(R.id.tv_change_form);
    }
}
