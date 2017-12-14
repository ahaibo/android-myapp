package hai.com.myapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/6/5.
 */

@SuppressLint("AppCompatCustomView")
public class MySmartImageView extends ImageView {

    private static final int GET_PIC_SUCCESSED = 1;
    private static final int GET_PIC_FAILED = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GET_PIC_SUCCESSED:
                    setImageBitmap((Bitmap) msg.obj);
                    break;
                //图片获取失败，展示指定的资源
                case GET_PIC_FAILED:
                    setImageResource(msg.arg1);
                    break;
            }
        }
    };

    public MySmartImageView(Context context) {
        super(context);
    }

    public MySmartImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MySmartImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MySmartImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void setImageURL(final String path, final int resId) {
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(10000);
                    int code = connection.getResponseCode();
                    if (code == 200) {
                        InputStream inputStream = connection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        //发送消息到主线程
                        Message msg = Message.obtain();
                        msg.obj = bitmap;
                        msg.what = GET_PIC_SUCCESSED;
                        handler.sendMessage(msg);
                    } else {
                        handleError();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    handleError();
                }
            }

            private void handleError() {
                Message msg = Message.obtain();
                msg.arg1 = resId;
                msg.what = GET_PIC_FAILED;
                handler.sendMessage(msg);
            }
        }.start();
    }
}
