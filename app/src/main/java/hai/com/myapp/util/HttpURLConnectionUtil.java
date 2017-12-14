package hai.com.myapp.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017/6/5.
 */

public class HttpURLConnectionUtil {

    public static String post(String path, String params) {

        String responseString = null;
        String uname = null;
        String upass = null;
        try {
            //防止中文乱码，进行编码
            params = "uname=" + URLEncoder.encode(uname, "utf-8") + "&upass=" + URLEncoder.encode(upass, "utf-8");
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(1000);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Cotent-Length", String.valueOf(params.length()));
            connection.setDoOutput(true);//打开输出流
            connection.getOutputStream().write(params.getBytes());//通过流把请求体写入到服务端
            int code = connection.getResponseCode();
            if (code == 200) {
                InputStream inputStream = connection.getInputStream();
                responseString = Utils.getStringFromStream(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseString;
    }
}
