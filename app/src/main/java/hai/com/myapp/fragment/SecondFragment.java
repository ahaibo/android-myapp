package hai.com.myapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hai.com.myapp.R;

/**
 * Created by Administrator on 2017/6/10.
 */

public class SecondFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println(getClass().getName() + ".onCreate...");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, null);
    }
}
