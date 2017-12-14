package hai.com.myapp.util;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/6/6.
 */

public class MultiThreadDownLoad {
    private String urlString = "http://10.0.0.2:8080/python-3.5.2-amd64.exe";
    private int threadCount=4;

    public void download() {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
//            connection.setRequestProperty("Range","bytes="+startIndex+"-"+endIndex);
            int code = connection.getResponseCode();
            if (code == 200) {
                InputStream inputStream = connection.getInputStream();
                int length = connection.getContentLength();
                RandomAccessFile file = new RandomAccessFile(getFileName(), "rw");
                file.setLength(length);
                //计算每个线程要下载的数据范围

//                file.seek();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFileName() {
        String[] paths = urlString.split("/");
        return paths[paths.length - 1];
    }

    void test(){

    }
}
