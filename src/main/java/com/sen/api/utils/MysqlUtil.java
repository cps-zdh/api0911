package com.sen.api.utils;


import java.io.InputStream;

import java.sql.*;
import java.util.*;

/**
 * 数据库连接工具
 */
public class MysqlUtil {

    // JDBC 驱动名及数据库 URL
    private String jdbcDriver;
    private String dbUrl;

    // 数据库的用户名与密码，需要根据自己的设置
    private String username;
    private String password;

    private Connection conn=null;
    private Statement stmt=null;

    /**
     * 读取外部配置文件
     */

    public void init(){
        Properties p=new Properties();
        String file="mysql.properties";
        InputStream inputStream = MysqlUtil.class.getClassLoader().getResourceAsStream(file);
        try {
            p.load(inputStream);
            jdbcDriver=p.getProperty("jdbcDriver");
            dbUrl=p.getProperty("dbUrl");
            username=p.getProperty("username");
            password=p.getProperty("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立数据库连接
     */

    public void connect(){
        init();
        //注册JDBC驱动
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //打开链接
        System.out.println("连接数据库。。。");
        try {
            conn= DriverManager.getConnection(dbUrl,username,password);
            System.out.println(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭数据库连接
     */
    public void close(){
        try {
            conn.close();
            System.out.println("关闭数据库连接。。。");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行查询语句
     * @param sql:查询语句  strs:要存储的字段值
     * @return 数据列表
     */
    public List<Map<String,String>> selectSql(String sql, String[] keys){
        try {
            stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            List<Map<String,String>> list=new ArrayList<>();
            while(rs.next()) {
                Map<String, String> map = new HashMap<>();
                for (String key : keys) {
                    String value = rs.getString(key);
                    map.put(key, value);
                }
                list.add(map);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        MysqlUtil m=new MysqlUtil();
        m.connect();
        String sql="SELECT * FROM t_push_phonemsg ph LEFT JOIN t_push_msg pm ON ph.fmsgid = pm.fid WHERE ph.fphone='17826826147'";
        String[] strs={"fphone","fmsg"};
        List<Map<String,String>> maps=m.selectSql(sql,strs);
        for(Map<String,String> map:maps){
            System.out.println(map);
        }
        m.close();
    }
}

