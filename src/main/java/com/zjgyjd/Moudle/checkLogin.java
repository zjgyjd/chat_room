package com.zjgyjd.Moudle;

import com.zjgyjd.Biz.DbBiz;
import com.zjgyjd.vo.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class checkLogin {
    public User user = null;
    private ResultSet rs = null;

    public boolean checkL() {
        DbBiz db = new DbBiz();
        try {
            rs = db.query();
            while (rs.next()) {
                String db_username = rs.getString("name");
                String db_password = rs.getString("password");
                if (db_username.equals(user.getUsername()) && db_password.equals(user.getPassword())) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return false;
    }
}
