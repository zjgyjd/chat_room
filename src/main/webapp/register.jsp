<%@ page import="com.zjgyjd.Moudle.checkRegister" %><%--
  Created by IntelliJ IDEA.
  User: Thinkpad
  Date: 2019/6/4
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <style>
        .center {
            width: 500px;
            height: 600px;
            background: #808080;
            top: 50%;
            left: 50%;
            position: absolute;
            margin-top: -300px;
            margin-left: -250px;
            border-radius: calc(60px);
        }

        .font {
            width: 100%;
        }

        .block {
            outline: none;
            border-radius: calc(20px);
            margin: 0px 0px 0px 12px;
            height: 30px;
            padding-left: calc(20px);
            font-size: calc(20px);
        }

        .blank {

            display: inline-block;
            width: calc(200px);
            text-align: right;
        }
    </style>
    <script>
        function getPassWord() {
            var password1 = document.getElementById("password1").value;
            var password2 = document.getElementById("password2").value;
            var span = document.getElementById("error");
            if (password1 != "") {


                if (password1 != password2) {
                    span.innerHTML = "密码不相同".fontcolor("red");
                } else {
                    span.innerHTML = "密码相同".fontcolor("greatgreen");
                }
            } else {
                span.innerHTML = "";
            }
        }

        function check(form) {
            if (form.user.value == "") {
                alert("请输入用户名");
                form.user.focus();
                return false;
            }
            if (form.password1.value == "") {
                alert("请输入密码");
                form.password1.focus();
                return false;
            }
            if (form.password2.value == "") {
                alert("请核对密码");
                form.password2.focus();
                return false;
            }
            if (form.email.value == "") {
                alert("请填写邮箱");
                form.email.focus();
                return false;
            }
            var password1 = document.getElementById("password1").value;
            var password2 = document.getElementById("password2").value;
            if (password1 != password2) {
                alert("密码不一致");
                form.password2.focus();
                return false;
            }
            return true;
        }
        function checkU() {
            var username = document.getElementById("user");
            <%=new checkRegister().checkR(username.value)%>
        }
    </script>
</head>
<link href="css/01.css" type="text/css" rel="stylesheet">
<body background="img/register.png">
<div class="center">
    <div class="font" align="center">
        <font style="color: #ffffff;font-size:calc(50px);">注册</font>
    </div>
    <form action="/re" style="text-align:left;" onsubmit="return check(this)"><br/><br/><br/>
        <div class="blank"><font size="5">用户名:</font></div>
        <input type="text" name="user" id='user' placeholder="请注册用户名" class="block" onblur="checkU()"/><br/>
        <span></span><!-- 需要说出用户名是否重复  -->
        <br/><br/>
        <div class="blank"><font size="5">密码:</font></div>
        <input type="password" name="password1" id='password1' placeholder="请注册的密码" class="block"/><br/>
        <span></span><!-- 暂时不需要-->
        <br/><br/>
        <div class="blank"><font size="5">校验密码:</font></div>
        <input type="password" name="password2" id='password2' placeholder="请重新输入的密码"
               class="block" onblur="getPassWord()"/><br/>
        <div class="blank"></div>&nbsp;&nbsp;&nbsp;
        <span id="error"></span><!-- 需要说出密码两次是否一致  -->
        <br/><br/>
        <div class="blank"><font size="5">电子邮箱:</font></div>
        <input type="email" name="email" id='email' placeholder="请输入您的邮箱" class="block"/>
        <!--密码   再次输入密码 (需要进行校验,不对出现红叉)   邮箱(格式要对)
            注册按钮, 进行判断,用户名不能重复,重复则在用户名下面显示红色重复提示
            显示注册成功后有一个绿色的勾并停留几秒,之后跳转登录页面
        -->
        <br/><br/><br/><br/>
        <div style="width: 100%;text-align: center;">
            <input type="submit" value="登 录" name="load"
                   style="background-color: white; border-radius: calc(10px);
						height: 60px;	width:100px;display:inline-block; font-size: calc(20px); color:dodgerblue; outline: none;"/>
        </div>

    </form>
</div>
</body>
</html>

