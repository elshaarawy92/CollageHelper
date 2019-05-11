package com.example.collagehelper.activity.customer.fragment;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collagehelper.MyClickListener;
import com.example.collagehelper.R;
import com.example.collagehelper.activity.customer.fragment.presenter.ShoppingCartPresenter;
import com.example.collagehelper.activity.customer.fragment.view.IShoppingCartView;
import com.example.collagehelper.activity.customer.goodsdetails.view.GoodsDetailActivity;
import com.example.collagehelper.activity.customer.sellerdetails.view.SellerDetailsActivity;
import com.example.collagehelper.adapter.CollectedSellerAdapter;
import com.example.collagehelper.adapter.ShoppingCartAdapter;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.base.BaseFragment;
import com.example.collagehelper.bean.GoodsAllInfo;
import com.example.collagehelper.bean.GoodsInfo2;
import com.example.collagehelper.bean.SCOrder;
import com.example.collagehelper.bean.ShoppingCartInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCartFragment extends BaseFragment implements IShoppingCartView {
    private RecyclerView rvShoppingCart;
    private ShoppingCartPresenter presenter;
    private LinearLayoutManager manager;
    private ShoppingCartAdapter adapter;
    private List<ShoppingCartInfo> list = new ArrayList<>();
    private List<GoodsInfo2> list2 = new ArrayList<>();
    private List<ShoppingCartInfo> list1 = new ArrayList<>();
    private List<GoodsInfo2> list21 = new ArrayList<>();
    private IntentFilter filter;
    private SCReciever scReciever;
    private List<Integer> goodsIdList = new ArrayList<>();
    private TextView tvPrice;
    private Button btnPay;
    private int totalMoney = 0;
    private List<SCOrder> scList = new ArrayList<>();

    private int i = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart,container,false);
        rvShoppingCart = view.findViewById(R.id.rv_shopping_cart);
        tvPrice = view.findViewById(R.id.tv_price);
        tvPrice.setText("0元");
        btnPay = view.findViewById(R.id.btn_pay);
        presenter = new ShoppingCartPresenter(this);
        filter = new IntentFilter();
        filter.addAction("android.intent.shoppingcartreciever");
        scReciever = new SCReciever();
        getActivity().registerReceiver(scReciever,filter);
        presenter.getFromCart(BaseActivity.phone);
        proxyOnClickListener(2, btnPay, new MyClickListener() {
            @Override
            public void onClick(View view) {
                if (scList.size() == 0){
                    Toast.makeText(getContext(),"请先选择要支付的商品",Toast.LENGTH_SHORT).show();
                }else {
                    for (int i = 0; i < scList.size(); i++){
                        presenter.addOrder(scList.get(i).getCustomerPhone(),scList.get(i).getSellerPhone(),scList.get(i).getOrderId(),scList.get(i).getTime(),scList.get(i).getMoney(),scList.get(i).getGoodsId(),scList.get(i).getGoodsCount(),scList.get(i).getStatus());
                        presenter.delete(scList.get(i).getScId());
                    }
                    list.clear();
                    list2.clear();
                    goodsIdList.clear();
                    scList.clear();
                    totalMoney = 0;
                    tvPrice.setText("0元");
                    i = 0;
                    presenter.getFromCart(BaseActivity.phone);
                }
            }
        });
        return view;
    }

    @Override
    public void getFromCartSuccess(List<ShoppingCartInfo> info) {
        list = info;
        for (int i = 0;i < list.size();i++){
            goodsIdList.add(info.get(i).getGoodsId());
        }
        getGoods(goodsIdList);
    }

    private void getGoods(List<Integer> list){
        presenter.getGoodsDetail(list.get(i));
        i++;
    }

    @Override
    public void getFromCartNull() {
            list1.clear();
            list21.clear();
            list1.addAll(list);
            list21.addAll(list2);
            adapter = new ShoppingCartAdapter(list21,list1,getContext());
            rvShoppingCart.setAdapter(adapter);
            return;
    }

    @Override
    public void getFromCartFailure() {

    }

    @Override
    public void getGoodsSuccess(GoodsAllInfo goodsAllInfo) {
        GoodsInfo2 goodsInfo2 = new GoodsInfo2();
        goodsInfo2.setPhone(goodsAllInfo.getData().getPhone());
        goodsInfo2.setGoodsPrice(goodsAllInfo.getData().getGoodsPrice());
        goodsInfo2.setGoodsImg(goodsAllInfo.getData().getGoodsImg());
        goodsInfo2.setGoodsDes(goodsAllInfo.getData().getGoodsDes());
        goodsInfo2.setGoodsName(goodsAllInfo.getData().getGoodsName());
        goodsInfo2.setGoodsId(goodsAllInfo.getData().getGoodsId());
        list2.add(goodsInfo2);
        if (i != goodsIdList.size()){
            getGoods(goodsIdList);
            return;
        }
        list1.clear();
        list21.clear();
        list1.addAll(list);
        list21.addAll(list2);
        adapter = new ShoppingCartAdapter(list21,list1,getContext());
        manager = new LinearLayoutManager(getContext());
        rvShoppingCart.setAdapter(adapter);
        rvShoppingCart.setLayoutManager(manager);
        adapter.setOnItemClickListener(new ShoppingCartAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int id,int catId) {
                Intent intent = new Intent(getContext(),GoodsDetailActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position, int id, final int cartId) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setNegativeButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                        builder1.setNegativeButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                presenter.delete(cartId);
                                list.clear();
                                list2.clear();
                                goodsIdList.clear();
                                i = 0;
                                presenter.getFromCart(BaseActivity.phone);
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

            @Override
            public void onCheckBoxClick(View view, int position, boolean isChecked, int total,String sellerPhone,int goodsId,int goodsCount,int scId) {
                if (isChecked){
                    totalMoney = totalMoney + total;
                    tvPrice.setText(totalMoney + "元");
                    SCOrder scOrder = new SCOrder();
                    scOrder.setCustomerPhone(BaseActivity.phone);
                    scOrder.setSellerPhone(sellerPhone);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                    String time = df.format(new Date());
                    SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");
                    String time2 = df2.format(new Date());
                    String orderId = time2 + BaseActivity.phone + position;
                    scOrder.setOrderId(orderId);
                    scOrder.setTime(time);
                    scOrder.setMoney(total + "");
                    scOrder.setGoodsId(goodsId);
                    scOrder.setGoodsCount(goodsCount);
                    scOrder.setStatus("待发货");
                    scOrder.setScId(scId);
                    scList.add(scOrder);
                }else {
                    totalMoney = totalMoney - total;
                    tvPrice.setText(totalMoney + "元");
                    for (int i=0;i<scList.size();i++){
                        if (scList.get(i).getScId() == scId){
                            scList.remove(i);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void getGoodsFailure() {

    }

    @Override
    public void deleteSuccess() {
        Toast.makeText(getContext(),"删除成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteFailure() {
        Toast.makeText(getContext(),"删除失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addOrderSuccess() {
        Toast.makeText(getContext(),"支付成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addOrderFailure() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        list.clear();
        list2.clear();
        goodsIdList.clear();
        i = 0;
        getActivity().unregisterReceiver(scReciever);
    }

    public void refresh(){
        presenter.getFromCart(BaseActivity.phone);
    }

    class SCReciever extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            refresh();
        }
    }
}
