package com.example.collagehelper.activity.customer.sellerdetails.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.example.collagehelper.MyClickListener;
import com.example.collagehelper.R;
import com.example.collagehelper.activity.customer.goodsdetails.view.GoodsDetailActivity;
import com.example.collagehelper.activity.customer.sellerdetails.presenter.SellerDetailsPresenter;
import com.example.collagehelper.adapter.GoodsByNameAdapter;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.bean.CTSDO;
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
    private Button btnContactSeller;
    private Button btnColllectSeller;
    private int id;

    private boolean exist;
    private int status = 0;
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
        presenter.getCollectedSeller(phone);
        clickEvent();
    }

    private void initView(){
        civSellerHead = findViewById(R.id.civ_seller_head);
        tvSellerName = findViewById(R.id.tv_seller_name);
        rvSellerGoods = findViewById(R.id.rv_seller_goods);
        btnContactSeller = findViewById(R.id.btn_contact_seller);
        btnColllectSeller = findViewById(R.id.btn_collect_seller);
    }

    private void clickEvent(){
        proxyOnClickListener(2, btnContactSeller, new MyClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 联系店家
            }
        });

        proxyOnClickListener(2, btnColllectSeller, new MyClickListener() {
            @Override
            public void onClick(View view) {
                status = 1;
                if (btnColllectSeller.getText().toString().trim().equals("收藏店家")) {
                    btnColllectSeller.setText("取消收藏");
                }else {
                    btnColllectSeller.setText("收藏店家");
                }
                presenter.getCollectedSeller(phone);
            }
        });
    }

    @Override
    public void getGoodsSuccess(List<GoodsInfo2> list) {
        adapter = new GoodsByNameAdapter(list,SellerDetailsActivity.this);
        manager = new LinearLayoutManager(SellerDetailsActivity.this);
        rvSellerGoods.setLayoutManager(manager);
        rvSellerGoods.setAdapter(adapter);
        adapter.setOnItemClickListener(new GoodsByNameAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int id) {
                Intent intent = new Intent(SellerDetailsActivity.this,GoodsDetailActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position, int id) {

            }
        });
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

    @Override
    public void collectSuccess() {
        Toast.makeText(SellerDetailsActivity.this,"收藏成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void collectFailure() {
        Toast.makeText(SellerDetailsActivity.this,"收藏失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteSuccess() {
        Toast.makeText(SellerDetailsActivity.this,"取消收藏成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteFailure() {
        Toast.makeText(SellerDetailsActivity.this,"取消收藏失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCollectedSellerSuccess(CTSDO ctsdo) {
        for (int i = 0;i < ctsdo.getData().size();i++){
            if (ctsdo.getData().get(i).getSellerPhone().equals(sellerPhone)){
                exist = true;
                id = ctsdo.getData().get(i).getId();
                break;
            }
        }
        if (exist){
            btnColllectSeller.setText("取消收藏");
            if (status == 1){
                presenter.deleteSellerById(id);
                exist = false;
                btnColllectSeller.setText("收藏店家");
            }
        }else {
            btnColllectSeller.setText("收藏店家");
            if (status == 1){
                presenter.collectSeller(phone,sellerPhone);
                btnColllectSeller.setText("取消收藏");
            }
        }
    }

    @Override
    public void getCollectedSellerFailure() {

    }

    @Override
    public void getCollectedSellerNull() {
        btnColllectSeller.setText("收藏店家");
        if (status == 1){
            presenter.collectSeller(phone,sellerPhone);
            btnColllectSeller.setText("取消收藏");
        }
    }
}
