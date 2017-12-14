package hai.com.myapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import hai.com.myapp.MainActivity;

/**
 * Created by Administrator on 2017/6/7.
 */

public class BootStartReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("开机启动 Receiver ...");

        Intent newIntent = new Intent(context, MainActivity.class);
        //指定在创建activity的同时创建一个新的任务栈
        newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(newIntent);
    }
}
