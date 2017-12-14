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

public class MusicPlayerService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
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
        //内部类持有一个外部类对象，可访问其方法


        public void callPre() {
            pre();
        }

        public void callPlay() {
            play();
        }

        public void callNext() {
            next();
        }

        public void callShowToast(String s) {
            showToast(s);
        }

        public void showToast2(String s) {
            System.out.println(this.getClass().getName() + ".showToast2...");
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        }
    }

    public void pre() {
        System.out.println(this.getClass().getName() + ".pre..");
    }

    public void play() {
        System.out.println(this.getClass().getName() + ".play...");
    }

    public void next() {
        System.out.println(this.getClass().getName() + ".next...");
    }

    public void showToast(String s) {
        System.out.println(this.getClass().getName() + ".showToast...");
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}
