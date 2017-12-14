package hai.com.myapp.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import hai.com.myapp.R;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        //获取 FragmentManager
        FragmentManager fragmentManager = getFragmentManager();

        //开启 FragmentTransaction
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //3.把 Fragment 替换到对应的 ViewGroup 中
        if (point.x > point.y) {//横屏
            //transaction.replace(android.R.id.content, new SecondFragment());
            transaction.replace(R.id.fragment_container, new SecondFragment());
        } else {
            //transaction.replace(android.R.id.content, new FirstFragment());
            transaction.replace(R.id.fragment_container, new FirstFragment());
        }
        //记得提交事务
        transaction.commit();
    }


}
