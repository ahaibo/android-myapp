package hai.com.myapp.connection;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import hai.com.myapp.service.BindService;

/**
 * Created by Administrator on 2017/6/8.
 */

public class MyBindServiceConnection implements ServiceConnection {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        System.out.println("onServiceConnected. ComponentName: " + name);
        BindService.MyBinder myBinder = (BindService.MyBinder) service;
        myBinder.callShowToast("hello");
        myBinder.showToast2("hi...");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        System.out.println("onServiceDisconnected. ComponentName: " + name);
    }
}
