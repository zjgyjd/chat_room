package com.zjgyjd.Moudle;

import com.zjgyjd.Biz.DbBiz;
import com.zjgyjd.vo.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class checkRegister {
    private ResultSet rs = null;
    public boolean checkR(String user){
        DbBiz db = new DbBiz();
        try {
            rs = db.query();
            while (rs.next()) {
                String db_username = rs.getString("name");

                if (db_username.equals(user)) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean insert(User user){
        DbBiz db = new DbBiz();
        try {
            db.insert(user);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
