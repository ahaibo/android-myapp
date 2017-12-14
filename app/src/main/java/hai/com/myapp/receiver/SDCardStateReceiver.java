package hai.com.myapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/6/7.
 */

public class SDCardStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("SD卡被操作了。。。");
        String action = intent.getAction();
        System.out.println("Action: " + action);
    }
}
