package com.example.collagehelper.activity.customer.assemble.view;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.collagehelper.R;
import com.example.collagehelper.activity.customer.assemble.presenter.AssemblePresenter;
import com.example.collagehelper.adapter.AssembleAdapter2;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.bean.APDO;
import com.example.collagehelper.bean.Assemble;
import com.example.collagehelper.bean.AssembleAdapterBean;
import com.example.collagehelper.bean.GoodsAllInfo;
import com.example.collagehelper.bean.OrderAdapterBean2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssembleActivity extends BaseActivity implements IAssembleView {
    private int i = 0;
    private int j = 0;
    private int k = 0;

    private AssemblePresenter presenter;
    private RecyclerView rvAssemble;
    private List<String> assembleIdList = new ArrayList<>();
    private List<String> statusList = new ArrayList<>();
    private List<AssembleAdapterBean> list1 = new ArrayList<>();
    private List<Integer> goodsIdList = new ArrayList<>();
    private List<Integer> nopList = new ArrayList<>();
    private List<OrderAdapterBean2> list2 = new ArrayList<>();
    private AssembleAdapter2 adapter;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assemble);
        hideActionBar();
        setTittle("拼单管理");
        presenter = new AssemblePresenter(this);
        rvAssemble = findViewById(R.id.rv_manage_assemble);
        presenter.getAssemble(phone);
    }

    @Override
    public void getAssembleSuccess(List<APDO> list) {
        if (list == null){
            return;
        }
        for (int i = 0; i < list.size(); i++){
            assembleIdList.add(list.get(i).getAssembleId());
            statusList.add(list.get(i).getStatus());
        }
        getAssemble(assembleIdList);
    }

    private void getAssemble(List<String> list){
        presenter.getByAssembleId(list.get(i));
        i++;
    }

    @Override
    public void getAssembleFailure() {

    }

    @Override
    public void getByAssembleIdSuccess(List<Assemble> list) {
        for (int i = 0; i < list.size(); i++){
            AssembleAdapterBean assembleAdapterBean = new AssembleAdapterBean();
            assembleAdapterBean.setId(list.get(i).getId());
            assembleAdapterBean.setAccount(list.get(i).getGoodsCount() + "");
            assembleAdapterBean.setTime(list.get(i).getTime());
            assembleAdapterBean.setTotal(list.get(i).getMoney());
            assembleAdapterBean.setGoodsId(list.get(i).getGoodsId());
            assembleAdapterBean.setStatus(list.get(i).getStatus());
            list1.add(assembleAdapterBean);
            goodsIdList.add(list.get(i).getGoodsId());
        }
        if (i != assembleIdList.size()){
            getAssemble(assembleIdList);
            return;
        }
        getGoods(goodsIdList);
    }

    private void getGoods(List<Integer> list){
        presenter.getGodsById(list.get(j));
        j++;
    }

    @Override
    public void getByAssembleIdFailure() {

    }

    @Override
    public void getGoodsSuccess(GoodsAllInfo goodsAllInfo) {
        OrderAdapterBean2 orderAdapterBean2 = new OrderAdapterBean2();
        orderAdapterBean2.setImg(goodsAllInfo.getData().getGoodsImg());
        orderAdapterBean2.setName(goodsAllInfo.getData().getGoodsName());
        orderAdapterBean2.setPrice(goodsAllInfo.getData().getGoodsPrice());
        orderAdapterBean2.setSellerPhone(goodsAllInfo.getData().getPhone());
        list2.add(orderAdapterBean2);
        if (j != assembleIdList.size()){
            getGoods(goodsIdList);
            return;
        }
        getAp(assembleIdList);
    }

    private void getAp(List<String> list){
        presenter.getAp(list.get(k));
        k++;
    }

    @Override
    public void getGoodsFailure() {

    }

    @Override
    public void getAPSuccess(List<APDO> list) {
        int account;
        account = list.size();
        nopList.add(account);
        if (k != assembleIdList.size()){
            getAp(assembleIdList);
            return;
        }
        adapter = new AssembleAdapter2(list1,list2,nopList,statusList,AssembleActivity.this);
        manager = new LinearLayoutManager(this);
        rvAssemble.setAdapter(adapter);
        rvAssemble.setLayoutManager(manager);
        adapter.setOnItemClickListener(new AssembleAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position, int nop, Button status, final List<String> money, final List<Integer> goodsCount, final List<String> sellerPhone) {
                if (nop < 3){
                    Toast.makeText(AssembleActivity.this,"人数不足三人，拼团未完成",Toast.LENGTH_SHORT).show();
                    return;
                }else if (nop == 3){
                    if (status.getText().toString().equals("已支付")){
                        Toast.makeText(AssembleActivity.this,"已支付过拼单",Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(AssembleActivity.this);
                        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                presenter.updateApById(assembleIdList.get(position),phone,"已支付");
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                                String time = df.format(new Date());
                                SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");
                                String time2 = df2.format(new Date());
                                String orderId = time2 + BaseActivity.phone;
                                presenter.addOrder(phone,sellerPhone.get(position),orderId,time,String.valueOf(Integer.valueOf(money.get(position)) * 85 / 100),goodsIdList.get(position),goodsCount.get(position));
                                refresh();
                                Intent intent = new Intent();
                                intent.setAction("android.intent.neworder");
                                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                                sendBroadcast(intent);
                            }
                        });
                        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.setTitle("确认支付吗？");
                        builder.show();
                    }
                }
            }

            @Override
            public void onItemLongClick(View view, int position, int nop, Button status) {

            }
        });
    }

    @Override
    public void getAPFailure() {

    }

    @Override
    public void updateApSuccess() {

    }

    @Override
    public void updateApFailure() {

    }

    @Override
    public void addOrderSuccess() {
        Toast.makeText(AssembleActivity.this,"支付成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addOrderFailure() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void refresh(){
        i = 0;
        j = 0;
        k = 0;
        assembleIdList.clear();
        statusList.clear();
        list2.clear();
        list1.clear();
        goodsIdList.clear();
        nopList.clear();
        rvAssemble.setAdapter(null);
        presenter.getAssemble(phone);
    }
}
