<%--
  Created by IntelliJ IDEA.
  User: BATMAN
  Date: 2021/4/28
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>课程管理</title>
    <link rel="stylesheet" type="text/css" href="../layui/css/layui.css">
    <script type="text/javascript" src="../layui/layui.js"></script>
    <script src="../jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="../jquery-validation-1.14.0/jquery.validate.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../bootstrap.css">
    <script type="text/javascript">
        $(function (){
            $('.delete').click(function (){
                if (confirm("确定要删除吗?")){
                    window.location.href="/course?method=delete&cid="+$(this).attr("keyword");
                }
            })
        })
    </script>
</head>
<body>
<div>
    <form name="queryCourseByName" method="post" action="/course?method=findByName">
        用户名:<input type="text" name="username">
        <button class="layui-btn" type="submit">
            <i class="layui-icon">&#xe615;</i>
        </button>

        <button class="layui-btn" type="button" onclick="window.location.href='/view/addCourse.jsp'">
            <i class="layui-icon">&#xe654;</i>
        </button>
    </form>
</div>

<table class="layui-table">
    <colgroup>
        <col width="150">
        <col width="200">
        <col>
    </colgroup>
    <thead>
    <tr>
        <th>课程ID</th>
        <th>课程名</th>
        <th>任课老师ID</th>
        <th>课程数量</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${page != null}">
    <c:forEach items="${page.data}" var="course">
        <tr>
            <td>${course.cid}</td>
            <td>${course.cname}</td>
            <td>${course.tid}</td>
            <td>${course.cstock}</td>
            <td>
                <button class="layui-btn" type="button" onclick="window.location.href='/course?method=update&cid=${course.cid}'">
                    <i class="layui-icon">&#xe642;</i>
                </button>
                <button type="button" class="delete" keyword="${course.cid}">
                    <i class="layui-btn">删除</i>
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<table class="pager">
    <td>
        <button class="layui-btn" onclick=window.location.href="/pageCourse?pageNum=1">首页</button>
        <button class="layui-btn" onclick=window.location.href="/pageCourse?pageNum=${page.pageNum-1}">
            <i class="layui-icon">&#xe65a;</i>
        </button>
        <button class="layui-btn" onclick=window.location.href="/pageCourse?pageNum=${page.pageNum+1}">
            <i class="layui-icon">&#xe65b;</i>
        </button>
        <button class="layui-btn" onclick=window.location.href="/pageCourse?pageNum=${page.totalPage}">尾页</button>
    </td>
</table>
</c:if>

</body>
</html>