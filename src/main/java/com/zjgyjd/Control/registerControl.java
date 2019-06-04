package com.zjgyjd.Control;

import com.zjgyjd.Moudle.checkLogin;
import com.zjgyjd.Moudle.checkRegister;
import com.zjgyjd.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/re")
public class registerControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setUsername(req.getParameter("user"));
        user.setPassword(req.getParameter("password1"));
        checkRegister c = new checkRegister();
        if(true){
            //进行注册
        }else{
            //返回用户名相同
            req.getRequestDispatcher("register.jsp").forward(req,resp);
        }
    }
}
