package com.zjgyjd.DBconnect;

import java.sql.*;

public class DB {
    private String driver = "com.mysql.jdbc.Driver";
    private String username = "root";
    private String password = "1234";
    private String url = "jdbc:mysql://localhost:3306/chat_room";

    public Connection conn = null;
    public Statement state = null;
    public ResultSet rs = null;

    public Connection getConn() {
        try {
            Class.forName(driver);//驱动
            conn = DriverManager.getConnection(url, username, password);//连接
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public ResultSet queryFunction(String strSql){
        conn = this.getConn();
        try {
            state = conn.createStatement();
            rs  = state.executeQuery(strSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean insertFunction(String strInster){
        conn = this.getConn();
        try {
            state = conn.createStatement();
            return state.execute(strInster);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void closeDb() {
        try {
            if (state != null) state.close();
            if (conn != null) conn.close();
            if(rs != null) rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
