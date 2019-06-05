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
public class registerCheck extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String registerName =req.getParameter("registerName");
        checkRegister c = new checkRegister();
        if(c.checkR(registerName)){
            resp.getWriter().print("<font color='green' id='f'>恭喜,该用户名可用</font>");
        }else{
            resp.getWriter().print("<font color='red' id='f'>抱歉，该用户名已被注册，请重新设置</font>");
        }
    }
}
