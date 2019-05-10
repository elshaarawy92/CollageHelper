package com.example.collagehelper.activity.customer.goodsstatus.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.collagehelper.R;
import com.example.collagehelper.activity.customer.comment.view.CommentActivity;
import com.example.collagehelper.activity.customer.goodsdetails.view.GoodsDetailActivity;
import com.example.collagehelper.activity.customer.goodsstatus.presenter.GoodsStatusPresenter;
import com.example.collagehelper.activity.customer.order.view.IOrderView;
import com.example.collagehelper.activity.customer.order.view.OrderActivity;
import com.example.collagehelper.adapter.OrderAdapter;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.bean.GoodsAllInfo;
import com.example.collagehelper.bean.Order;
import com.example.collagehelper.bean.OrderAdapterBean1;
import com.example.collagehelper.bean.OrderAdapterBean2;

import java.util.ArrayList;
import java.util.List;

public class GoodsStatusActivity extends BaseActivity implements IGoodsStatusView{
    private RecyclerView rvStatusOrder;
    private GoodsStatusPresenter presenter;
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
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_status);
        hideActionBar();
        rvStatusOrder = findViewById(R.id.rv_status_order);
        Intent intent = getIntent();
        status = intent.getStringExtra("status");
        setTittle(status);
        presenter = new GoodsStatusPresenter(this);
        presenter.getOrder(status,phone);
    }

    @Override
    public void getSuccess(List<Order> list) {
        if (list.size() == 0){
            rvStatusOrder.setAdapter(null);
            return;
        }
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
        adapter = new OrderAdapter(bList11,bList21,GoodsStatusActivity.this);
        manager = new LinearLayoutManager(GoodsStatusActivity.this);
        adapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(GoodsStatusActivity.this,GoodsDetailActivity.class);
                intent.putExtra("id",bList1.get(position).getGoodsId());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }

            @Override
            public void onReceiveClick(View view, int position) {
                presenter.updateByOrderId(orderIdList.get(position),"待评价");
                refresh();
            }

            @Override
            public void onCommentClick(View view, int position) {
                presenter.updateByOrderId(orderIdList.get(position),"订单已完成");
                refresh();
                Intent intent = new Intent(GoodsStatusActivity.this,CommentActivity.class);
                intent.putExtra("goods_id",iList.get(position));
                startActivity(intent);
            }
        });
        rvStatusOrder.setAdapter(adapter);
        rvStatusOrder.setLayoutManager(manager);
    }

    @Override
    public void updateSuccess() {
        Toast.makeText(GoodsStatusActivity.this,"确认收货成功",Toast.LENGTH_SHORT).show();
    }

    private void refresh(){
        bList1.clear();
        bList2.clear();
        iList.clear();
        orderIdList.clear();
        i = 0;
        presenter.getOrder(status,phone);
    }

    @Override
    protected void onStop() {
        super.onStop();
        bList1.clear();
        bList2.clear();
        iList.clear();
        orderIdList.clear();
        rvStatusOrder.setAdapter(null);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        i = 0;
        presenter.getOrder(status,phone);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bList1.clear();
        bList2.clear();
        iList.clear();
        orderIdList.clear();
        i = 0;
        rvStatusOrder.setAdapter(null);
    }
}
