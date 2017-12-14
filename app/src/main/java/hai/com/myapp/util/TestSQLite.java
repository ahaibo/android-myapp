package hai.com.myapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSQLite {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            //连接SQLite的JDBC
            Class.forName("org.sqlite.JDBC");
            //建立一个数据库名zieckey.db的连接，如果不存在就在当前目录下创建之
            conn = DriverManager.getConnection("jdbc:sqlite:zieckey.db");
            stat = conn.createStatement();
            stat.executeUpdate("create table tbl1(name varchar(20), salary int);");//创建一个表，两列
            stat.executeUpdate("insert into tbl1values('ZhangSan',8000);");//插入数据
            stat.executeUpdate("insert into tbl1values('LiSi',7800);");
            stat.executeUpdate("insert into tbl1values('WangWu',5800);");
            stat.executeUpdate("insert into tbl1values('ZhaoLiu',9100);");
            rs = stat.executeQuery("select * from tbl1;");//查询数据
            while (rs.next()) {//将查询到的数据打印出来
                System.out.print("name = " + rs.getString("name") + " ");//列属性一
                System.out.println("salary = " + rs.getString("salary"));//列属性二
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != rs) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != stat) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}