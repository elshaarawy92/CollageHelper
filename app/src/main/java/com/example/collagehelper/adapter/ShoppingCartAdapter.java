package com.example.collagehelper.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.example.collagehelper.R;
import com.example.collagehelper.bean.GoodsAdapterBean;
import com.example.collagehelper.bean.GoodsEvent;
import com.example.collagehelper.bean.GoodsInfo2;
import com.example.collagehelper.bean.ShoppingCart;
import com.example.collagehelper.bean.ShoppingCartInfo;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyHolder> {

    private List<GoodsInfo2> list;

    private List<ShoppingCartInfo> list2;

    private OnItemClickListener onItemClickListener;

    private Context context;

    int iAccount = 1;

    int id;

    public interface OnItemClickListener{
        void onItemClick(View view, int position,int id,int cartId);
        void onItemLongClick(View view, int position,int id,int cartId);
        void onCheckBoxClick(View view,int position,boolean isChecked,int total,String sellerPhone,int goodsId,int goodsCount,int scID);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ShoppingCartAdapter(List<GoodsInfo2> list, List<ShoppingCartInfo> list2, Context context) {
        this.list = list;
        this.list2 = list2;
        this.context = context;

    }

    public class MyHolder extends RecyclerView.ViewHolder{

        public ImageView goodsPicture;
        public TextView goodsName;
        public TextView goodsPrice;
        public ImageButton addGoods;
        public ImageButton minusGoods;
        public TextView goodsAccounts;
        public CheckBox cbSelect;
        View view;

        public MyHolder(View itemView) {
            super(itemView);
            goodsPicture = itemView.findViewById(R.id.iv_goods_picture);
            goodsName = itemView.findViewById(R.id.tv_goods_name);
            goodsPrice = itemView.findViewById(R.id.tv_goods_price);
            addGoods = itemView.findViewById(R.id.ib_goods_add);
            minusGoods = itemView.findViewById(R.id.ib_goods_minus);
            goodsAccounts = itemView.findViewById(R.id.tv_goods_account);
            cbSelect = itemView.findViewById(R.id.cb_select);
            view = itemView;
        }
    }

    @NonNull
    @Override
    public ShoppingCartAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_cart,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShoppingCartAdapter.MyHolder holder, final int position) {
        final GoodsInfo2 goodsInfo2 = list.get(position);
        String updateTime = String.valueOf(System.currentTimeMillis()); // 在需要重新获取更新的图片时调用
        Glide.with(context).load(goodsInfo2.getGoodsImg())
                .signature(new ObjectKey(updateTime))
                .into(holder.goodsPicture);
        holder.goodsName.setText(goodsInfo2.getGoodsName());
        holder.goodsPrice.setText(goodsInfo2.getGoodsPrice());
        holder.goodsAccounts.setText(list2.get(position).getGoodsCount() + "");
        holder.addGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iAccount = list2.get(position).getGoodsCount();
                iAccount++;
                holder.goodsAccounts.setText(String.valueOf(iAccount));
            }
        });
        holder.minusGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iAccount = list2.get(position).getGoodsCount();
                if (iAccount > 1){
                    iAccount--;
                    holder.goodsAccounts.setText(String.valueOf(iAccount));
                }
            }
        });

//        holder.cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    //被选中
//                    Intent intent = new Intent("android.intent.shoppingcartreciever");
//                    context.sendBroadcast(intent);
//                }else {
//                    //没有被选
//                    Intent intent = new Intent("android.intent.goodsunselected");
//                    context.sendBroadcast(intent);
//                }
//            }
//        });
        if (onItemClickListener != null){
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    id = list2.get(pos).getId();
                    onItemClickListener.onItemClick(holder.view,pos,goodsInfo2.getGoodsId(),id);
                }
            });
            holder.view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    id = list2.get(pos).getId();
                    onItemClickListener.onItemLongClick(holder.view,pos,goodsInfo2.getGoodsId(),id);
                    return false;
                }
            });
            holder.cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int pos = holder.getLayoutPosition();
                    int total = Integer.valueOf(list.get(pos).getGoodsPrice()) * list2.get(pos).getGoodsCount();
                    String sellerPhone = list.get(pos).getPhone();
                    int goodsId = list.get(pos).getGoodsId();
                    int scId = list2.get(pos).getId();
                    onItemClickListener.onCheckBoxClick(holder.cbSelect,pos,isChecked,total,sellerPhone,goodsId,Integer.valueOf(holder.goodsAccounts.getText().toString()),scId);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
