<%--
  Created by IntelliJ IDEA.
  User: hly
  Date: 2018/10/5
  Time: 15:08
  github :github.com/SiriusHly
  blog :blog.csdn.net/Sirius_hly
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang ="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="/static/css/login-register.css"/>
    <script src="/libs/angular/angular/angular.js"></script>

</head>
<body>

<div class="login-register">
    <h1>SpringBoot框架登录</h1>

    <form method="post" id="loginForm" >
        <!--autocomplete：其含义代表是否让浏览器自动记录之前输入的值-->
        <div>
            <input type="text" name="user_id" id="user_id"  placeholder="用户名" />
            <input type="password" name="user_password" id="user_password"  placeholder="密码"/>
        </div>

        <input type="submit" id="submit" value="Login" />
    </form>

    <a href="register.html"><button type="button" >还有没有账号？</button></a>

</div>

<script>


</script>
</body>
</html>
