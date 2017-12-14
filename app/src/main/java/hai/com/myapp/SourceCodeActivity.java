package hai.com.myapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import hai.com.myapp.util.Utils;

/**
 * Created by Administrator on 2017/6/4.
 */

public class SourceCodeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_url;
    private Button btn_show;
    private TextView tv_code;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //通过handler在主线程中处理消息
            tv_code.setText((String) msg.obj);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.source_code);

        et_url = (EditText) findViewById(R.id.et_url);
        btn_show = (Button) findViewById(R.id.btn_show);
        tv_code = (TextView) findViewById(R.id.tv_code);
        //et_url.setText("http://10.0.2.2:8080");
        et_url.setText("https://www.baidu.com");

        btn_show.setOnClickListener(this);
        //Toast.makeText(this, Thread.currentThread().getName(), Toast.LENGTH_SHORT);

    }

    @Override
    public void onClick(View v) {
        new Thread(new Thread() {
            @Override
            public void run() {
                final String path = et_url.getText().toString().trim();
                try {
                    URL url = new URL(path);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(10000);
                    int code = httpURLConnection.getResponseCode();
                    if (code == 200) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        //通过流创建一个bitmap
                        //Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
                        String content = Utils.getStringFromStream(inputStream);
                        //tv_code.setText(content);
                        //不能直接修改而是通知主线程
                        Message msg = new Message();
                        msg.obj = content;
                        handler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    final String error = e.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //这里的代码一定会在主线程中执行
                            Toast.makeText(SourceCodeActivity.this, "获取 " + path + " 网页信息出错：" + error, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

}
