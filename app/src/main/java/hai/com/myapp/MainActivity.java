package hai.com.myapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hai.com.myapp.adapter.MyAdapter;
import hai.com.myapp.model.Person;
import hai.com.myapp.util.SQLiteHelper;

public class MainActivity extends AppCompatActivity {

    private SQLiteHelper sqLiteHelper;
    private List<Person> persons = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main_listview);


//        TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());

        listView = (ListView) findViewById(R.id.lv_list);
//        listView.setAdapter(new MyAdapter(this));

//        String[] objects = {"zhangsan", "lisi", "wangwu", "zhaoli", "fengqi", "sanba"};
        //展示的内容较简单时可采用ArrayAdapter。注：第二个参数必须为一个空的textview布局文件
//        ArrayAdapter<String> adapter = null;
//        adapter = new ArrayAdapter<String>(this, R.layout.item_view, objects);

        //同上：当只有一个textview的内容需要修改时可用以下ArrayAdapter
//        adapter = new ArrayAdapter<String>(this, R.layout.item2, R.id.tv_text, objects);
//        listView.setAdapter(adapter);

//        simpleAdapter();

        sqLiteHelper = new SQLiteHelper(this, "ahaiboa.db");
    }

    void simpleAdapter() {
        ListView listView = (ListView) findViewById(R.id.lv_list);
        List<Map<String, String>> data = new ArrayList<>();
        Map<String, String> item1 = new HashMap<>();
        item1.put("title", "中国足球再次冲击世界杯");
        item1.put("content", "9月6日晚在沈阳与伊朗0:0");
        data.add(item1);

        Map<String, String> item2 = new HashMap<>();
        item2.put("title", "Java");
        item2.put("content", "JDK8 release...");
        data.add(item2);

        String[] from = {"title", "content"};
        int[] to = {R.id.tv_simple_title, R.id.tv_simple_content};
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item_simple_adapter, from, to);
        listView.setAdapter(adapter);
    }

    ////////////////////////db op //////////////////////////
    public void insert(View view) {
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
        db.execSQL("insert into info(name,phone) values('张三','13333333333')");
        db.execSQL("insert into info(name,phone) values('李四','13444444444')");
        db.execSQL("insert into info(name,phone) values('王五','13555555555')");
        db.close();
    }

    public void query(View view) {
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from info", null);

        String[] columnNames = cursor.getColumnNames();
        System.out.println("column names:");
        for (String name : columnNames) {
            System.out.print(name + "\t");
        }
        System.out.println();

        persons.clear();

        while (cursor.moveToNext()) {
            Person person = new Person();
            person.setId(cursor.getInt(0));
            person.setName(cursor.getString(1));
            person.setPhone(cursor.getString(2));
            persons.add(person);
        }
        cursor.close();
        db.close();
        listView.setAdapter(new MyAdapter(this));
        printPersons();
    }

    private void printPersons() {
        System.out.println("total persons size: " + persons.size());
        for (Person p : persons) {
            System.out.println(p);
        }
    }

    public void save(View view) {
//        MODE_PRIVATE,MODE_APPEND
//        Environment.getExternalStorageDirectory();
//        Environment.getExternalStorageState();
//        SharedPreferences sharedPreferences = getSharedPreferences("", MODE_APPEND);
//        sharedPreferences.getBoolean("key", defValue);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.remove("");
//        editor.putBoolean("", false);
//        editor.clear();
//        editor.apply();
//        editor.commit();
//
//        XmlSerializer xmlSerializer = Xml.newSerializer();
//        XmlPullParser xmlPullParser=Xml.newPullParser();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public SQLiteHelper getSqLiteHelper() {
        return sqLiteHelper;
    }

    public void setSqLiteHelper(SQLiteHelper sqLiteHelper) {
        this.sqLiteHelper = sqLiteHelper;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
