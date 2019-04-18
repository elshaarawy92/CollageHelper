package com.example.collagehelper.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.collagehelper.R;
import com.example.collagehelper.bean.Function;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 客户界面的客户Recyclerview的适配器
 * Created by liang on 2018/10/30
 */
public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {

    private List<Function> list;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CustomerAdapter(List<Function> list){
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_customer,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Function function = list.get(position);
        holder.civCustomerHead.setImageResource(function.getPicture());
        holder.tvCustomerName.setText(function.getText());
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

    public class MyViewHolder extends RecyclerView.ViewHolder {

        View view;
        CircleImageView civCustomerHead;
        TextView tvCustomerName;


        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            civCustomerHead = itemView.findViewById(R.id.civ_customer_head);
            tvCustomerName = itemView.findViewById(R.id.tv_customer_name);
        }
    }
}
