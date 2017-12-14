package hai.com.myapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import hai.com.myapp.listener.MyPhoneStateListener;

/**
 * Created by Administrator on 2017/6/8.
 */

public class PhoneStateListenerService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        System.out.println(this.getClass().getName() + ".onCreate...");
        TelephonyManager telephonyManager = getSystemService(TelephonyManager.class);
        telephonyManager.listen(new MyPhoneStateListener(this), PhoneStateListener.LISTEN_CALL_STATE);
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

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println(this.getClass().getName() + ".onUnbind...");
        return super.onUnbind(intent);
    }

}
