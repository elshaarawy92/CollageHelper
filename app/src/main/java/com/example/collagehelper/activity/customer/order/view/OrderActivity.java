package com.example.collagehelper.activity.customer.order.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.collagehelper.R;
import com.example.collagehelper.activity.customer.order.presenter.OrderPresenter;
import com.example.collagehelper.adapter.OrderAdapter;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.bean.GoodsAllInfo;
import com.example.collagehelper.bean.Order;
import com.example.collagehelper.bean.OrderAdapterBean1;
import com.example.collagehelper.bean.OrderAdapterBean2;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends BaseActivity implements IOrderView{

    private RecyclerView rvOrder;
    private OrderPresenter presenter;
    private List<OrderAdapterBean1> bList1 = new ArrayList<>();
    private List<OrderAdapterBean2> bList2 = new ArrayList<>();
    private List<Integer> iList = new ArrayList<>();
    OrderAdapterBean1 orderAdapterBean1;
    OrderAdapterBean2 orderAdapterBean2;
    private OrderAdapter adapter;
    private LinearLayoutManager manager;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order2);
        hideActionBar();
        setTittle("订单");
        rvOrder = findViewById(R.id.rv_order);
        presenter = new OrderPresenter(this);
        presenter.getOrder(BaseActivity.phone);
    }

    @Override
    public void getOrderSuccess(List<Order> list) {
        for (int i = 0;i < list.size();i++){
            orderAdapterBean1 = new OrderAdapterBean1();
            orderAdapterBean1.setId(list.get(i).getId());
            orderAdapterBean1.setAccount(list.get(i).getGoodsCount() + "");
            orderAdapterBean1.setTime(list.get(i).getTime());
            orderAdapterBean1.setTotal(list.get(i).getMoney());
            orderAdapterBean1.setGoodsId(list.get(i).getGoodsId());
            bList1.add(orderAdapterBean1);
            iList.add(list.get(i).getGoodsId());
        }
        getGoods(iList);
    }

    private void getGoods(List<Integer> list){
        presenter.getGodsById(list.get(i));
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
        bList2.add(orderAdapterBean2);
        if (i != bList1.size()){
            getGoods(iList);
            return;
        }
        adapter = new OrderAdapter(bList1,bList2,OrderActivity.this);
        manager = new LinearLayoutManager(OrderActivity.this);
        rvOrder.setAdapter(adapter);
        rvOrder.setLayoutManager(manager);
    }

    @Override
    public void getGoodsFailure() {

    }

    @Override
    protected void onStop() {
        super.onStop();
        bList1.clear();
        bList2.clear();
        rvOrder.setAdapter(null);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.getOrder(BaseActivity.phone);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bList1.clear();
        bList2.clear();
        rvOrder.setAdapter(null);
    }
}
