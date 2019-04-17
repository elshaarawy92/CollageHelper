package com.example.collagehelper.activity.goods;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.collagehelper.MyClickListener;
import com.example.collagehelper.R;
import com.example.collagehelper.adapter.GoodsAdapter;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.bean.GoodsAdapterBean;
import com.example.collagehelper.bean.GoodsEvent;
import com.example.collagehelper.activity.order.OrderActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品详情页面
 * Create by liang on 2018/11/14
 */
public class GoodsActivity extends BaseActivity {

    private double accountMoney;

    private List<GoodsEvent> goodsEventList;
    private List<GoodsEvent> goodsEventList2;
    private RecyclerView goodsRecyclerview;
    private Button bSure;
    private List<GoodsAdapterBean> list;
    private GoodsAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        hideActionBar();
        setTittle("商品");
        initView();
        initRecyclerView();
        clickEvent();
    }

    //点击事件
    private void clickEvent() {

        setLeftClick(new MyClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        proxyOnClickListener(2, bSure, new MyClickListener() {
            @Override
            public void onClick(View view) {
                if (goodsEventList.size() != 0){
                    String goodsName = goodsEventList.get(goodsEventList.size() - 1).name;
                    goodsEventList2.add(goodsEventList.get(goodsEventList.size() - 1));
                    for (int i = goodsEventList.size() - 2; i >= 0; i--){
                        for (int j = 0; j < goodsEventList2.size(); j++){
                            if (goodsEventList.get(i).name.equals(goodsEventList2.get(j).name)){
                                goodsEventList.remove(i);
                                break;
                            }
                        }
                        goodsEventList2.add(goodsEventList.get(i));
                    }
                    goodsEventList2.clear();
                    for (int i = 0; i < goodsEventList.size(); i++){
                        accountMoney = accountMoney + goodsEventList.get(i).getAccountPrice();
                    }
                    Intent intent = new Intent();
                    intent.putExtra("goodslist", (Serializable) goodsEventList);
                    intent.putExtra("accountmoney",accountMoney);
                    setResult(1,intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(GoodsActivity.this,OrderActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    //初始化Recyclerview
    private void initRecyclerView() {
        list = new ArrayList<>();
        initList();
        adapter = new GoodsAdapter(list,GoodsActivity.this);
        manager = new LinearLayoutManager(this);
        goodsRecyclerview.setLayoutManager(manager);
        goodsRecyclerview.setAdapter(adapter);
    }

    //初始化list
    private void initList() {
        list.add(new GoodsAdapterBean(R.drawable.apple,"苹果","5"));
        list.add(new GoodsAdapterBean(R.drawable.banana,"香蕉","3"));
        list.add(new GoodsAdapterBean(R.drawable.grapes,"葡萄","8"));
        list.add(new GoodsAdapterBean(R.drawable.pear,"梨","6"));
        list.add(new GoodsAdapterBean(R.drawable.orange,"橘子","9"));
        list.add(new GoodsAdapterBean(R.drawable.mango,"芒果","11"));
    }

    //实例化控件
    private void initView(){
        goodsRecyclerview = findViewById(R.id.rv_goods);
        bSure = findViewById(R.id.btn_sure);
        goodsEventList = new ArrayList<>();
        goodsEventList2 = new ArrayList<>();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEvent(GoodsEvent goodsEvent){
        goodsEventList.add(goodsEvent);
    }

    private boolean compareName(String name){
        for(int i = 0; i < goodsEventList2.size(); i++){
            if (name.equals(goodsEventList2.get(i).name)){
                break;
            }
        }
        return false;
    }

}
