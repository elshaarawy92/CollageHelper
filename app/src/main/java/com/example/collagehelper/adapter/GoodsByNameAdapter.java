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

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.example.collagehelper.R;
import com.example.collagehelper.bean.GoodsAdapterBean;
import com.example.collagehelper.bean.GoodsAllInfo;
import com.example.collagehelper.bean.GoodsEvent;
import com.example.collagehelper.bean.GoodsInfo2;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsByNameAdapter extends RecyclerView.Adapter<GoodsByNameAdapter.MyHolder> {

    private List<GoodsInfo2> list;

    private OnItemClickListener onItemClickListener;

    private Context context;

    public interface OnItemClickListener{
        void onItemClick(View view, int position,int id);
        void onItemLongClick(View view, int position,int id);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public GoodsByNameAdapter(List<GoodsInfo2> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        public ImageView goodsPicture;
        public TextView goodsName;
        public TextView goodsPrice;
        TextView goodsSeller;
        View view;
        public int id;

        public MyHolder(View itemView) {
            super(itemView);
            view = itemView;
            goodsPicture = itemView.findViewById(R.id.iv_goods_view_picture2);
            goodsName = itemView.findViewById(R.id.tv_goods_view_name2);
            goodsPrice = itemView.findViewById(R.id.tv_goods_view_price2);
            goodsSeller = itemView.findViewById(R.id.tv_seller_name);
        }
    }

    @NonNull
    @Override
    public GoodsByNameAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods_view2,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GoodsByNameAdapter.MyHolder holder, final int position) {
        GoodsInfo2 goods = list.get(position);
        String updateTime = String.valueOf(System.currentTimeMillis()); // 在需要重新获取更新的图片时调用
        Glide.with(context).load(goods.getGoodsImg())
                .signature(new ObjectKey(updateTime))
                .into(holder.goodsPicture);
        holder.goodsName.setText(goods.getGoodsName());
        holder.goodsPrice.setText(goods.getGoodsPrice());
        holder.goodsSeller.setText("店家: " + goods.getPhone());
        holder.id = goods.getGoodsId();
        if (onItemClickListener != null){
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.view,pos,holder.id);;
                }
            });
            holder.view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.view,pos,holder.id);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
