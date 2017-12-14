package hai.com.myapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

/**
 * Created by Administrator on 2017/6/7.
 */

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("短信监听 Receiver ...");
        Object[] objects = (Object[]) intent.getExtras().get("plus");
        for (Object obj : objects) {
            //创建短信的消息对象
            SmsMessage message = SmsMessage.createFromPdu((byte[]) obj);
            String from = message.getOriginatingAddress();
            String body = message.getMessageBody();
            System.out.println("from: " + from + "\tbody: " + body);
        }
    }
}
