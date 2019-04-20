package com.example.collagehelper.activity.customer.sellerdetails.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.example.collagehelper.R;
import com.example.collagehelper.activity.customer.goodsdetails.view.GoodsDetailActivity;
import com.example.collagehelper.activity.customer.searchgoodsbyname.view.SearchGoodsByNameActivity;
import com.example.collagehelper.activity.customer.sellerdetails.presenter.SellerDetailsPresenter;
import com.example.collagehelper.adapter.GoodsByNameAdapter;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.bean.GoodsInfo2;
import com.example.collagehelper.bean.User;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SellerDetailsActivity extends BaseActivity implements ISellerDetailsView {
    private Intent intent;
    private String sellerPhone;
    private SellerDetailsPresenter presenter;
    private CircleImageView civSellerHead;
    private TextView tvSellerName;
    private RecyclerView rvSellerGoods;
    private GoodsByNameAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_details);
        hideActionBar();
        setTittle("店家详情");
        intent = getIntent();
        sellerPhone = intent.getStringExtra("sellerphone");
        presenter = new SellerDetailsPresenter(this);
        initView();
        presenter.getSellerByPhone(sellerPhone);
        presenter.getGoodsByPhone(sellerPhone);
    }

    private void initView(){
        civSellerHead = findViewById(R.id.civ_seller_head);
        tvSellerName = findViewById(R.id.tv_seller_name);
        rvSellerGoods = findViewById(R.id.rv_seller_goods);
    }

    @Override
    public void getGoodsSuccess(List<GoodsInfo2> list) {
        adapter = new GoodsByNameAdapter(list,SellerDetailsActivity.this);
        manager = new LinearLayoutManager(SellerDetailsActivity.this);
        rvSellerGoods.setLayoutManager(manager);
        rvSellerGoods.setAdapter(adapter);
    }

    @Override
    public void getGoodsFailure() {

    }

    @Override
    public void getSellerSuccess(User user) {
        tvSellerName.setText(user.getData().getName());
        String updateTime = String.valueOf(System.currentTimeMillis()); // 在需要重新获取更新的图片时调用
        Glide.with(SellerDetailsActivity.this).load(user.getData().getHead())
                .signature(new ObjectKey(updateTime))
                .into(civSellerHead);
    }
}
