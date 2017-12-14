package hai.com.myapp.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/6/2.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    /**
     * @param context
     * @param name    数据库名称，null：表示在内存中创建一个数据库
     * @param factory 游标工厂，null：用系统默认的游标工厂
     * @param version 控制数据库的升降级，从1开始
     */
    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SQLiteHelper(Context context, int version) {
        super(context, "ahai.db", null, version);
    }

    public SQLiteHelper(Context context, String name) {
        super(context, name, null, 1);
    }

    public SQLiteHelper(Context context) {
        super(context, "ahaiboa.db", null, 1);
    }

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    /**
     * 创建数据库时调用
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table info(id integer primary key autoincrement, name varchar(20), phone varchar(20))");
//        db.execSQL("alter table info add age integer");
        //insert into info(name,phone) values('张三','13333333333');
        //insert into info(name,phone) values('李四','13444444444');
        //insert into info(name,phone) values('王五','13555555555');
        //delete from info where name='张三'
        //select * from info;
    }



    /**
     * 版本号发生变化时调用
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("onUpgrade, oldVersion: " + oldVersion + "; newVersion: " + newVersion);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        System.out.println("onDowngrade, oldVersion: " + oldVersion + "; newVersion: " + newVersion);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    void test() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        sqLiteDatabase.beginTransactionNonExclusive();

        sqLiteDatabase.execSQL("");

        ContentValues values = new ContentValues();
        values.put("key", "value");

        sqLiteDatabase.insert("table", null, values);

    }
}
