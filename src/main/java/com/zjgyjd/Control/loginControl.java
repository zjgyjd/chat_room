package com.zjgyjd.Control;

import com.zjgyjd.Moudle.checkLogin;
import com.zjgyjd.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/lo")
public class loginControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User user = new User();
        user.setUsername(req.getParameter("username"));
        System.out.println(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        System.out.println(req.getParameter("password"));
        checkLogin c = new checkLogin();
        c.user = user;
        if (c.checkL()) {
            req.getRequestDispatcher("success.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("fail.jsp").forward(req, resp);
        }
    }
}
