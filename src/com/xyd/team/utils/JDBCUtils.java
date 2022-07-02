package com.xyd.team.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author xyd
 * @create 2022-07-01 22:13
 */
public class JDBCUtils {
    //获取数据库连接
    private static DataSource dataSource;
    static {
        try {
            Properties pros = new Properties();
            FileInputStream is = new FileInputStream(new File("druid.properties"));
            pros.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(pros);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }

    //关闭资源操作
    public static void closeResource(Connection conn, Statement ps){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResource(Connection conn, Statement ps, ResultSet rs){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test() throws Exception {
        Connection conn = getConnection();
        System.out.println(conn);
        String sql = "SELECT type,id,name,age,salary,bonus,stock FROM employee WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i = 0; i < 12; i++) {
            ps.setInt(1, i + 1);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int label = rs.getInt("type");
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                Double salary = rs.getDouble("salary");
                int bonus = rs.getInt("bonus");
                int stock = rs.getInt("stock");
                System.out.println("type = " + label + "\tid = " + id + "\tname = " + name
                        + "\tage = " + age + "\t salary = " + salary + "\tbonus = " + bonus + "\t stock = " + stock );
            }
        }

    }

}
