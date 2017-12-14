package hai.com.myapp.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/6/4.
 */

public class Utils {
    public static String getStringFromStream(InputStream inputStream) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = -1;
        byte[] buffer = new byte[1024];
        String content = null;
        try {
            while ((len = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            content = new String(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }
}
