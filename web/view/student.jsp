<%--
  Created by IntelliJ IDEA.
  User: BATMAN
  Date: 2021/4/24
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>在线选课系统</title>
    <!--    <script src="js/jquery-3.4.1.min.js"></script>-->
    <link rel="stylesheet" type="text/css" href="../layui/css/layui.css" >
    <script type="text/javascript" src="../layui/layui.js"></script>
    <script src="../jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="../jquery-validation-1.14.0/jquery.validate.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="../bootstrap.css">

    <style>
        .layui-layout-admin .layui-body{
            bottom:0px;
        }
        .layui-table-grid-down {
            display: none;
        }
        .layui-table-cell {
            font-size:14px;
            padding:0 5px;
            height:auto;
            overflow:visible;
            text-overflow:inherit;
            white-space:normal;
            word-break: break-all;
        }
    </style>

    <script type="text/javascript">
        $(function ()
        {
            $('.layui-side-scroll dd ol li a,.layui-side-scroll dd a').click(function (){
                $('iframe').attr("src",$(this).attr("url"))
            })
        });


    </script>
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo" style="color: #fff"><a href=""><img src="../layui/images/face/1.gif" class="layui-nav-img"></a>在线选课系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">

        </ul>
        <ul class="layui-nav layui-layout-right">
            <ul class="layui-nav">
                <li class="layui-nav-item">
                    <a href=""><img src="../layui/images/face/2.gif" class="layui-nav-img">
                        ${student.sname}
                    </a>
                    <dl class="layui-nav-child">
                        <%--                        <dd><a href="javascript:;">修改信息</a></dd>--%>
                        <dd><a href="/logoutServlet">退出</a></dd>
                    </dl>
                </li>
                <%--                <li class="layui-nav-item">--%>
                <%--                    <a href="">个人中心<span class="layui-badge-dot"></span></a>--%>
                <%--                </li>--%>
            </ul>
        </ul>
    </div>

    <div class="layui-side layui-bg-black" >
        <div class="layui-side-scroll" >
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree "lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">学生权限</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;" url="/pageSelectCourse?pageNum=1&sid=${student.sid}">选课</a>
                        </dd>
                        <dd>
                            <a href="javascript:;" url="/querySelected?sid=${student.sid}">查看已选课程</a>
                        </dd>
                        <dd>
                            <a href="javascript:;" url="queryScore?sid=${student.sid}">查询成绩</a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body" style=" display: flex;flex-direction: column; justify-content: flex-start;padding: 10px;">    <!-- 内容主体区域 -->
        <iframe src="/view/bg.jsp" height="99%" width="99%" frameborder="0px"></iframe>
    </div>



</div>
</body>



<script>
    layui.use(['table','element'], function(){

        var table = layui.table;
        var laydate = layui.laydate;
        var form = layui.form;
        var util=layui.util;

    });
</script>
