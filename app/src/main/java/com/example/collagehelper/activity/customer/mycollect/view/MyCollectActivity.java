package com.example.collagehelper.activity.customer.mycollect.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.collagehelper.R;
import com.example.collagehelper.activity.customer.goodsdetails.view.GoodsDetailActivity;
import com.example.collagehelper.activity.customer.mycollect.presenter.MyCollectPresenter;
import com.example.collagehelper.adapter.CollectAdapter;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.bean.CGDO;
import com.example.collagehelper.bean.Goods;
import com.example.collagehelper.bean.GoodsAllInfo;

import java.util.ArrayList;
import java.util.List;

public class MyCollectActivity extends BaseActivity implements IMyCollectView{
    private MyCollectPresenter presenter;
    private RecyclerView rvCollect;
    private CollectAdapter adapter;
    private LinearLayoutManager manager;
    private List<Integer> goodsIdList = new ArrayList<>();
    private List<Goods> list = new ArrayList<>();
    private List<Goods> list1 = new ArrayList<>();
    private List<CGDO> cgList = new ArrayList<>();
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        hideActionBar();
        setTittle("我的收藏");
        presenter = new MyCollectPresenter(this);
        rvCollect = findViewById(R.id.rv_collect);
        presenter.getCg(phone);
    }

    @Override
    public void getCgSuccess(List<CGDO> list) {
        if (list == null){
            return;
        }
        if (list.size() == 0){
            return;
        }
        cgList = list;
        for (int i = 0; i < list.size(); i++){
            goodsIdList.add(list.get(i).getGoodsId());
        }
        getGoods(goodsIdList);
    }

    private void getGoods(List<Integer> list){
        presenter.getGoodsDetail(list.get(i));
        i++;
    }

    @Override
    public void getCgFailure() {

    }

    @Override
    public void deleteCgSuccess() {

    }

    @Override
    public void deleteCgFailure() {

    }

    @Override
    public void getGoodsSuccess(GoodsAllInfo goodsAllInfo) {
        Goods goods = new Goods();
        goods.setImg(goodsAllInfo.getData().getGoodsImg());
        goods.setName(goodsAllInfo.getData().getGoodsName());
        list.add(goods);
        if (i != goodsIdList.size()){
            getGoods(goodsIdList);
            return;
        }
        list1.clear();
        list1.addAll(list);
        adapter = new CollectAdapter(list1,MyCollectActivity.this);
        manager = new LinearLayoutManager(this);
        adapter.setOnItemClickListener(new CollectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MyCollectActivity.this,GoodsDetailActivity.class);
                intent.putExtra("id",goodsIdList.get(position));
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MyCollectActivity.this);
                builder.setNegativeButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MyCollectActivity.this);
                        builder1.setNegativeButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                presenter.deleteCg(cgList.get(position).getId());
                                list.clear();
                                i = 0;
                                goodsIdList.clear();
                                cgList.clear();
                                presenter.getCg(phone);
                            }
                        });
                        builder1.setPositiveButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder1.show();
                    }
                });
                builder.show();
            }
        });
        rvCollect.setAdapter(adapter);
        rvCollect.setLayoutManager(manager);
    }

    @Override
    public void getGoodsFailure() {

    }
}
