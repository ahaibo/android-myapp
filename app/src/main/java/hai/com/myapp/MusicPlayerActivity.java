package hai.com.myapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import hai.com.myapp.service.MusicPlayerService;

/**
 * startService(Intent)
 * stopService(Intent)
 * <p>
 * bindService(Intent,ServiceConnection,int)
 * unbindService(ServiceConnection)
 */
public class MusicPlayerActivity extends AppCompatActivity {

    private Intent intent;
    private MusicPlayerConnection connection;
    private MusicPlayerService.MyBinder musicControler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        startMusicService();
    }

    public void startMusicService() {
        //使用显示意图开启service
        intent = new Intent(this, MusicPlayerService.class);
        connection = new MusicPlayerConnection();
        //通过混合方式开启服务
        bindService(intent, connection, BIND_AUTO_CREATE);
        startService(intent);
    }

    private class MusicPlayerConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println(this.getClass().getName() + ".onServiceConnected");
            musicControler = (MusicPlayerService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println(this.getClass().getName() + ".onServiceDisconnected");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //当前activity生命周期结束时，解绑通过bindService绑定的服务
        unbindService(connection);
        stopService(intent);
    }

    public void previous(View view) {
        System.out.println(this.getClass().getName() + ".pre...");
        musicControler.callPre();
    }

    public void play(View view) {
        System.out.println(this.getClass().getName() + ".play...");
        musicControler.callPlay();
    }

    public void next(View view) {
        System.out.println(this.getClass().getName() + ".next...");
        musicControler.callNext();
    }
}
