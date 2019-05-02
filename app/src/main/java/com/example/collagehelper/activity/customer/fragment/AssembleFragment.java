package com.example.collagehelper.activity.customer.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.collagehelper.R;
import com.example.collagehelper.activity.customer.fragment.presenter.AssemblePresenter;
import com.example.collagehelper.activity.customer.fragment.view.IAssembleView;
import com.example.collagehelper.adapter.AssembleAdapter;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.base.BaseFragment;
import com.example.collagehelper.bean.APDO;
import com.example.collagehelper.bean.Assemble;
import com.example.collagehelper.bean.AssembleAdapterBean;
import com.example.collagehelper.bean.GoodsAllInfo;
import com.example.collagehelper.bean.OrderAdapterBean2;

import java.util.ArrayList;
import java.util.List;

public class AssembleFragment extends BaseFragment implements IAssembleView {
    private EditText etAssemble;
    private RecyclerView rvAssemble;
    private AssemblePresenter presenter;
    private List<AssembleAdapterBean> bList1 = new ArrayList<>();
    private List<OrderAdapterBean2> bList2 = new ArrayList<>();
    private List<Integer> iList = new ArrayList<>();
    OrderAdapterBean2 orderAdapterBean2;
    private AssembleAdapter adapter;
    private LinearLayoutManager manager;
    private int i = 0;
    private IntentFilter filter;
    private AssembleReciever reciever;
    private List<String> assembleIdList = new ArrayList<>();
    private int j = 0;
    private List<Integer> list0 = new ArrayList<>();
    private List<AssembleAdapterBean> bList11 = new ArrayList<>();
    private List<OrderAdapterBean2> bList21 = new ArrayList<>();
    private List<Integer> list1 = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assemble,container,false);
        etAssemble = view.findViewById(R.id.et_search_assemble);
        rvAssemble = view.findViewById(R.id.rv_assemble);
        presenter = new AssemblePresenter(this);
        presenter.getAssemble();
        filter = new IntentFilter();
        filter.addAction("android.intent.assemblereciever");
        reciever = new AssembleReciever();
        getActivity().registerReceiver(reciever,filter);
        return view;
    }

    @Override
    public void getAssembleSuccess(List<Assemble> list) {
        for (int i = 0;i < list.size();i++){
            AssembleAdapterBean assembleAdapterBean = new AssembleAdapterBean();
            assembleAdapterBean.setId(list.get(i).getId());
            assembleAdapterBean.setAccount(list.get(i).getGoodsCount() + "");
            assembleAdapterBean.setTime(list.get(i).getTime());
            assembleAdapterBean.setTotal(list.get(i).getMoney());
            assembleAdapterBean.setGoodsId(list.get(i).getGoodsId());
            assembleAdapterBean.setStatus(list.get(i).getStatus());
            bList1.add(assembleAdapterBean);
            iList.add(list.get(i).getGoodsId());
            assembleIdList.add(list.get(i).getAssembleId());
        }
        getGoods(iList);
    }

    private void getGoods(List<Integer> list){
        presenter.getGodsById(list.get(i));
        i++;
    }

    private void getAP(List<String> list){
        presenter.getAp(list.get(j));
        j++;
    }

    @Override
    public void getAssembleFailure() {

    }

    @Override
    public void getGoodsSuccess(GoodsAllInfo goodsAllInfo) {
        orderAdapterBean2 = new OrderAdapterBean2();
        orderAdapterBean2.setImg(goodsAllInfo.getData().getGoodsImg());
        orderAdapterBean2.setName(goodsAllInfo.getData().getGoodsName());
        orderAdapterBean2.setPrice(goodsAllInfo.getData().getGoodsPrice());
        bList2.add(orderAdapterBean2);
        if (i != bList1.size()){
            getGoods(iList);
            return;
        }
        getAP(assembleIdList);
    }

    @Override
    public void getGoodsFailure() {

    }

    @Override
    public void getAPSuccess(final List<APDO> list) {
        int account;
        account = list.size();
        list0.add(account);
        if (j != bList1.size()){
            getAP(assembleIdList);
            return;
        }
        bList11.clear();
        bList21.clear();
        list1.clear();
        bList11.addAll(bList1);
        bList21.addAll(bList2);
        list1.addAll(list0);
        adapter = new AssembleAdapter(bList11,bList21,this.list1,getContext());
        manager = new LinearLayoutManager(getContext());
        rvAssemble.setAdapter(adapter);
        rvAssemble.setLayoutManager(manager);
        adapter.setOnItemClickListener(new AssembleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (list0.get(position) == 3){
                    Toast.makeText(getContext(),"人数已满",Toast.LENGTH_SHORT).show();
                }else if (list0.get(position) == 2){
                    presenter.joinAssemble(assembleIdList.get(position),BaseActivity.phone);
                    Intent intent = new Intent();
                    intent.setAction("android.intent.assemblefinish");
                    intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    getActivity().sendBroadcast(intent);
                } else {
                    presenter.joinAssemble(assembleIdList.get(position),BaseActivity.phone);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    public void getAPFailure() {

    }

    @Override
    public void joinSuccess() {
        Toast.makeText(getContext(),"参加拼团成功",Toast.LENGTH_SHORT).show();
        refresh();
    }

    @Override
    public void alreadyJoin() {
        Toast.makeText(getContext(),"已参加过拼团",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void joinFailure() {
        Toast.makeText(getContext(),"参加拼团失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bList1.clear();
        bList2.clear();
        iList.clear();
        assembleIdList.clear();
        list0.clear();
        i = 0;
        j = 0;
        rvAssemble.setAdapter(null);
        getActivity().unregisterReceiver(reciever);
    }

    @Override
    public void onStop() {
        super.onStop();
        bList1.clear();
        bList2.clear();
        iList.clear();
        assembleIdList.clear();
        list0.clear();
        i = 0;
        j = 0;
    }

    private void refresh(){
        rvAssemble.setAdapter(null);
        bList1.clear();
        bList2.clear();
        iList.clear();
        assembleIdList.clear();
        list0.clear();
        i = 0;
        presenter.getAssemble();
        j = 0;
    }

    class AssembleReciever extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            refresh();
        }
    }
}
