package com.example.collagehelper.activity.customer.order.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.collagehelper.R;
import com.example.collagehelper.activity.customer.comment.view.CommentActivity;
import com.example.collagehelper.activity.customer.goodsdetails.view.GoodsDetailActivity;
import com.example.collagehelper.activity.customer.mycollect.view.MyCollectActivity;
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
    private List<OrderAdapterBean1> bList11 = new ArrayList<>();
    private List<OrderAdapterBean2> bList21 = new ArrayList<>();
    private List<Integer> iList = new ArrayList<>();
    private List<String> orderIdList = new ArrayList<>();
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
            orderAdapterBean1.setStatus(list.get(i).getStatus());
            orderIdList.add(list.get(i).getOrderId());
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
        bList11.clear();
        bList21.clear();
        bList11.addAll(bList1);
        bList21.addAll(bList2);
        adapter = new OrderAdapter(bList11,bList21,OrderActivity.this);
        manager = new LinearLayoutManager(OrderActivity.this);
        adapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(OrderActivity.this,GoodsDetailActivity.class);
                intent.putExtra("id",bList1.get(position).getGoodsId());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }

            @Override
            public void onReceiveClick(View view, int position) {
                presenter.updateByOrderId(orderIdList.get(position),"待评价");
            }

            @Override
            public void onCommentClick(View view, int position) {
                Intent intent = new Intent(OrderActivity.this,CommentActivity.class);
                intent.putExtra("goods_id",iList.get(position));
                startActivity(intent);
                presenter.updateByOrderId(orderIdList.get(position),"订单已完成");
            }
        });
        rvOrder.setAdapter(adapter);
        rvOrder.setLayoutManager(manager);
    }

    @Override
    public void getGoodsFailure() {

    }

    @Override
    public void updateSuccess() {
        Toast.makeText(OrderActivity.this,"确认收货成功",Toast.LENGTH_SHORT).show();
        refresh();
    }

    private void refresh(){
        bList1.clear();
        bList2.clear();
        iList.clear();
        orderIdList.clear();
        i = 0;
        presenter.getOrder(BaseActivity.phone);
    }

    @Override
    protected void onStop() {
        super.onStop();
        bList1.clear();
        bList2.clear();
        iList.clear();
        orderIdList.clear();
        rvOrder.setAdapter(null);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        i = 0;
        presenter.getOrder(BaseActivity.phone);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bList1.clear();
        bList2.clear();
        iList.clear();
        orderIdList.clear();
        i = 0;
        rvOrder.setAdapter(null);
    }
}
