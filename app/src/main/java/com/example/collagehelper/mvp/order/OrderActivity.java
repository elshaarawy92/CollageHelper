package com.example.collagehelper.mvp.order;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collagehelper.MyClickListener;
import com.example.collagehelper.R;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.bean.GoodsEvent;
import com.example.collagehelper.mvp.goods.GoodsActivity;
import com.example.collagehelper.mvp.selectcustomer.SelectCustomerActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 新增订单页面
 * Created by liang on 2018/11/14
 */
public class OrderActivity extends BaseActivity{

    private final static String TAG = "OrderActivity";

    private List<GoodsEvent> goodsEventList;
    private double accountMoney;
    private LinearLayout llSelectGoods;
    private LinearLayout llSelectCustomer;
    private TextView tvCustomer;
    private TextView tvGoods;
    private TextView tvTime;
    private TextView tvAccountMoney;
    private Button bSure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        hideActionBar();
        setTittle("新增订单");
        initView();
        clickEvent();
    }


    /**
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        initGoodsTextview();
    }**/

    //实例化控件
    private void initView(){
        llSelectGoods = findViewById(R.id.ll_select_goods);
        llSelectCustomer = findViewById(R.id.ll_select_customer);
        tvTime = findViewById(R.id.tv_time);
        bSure = findViewById(R.id.btn_sure);
        tvCustomer = findViewById(R.id.tv_itemm_customer);
        tvGoods = findViewById(R.id.tv_item_goods);
        tvAccountMoney = findViewById(R.id.tv_account_money);
        goodsEventList = new ArrayList<>();
    }

    //给展示商品的Textview添加内容
    private void initGoodsTextview(Intent intent){
        goodsEventList = (List<GoodsEvent>) intent.getSerializableExtra("goodslist");
        accountMoney = intent.getDoubleExtra("accountmoney",0d);
        tvAccountMoney.setText(String.valueOf(accountMoney) + "元");
        if (goodsEventList != null){
            for (int i = 0; i < goodsEventList.size(); i++){
                tvGoods.append(goodsEventList.get(i).name + "   " + goodsEventList.get(i).account + "斤  " + goodsEventList.get(i).accountPrice + "元" + "\n");
            }
        }
    }

    //点击事件
    private void clickEvent(){
        setLeftClick(new MyClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        proxyOnClickListener(2, llSelectGoods, new MyClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this,GoodsActivity.class);
                startActivityForResult(intent,1);
            }
        });
        proxyOnClickListener(2, llSelectCustomer, new MyClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this,SelectCustomerActivity.class);
                startActivityForResult(intent,2);
            }
        });
        proxyOnClickListener(2, bSure, new MyClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == 1){
                    tvGoods.setText("");
                    initGoodsTextview(data);
                }
                break;
        }
    }
}
