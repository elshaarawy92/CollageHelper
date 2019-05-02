package com.example.collagehelper.reciever;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import com.example.collagehelper.R;
import com.example.collagehelper.activity.customer.assemble.view.AssembleActivity;
import com.example.collagehelper.base.BaseActivity;

public class AssembleFinishReciver extends BroadcastReceiver {
    private NotificationManager manager;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (BaseActivity.how == 0){
            manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Intent intent1 = new Intent(context,AssembleActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent1,0);
            Notification notification = new Notification.Builder(context)
                    .setContentTitle("你有拼单拼团成功啦！")
                    .setContentText("查看详情")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher))
                    .setAutoCancel(true)
                    .build();
            manager.notify(1,notification);
        }
    }
}
