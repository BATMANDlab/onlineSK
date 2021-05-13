<%--
  Created by IntelliJ IDEA.
  User: BATMAN
  Date: 2021/4/26
  Time: 12:56
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
                    sname:"required",
                    snum:{
                        required:true,
                        digits:true
                    },
                    spwd:{
                        required: true,
                        rangelength:[6,10]
                    }
                },
            });
        });
    </script>
</head>
<body>
修改学生
<div>
    <form id="updateForm" action="/student?method=updateSubmit" method="post">
        <input type="hidden" name="sid" value="${student.sid}">
        <table>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="sname" value="${student.sname}"></td>
            </tr>
            <tr>
                <td>学号</td>
                <td><input type="text" name="snum" value="${student.snum}"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td>
                    <input type="password" name="spwd" value="123456">
                    初始化密码为:123456
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