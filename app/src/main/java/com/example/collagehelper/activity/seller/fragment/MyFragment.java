package com.example.collagehelper.activity.seller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.example.collagehelper.R;
import com.example.collagehelper.activity.seller.main.presenter.MainPresenter;
import com.example.collagehelper.activity.seller.main.view.IMainView;
import com.example.collagehelper.activity.seller.goods.managegoods.ManageGoodsActivity;
import com.example.collagehelper.activity.seller.adapter.MyAdapter;
import com.example.collagehelper.base.BaseFragment;
import com.example.collagehelper.activity.seller.bean.Function;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by liang on 2018/10/28
 * 我的界面
 */
public class MyFragment extends BaseFragment implements IMainView {

    private List<Function> list;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private LinearLayoutManager manager;
    private CircleImageView civHead;
    private TextView tvHead;

    private String phone;

    private MainPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my,container,false);
        presenter = new MainPresenter(this);
        list = new ArrayList<>();
        initList();
        recyclerView = view.findViewById(R.id.my_recyclerview);
        civHead = view.findViewById(R.id.civ_head);
        tvHead = view.findViewById(R.id.tv_username);
        adapter = new MyAdapter(list);
        manager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        Bundle bundle = this.getArguments();
        phone = bundle.getString("phone");
        presenter.getUserInfo(phone);
        ClickEvent();
        return view;
    }

    //recyclerview的item的点击事件
    private void ClickEvent() {
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        Intent intent = new Intent(getContext(),ManageGoodsActivity.class);
                        intent.putExtra("phone",phone);
                        startActivity(intent);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    //初始化list
    private void initList() {
        list.add(new Function(R.drawable.customer_manage,"客户管理"));
        list.add(new Function(R.drawable.thing_manage,"商品管理"));
        list.add(new Function(R.drawable.form_manage,"订单管理"));
        list.add(new Function(R.drawable.statistics_manage,"统计管理"));
        list.add(new Function(R.drawable.head_setting,"头像设置"));
    }

    @Override
    public void getUserInfoSuccess(String name,String headUrl) {
        tvHead.setText(name);
        String updateTime = String.valueOf(System.currentTimeMillis()); // 在需要重新获取更新的图片时调用
        Glide.with(getContext()).load(headUrl)
                .signature(new ObjectKey(updateTime))
                .into(civHead);
    }

    @Override
    public void getUserInfoFaiure() {
        Toast.makeText(getContext(),"获取用户信息失败",Toast.LENGTH_SHORT).show();
    }
}
