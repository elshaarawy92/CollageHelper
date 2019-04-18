package com.example.collagehelper.activity.seller.goods.seegoods.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.collagehelper.R;
import com.example.collagehelper.activity.seller.goods.goodsdetails.GoodsDetailsActivity;
import com.example.collagehelper.activity.seller.goods.seegoods.presenter.SeeGoodsPresenter;
import com.example.collagehelper.activity.seller.goods.updategoods.view.UpdateGoodsActivity;
import com.example.collagehelper.adapter.GoodsInfoAdapter;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.bean.GoodsInfo;

import java.util.List;

public class SeeGoodsActivity extends BaseActivity implements ISeeGoodsView {

    private RecyclerView rvSeeGoods;
    private GoodsInfoAdapter adapter;
    private LinearLayoutManager manager;

    private SeeGoodsPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_goods);
        hideActionBar();
        setTittle("管理已上架商品");
        init();
    }

    private void init(){
        rvSeeGoods = findViewById(R.id.rv_see_goods);
        presenter = new SeeGoodsPresenter(this);
        presenter.getGoods(phone);
    }

    @Override
    public void getGoodsSuccess(List<GoodsInfo> list) {
        adapter = new GoodsInfoAdapter(list,SeeGoodsActivity.this);
        manager = new LinearLayoutManager(this);
        rvSeeGoods.setAdapter(adapter);
        rvSeeGoods.setLayoutManager(manager);
        adapter.setOnItemClickListener(new GoodsInfoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int id) {
                Intent intent = new Intent(SeeGoodsActivity.this,GoodsDetailsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position, final int id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SeeGoodsActivity.this);
                dialog.setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(SeeGoodsActivity.this,UpdateGoodsActivity.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }
                })
                        .setNegativeButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SeeGoodsActivity.this);
                                builder.setTitle("您确定要删除吗?")
                                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                presenter.deleteGoods(id);
                                            }
                                        })
                                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                            }
                                        })
                                        .show();
                            }
                        })
                        .setTitle("请选择您要执行的操作");
                dialog.show();
            }
        });
    }

    @Override
    public void getGoodsFailure() {
        Toast.makeText(SeeGoodsActivity.this,"获取商品列表失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noGoods() {
        Toast.makeText(SeeGoodsActivity.this,"列表中没有商品",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteSuccess() {
        Toast.makeText(SeeGoodsActivity.this,"删除商品成功",Toast.LENGTH_SHORT).show();
        presenter.getGoods(phone);
    }

    @Override
    public void deleteFailure() {
        Toast.makeText(SeeGoodsActivity.this,"删除商品失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.getGoods(phone);
    }
}
