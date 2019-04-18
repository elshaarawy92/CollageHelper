package com.example.collagehelper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.example.collagehelper.R;
import com.example.collagehelper.bean.GoodsInfo;

import java.util.List;

public class GoodsInfoAdapter extends RecyclerView.Adapter<GoodsInfoAdapter.MyHolder> {

    private List<GoodsInfo> list;

    private OnItemClickListener onItemClickListener;

    private Context context;

    public interface OnItemClickListener{
        void onItemClick(View view, int position,int id);
        void onItemLongClick(View view, int position,int id);
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
        public int id;

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
        GoodsInfo goodsInfo = list.get(position);
        String updateTime = String.valueOf(System.currentTimeMillis()); // 在需要重新获取更新的图片时调用
        Glide.with(context).load(goodsInfo.getImg())
                .signature(new ObjectKey(updateTime))
                .into(holder.goodsPicture);
        holder.goodsName.setText(goodsInfo.getName());
        holder.goodsPrice.setText(goodsInfo.getPrice());
        holder.id = goodsInfo.getId();
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
