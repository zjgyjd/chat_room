package com.zjgyjd.Control;

import com.zjgyjd.Moudle.checkLogin;
import com.zjgyjd.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

@WebServlet("/lo")
public class loginControl extends HttpServlet {
    public static HashSet<String> onlineSet = new HashSet<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User();
       // System.out.println(username);//问题1不是此问题
        user.setUsername(username);
        System.out.println(username);
        user.setPassword(password);
        System.out.println(password);
        checkLogin c = new checkLogin();
        c.user = user;
        if (c.checkL() || (username.equals("admin") )) {
            //登录成功,此时进入聊天室,需要在Session中放入用户名,此时将名字传给了后台
            req.setAttribute("username",req);//问题2//需要用一个静态的HashSet,所有人只有一个
            onlineSet.add(username);//此时登陆页面,当有人退出时需要取出
            req.getRequestDispatcher("chat_room/chat_room.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("chat_room/fail.jsp").forward(req, resp);
        }
    }
}
