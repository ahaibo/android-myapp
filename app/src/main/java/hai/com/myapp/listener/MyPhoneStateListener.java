package hai.com.myapp.listener;

import android.content.Context;
import android.media.MediaRecorder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import java.io.IOException;

/**
 * Created by Administrator on 2017/6/8.
 */

public class MyPhoneStateListener extends PhoneStateListener {
    private MediaRecorder recorder;
    private Context context;

    public MyPhoneStateListener(Context context) {
        this.context = context;
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                System.out.println("空闲状态：录音结束");
                if (null != recorder) {
                    try {
                        recorder.stop();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        recorder.reset();   // You can reuse the object by going back to setAudioSource() step
                        recorder.release(); // Now the object cannot be reused
                    }
                }
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                System.out.println("响铃: " + incomingNumber + ". 准备一个录音机");
                recorder = new MediaRecorder();
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                recorder.setOutputFile(this.context.getCacheDir() + "/" + incomingNumber + ".3gp");
                try {
                    recorder.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                System.out.println("接电话了" + incomingNumber + "；开始录音");
                recorder.start();   // Recording is now started
                break;
        }
    }
}
