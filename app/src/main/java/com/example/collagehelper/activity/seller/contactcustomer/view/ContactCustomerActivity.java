package com.example.collagehelper.activity.seller.contactcustomer.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.collagehelper.MyClickListener;
import com.example.collagehelper.R;
import com.example.collagehelper.activity.customer.contactseller.view.ContactSellerActivity;
import com.example.collagehelper.activity.seller.contactcustomer.presenter.ContactCustomerPresenter;
import com.example.collagehelper.adapter.ChatAdapter;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.bean.ChatDO;
import com.example.collagehelper.bean.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContactCustomerActivity extends BaseActivity implements IContactCustomerView {
    private String sellerPhone;
    private RecyclerView rvContactSeller;
    private EditText etContactSeller;
    private Button btnContactSeller;
    private String message;

    private ContactCustomerPresenter presenter;
    private ChatAdapter adapter;
    private LinearLayoutManager manager;
    private List<ChatDO> chatList = new ArrayList<>();
    private List<ChatDO> chatList2 = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private List<User> userList2 = new ArrayList<>();
    private List<String> phoneList = new ArrayList<>();
    private int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_customer);
        hideActionBar();
        Intent intent = getIntent();
        sellerPhone = intent.getStringExtra("customerphone");
        presenter = new ContactCustomerPresenter(this);
        setTittle(sellerPhone);
        rvContactSeller = findViewById(R.id.rv_contact_customer);
        etContactSeller = findViewById(R.id.et_contact_customer);
        btnContactSeller = findViewById(R.id.btn_sendc_msg);
        presenter.get(sellerPhone,phone);
        proxyOnClickListener(2, btnContactSeller, new MyClickListener() {
            @Override
            public void onClick(View view) {
                message = etContactSeller.getText().toString();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String time = df.format(new Date());
                presenter.add(sellerPhone,phone,message,time,"商家");
                etContactSeller.setText(null);
                refresh();
            }
        });
    }

    private void refresh(){
        i = 0;
        chatList.clear();
        userList.clear();
        phoneList.clear();
        presenter.get(sellerPhone,phone);
    }

    @Override
    public void addSuccess() {

    }

    @Override
    public void addFailure() {

    }

    @Override
    public void getSuccess(List<ChatDO> list) {
        if (list.size() == 0){
            return;
        }
        chatList.addAll(list);
        for (int i = 0; i < chatList.size(); i++){
            if (chatList.get(i).getStart().equals("客户")){
                phoneList.add(chatList.get(i).getCustomerPhone());
            }else {
                phoneList.add(chatList.get(i).getSellerPhone());
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
        if (i != chatList.size()){
            getUser(phoneList);
            return;
        }
        chatList2.clear();
        userList2.clear();
        chatList2.addAll(chatList);
        userList2.addAll(userList);
        adapter = new ChatAdapter(chatList2,userList2,ContactCustomerActivity.this);
        manager = new LinearLayoutManager(this);
        rvContactSeller.setAdapter(adapter);
        rvContactSeller.setLayoutManager(manager);
        rvContactSeller.smoothScrollToPosition(adapter.getItemCount() - 1);
    }
}
