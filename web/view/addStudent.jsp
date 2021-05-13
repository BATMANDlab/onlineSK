<%--
  Created by IntelliJ IDEA.
  User: BATMAN
  Date: 2021/4/25
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addStudent</title>
    <script src="../jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="../jquery-validation-1.14.0/jquery.validate.js" type="text/javascript"></script>
    <script type="text/javascript" src="../jquery-validation-1.14.0/localization/messages_zh.js"></script>
    <link rel="stylesheet" type="text/css" href="../layui/css/layui.css">
    <script type="text/javascript" src="../layui/layui.js"></script>
    <script type="text/javascript">

        $(function () {
            $("#addForm").validate({
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
新增学生
    <div>
        <form id="addForm" action="/student?method=add" method="post">
        <table>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="sname"></td>
            </tr>
            <tr>
                <td>学号</td>
                <td><input type="text" name="snum"></td>
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
                        <i>提交</i>
                    </button>
                </td>
            </tr>
        </table>
        </form>
    </div>

</body>
</html>
