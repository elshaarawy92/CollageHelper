package com.example.collagehelper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.example.collagehelper.R;
import com.example.collagehelper.bean.OrderAdapterBean1;
import com.example.collagehelper.bean.OrderAdapterBean2;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    private List<OrderAdapterBean1> list1;
    private List<OrderAdapterBean2> list2;
    private Context context;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
        void onReceiveClick(View view,int position);
        void onCommentClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public OrderAdapter(List<OrderAdapterBean1> list1, List<OrderAdapterBean2> list2, Context context){
        this.list1 = list1;
        this.list2 = list2;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        OrderAdapterBean1 order1 = list1.get(position);
        OrderAdapterBean2 order2 = list2.get(position);
        String updateTime = String.valueOf(System.currentTimeMillis()); // 在需要重新获取更新的图片时调用
        Glide.with(context).load(order2.getImg())
                .signature(new ObjectKey(updateTime))
                .into(holder.ivOrderImg);
        holder.tvOrderName.setText(order2.getName());
        holder.tvOrderPrice.setText("单价:" + order2.getPrice() + "元");
        holder.tvOrderAccount.setText("数量:" + order1.getAccount());
        holder.tvOrderTotal.setText("总计:" + order1.getTotal() + "元");
        holder.tvOrderTime.setText("时间:" + order1.getTime());
        holder.tvOrderStatus.setText(order1.getStatus());
        int p = holder.getLayoutPosition();
        if (list1.get(p).getStatus().equals("待收货")){
            holder.tvOrderStatus.setVisibility(View.GONE);
            holder.btnConfirmReceive.setVisibility(View.VISIBLE);
            holder.btnConfirmReceive.setText("确认收货");
            if (onItemClickListener != null){
                holder.btnConfirmReceive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        onItemClickListener.onReceiveClick(holder.btnConfirmReceive,pos);
                    }
                });
            }
        }else if (list1.get(p).getStatus().equals("待评价")){
            holder.tvOrderStatus.setVisibility(View.GONE);
            holder.btnConfirmReceive.setVisibility(View.VISIBLE);
            holder.btnConfirmReceive.setText("去评价");
            if (onItemClickListener != null){
                holder.btnConfirmReceive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        onItemClickListener.onCommentClick(holder.btnConfirmReceive,pos);
                    }
                });
            }
        }
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
        return list2.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView ivOrderImg;
        TextView tvOrderName;
        TextView tvOrderPrice;
        TextView tvOrderAccount;
        TextView tvOrderTotal;
        TextView tvOrderTime;
        TextView tvOrderStatus;
        Button btnConfirmReceive;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ivOrderImg = itemView.findViewById(R.id.iv_order_img);
            tvOrderName = itemView.findViewById(R.id.tv_order_name);
            tvOrderPrice = itemView.findViewById(R.id.tv_order_price);
            tvOrderAccount = itemView.findViewById(R.id.tv_order_account);
            tvOrderTotal = itemView.findViewById(R.id.tv_order_total);
            tvOrderTime = itemView.findViewById(R.id.tv_order_time);
            tvOrderStatus = itemView.findViewById(R.id.tv_order_status);
            btnConfirmReceive = itemView.findViewById(R.id.btn_confirm_receive);
        }
    }
}
