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
import com.example.collagehelper.bean.Goods;
import com.example.collagehelper.bean.GoodsAdapterBean;
import com.example.collagehelper.bean.GoodsEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.MyHolder> {

    private List<Goods> list;

    private OnItemClickListener onItemClickListener;

    private Context context;

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CollectAdapter(List<Goods> list,Context context) {
        this.list = list;
        this.context = context;
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        public CircleImageView civGoodsImg;
        public TextView tvGoodsName;
        View view;

        public MyHolder(View itemView) {
            super(itemView);
            view = itemView;
            civGoodsImg = itemView.findViewById(R.id.civ_goods_img);
            tvGoodsName = itemView.findViewById(R.id.tv_goods_name2);
        }
    }

    @NonNull
    @Override
    public CollectAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collect,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CollectAdapter.MyHolder holder, final int position) {
        Goods goods = list.get(position);
        holder.tvGoodsName.setText(goods.getName());
        String updateTime = String.valueOf(System.currentTimeMillis()); // 在需要重新获取更新的图片时调用
        Glide.with(context).load(goods.getImg())
                .signature(new ObjectKey(updateTime))
                .into(holder.civGoodsImg);
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
