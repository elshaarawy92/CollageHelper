package com.example.collagehelper.activity.selectcustomer;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.collagehelper.MyClickListener;
import com.example.collagehelper.R;
import com.example.collagehelper.base.BaseActivity;

public class SelectCustomerActivity extends BaseActivity {

    private ImageButton ibSearchCustomer;
    private EditText etSearchCustomer;
    private RelativeLayout rlAddCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_customer);
        hideActionBar();
        setTittle("新增客户");
        initView();
        clickEvent();
    }

    private void initView(){
        ibSearchCustomer = findViewById(R.id.ib_search_customer);
        etSearchCustomer = findViewById(R.id.et_search_customer);
        rlAddCustomer = findViewById(R.id.rl_add_customer);
    }

    private void clickEvent(){
        proxyOnClickListener(2, ibSearchCustomer, new MyClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        proxyOnClickListener(2, rlAddCustomer, new MyClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        setLeftClick(new MyClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
