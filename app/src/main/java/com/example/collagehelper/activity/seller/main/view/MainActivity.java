package com.example.collagehelper.activity.seller.main.view;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.collagehelper.MyClickListener;
import com.example.collagehelper.R;
import com.example.collagehelper.activity.seller.fragment.MessageFragment;
import com.example.collagehelper.activity.seller.main.presenter.MainPresenter;
import com.example.collagehelper.activity.seller.adapter.MyFragmentPagerAdapter;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.activity.seller.fragment.CustomerFragment;
import com.example.collagehelper.activity.seller.fragment.FormFragment;
import com.example.collagehelper.activity.seller.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements IMainView {

    private ViewPager vpPage;
    //private ImageButton ivHome;
    private ImageButton ivForm;
    private ImageButton ivCustomer;
    private ImageButton ivMessage;
    private ImageButton ivMy;
    //private TextView tvHome;
    private TextView tvForm;
    private TextView tvCustomer;
    private TextView tvMessage;
    private TextView tvMy;
    //private HomeFragment homeFragment;
    private FormFragment formFragment;
    private CustomerFragment customerFragment;
    private MessageFragment messageFragment;
    private MyFragment myFragment;
    private List<Fragment> list;
    private MyFragmentPagerAdapter adapter;

    private Intent intent;
    private MainPresenter mainPresenter;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideActionBar();
        initView();
        clickEvent();
        listenViewPager();
    }

    //监听ViewPager的滑动
    private void listenViewPager() {
        vpPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:{
                        whenAtForm();
                        break;
                    }
                    case 1:{
                        whenAtCustomer();
                        break;
                    }
                    case 2:{
                        whenAtMessage();
                        break;
                    }
                    case 3:{
                        whenAtMy();
                        break;
                    }
                    /**case 3:{
                        whenAtMy();
                        break;
                    }**/
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //初始化控件
    public void initView(){
        vpPage = findViewById(R.id.vp_page);
        //ivHome = findViewById(R.id.iv_home);
        ivForm = findViewById(R.id.iv_form);
        ivCustomer = findViewById(R.id.iv_customer);
        ivMessage = findViewById(R.id.iv_message);
        ivMy = findViewById(R.id.iv_my);
        //tvHome = findViewById(R.id.tv_home);
        tvForm = findViewById(R.id.tv_form);
        tvCustomer = findViewById(R.id.tv_customer);
        tvMessage = findViewById(R.id.tv_message);
        tvMy = findViewById(R.id.tv_my);
        list = new ArrayList<>();
        //homeFragment = new HomeFragment();
        formFragment = new FormFragment();
        customerFragment = new CustomerFragment();
        messageFragment = new MessageFragment();
        myFragment = new MyFragment();
        initList();
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),list);
        vpPage.setAdapter(adapter);
        setTittle("主页");
        intent = getIntent();
        phone = intent.getStringExtra("phone");
        Bundle bundle = new Bundle();
        bundle.putString("phone",phone);
        formFragment.setArguments(bundle);
        customerFragment.setArguments(bundle);
        myFragment.setArguments(bundle);
        mainPresenter = new MainPresenter(this);
    }

    //初始化list
    private void initList() {
        //list.add(homeFragment);
        list.add(formFragment);
        list.add(customerFragment);
        list.add(messageFragment);
        list.add(myFragment);
    }

    //点击事件
    private void clickEvent() {
        /**proxyOnClickListener(2, ivHome, new MyClickListener() {
            @Override
            public void onClick(View view) {
                vpPage.setCurrentItem(0);
                whenAtHome();
            }
        });**/
        proxyOnClickListener(2, ivForm, new MyClickListener() {
            @Override
            public void onClick(View view) {
                vpPage.setCurrentItem(0);
                whenAtForm();
            }
        });
        proxyOnClickListener(2, ivCustomer, new MyClickListener() {
            @Override
            public void onClick(View view) {
                vpPage.setCurrentItem(1);
                whenAtCustomer();
            }
        });
        proxyOnClickListener(2, ivMessage, new MyClickListener() {
            @Override
            public void onClick(View view) {
                vpPage.setCurrentItem(2);
                whenAtMessage();
            }
        });
        proxyOnClickListener(2, ivMy, new MyClickListener() {
            @Override
            public void onClick(View view) {
                vpPage.setCurrentItem(3);
                whenAtMy();
            }
        });
    }

    /**
    public void whenAtHome(){
        ivHome.setBackgroundResource(R.drawable.homepage_clicked);
        ivForm.setBackgroundResource(R.drawable.collection);
        ivCustomer.setBackgroundResource(R.drawable.personal_data);
        ivMy.setBackgroundResource(R.drawable.personal_center);
        tvHome.setTextColor(Color.parseColor("#000000"));
        tvForm.setTextColor(Color.parseColor("#01B468"));
        tvCustomer.setTextColor(Color.parseColor("#01B468"));
        tvMy.setTextColor(Color.parseColor("#01B468"));
        setTittle("主页");
    }**/

    public void whenAtForm(){
        //ivHome.setBackgroundResource(R.drawable.homepage);
        ivForm.setBackgroundResource(R.drawable.collection);
        ivCustomer.setBackgroundResource(R.drawable.personal_data_clicked);
        ivMessage.setBackgroundResource(R.drawable.message_unclicked);
        ivMy.setBackgroundResource(R.drawable.personal_center_clicked);
        tvForm.setTextColor(Color.parseColor("#01B468"));
        //tvHome.setTextColor(Color.parseColor("#01B468"));
        tvCustomer.setTextColor(Color.parseColor("#000000"));
        tvMessage.setTextColor(Color.parseColor("#000000"));
        tvMy.setTextColor(Color.parseColor("#000000"));
        setTittle("订单");
    }

    public void whenAtCustomer(){
        //ivHome.setBackgroundResource(R.drawable.homepage);
        ivForm.setBackgroundResource(R.drawable.collection_clicked);
        ivCustomer.setBackgroundResource(R.drawable.personal_data);
        ivMessage.setBackgroundResource(R.drawable.message_unclicked);
        ivMy.setBackgroundResource(R.drawable.personal_center_clicked);
        tvCustomer.setTextColor(Color.parseColor("#01B468"));
        //tvHome.setTextColor(Color.parseColor("#01B468"));
        tvForm.setTextColor(Color.parseColor("#000000"));
        tvMessage.setTextColor(Color.parseColor("#000000"));
        tvMy.setTextColor(Color.parseColor("#000000"));
        setTittle("客户");
    }

    public void whenAtMessage(){
        //ivHome.setBackgroundResource(R.drawable.homepage);
        ivMessage.setBackgroundResource(R.drawable.message_clicked);
        ivForm.setBackgroundResource(R.drawable.collection_clicked);
        ivCustomer.setBackgroundResource(R.drawable.personal_data_clicked);
        ivMy.setBackgroundResource(R.drawable.personal_center_clicked);
        tvCustomer.setTextColor(Color.parseColor("#000000"));
        tvMessage.setTextColor(Color.parseColor("#01B468"));
        //tvHome.setTextColor(Color.parseColor("#01B468"));
        tvForm.setTextColor(Color.parseColor("#000000"));
        tvMy.setTextColor(Color.parseColor("#000000"));
        setTittle("消息");
    }

    public void whenAtMy(){
        //ivHome.setBackgroundResource(R.drawable.homepage);
        ivMessage.setBackgroundResource(R.drawable.message_unclicked);
        ivForm.setBackgroundResource(R.drawable.collection_clicked);
        ivCustomer.setBackgroundResource(R.drawable.personal_data_clicked);
        ivMy.setBackgroundResource(R.drawable.personal_center);
        tvMy.setTextColor(Color.parseColor("#01B468"));
        //tvHome.setTextColor(Color.parseColor("#01B468"));
        tvCustomer.setTextColor(Color.parseColor("#000000"));
        tvMessage.setTextColor(Color.parseColor("#000000"));
        tvForm.setTextColor(Color.parseColor("#000000"));
        setTittle("我的");
    }

    @Override
    public void onBackPressed() {
        clickTwiceToExit();
    }

    @Override
    public void getUserInfoSuccess(String name,String headUrl) {

    }

    @Override
    public void getUserInfoFaiure() {

    }
}
