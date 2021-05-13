<%--
  Created by IntelliJ IDEA.
  User: BATMAN
  Date: 2021/4/23
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>在线选课系统</title>
    <meta name="description" content="particles.js is a lightweight JavaScript library for creating particles.">
    <meta name="author" content="Vincent Garreau" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" media="screen" href="../login/css/style.css">
    <link rel="stylesheet" type="text/css" href="../login/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="../layui/css/layui.css">
</head>
<body>
<div id="particles-js">
    <div class="login">
        <div class="layui-bg-red">
            ${error}
        </div>
        <div class="login-top">
            <i>在线选课系统</i>
        </div>
        <form action="/loginServlet" method="post">
            <div class="login-center clearfix">
                <div class="login-center-img"><img src="../login/img/name.png"/></div>
                <div class="login-center-input">
                    <input type="text" name="username" value="admin" placeholder="请输入您的用户名" onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的用户名'"/>
                    <div class="login-center-input-text">用户名</div>
                </div>
            </div>
            <div class="login-center clearfix">
                <div class="login-center-img"><img src="../login/img/password.png"/></div>
                <div class="login-center-input">
                    <input type="password" name="password"value="admin" placeholder="请输入您的密码" onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的密码'"/>
                    <div class="login-center-input-text">密码</div>
                </div>
            </div>
            <div class="login-center">
                <select name="type">
                    <option value="">请选择登录类型</option>
                    <option value="0">学生</option>
                    <option value="1">老师</option>
                    <option value="2" selected>管理员</option>
                </select>
            </div>
            <div class="login-center">
                <button type="submit" class="layui-btn layui-btn-lg layui-btn-radius layui-btn-normal">登录</button>
            </div>
        </form>
    </div>
    <div class="sk-rotating-plane"></div>
</div>

</body>
</html>
