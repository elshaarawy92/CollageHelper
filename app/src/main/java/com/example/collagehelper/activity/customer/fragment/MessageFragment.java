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

import com.example.collagehelper.R;
import com.example.collagehelper.activity.customer.contactseller.view.ContactSellerActivity;
import com.example.collagehelper.activity.customer.fragment.presenter.MessagePresenter;
import com.example.collagehelper.activity.customer.fragment.view.IMessageView;
import com.example.collagehelper.adapter.MessageAdapter;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.base.BaseFragment;
import com.example.collagehelper.bean.ChatDO;
import com.example.collagehelper.bean.User;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends BaseFragment implements IMessageView {
    private RecyclerView rvMessageSeller;
    private MessagePresenter presenter;
    private MessageAdapter adapter;
    private LinearLayoutManager manager;

    private List<String> phoneList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private List<User> userList2 = new ArrayList<>();
    private int i = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message2,container,false);
        rvMessageSeller = view.findViewById(R.id.rv_message_seller);
        presenter = new MessagePresenter(this);
        presenter.get(BaseActivity.phone);
        return view;
    }

    @Override
    public void getSuccess(List<ChatDO> list) {
        if (list.size() == 0){
            return;
        }
        for (int i = 0; i < list.size(); i++){
            if (!phoneList.contains(list.get(i).getSellerPhone())){
                phoneList.add(list.get(i).getSellerPhone());
            }
        }
        getUser(phoneList);
    }

    private void getUser(List<String> list){
        presenter.getSellerByPhone(list.get(i));
        i++;
    }

    @Override
    public void getFailure() {

    }

    @Override
    public void getSellerSuccess(User user) {
        userList.add(user);
        if (i < phoneList.size()){
            getUser(phoneList);
            return;
        }
        userList2.clear();
        userList2.addAll(userList);
        adapter = new MessageAdapter(userList2,getContext());
        adapter.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(),ContactSellerActivity.class);
                intent.putExtra("sellerphone",phoneList.get(position));
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        manager = new LinearLayoutManager(getContext());
        rvMessageSeller.setAdapter(adapter);
        rvMessageSeller.setLayoutManager(manager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        userList.clear();
        phoneList.clear();
        i = 0;
    }
}
