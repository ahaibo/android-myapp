package hai.com.myapp;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import hai.com.myapp.receiver.ScreenLightReceiver;

public class DynamicBroadcastActivity extends AppCompatActivity {

    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println(this.getClass().getName());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //广播接收者对象
        receiver = new ScreenLightReceiver();

        //意图过滤器对象
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.SCREEN_OFF");
        filter.addAction("android.intent.action.SCREEN_ON");

        //动态注册一个广播接收者
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //动态注册的广播接收者在当前activity销毁的时候需要注销掉
        unregisterReceiver(receiver);
    }

}
