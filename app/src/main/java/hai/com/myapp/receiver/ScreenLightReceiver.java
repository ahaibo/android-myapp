package hai.com.myapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/6/7.
 */

public class ScreenLightReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("android.intent.action.SCREEN_OFF")) {
            System.out.println("屏幕关闭");
        } else if (action.equals("android.intent.action.SCREEN_ON")) {
            System.out.println("屏幕点亮");
        }
    }
}
