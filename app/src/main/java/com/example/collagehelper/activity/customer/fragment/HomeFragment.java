package com.example.collagehelper.activity.customer.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.collagehelper.R;
import com.example.collagehelper.activity.customer.main.presenter.Main2Presenter;
import com.example.collagehelper.activity.customer.main.view.IMainView2;
import com.example.collagehelper.activity.customer.searchgoodsbyname.view.SearchGoodsByNameActivity;
import com.example.collagehelper.activity.customer.sellerdetails.view.SellerDetailsActivity;
import com.example.collagehelper.adapter.CollectedSellerAdapter;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.bean.CTSDO;
import com.example.collagehelper.bean.CollectedSeller;
import com.example.collagehelper.bean.User;
import com.example.collagehelper.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements ViewPager.OnPageChangeListener,IMainView2 {

    private EditText etSearchGoods;
    private ViewPager viewPager;
    private int[] imageResIds;
    private ArrayList<ImageView> imageViewList;
    private LinearLayout ll_point_container;
    private String[] contentDescs;
    private TextView tv_desc;
    private int previousSelectedPosition = 0;
    boolean isRunning = false;
    private RecyclerView recyclerView;

    private CollectedSellerAdapter adapter;
    private LinearLayoutManager manager;

    private String name;
    private Main2Presenter presenter;

    private List<CollectedSeller> list = new ArrayList<>();
    private List<CollectedSeller> list1 = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        presenter = new Main2Presenter(this);
        initView(view);
        presenter.getCollectedSeller(BaseActivity.phone);
        initData();
        initAdapter();
        // 开启轮询
        new Thread() {
            public void run() {
                isRunning = true;
                while (isRunning) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 往下跳一位
                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            System.out.println("设置当前位置: " + viewPager.getCurrentItem());
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        }
                    });
                }
            }
            ;
        }.start();
        etSearchGoods.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    //隐藏软键盘
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                    name = etSearchGoods.getText().toString().trim();
                    Intent intent = new Intent(getContext(),SearchGoodsByNameActivity.class);
                    intent.putExtra("name",name);
                    startActivityForResult(intent,1);
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == 1){
                    refresh();
                }
                break;

        }
    }

    private void refresh(){
        list.clear();
        presenter.getCollectedSeller(BaseActivity.phone);
    }

    private void initView(View view){
        etSearchGoods = view.findViewById(R.id.et_search_goods);
        viewPager = view.findViewById(R.id.viewpager);
        ll_point_container = view.findViewById(R.id.ll_point_container);
        tv_desc = view.findViewById(R.id.tv_desc);
        recyclerView = view.findViewById(R.id.rv_seller);
        viewPager.addOnPageChangeListener(this);
    }

    private void initData(){
        imageResIds = new int[]{R.drawable.apple,R.drawable.banana,R.drawable.grapes,R.drawable.mango,R.drawable.orange};
        contentDescs = new String[]{"苹果红彤彤","香蕉黄澄澄","葡萄酸溜溜","芒果好好吃","橘子瓣瓣香"};
        imageViewList = new ArrayList<>();
        ImageView imageView;
        View pointView;
        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < imageResIds.length; i++) {
            // 初始化要显示的图片对象
            imageView = new ImageView(getContext());
            imageView.setBackgroundResource(imageResIds[i]);
            imageViewList.add(imageView);

            // 加小白点, 指示器
            pointView = new View(getContext());
            pointView.setBackgroundResource(R.drawable.selector_bg_point);
            layoutParams = new LinearLayout.LayoutParams(5, 5);
            if (i != 0)
                layoutParams.leftMargin = 10;
            // 设置默认所有都不可用
            pointView.setEnabled(false);
            ll_point_container.addView(pointView, layoutParams);
        }
    }

    private void initAdapter(){
        ll_point_container.getChildAt(0).setEnabled(true);
        tv_desc.setText(contentDescs[0]);
        previousSelectedPosition = 0;

        // 设置适配器
        viewPager.setAdapter(new MyAdapter());

        // 默认设置到中间的某个位置
        int pos = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % imageViewList.size());
        // 2147483647 / 2 = 1073741823 - (1073741823 % 5)
        viewPager.setCurrentItem(5000000); // 设置到某个位置
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        System.out.println("onPageSelected: " + position);
        int newPosition = position % imageViewList.size();

        //设置文本
        tv_desc.setText(contentDescs[newPosition]);
        // 把之前的禁用, 把最新的启用, 更新指示器
        ll_point_container.getChildAt(previousSelectedPosition).setEnabled(false);
        ll_point_container.getChildAt(newPosition).setEnabled(true);

        // 记录之前的位置
        previousSelectedPosition = newPosition;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }

    @Override
    public void getUserInfoSuccess(User user) {

    }

    @Override
    public void getCollectedSellerSuccess(CTSDO ctsdo) {
        List<String> list = new ArrayList<>();
        for (int i = 0;i < ctsdo.getData().size();i++){
            list.add(ctsdo.getData().get(i).getSellerPhone());
        }
        for (int j = 0; j < list.size(); j++){
            presenter.getSellerByPhone(list.get(j));
        }
    }

    @Override
    public void getCollectedSellerFailure() {

    }

    @Override
    public void getCollectedSellerNull() {

    }

    @Override
    public void getSellerSuccess(final User user) {
        CollectedSeller collectedSeller = new CollectedSeller();
        collectedSeller.setHead(user.getData().getHead());
        collectedSeller.setName(user.getData().getName());
        list.add(collectedSeller);
        list1.clear();
        list1.addAll(list);
        adapter = new CollectedSellerAdapter(list1,getContext());
        manager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        adapter.setOnItemClickListener(new CollectedSellerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(),SellerDetailsActivity.class);
                intent.putExtra("sellerphone",user.getData().getPhone());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        // 3. 指定复用的判断逻辑, 固定写法
        @Override
        public boolean isViewFromObject(View view, Object object) {
//			System.out.println("isViewFromObject: "+(view == object));
            // 当划到新的条目, 又返回来, view是否可以被复用.
            // 返回判断规则
            return view == object;
        }

        // 1. 返回要显示的条目内容, 创建条目
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            System.out.println("instantiateItem初始化: " + position);

//			newPosition = position % 5
            int newPosition = position % imageViewList.size();

            ImageView imageView = imageViewList.get(newPosition);
            // a. 把View对象添加到container中
            container.addView(imageView);
            // b. 把View对象返回给框架, 适配器
            return imageView; // 必须重写, 否则报异常
        }

        // 2. 销毁条目
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // object 要销毁的对象
            System.out.println("destroyItem销毁: " + position);
            container.removeView((View) object);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        list.clear();
    }
}