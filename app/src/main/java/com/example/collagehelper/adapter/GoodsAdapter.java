package com.example.collagehelper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collagehelper.R;
import com.example.collagehelper.bean.GoodsAdapterBean;
import com.example.collagehelper.bean.GoodsEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 商品界面的recyclerview的适配器
 * Created by liang on 2018/11/14
 */
public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.MyHolder> {

    private List<GoodsAdapterBean> list;

    private OnItemClickListener onItemClickListener;

    private Context context;

    private Map<Integer,RecyclerView.ViewHolder> viewHolderMap = new HashMap<>();

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view,int position);
    }

    public Map getViewHolderMap(){
        return viewHolderMap;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public GoodsAdapter(List<GoodsAdapterBean> list,Context context) {
        this.list = list;
        this.context = context;
        EventBus.getDefault().register(context);
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        public ImageView goodsPicture;
        public TextView goodsName;
        public TextView goodsPrice;
        public ImageButton addGoods;
        public ImageButton minusGoods;
        public TextView goodsAccounts;

        public MyHolder(View itemView) {
            super(itemView);
            goodsPicture = itemView.findViewById(R.id.iv_goods_picture);
            goodsName = itemView.findViewById(R.id.tv_goods_name);
            goodsPrice = itemView.findViewById(R.id.tv_goods_price);
            addGoods = itemView.findViewById(R.id.ib_goods_add);
            minusGoods = itemView.findViewById(R.id.ib_goods_minus);
            goodsAccounts = itemView.findViewById(R.id.tv_goods_account);
        }
    }

    @NonNull
    @Override
    public GoodsAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GoodsAdapter.MyHolder holder, final int position) {
        viewHolderMap.put(position,holder);
        GoodsAdapterBean goods = list.get(position);
        holder.goodsPicture.setBackgroundResource(goods.getPicture());
        holder.goodsName.setText(goods.getName());
        holder.goodsPrice.setText(goods.getPrice());
        holder.addGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = holder.goodsAccounts.getText().toString();
                int iAccount = Integer.valueOf(account);
                iAccount++;
                account = String.valueOf(iAccount);
                holder.goodsAccounts.setText(account);
                String name = holder.goodsName.getText().toString();
                double price = Double.valueOf(holder.goodsPrice.getText().toString());
                double accountPrice = price * iAccount;
                EventBus.getDefault().post(new GoodsEvent(name,iAccount,price,accountPrice));
            }
        });
        holder.minusGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = holder.goodsAccounts.getText().toString();
                int iAccount = Integer.valueOf(account);
                if (iAccount != 0){
                    iAccount--;
                    account = String.valueOf(iAccount);
                    holder.goodsAccounts.setText(account);
                    String name = holder.goodsName.getText().toString();
                    double price = Double.valueOf(holder.goodsPrice.getText().toString());
                    double accountPrice = price * iAccount;
                    EventBus.getDefault().post(new GoodsEvent(name,iAccount,price,accountPrice));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
