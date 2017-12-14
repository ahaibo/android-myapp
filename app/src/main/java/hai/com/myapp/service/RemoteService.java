package hai.com.myapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import hai.com.myapp.iservice.IRemoteService;


/**
 * Created by Administrator on 2017/6/8.
 */

public class RemoteService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
//        return null;
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

    public class MyBinder extends IRemoteService.Stub {
        //binder ...
        //内部类持有一个外部类对象，可访问其方法
        public void callRemoteMethod() {
            System.out.println(this.getClass().getName() + ".callRemoteMethod...");
            remoteMethod();
        }

    }

    public void remoteMethod() {
        System.out.println(this.getClass().getName() + ".remoteMethod...");
    }
}
