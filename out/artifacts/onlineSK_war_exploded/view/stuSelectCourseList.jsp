<%--
  Created by IntelliJ IDEA.
  User: BATMAN
  Date: 2021/4/29
  Time: 13:48
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
            $('.select').click(function (){
                if (confirm("确定选此课程吗?")){
                    let cid = $(this).attr("keyword");
                    window.location.href="/selectCourse?method=select&cid="+cid+"&sid=${sid}";
                }
            })
        })
    </script>
</head>
<body>
<div>
    <form name="queryCourseByName" method="post" action="/selectCourse?method=findByName">
        课程名:<input type="text" name="username">
        <button class="layui-btn" type="submit">
            <i class="layui-icon">&#xe615;</i>
        </button>
    </form>
</div>
<div>
    <i class="layui-icon">&#xe667;</i>
    <font color="blue">${msg}</font>
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
        <th>操作</th>
    </tr>
    </thead>
    <tbody>

    <c:if test="${page != null}">
    <c:forEach items="${page.data}" var="course">
        <tr>
            <td>${course.cid}</td>
            <td>${course.cname}</td>
            <td>${course .tid}</td>
            <td>
                <button class="select" type="button" keyword="${course.cid}">
                    <i class="layui-icon">&#xe61f;</i>
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<table class="pager">
    <td>
        <button class="layui-btn" onclick=window.location.href="/pageSelectCourse?pageNum=1">首页</button>
        <button class="layui-btn" onclick=window.location.href="/pageSelectCourse?pageNum=${page.pageNum-1}&sid="+${user.sid}>
            <i class="layui-icon">&#xe65a;</i>
        </button>
        <button class="layui-btn" onclick=window.location.href="/pageSelectCourse?pageNum=${page.pageNum+1}&sid="+${user.sid}>
            <i class="layui-icon">&#xe65b;</i>
        </button>
        <button class="layui-btn" onclick=window.location.href="/pageSelectCourse?pageNum=${page.totalPage}&sid="+${user.sid}>尾页</button>
    </td>
</table>
</c:if>

</body>
</html>
