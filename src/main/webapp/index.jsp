<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎登陆聊天室!!!</title>
    <meta charset="UTF-8">
    <style type="text/css">
        body{
            background-size: 100%;
            background: #f4f4f4;
        }
        .ok {
            background: #ffffff;
            border: 1px solid  #999;
            border-radius: calc(20px);
            top: 50%;
            left: 50%;
            position: absolute;
            margin-top: -300px;
            margin-left: -250px;
            width: 500px;
            height: 300px;
        }

        .u {

        }
    </style>
</head>
<body>
<br/>
<br/><br/><br/><br/><br/><br/>

<div class="ok" >
    <h1 align="center"><font color=#999>Chat-Room</font></h1>
    <br/> <br/>

    <div align="center" class="u">
        <form action="/chat_room/lo" method="post" accept-charset="UTF-8">

            <div align="absmiddle">
                <font size="5">用户名: </font>
                <input type="text" id="user" name="username" placeholder="请输入用户名"/>
            </div>
            <div align="absmiddle">
                <font size="5">密&nbsp;&nbsp;码 : </font>

                <input type="password" id="password" name="password" placeholder="请输入密码"/>
            </div>
            <br/><br/>
            <input type="submit" value="登 录" name="load"
                   style="background-color: #0dbfdd; border-radius: calc(10px);
						height: 40px;	width:100px;display:inline-block; font-size: calc(20px); color:#fff; outline: none;"/>

            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="register.jsp"/>
            <input type="button" value="注 册" name="register"
                   style="background-color: #0dbfdd; border-radius: calc(10px);
						height: 40px;	width:100px;display:inline-block; font-size: calc(20px); color:#fff; outline: none;"/>
        </form>
    </div>
</div>
</body>
</html>
