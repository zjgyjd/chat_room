package com.zjgyjd.Moudle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

@WebServlet("/te")
public class ClientSocket extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset = utf-8");
        star();
        PrintWriter writer = resp.getWriter();
        writer.append("<html>")
                .append("<h1>Come</h1>")
                .append("</html>");
    }

    public  void star() {
        try {
            //1.创建客户端,连接指定服务
            final Socket socket = new Socket("127.0.0.1", 6666);
            System.out.println("客户端创建完毕:" + socket.getLocalSocketAddress());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                            //2.发送接收数据
                            OutputStream outputStream = socket.getOutputStream();
                            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                            String message = "hello 服务端 \n";


                            System.out.println(message);

                            writer.write(message + "\n");
                            writer.flush();
                            if (message.equals("quit")) {

                            }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


            new Thread(new Runnable() {
                public void run() {
                    try {
                        while (true) {
                            //2.2接收数据
                            InputStream inputStream = socket.getInputStream();
                            Scanner scanner = new Scanner(inputStream);
                            String messages = scanner.nextLine();
                            if (messages.equals("quit")) {
                                socket.close();
                                System.out.println("客户端关闭");
                                break;
                            }
                            System.out.println("接收服务器消息:" + messages);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
