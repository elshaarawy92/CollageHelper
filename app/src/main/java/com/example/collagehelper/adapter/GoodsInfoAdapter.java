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
import com.example.collagehelper.R;
import com.example.collagehelper.bean.GoodsAdapterBean;
import com.example.collagehelper.bean.GoodsEvent;
import com.example.collagehelper.bean.GoodsInfo;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GoodsInfoAdapter extends RecyclerView.Adapter<GoodsInfoAdapter.MyHolder> {

    private List<GoodsInfo> list;

    private OnItemClickListener onItemClickListener;

    private Context context;

    private Map<Integer,RecyclerView.ViewHolder> viewHolderMap = new HashMap<>();

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public Map getViewHolderMap(){
        return viewHolderMap;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public GoodsInfoAdapter(List<GoodsInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        public View view;
        public ImageView goodsPicture;
        public TextView goodsName;
        public TextView goodsPrice;

        public MyHolder(View itemView) {
            super(itemView);
            view = itemView;
            goodsPicture = itemView.findViewById(R.id.iv_goods_view_picture);
            goodsName = itemView.findViewById(R.id.tv_goods_view_name);
            goodsPrice = itemView.findViewById(R.id.tv_goods_view_price);
        }
    }

    @NonNull
    @Override
    public GoodsInfoAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods_view,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GoodsInfoAdapter.MyHolder holder, final int position) {
        viewHolderMap.put(position,holder);
        GoodsInfo goodsInfo = list.get(position);
        Glide.with(context).load(goodsInfo.getImg()).into(holder.goodsPicture);
        holder.goodsName.setText(goodsInfo.getName());
        holder.goodsPrice.setText(goodsInfo.getPrice());
        if (onItemClickListener != null){
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.view,pos);;
                }
            });
            holder.view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.view,pos);
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
