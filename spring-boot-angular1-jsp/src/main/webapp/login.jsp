<%--
  Created by IntelliJ IDEA.
  User: hly
  Date: 2018/10/5
  Time: 15:08
  github :github.com/SiriusHly
  blog :blog.csdn.net/Sirius_hly
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="app">
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <link rel="stylesheet" href="/static/css/login-register.css"/>
    <script src="/libs/angular/angular/angular.js"></script>
    <script src="/libs/angular/angularjs-toaster/toaster.js"></script>
    <script src="/static/js/loginApp.js"></script>
    <script src="/static/js/signIn.js"></script>

</head>
<body>
<div class="login-register" ng-controller="SignInFormController">
    <h1>SpringBoot框架登录</h1>

    <form method="post" id="loginForm" >
        <!--autocomplete：其含义代表是否让浏览器自动记录之前输入的值-->
        <div>
            <input type="text" name="userId"  placeholder="用户名" ng-model="user.userId"/>
            <input type="password" name="password"  placeholder="密码" ng-model="user.password"/>
        </div>

        <button type="submit" id="submit" ng-click="Login()">LOGIN</button>
    </form>

    <a href="register.html"><button type="button" >还有没有账号？</button></a>

</div>
</body>
</html>
