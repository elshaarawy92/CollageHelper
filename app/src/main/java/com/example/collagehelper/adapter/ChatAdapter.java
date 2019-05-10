package com.example.collagehelper.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.example.collagehelper.bean.ChatDO;
import com.example.collagehelper.bean.OrderAdapterBean2;
import com.example.collagehelper.bean.User;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    private List<ChatDO> chatList;
    private List<User> userList;
    private List<String> startList = new ArrayList<>();
    private Context context;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ChatAdapter(List<ChatDO> chatList, List<User> userList, Context context){
        this.chatList = chatList;
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        ChatDO chatDO = chatList.get(position);
        User user = userList.get(position);
        String updateTime = String.valueOf(System.currentTimeMillis()); // 在需要重新获取更新的图片时调用
        Glide.with(context).load(user.getData().getHead())
                .signature(new ObjectKey(updateTime))
                .into(holder.civHead);
        holder.tvName.setText(user.getData().getPhone());
        holder.tvTime.setText(chatDO.getTime());
        holder.tvMessage.setText(chatDO.getMessage());
        startList.add(chatDO.getStart());
        if (startList.get(position).equals("客户")){
            holder.tvMessage.setTextColor(Color.parseColor("#1e90ff"));
        }else {
            holder.tvMessage.setTextColor(Color.parseColor("#ff0000"));
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
        return chatList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        View view;
        CircleImageView civHead;
        TextView tvName;
        TextView tvTime;
        TextView tvMessage;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            civHead = itemView.findViewById(R.id.civ_chat_head);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_chat_time);
            tvMessage = itemView.findViewById(R.id.tv_chat_message);
        }
    }
}
