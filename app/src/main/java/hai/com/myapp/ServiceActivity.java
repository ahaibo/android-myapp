package hai.com.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import hai.com.myapp.connection.MyBindServiceConnection;
import hai.com.myapp.service.BindService;
import hai.com.myapp.service.PhoneStateListenerService;

/**
 * startService(Intent)
 * stopService(Intent)
 * <p>
 * bindService(Intent,ServiceConnection,int)
 * unbindService(ServiceConnection)
 */
public class ServiceActivity extends AppCompatActivity {

    private Intent startServiceIntent;
    private MyBindServiceConnection myBindServiceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    public void startService(View view) {
        //使用显示意图开启service
        startServiceIntent = new Intent(this, PhoneStateListenerService.class);
        //通过startService方法开启一个服务
        startService(startServiceIntent);
    }

    public void stopService(View view) {
        stopService(startServiceIntent);
    }

    public void bindService(View view) {
        //使用显示意图开启service
        Intent bindServiceIntent = new Intent(this, BindService.class);
        myBindServiceConnection = new MyBindServiceConnection();
        //通过startService方法开启一个服务
        bindService(bindServiceIntent, myBindServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //当前activity生命周期结束时，解绑通过bindService绑定的服务
        toUnbindService();
    }

    public void unbindService(View view) {
        //toUnbindService();
        Toast.makeText(this, "unbindService 在 onDestroy 时调用", Toast.LENGTH_SHORT).show();
    }

    public void toUnbindService() {
        unbindService(myBindServiceConnection);
    }
}
