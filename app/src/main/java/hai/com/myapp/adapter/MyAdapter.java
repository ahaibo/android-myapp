package hai.com.myapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import hai.com.myapp.MainActivity;
import hai.com.myapp.R;
import hai.com.myapp.model.Person;

/**
 * Created by Administrator on 2017/6/3.
 */

public class MyAdapter extends BaseAdapter {

    private MainActivity context;

    @Override
    public int getCount() {
        return this.context.getPersons().size();//返回listview要展示的条目数
    }

    @Override
    public Object getItem(int position) {
        return context.getPersons().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = View.inflate(this.context, R.layout.item, null);
            /*LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            layoutInflater.inflate(R.layout.item, null);
            layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            layoutInflater = getLayoutInflater();*/
        }
        //找到要修改的控件对象，在当前布局convertView中查找(R.layout.item)
        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView tv_content = (TextView) convertView.findViewById(R.id.tv_content);
        //修改
        Person person = this.context.getPersons().get(position);
        tv_title.setText(person.getName());
        tv_content.setText(person.getPhone());
        return convertView;
    }

    public MyAdapter() {
    }

    public MyAdapter(MainActivity context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(MainActivity context) {
        this.context = context;
    }
}
