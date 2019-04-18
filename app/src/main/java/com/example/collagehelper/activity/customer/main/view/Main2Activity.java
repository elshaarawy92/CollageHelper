package com.example.collagehelper.activity.customer.main.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.collagehelper.MyClickListener;
import com.example.collagehelper.R;
import com.example.collagehelper.activity.customer.fragment.AssembleFragment;
import com.example.collagehelper.activity.customer.fragment.HomeFragment;
import com.example.collagehelper.activity.customer.fragment.MessageFragment;
import com.example.collagehelper.activity.customer.fragment.MyFragment;
import com.example.collagehelper.activity.customer.fragment.ShoppingCartFragment;
import com.example.collagehelper.adapter.MyFragmentPagerAdapter;
import com.example.collagehelper.bean.User;
import com.example.collagehelper.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends BaseActivity implements IMainView2 {

    private ViewPager viewPager;
    private ImageButton ibHome;
    private ImageButton ibAssemble;
    private ImageButton ibMessage;
    private ImageButton ibShoppingCart;
    private ImageButton ibMy;

    private TextView tvHome;
    private TextView tvAssemble;
    private TextView tvMessage;
    private TextView tvShoppingCart;
    private TextView tvMy;

    private AssembleFragment assembleFragment;
    private HomeFragment homeFragment;
    private MessageFragment messageFragment;
    private ShoppingCartFragment shoppingCartFragment;
    private MyFragment myFragment;

    private List<Fragment> list;
    private MyFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        hideActionBar();
        initView();
        clickEvent();
        listenViewPager();
    }

    private void initView(){
        viewPager = findViewById(R.id.vp_page2);
        ibHome = findViewById(R.id.iv_home);
        ibAssemble = findViewById(R.id.iv_assemble);
        ibMessage = findViewById(R.id.iv_message);
        ibShoppingCart = findViewById(R.id.iv_shopping_cart);
        ibMy = findViewById(R.id.iv_my);
        tvHome = findViewById(R.id.tv_home);
        tvAssemble = findViewById(R.id.tv_assemble);
        tvMessage = findViewById(R.id.tv_message);
        tvShoppingCart = findViewById(R.id.tv_shopping_cart);
        tvMy = findViewById(R.id.tv_my);
        list = new ArrayList<>();
        assembleFragment = new AssembleFragment();
        homeFragment = new HomeFragment();
        messageFragment = new MessageFragment();
        myFragment = new MyFragment();
        shoppingCartFragment = new ShoppingCartFragment();
        initList();
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(adapter);
        setTittle("首页");

    }

    private void initList(){
        list.add(homeFragment);
        list.add(assembleFragment);
        list.add(messageFragment);
        list.add(shoppingCartFragment);
        list.add(myFragment);
    }

    private void clickEvent(){
        proxyOnClickListener(2, ibHome, new MyClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
                whenAtHome();
            }
        });
        proxyOnClickListener(2, ibAssemble, new MyClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
                whenAtAssemble();
            }
        });
        proxyOnClickListener(2, ibMessage, new MyClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
                whenAtMessage();
            }
        });
        proxyOnClickListener(2, ibShoppingCart, new MyClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(3);
                whenAtShoppingCart();
            }
        });
        proxyOnClickListener(2, ibMy, new MyClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(4);
                whenAtMy();
            }
        });
    }

    private void listenViewPager(){
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        whenAtHome();
                        break;
                    case 1:
                        whenAtAssemble();
                        break;
                    case 2:
                        whenAtMessage();
                        break;
                    case 3:
                        whenAtShoppingCart();
                        break;
                    case 4:
                        whenAtMy();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void whenAtHome(){
        ibHome.setBackgroundResource(R.drawable.homepage);
        ibAssemble.setBackgroundResource(R.drawable.assemble_unclicked);
        ibMessage.setBackgroundResource(R.drawable.message_unclicked);
        ibShoppingCart.setBackgroundResource(R.drawable.shopping_cart_unclicked);
        ibMy.setBackgroundResource(R.drawable.personal_data_clicked);
        tvHome.setTextColor(Color.parseColor("#01B468"));
        tvAssemble.setTextColor(Color.parseColor("#000000"));
        tvMessage.setTextColor(Color.parseColor("#000000"));
        tvShoppingCart.setTextColor(Color.parseColor("#000000"));
        tvMy.setTextColor(Color.parseColor("#000000"));
        setTittle("主页");
    }

    private void whenAtAssemble(){
        ibHome.setBackgroundResource(R.drawable.homepage_clicked);
        ibAssemble.setBackgroundResource(R.drawable.assemble_clicked);
        ibMessage.setBackgroundResource(R.drawable.message_unclicked);
        ibShoppingCart.setBackgroundResource(R.drawable.shopping_cart_unclicked);
        ibMy.setBackgroundResource(R.drawable.personal_data_clicked);
        tvAssemble.setTextColor(Color.parseColor("#01B468"));
        tvHome.setTextColor(Color.parseColor("#000000"));
        tvMessage.setTextColor(Color.parseColor("#000000"));
        tvShoppingCart.setTextColor(Color.parseColor("#000000"));
        tvMy.setTextColor(Color.parseColor("#000000"));
        setTittle("拼吧");
    }

    private void whenAtMessage(){
        ibHome.setBackgroundResource(R.drawable.homepage_clicked);
        ibAssemble.setBackgroundResource(R.drawable.assemble_unclicked);
        ibMessage.setBackgroundResource(R.drawable.message_clicked);
        ibShoppingCart.setBackgroundResource(R.drawable.shopping_cart_unclicked);
        ibMy.setBackgroundResource(R.drawable.personal_data_clicked);
        tvMessage.setTextColor(Color.parseColor("#01B468"));
        tvAssemble.setTextColor(Color.parseColor("#000000"));
        tvHome.setTextColor(Color.parseColor("#000000"));
        tvShoppingCart.setTextColor(Color.parseColor("#000000"));
        tvMy.setTextColor(Color.parseColor("#000000"));
        setTittle("消息");
    }

    private void whenAtShoppingCart(){
        ibHome.setBackgroundResource(R.drawable.homepage_clicked);
        ibAssemble.setBackgroundResource(R.drawable.assemble_unclicked);
        ibMessage.setBackgroundResource(R.drawable.message_unclicked);
        ibShoppingCart.setBackgroundResource(R.drawable.shopping_cart_clicked);
        ibMy.setBackgroundResource(R.drawable.personal_data_clicked);
        tvShoppingCart.setTextColor(Color.parseColor("#01B468"));
        tvAssemble.setTextColor(Color.parseColor("#000000"));
        tvMessage.setTextColor(Color.parseColor("#000000"));
        tvHome.setTextColor(Color.parseColor("#000000"));
        tvMy.setTextColor(Color.parseColor("#000000"));
        setTittle("购物车");
    }

    private void whenAtMy(){
        ibHome.setBackgroundResource(R.drawable.homepage_clicked);
        ibAssemble.setBackgroundResource(R.drawable.assemble_unclicked);
        ibMessage.setBackgroundResource(R.drawable.message_unclicked);
        ibShoppingCart.setBackgroundResource(R.drawable.shopping_cart_unclicked);
        ibMy.setBackgroundResource(R.drawable.personal_data);
        tvMy.setTextColor(Color.parseColor("#01B468"));
        tvAssemble.setTextColor(Color.parseColor("#000000"));
        tvMessage.setTextColor(Color.parseColor("#000000"));
        tvShoppingCart.setTextColor(Color.parseColor("#000000"));
        tvHome.setTextColor(Color.parseColor("#000000"));
        setTittle("我的");
    }

    @Override
    public void getUserInfoSuccess(User user) {

    }
}
