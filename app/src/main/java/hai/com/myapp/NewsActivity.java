package hai.com.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import hai.com.myapp.news.Image;
import hai.com.myapp.news.Item;

/**
 * Created by Administrator on 2017/6/4.
 */

public class NewsActivity extends AppCompatActivity {

    private static final int STATUS_CODE = 0;
    private String rssXmlPath = "http://news.qq.com/newsgn/rss_newsgn.xml";
    private List<Item> newsList = new ArrayList<>();
    private ListView news_lv_list;
    private TextView news_tv_title;
    private TextView news_tv_content;
    //    private TextView news_tv_comment;
    private NewsAdapter newsAdapter;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //通过handler在主线程中处理消息
            if (msg.what == STATUS_CODE) {
                news_lv_list.setAdapter(newsAdapter);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);

        news_lv_list = (ListView) findViewById(R.id.news_lv_list);
        newsAdapter = new NewsAdapter();
        initData();

    }

    public void initData() {
        System.out.println(this.getClass().getName() + ".initData()...");
        new Thread(new Thread() {
            @Override
            public void run() {
                System.out.println("run initData path=" + rssXmlPath);
                try {
                    URL url = new URL(rssXmlPath);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(10000);
                    int code = httpURLConnection.getResponseCode();
                    if (code == 200) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        //解析xml文档
                        parseXml(inputStream);
                        //不能直接修改而是通知主线程
                        handler.sendEmptyMessage(STATUS_CODE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }).start();
    }

    private void parseXml(InputStream inputStream) throws Exception {
        XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(inputStream, "utf-8");
        int eventType = pullParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tagName = pullParser.getName();
            int depth = pullParser.getDepth();
            String positionDesc = pullParser.getPositionDescription();
            if (null != tagName && tagName.trim().length() > 0) {
                System.out.println("Name: " + tagName + "\tDepth: " + depth + "\tPositionDescription: " + positionDesc);
            }
            Item item = null;
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    switch (tagName) {
                        case "item":
                            item = new Item();
                            break;
                        case "url":
                            Image image = new Image();
                            image.setUrl(pullParser.getText());
                            item = null == item ? new Item() : item;
                            item.setImage(image);
                            break;
                        case "title":
                            if (depth == 4) {
                                item = null == item ? new Item() : item;
                                item.setTitle(pullParser.nextText());
                            }
                            break;
                        case "description":
                            if (depth == 4) {
                                item = null == item ? new Item() : item;
                                item.setDescription(pullParser.nextText());
                            }
                            break;
                        case "link":
                            if (depth == 4) {
                                item = null == item ? new Item() : item;
                                item.setLink(pullParser.nextText());
                            }
                            break;
                        case "author":
                            if (depth == 4) {
                                item = null == item ? new Item() : item;
                                item.setAuthor(pullParser.nextText());
                            }
                            break;
                        case "pubDate":
                            if (depth == 4) {
                                item = null == item ? new Item() : item;
                                item.setPubDate(pullParser.nextText());
                            }
                            break;
                        case "comments":
                            if (depth == 4) {
                                item = null == item ? new Item() : item;
                                String comments = pullParser.nextText();
                                if (null != comments && comments.trim().length() > 0)
                                    item.setComments(comments);
                            }
                            break;
                    }
                    break;
                case XmlPullParser.END_TAG:
                    switch (tagName) {
                        case "item":
                            newsList.add(item);
                            break;
                    }
                    break;
            }
            eventType = pullParser.next();
        }
        printNewsInfo();
    }

    private void printNewsInfo() {
        System.out.println("newsInfo:");
        for (Item item : newsList) {
            System.out.println(item);
        }
    }

    private class NewsAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return newsList.size();
        }

        @Override
        public Object getItem(int position) {
            return newsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = View.inflate(NewsActivity.this, R.layout.news_item, null);
            }
            news_tv_title = (TextView) convertView.findViewById(R.id.news_tv_title);
            news_tv_content = (TextView) convertView.findViewById(R.id.news_tv_content);
//            news_tv_comment = (TextView) convertView.findViewById(R.id.news_tv_comment);
            SmartImageView smartImageView = (SmartImageView) convertView.findViewById(R.id.news_siv_icon);

            Item news = (Item) getItem(position);
            news_tv_title.setText(news.getTitle());
            news_tv_content.setText(news.getDescription());
            smartImageView.setImageUrl(news.getImage().getUrl());

            return convertView;
        }
    }

    private void sendMsmTest() {
        final String[] smss = new String[]{"", ""};
        news_lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取短信内容
                String sms = smss[position];
                //创建意图对象，发送短信
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("sms_body", sms);
                startActivity(intent);
                //setResult(2,intent);
                //startActivityForResult(intent,1);
            }
        });
    }

    //返回事件
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //...
    }

    //onBackPressed 回传参数的接收方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        SmsManager manager=SmsManager.getDefault();
//        manager.sendTextMessage();
    }
}
