package com.example.collagehelper.activity.customer.fragment;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.example.collagehelper.R;
import com.example.collagehelper.activity.customer.assemble.view.AssembleActivity;
import com.example.collagehelper.activity.customer.main.presenter.Main2Presenter;
import com.example.collagehelper.activity.customer.main.view.IMainView2;
import com.example.collagehelper.activity.customer.mycollect.view.MyCollectActivity;
import com.example.collagehelper.activity.customer.order.view.OrderActivity;
import com.example.collagehelper.adapter.MyAdapter;
import com.example.collagehelper.bean.CTSDO;
import com.example.collagehelper.bean.Function;
import com.example.collagehelper.bean.User;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyFragment extends BaseFragment implements IMainView2 {
    private List<Function> list;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private LinearLayoutManager manager;
    private CircleImageView civHead;
    private TextView tvHead;

    private String phone;
    private Main2Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my2,container,false);
        list = new ArrayList<>();
        initList();
        recyclerView = view.findViewById(R.id.my_recyclerview);
        civHead = view.findViewById(R.id.civ_head);
        tvHead = view.findViewById(R.id.tv_username);
        adapter = new MyAdapter(list);
        manager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        phone = BaseActivity.phone;
        presenter = new Main2Presenter(this);
        presenter.getUser(phone);
        ClickEvent();
        return view;
    }

    private void ClickEvent() {
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        Intent intent = new Intent(getContext(),AssembleActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getContext(),OrderActivity.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(getContext(),MyCollectActivity.class);
                        startActivity(intent3);
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

    private void initList() {
        list.add(new Function(R.drawable.customer_manage,"商家管理"));
        list.add(new Function(R.drawable.thing_manage,"拼单管理"));
        list.add(new Function(R.drawable.form_manage,"订单管理"));
        list.add(new Function(R.drawable.statistics_manage,"我的收藏"));
        list.add(new Function(R.drawable.head_setting,"个人设置"));
    }

    @Override
    public void getUserInfoSuccess(User user) {
        tvHead.setText(user.getData().getName());
        String updateTime = String.valueOf(System.currentTimeMillis()); // 在需要重新获取更新的图片时调用
        Glide.with(getContext()).load(user.getData().getHead())
                .signature(new ObjectKey(updateTime))
                .into(civHead);
    }

    @Override
    public void getCollectedSellerSuccess(CTSDO ctsdo) {

    }

    @Override
    public void getCollectedSellerFailure() {

    }

    @Override
    public void getCollectedSellerNull() {

    }

    @Override
    public void getSellerSuccess(User user) {

    }
}
