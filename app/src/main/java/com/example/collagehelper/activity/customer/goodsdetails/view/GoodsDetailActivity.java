package com.example.collagehelper.activity.customer.goodsdetails.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.example.collagehelper.MyClickListener;
import com.example.collagehelper.R;
import com.example.collagehelper.activity.customer.goodsdetails.presenter.GoodsDetailPresenter;
import com.example.collagehelper.activity.customer.sellerdetails.view.SellerDetailsActivity;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.bean.GoodsAllInfo;

public class GoodsDetailActivity extends BaseActivity implements IGoodsDetailView{
    private Intent intent;
    private int id;

    private GoodsDetailPresenter presenter;
    private String sellerPhone;

    private ImageView ivGoodsImg;
    private TextView tvGoodsName;
    private TextView tvGoodsPrice;
    private TextView tvGoodsDes;
    private TextView tvGoodsSeller;
    private ImageView ivShop;
    private ImageView ivCollect;
    private ImageView ivComment;
    private ImageView ivShoppingCart;
    private ImageView ivAssemble;
    private ImageView ivPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        hideActionBar();
        setTittle("商品详情");
        intent = getIntent();
        id = intent.getIntExtra("id",-1);
        presenter = new GoodsDetailPresenter(this);
        initView();
        presenter.getGoodsDetail(id);
        clickEvent();
    }

    private void initView(){
        ivGoodsImg = findViewById(R.id.iv_goods_detail_img2);
        tvGoodsName = findViewById(R.id.tv_goods_detail_name2);
        tvGoodsPrice = findViewById(R.id.tv_goods_detail_price2);
        tvGoodsDes = findViewById(R.id.tv_goods_detail_des2);
        tvGoodsSeller = findViewById(R.id.tv_goods_detail_seller);
        ivShop = findViewById(R.id.iv_detail_shop);
        ivCollect = findViewById(R.id.iv_detail_collect);
        ivComment = findViewById(R.id.iv_detail_comment);
        ivShoppingCart = findViewById(R.id.iv_detail_shopping_cart);
        ivAssemble = findViewById(R.id.iv_detail_assemble);
        ivPay = findViewById(R.id.iv_detail_buy);
    }

    private void clickEvent(){
        proxyOnClickListener(2, ivShop, new MyClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoodsDetailActivity.this,SellerDetailsActivity.class);
                intent.putExtra("sellerphone",sellerPhone);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getGoodsSuccess(GoodsAllInfo goodsAllInfo) {
        Toast.makeText(GoodsDetailActivity.this,"获取数据成功",Toast.LENGTH_SHORT).show();
        String updateTime = String.valueOf(System.currentTimeMillis()); // 在需要重新获取更新的图片时调用
        Glide.with(GoodsDetailActivity.this).load(goodsAllInfo.getData().getGoodsImg())
                .signature(new ObjectKey(updateTime))
                .into(ivGoodsImg);
        tvGoodsName.setText(goodsAllInfo.getData().getGoodsName());
        tvGoodsPrice.setText(goodsAllInfo.getData().getGoodsPrice() + "元");
        tvGoodsDes.setText(goodsAllInfo.getData().getGoodsDes());
        tvGoodsSeller.setText("店家: " + goodsAllInfo.getData().getPhone());
        sellerPhone = goodsAllInfo.getData().getPhone();
    }

    @Override
    public void getGoodsFailure() {
        Toast.makeText(GoodsDetailActivity.this,"获取数据失败",Toast.LENGTH_SHORT).show();
    }
}
