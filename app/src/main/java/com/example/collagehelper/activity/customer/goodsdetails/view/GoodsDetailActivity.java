package com.example.collagehelper.activity.customer.goodsdetails.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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

import java.text.SimpleDateFormat;
import java.util.Date;

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

    private int price;

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

        proxyOnClickListener(2, ivShoppingCart, new MyClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GoodsDetailActivity.this);
                View view1 = LayoutInflater.from(GoodsDetailActivity.this).inflate(R.layout.dialog_goods,null);
                ImageView ivDGoodsImg = view1.findViewById(R.id.iv_dialog_goods_img);
                TextView tvDGoodsName = view1.findViewById(R.id.tv_dialog_goods_name);
                TextView tvDGoodsPrice = view1.findViewById(R.id.tv_dialog_goods_price);
                final TextView tvDGoodsCount = view1.findViewById(R.id.tv_dialog_goods_count);
                ImageView ivAdd = view1.findViewById(R.id.iv_add);
                ImageView ivMinus = view1.findViewById(R.id.iv_minus);
                Button btnAddToCart = view1.findViewById(R.id.btn_add_to_cart);
                Bitmap bitmap = loadBitmapFromView(ivGoodsImg);
                ivDGoodsImg.setImageBitmap(bitmap);
                tvDGoodsName.setText(tvGoodsName.getText().toString().trim());
                tvDGoodsPrice.setText(tvGoodsPrice.getText().toString().trim());
                ivAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int count = Integer.valueOf(tvDGoodsCount.getText().toString().trim());
                        count++;
                        tvDGoodsCount.setText(count + "");
                    }
                });
                ivMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int count = Integer.valueOf(tvDGoodsCount.getText().toString().trim());
                        if (count > 1){
                            count--;
                            tvDGoodsCount.setText(count + "");
                        }
                    }
                });
                builder.setView(view1);
                final AlertDialog dialog = builder.show();
                proxyOnClickListener(2, btnAddToCart, new MyClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.addToCart(BaseActivity.phone,id,Integer.valueOf(tvDGoodsCount.getText().toString().trim()));
                        dialog.dismiss();
                        Intent intent = new Intent();
                        intent.setAction("android.intent.shoppingcartreciever");
                        sendBroadcast(intent);
                    }
                });
            }
        });
        proxyOnClickListener(2, ivAssemble, new MyClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GoodsDetailActivity.this);
                View view1 = LayoutInflater.from(GoodsDetailActivity.this).inflate(R.layout.dialog_goods3,null);
                ImageView ivDGoodsImg = view1.findViewById(R.id.iv_dialog_goods_img);
                TextView tvDGoodsName = view1.findViewById(R.id.tv_dialog_goods_name);
                TextView tvDGoodsPrice = view1.findViewById(R.id.tv_dialog_goods_price);
                final TextView tvDGoodsCount = view1.findViewById(R.id.tv_dialog_goods_count);
                ImageView ivAdd = view1.findViewById(R.id.iv_add);
                ImageView ivMinus = view1.findViewById(R.id.iv_minus);
                Button btnAddToCart = view1.findViewById(R.id.btn_add_to_cart);
                Bitmap bitmap = loadBitmapFromView(ivGoodsImg);
                ivDGoodsImg.setImageBitmap(bitmap);
                tvDGoodsName.setText(tvGoodsName.getText().toString().trim());
                tvDGoodsPrice.setText(tvGoodsPrice.getText().toString().trim());
                ivAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int count = Integer.valueOf(tvDGoodsCount.getText().toString().trim());
                        count++;
                        tvDGoodsCount.setText(count + "");
                    }
                });
                ivMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int count = Integer.valueOf(tvDGoodsCount.getText().toString().trim());
                        if (count > 1){
                            count--;
                            tvDGoodsCount.setText(count + "");
                        }
                    }
                });
                builder.setView(view1);
                final AlertDialog dialog = builder.show();
                proxyOnClickListener(2, btnAddToCart, new MyClickListener() {
                    @Override
                    public void onClick(View view) {
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                        String time = df.format(new Date());
                        SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");
                        String time2 = df2.format(new Date());
                        String assembleId = time2 + BaseActivity.phone;
                        int count = Integer.valueOf(tvDGoodsCount.getText().toString().trim());
                        String money = price * count + "";
                        presenter.addAssemble(phone,sellerPhone,assembleId,time,money,id,count);
                        dialog.dismiss();
                        Intent intent = new Intent();
                        intent.setAction("android.intent.assemblereciever");
                        sendBroadcast(intent);
                    }
                });
            }
        });
        proxyOnClickListener(2, ivPay, new MyClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GoodsDetailActivity.this);
                View view1 = LayoutInflater.from(GoodsDetailActivity.this).inflate(R.layout.dialog_goods2,null);
                ImageView ivDGoodsImg = view1.findViewById(R.id.iv_dialog_goods_img);
                TextView tvDGoodsName = view1.findViewById(R.id.tv_dialog_goods_name);
                final TextView tvDGoodsPrice = view1.findViewById(R.id.tv_dialog_goods_price);
                final TextView tvDGoodsCount = view1.findViewById(R.id.tv_dialog_goods_count);
                ImageView ivAdd = view1.findViewById(R.id.iv_add);
                ImageView ivMinus = view1.findViewById(R.id.iv_minus);
                Button btnAddToCart = view1.findViewById(R.id.btn_add_to_cart);
                Bitmap bitmap = loadBitmapFromView(ivGoodsImg);
                ivDGoodsImg.setImageBitmap(bitmap);
                tvDGoodsName.setText(tvGoodsName.getText().toString().trim());
                tvDGoodsPrice.setText(tvGoodsPrice.getText().toString().trim());
                ivAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int count = Integer.valueOf(tvDGoodsCount.getText().toString().trim());
                        count++;
                        tvDGoodsCount.setText(count + "");
                    }
                });
                ivMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int count = Integer.valueOf(tvDGoodsCount.getText().toString().trim());
                        if (count > 1){
                            count--;
                            tvDGoodsCount.setText(count + "");
                        }
                    }
                });
                builder.setView(view1);
                final AlertDialog dialog = builder.show();
                proxyOnClickListener(2, btnAddToCart, new MyClickListener() {
                    @Override
                    public void onClick(View view) {
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                        String time = df.format(new Date());
                        SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");
                        String time2 = df2.format(new Date());
                        String orderId = time2 + BaseActivity.phone;
                        int count = Integer.valueOf(tvDGoodsCount.getText().toString().trim());
                        String money = price * count + "";
                        presenter.addOrder(BaseActivity.phone,sellerPhone,orderId,time,money,id,count);
                        dialog.dismiss();
                    }
                });
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
        price = Integer.valueOf(goodsAllInfo.getData().getGoodsPrice());
    }

    @Override
    public void getGoodsFailure() {
        Toast.makeText(GoodsDetailActivity.this,"获取数据失败",Toast.LENGTH_SHORT).show();
    }

    public static Bitmap loadBitmapFromView(View v) {
        if (v == null) {
            return null;
        }
        Bitmap screenshot;
        screenshot = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(screenshot);
        canvas.translate(-v.getScrollX(), -v.getScrollY());//我们在用滑动View获得它的Bitmap时候，获得的是整个View的区域（包括隐藏的），如果想得到当前区域，需要重新定位到当前可显示的区域
        v.draw(canvas);// 将 view 画到画布上
        return screenshot;
    }

    @Override
    public void addToCatSuccess() {
        Toast.makeText(GoodsDetailActivity.this,"添加到购物车成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addToCartFailure() {
        Toast.makeText(GoodsDetailActivity.this,"添加到购物车失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addOrderSuccess() {
        Toast.makeText(GoodsDetailActivity.this,"支付成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addOrderFailure() {
        Toast.makeText(GoodsDetailActivity.this,"支付失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addAssembleSuccess() {
        Toast.makeText(GoodsDetailActivity.this,"发起拼团成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addAssembleFailure() {
        Toast.makeText(GoodsDetailActivity.this,"发起拼团失败",Toast.LENGTH_SHORT).show();
    }
}
