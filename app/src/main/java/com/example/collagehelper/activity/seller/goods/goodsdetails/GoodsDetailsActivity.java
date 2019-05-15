package com.example.collagehelper.activity.seller.goods.goodsdetails;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.example.collagehelper.R;
import com.example.collagehelper.activity.seller.goods.updategoods.presenter.UpdateGoodsPresenter;
import com.example.collagehelper.activity.seller.goods.updategoods.view.IUpdateGoodsView;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.bean.GoodsAllInfo;

public class GoodsDetailsActivity extends BaseActivity implements IUpdateGoodsView {

    private ImageView ivImg;
    private TextView tvName;
    private TextView tvPrice;
    private TextView tvDes;

    private int id;

    private UpdateGoodsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        hideActionBar();
        setTittle("商品详情");
        initView();
        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);
        presenter = new UpdateGoodsPresenter(this);
        presenter.getGoods(id);
    }

    public void initView(){
        ivImg = findViewById(R.id.iv_goods_detail_img);
        tvName = findViewById(R.id.tv_goods_detail_name);
        tvPrice = findViewById(R.id.tv_goods_detail_price);
        tvDes = findViewById(R.id.tv_goods_detail_des);
    }

    @Override
    public void getGoodsSuccess(GoodsAllInfo goodsAllInfo) {
        String updateTime = String.valueOf(System.currentTimeMillis()); // 在需要重新获取更新的图片时调用
        Glide.with(GoodsDetailsActivity.this).load(goodsAllInfo.getData().getGoodsImg())
                .signature(new ObjectKey(updateTime))
                .into(ivImg);
        tvName.setText(goodsAllInfo.getData().getGoodsName());
        tvPrice.setText(goodsAllInfo.getData().getGoodsPrice() + " 元");
        tvDes.setText(goodsAllInfo.getData().getGoodsDes());
    }

    @Override
    public void getGoodsFailure() {

    }

    @Override
    public void updateGoodsSuccess() {

    }

    @Override
    public void updateGoodsFailure() {

    }
}
