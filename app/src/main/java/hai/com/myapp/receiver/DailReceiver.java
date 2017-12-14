package hai.com.myapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/6/7.
 */

public class DailReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("Called Phone...");
        SharedPreferences sharedPreferences = context.getSharedPreferences("info", Context.MODE_PRIVATE);
        String prefix = "17951";
        if (null != sharedPreferences) {
            //sharedPreferences.edit().putString("prefix", "@value");
            prefix = sharedPreferences.getString("prefix", prefix);
        }
        //获取拨打的电话
        String number = getResultData();
        //设置前缀
        setResultData(prefix + number);
    }
}
