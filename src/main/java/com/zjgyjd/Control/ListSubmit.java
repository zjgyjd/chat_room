package com.zjgyjd.Control;

import com.zjgyjd.ServerSocket.Server;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/li")
public class ListSubmit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer sb = new StringBuffer();
        Set<String> mapSet = Server.getConnectionName();
        for(String s : mapSet){
            String temp = "<div>"+s+"</div><br/>";
            sb.append(temp);
        }
        resp.getWriter().print(sb.toString());
    }
}
