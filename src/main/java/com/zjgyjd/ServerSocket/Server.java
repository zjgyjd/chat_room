package com.zjgyjd.ServerSocket;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint(value = "/websocket/chat", configurator = HttpConfig.class)
public class Server {
    private static final String GUEST_PREFIX = "Guest"; //客人的前缀,这个地方要改,前端传过来
    private static final AtomicInteger connectionIds = new AtomicInteger(0); //连接的id
    private static final Set<Server> connections =
            new CopyOnWriteArraySet<>();                    //线程包下的线程安全Set
    private static final Set<String> connectionName =
            new CopyOnWriteArraySet<>();

    public static Set<String> getConnectionName() {
        return connectionName;
    }

    private String nickname;
    private Session session;
    private HttpSession httpSession;

    public String getNickname() {//得到一个名字,需要一个名字给前端
        return nickname;
    }

    public Server() {
        nickname = GUEST_PREFIX + connectionIds.getAndIncrement(); //确认出当前连接下的唯一顾客id
        //之后需要改进的,利用session将用户的名字读过来
    }


    @OnOpen
    public void start(Session session, EndpointConfig endpointConfig) {
        this.session = session;
        this.httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        this.nickname = (String) httpSession.getAttribute("name");//需要前面传值
        connections.add(this);
        connectionName.add(nickname);
        String message = String.format("<div class='atalk'>Robot: %s %s <div>", nickname, "加入了会话大厅\n"); // 此时某个用户加入了会话大厅
        httpSession.setAttribute("nameList", connectionName);
        broadcast(message, null, false);
    }


    @OnClose
    public void end() {
        connections.remove(this);
        connectionName.remove(nickname);
        String message = String.format("<div class='atalk'>Robot: %s %s <div>",
                nickname, "退出了聊天大厅");
        broadcast(message, null, false);//这个地方也要移出
    }


    @OnMessage
    public void incoming(String message) {
        String filteredMessage = message.replace("btalk", "atalk");
        int index = filteredMessage.indexOf("[");
        if (index > 0) {
            boradcast_01(filteredMessage, this, index);
        } else
            //需要自己不要显示自己说的话,唯一的方法就是不向自己穿输,需要将自己传进去
            broadcast(filteredMessage, this, true);//这个地方在传输消息,表示在通话
        httpSession.setAttribute("nameList", connectionName);//此时将值传回来了
    }

    private void boradcast_01(String filteredMessage, Server server, int index) {
        for (Server client : connections) {
            try {
                synchronized (client) {
                    String[] users = filteredMessage.substring(index + 1).split(" ");
                    for (int i = 0; i < users.length - 1; i++) {
                        if(client.nickname.equals(users[i])&& client != server){
                            client.session.getBasicRemote().sendText(filteredMessage.substring(0,index));//这一步可以将服务器数据传到客户端
                        }
                    }
                }
            } catch (IOException e) {
                //log.debug("Chat Error: Failed to send message to client", e);
                System.out.println("Chat Error: Failed to send message to client" + e.getMessage());
                connectionName.remove(client.nickname);
                connections.remove(client);//此时一个将该用户踢除,<----------就是这里
                try {
                    client.session.close();//发生异常应该及时关闭session
                } catch (IOException e1) {
                    // Ignore
                }
                String message = String.format("<div class='atalk'>Robot: %s %s <div>",
                        client.nickname, "退出了聊天大厅"); //抛异常此时退出连接
                broadcast(message, null, false);//使循环继续,将该用户退出连接的消息告诉所有人
            }

        }
    }


    @OnError
    public void onError(Throwable t) throws Throwable {
        //log.error("Chat Error: " + t.toString(), t);
        System.out.println("Chat Error: " + t.toString());
        //打印出错误信息
    }


    private static void broadcast(String msg, Server my, Boolean flag) {
        for (Server client : connections) { //遍历所有客户端,用于上传数据
            try {
                synchronized (client) {//对每一个用户上锁
                    if (flag && client == my) {//此时说明不需要传给自己
                        continue;
                    }
                    client.session.getBasicRemote().sendText(msg);//这一步可以将服务器数据传到客户端
                }
            } catch (IOException e) {
                //log.debug("Chat Error: Failed to send message to client", e);
                System.out.println("Chat Error: Failed to send message to client" + e.getMessage());
                connectionName.remove(client.nickname);
                connections.remove(client);//此时一个将该用户踢除,<----------就是这里
                try {
                    client.session.close();//发生异常应该及时关闭session
                } catch (IOException e1) {
                    // Ignore
                }
                String message = String.format("<div class='atalk'>Robot: %s %s <div>",
                        client.nickname, "退出了聊天大厅"); //抛异常此时退出连接
                broadcast(message, null, false);//使循环继续,将该用户退出连接的消息告诉所有人
            }
        }
    }
}
