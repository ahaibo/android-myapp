package hai.com.myapp.notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import hai.com.myapp.R;

/**
 * Created by Administrator on 2017/6/10.
 */

public class NotificationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }

    public void send(View view) {
        //设置通知信息
        Notification.Builder builder = new Notification.Builder(this);
        builder.setTicker("账户通知：恭喜你人品爆表，中奖1亿附美女一枚，请速联系110兑换奖品，过期不候！！");
        builder.setAutoCancel(true);
        builder.setContentTitle("账户通知");
        builder.setContentText("恭喜你人品爆表，中奖1亿附美女一枚，请速联系110兑换奖品，过期不候！！");
        //通知 icon 必须设置，否则不候显示
        builder.setSmallIcon(R.mipmap.ic_launcher);

        //设置点击通知打开的 Intent
        Intent intent = new Intent(this, NotificationActivity.class);
        //PendingIntent 操作什么就获取什么：PendingIntent.getActivity(...) PendingIntent.getBroadcast(...); PendingIntent.getService(...)
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        //获取 NotificationManager 发送通知
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = builder.build();
        manager.notify(1, notification);
    }
}
