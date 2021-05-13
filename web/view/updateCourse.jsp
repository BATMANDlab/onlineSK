<%--
  Created by IntelliJ IDEA.
  User: BATMAN
  Date: 2021/4/28
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>updateStudent</title>
    <script src="../jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="../jquery-validation-1.14.0/jquery.validate.js" type="text/javascript"></script>
    <script type="text/javascript" src="../jquery-validation-1.14.0/localization/messages_zh.js"></script>
    <link rel="stylesheet" type="text/css" href="../layui/css/layui.css">
    <script type="text/javascript" src="../layui/layui.js"></script>
    <script type="text/javascript">

        $(function () {
            $("#updateForm").validate({
                rules:{
                    cname:"required",
                    cid:{
                        required:true,
                        digits:true
                    },
                    tid:{
                        required:true,
                        digits:true
                    },
                },
            });
        });
    </script>
</head>
<body>
修改课程
<div>
    <form id="updateForm" action="/course?method=updateSubmit" method="post">
        <input type="hidden" name="cid" value="${course.cid}">
        <table>
            <tr>
                <td>课程ID</td>
                <td><input type="text" name="cid" value="${course.cid}"></td>
            </tr>
            <tr>
                <td>课程名</td>
                <td><input type="text" name="cname" value="${course.cname}"></td>
            </tr>
            <tr>
                <td>任课教师ID</td>
                <td>
                    <input type="text" name="tid" value="${course.tid}">
                </td>
            </tr>
            <tr>
                <td>课程数量</td>
                <td>
                    <input type="text" name="cstock" value="${course.cstock}">
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <button class="layui-btn" type="button" onclick="window.history.back(-1)">
                        <i class="layui-icon">&#xe65c;</i>
                    </button>
                    <button class="layui-btn" type="submit">
                        <i></i>
                        提交
                    </button>
                </td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>