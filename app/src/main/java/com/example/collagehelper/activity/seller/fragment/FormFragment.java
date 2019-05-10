package com.example.collagehelper.activity.seller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collagehelper.MyClickListener;
import com.example.collagehelper.R;
import com.example.collagehelper.activity.seller.fragment.presenter.FormPresenter;
import com.example.collagehelper.activity.seller.fragment.view.IFormView;
import com.example.collagehelper.adapter.DepthPageTransformer;
import com.example.collagehelper.adapter.FormAdapter;
import com.example.collagehelper.adapter.MyPager;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.base.BaseFragment;
import com.example.collagehelper.activity.seller.order.OrderActivity;
import com.example.collagehelper.bean.GoodsAllInfo;
import com.example.collagehelper.bean.Order;
import com.example.collagehelper.bean.OrderAdapterBean2;
import com.example.collagehelper.bean.OrderAdapterBean3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liang on 2018/10/28
 * 订单页面
 */
public class FormFragment extends BaseFragment implements IFormView {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<View> viewList;
    private RecyclerView rvForm;
    private RecyclerView rvForm2;
    private FormPresenter presenter;
    private List<OrderAdapterBean3> list3 = new ArrayList<>();
    private List<OrderAdapterBean2> list2 = new ArrayList<>();
    private List<OrderAdapterBean3> list31 = new ArrayList<>();
    private List<OrderAdapterBean2> list21 = new ArrayList<>();
    private List<Integer> integerList = new ArrayList<>();
    OrderAdapterBean3 orderAdapterBean3;
    OrderAdapterBean2 orderAdapterBean2;
    private FormAdapter adapter;
    private LinearLayoutManager manager;
    private LinearLayoutManager manager2;
    private List<String> orderIdList = new ArrayList<>();
    private int i = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form,container,false);
        initView(view);
        viewList = new ArrayList<>();
        presenter = new FormPresenter(this);
        initList();
        initViewPager();
        clickEvent();
        return view;
    }

    private void clickEvent() {
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
        rvForm = view1.findViewById(R.id.rv_form);
        rvForm2 = view2.findViewById(R.id.rv_form2);
        viewList.add(view1);
        viewList.add(view2);
        presenter.getForm(BaseActivity.phone);
    }

    //实例化控件
    private void initView(View view){
        viewPager = view.findViewById(R.id.viewpager);
        tabLayout = view.findViewById(R.id.tablayout);
    }

    @Override
    public void getOrderSuccess(List<Order> list) {
        for (int i = 0;i < list.size();i++){
            orderAdapterBean3 = new OrderAdapterBean3();
            orderAdapterBean3.setId(list.get(i).getId());
            orderAdapterBean3.setAccount(list.get(i).getGoodsCount() + "");
            orderAdapterBean3.setTime(list.get(i).getTime());
            orderAdapterBean3.setTotal(list.get(i).getMoney());
            orderAdapterBean3.setGoodsId(list.get(i).getGoodsId());
            orderAdapterBean3.setCustomerPhone(list.get(i).getCustomerPhone());
            orderAdapterBean3.setStatus(list.get(i).getStatus());
            orderIdList.add(list.get(i).getOrderId());
            list3.add(orderAdapterBean3);
            integerList.add(list.get(i).getGoodsId());
        }
        getGoods(integerList);
    }

    private void getGoods(List<Integer> list){
        presenter.getGoodsDetail(list.get(i));
        i++;
    }

    @Override
    public void getOrderFailure() {

    }

    @Override
    public void getGoodsSuccess(GoodsAllInfo goodsAllInfo) {
        orderAdapterBean2 = new OrderAdapterBean2();
        orderAdapterBean2.setImg(goodsAllInfo.getData().getGoodsImg());
        orderAdapterBean2.setName(goodsAllInfo.getData().getGoodsName());
        orderAdapterBean2.setPrice(goodsAllInfo.getData().getGoodsPrice());
        list2.add(orderAdapterBean2);
        if (i != list3.size()){
            getGoods(integerList);
            return;
        }
        list31.clear();
        list21.clear();
        list31.addAll(list3);
        list21.addAll(list2);
        adapter = new FormAdapter(list31,list21,getContext());
        adapter.setOnItemClickListener(new FormAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }

            @Override
            public void onButtonClick(View view, int position) {
                presenter.updateByOrderId(orderIdList.get(position),"待收货");
            }
        });
        manager = new LinearLayoutManager(getContext());
        manager2 = new LinearLayoutManager(getContext());
        rvForm.setAdapter(adapter);
        rvForm.setLayoutManager(manager);
        rvForm2.setAdapter(adapter);
        rvForm2.setLayoutManager(manager2);
    }

    @Override
    public void getGoodsFailure() {

    }

    @Override
    public void updateSuccess() {
        Toast.makeText(getContext(),"发货成功",Toast.LENGTH_SHORT).show();
        refresh();
    }

    private void refresh(){
        list3.clear();
        list2.clear();
        integerList.clear();
        orderIdList.clear();
        i = 0;
        presenter.getForm(BaseActivity.phone);
    }

    @Override
    public void updateFailure() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        list3.clear();
        list2.clear();
        integerList.clear();
        orderIdList.clear();
        i = 0;
        rvForm.setAdapter(null);
        rvForm2.setAdapter(null);
    }
}
