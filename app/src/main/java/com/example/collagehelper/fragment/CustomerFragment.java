package com.example.collagehelper.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.collagehelper.R;
import com.example.collagehelper.adapter.CustomerAdapter;
import com.example.collagehelper.bean.Function;
import com.example.collagehelper.mvp.customer.CustomerActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by liang on 2018/10/28
 * 客户界面
 */
public class CustomerFragment extends Fragment {

    private List<Function> list;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CustomerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer,container,false);
        list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.rv_customer);
        layoutManager = new LinearLayoutManager(getContext());
        initList();
        adapter = new CustomerAdapter(list);
        adapterClick();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void adapterClick() {
        adapter.setOnItemClickListener(new CustomerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(),CustomerActivity.class);
                CircleImageView circleImageView = view.findViewById(R.id.civ_customer_head);
                TextView textView = view.findViewById(R.id.tv_customer_name);
                intent.putExtra("name",textView.getText());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    //初始化list
    private void initList() {
        list.add(new Function(R.drawable.totti,"托蒂"));
        list.add(new Function(R.drawable.perotti,"佩罗蒂"));
        list.add(new Function(R.drawable.derossi,"德罗西"));
        list.add(new Function(R.drawable.manolas,"马诺拉斯"));
        list.add(new Function(R.drawable.shaarawy,"沙拉维"));
        list.add(new Function(R.drawable.lorenzo,"洛伦佐.佩莱格里尼"));
    }
}
