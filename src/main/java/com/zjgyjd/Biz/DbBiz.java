package com.zjgyjd.Biz;


import com.zjgyjd.DBconnect.DB;
import com.zjgyjd.vo.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DbBiz {
    public ResultSet rs;
    private DB Db= new DB();
    public ResultSet query() throws SQLException {
        rs = Db.queryFunction("select * from users;");
        return rs;
    }
}
