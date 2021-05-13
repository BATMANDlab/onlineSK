<%--
  Created by IntelliJ IDEA.
  User: BATMAN
  Date: 2021/4/30
  Time: 9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查看选课</title>
    <link rel="stylesheet" type="text/css" href="../layui/css/layui.css">
    <script type="text/javascript" src="../layui/layui.js"></script>
    <script src="../jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="../jquery-validation-1.14.0/jquery.validate.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../bootstrap.css">
</head>
<body>
<div>
<i class="layui-icon">&#xe770;</i>
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
        <th>任课老师名字</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="map">
        <tr>
            <td>${map.cid}</td>
            <td>${map.cname}</td>
            <td>${map.tid}</td>
            <td>${map.tname}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>