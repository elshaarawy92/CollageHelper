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
import com.example.collagehelper.bean.AssembleAdapterBean;
import com.example.collagehelper.bean.OrderAdapterBean1;
import com.example.collagehelper.bean.OrderAdapterBean2;
import com.example.collagehelper.bean.OrderAdapterBean3;

import java.util.List;

public class AssembleAdapter extends RecyclerView.Adapter<AssembleAdapter.MyViewHolder> {

    private List<AssembleAdapterBean> list3;
    private List<OrderAdapterBean2> list2;
    private List<Integer> list;
    private Context context;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public AssembleAdapter(List<AssembleAdapterBean> list3, List<OrderAdapterBean2> list2, List<Integer> list, Context context){
        this.list3 = list3;
        this.list2 = list2;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_assemble,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        AssembleAdapterBean order3 = list3.get(position);
        OrderAdapterBean2 order2 = list2.get(position);
        int i = list.get(position);
        String updateTime = String.valueOf(System.currentTimeMillis()); // 在需要重新获取更新的图片时调用
        Glide.with(context).load(order2.getImg())
                .signature(new ObjectKey(updateTime))
                .into(holder.ivOrderImg);
        holder.tvOrderName.setText(order2.getName());
        holder.tvOrderPrice.setText("单价:" + order2.getPrice() + "元");
        holder.tvOrderAccount.setText("数量:" + order3.getAccount());
        holder.tvOrderTotal.setText("总计:" + order3.getTotal() + "元");
        holder.tvOrderTime.setText("时间:" + order3.getTime());
        holder.tvAssembleStatus.setText("已有" + i + "人参加");
        if (onItemClickListener != null){
            holder.btnJoinAssemble.setOnClickListener(new View.OnClickListener() {
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
        TextView tvAssembleStatus;
        Button btnJoinAssemble;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ivOrderImg = itemView.findViewById(R.id.iv_assemble_img);
            tvOrderName = itemView.findViewById(R.id.tv_assemble_name);
            tvOrderPrice = itemView.findViewById(R.id.tv_assemble_price);
            tvOrderAccount = itemView.findViewById(R.id.tv_assemble_account);
            tvOrderTotal = itemView.findViewById(R.id.tv_assemble_total);
            tvOrderTime = itemView.findViewById(R.id.tv_assemble_time);
            tvAssembleStatus = itemView.findViewById(R.id.tv_assemble_status);
            btnJoinAssemble = itemView.findViewById(R.id.btn_join_assemble);
        }
    }
}
