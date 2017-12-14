package hai.com.myapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/6/8.
 */

public class BindService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public void showToast(String s) {
        System.out.println(this.getClass().getName() + ".showToast...");
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println(this.getClass().getName() + ".onCreate...");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println(this.getClass().getName() + ".onStartCommand...");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println(this.getClass().getName() + ".onDestroy...");
    }

    public class MyBinder extends Binder {
        //binder ...
        //内部类持有一个外部类对象，可访问其方法
        public void callShowToast(String s) {
            showToast(s);
        }

        public void showToast2(String s) {
            System.out.println(this.getClass().getName() + ".showToast2...");
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        }
    }
}
