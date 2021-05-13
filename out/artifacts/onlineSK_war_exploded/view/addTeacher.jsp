<%--
  Created by IntelliJ IDEA.
  User: BATMAN
  Date: 2021/4/28
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addTeacher</title>
    <script src="../jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="../jquery-validation-1.14.0/jquery.validate.js" type="text/javascript"></script>
    <script type="text/javascript" src="../jquery-validation-1.14.0/localization/messages_zh.js"></script>
    <link rel="stylesheet" type="text/css" href="../layui/css/layui.css">
    <script type="text/javascript" src="../layui/layui.js"></script>
    <script type="text/javascript">

        $(function () {
            $("#addForm").validate({
                rules:{
                    tname:"required",
                    taccount:{
                        required:true,
                        digits:true
                    },
                    tid:{
                        required:true,
                        digits:true
                    },
                    tpwd:{
                        required: true,
                        rangelength:[6,10]
                    }
                },
            });
        });
    </script>
</head>
<body>
新增教师
<div>
    <form id="addForm" action="/teacher?method=add" method="post">
        <table>
            <tr>
                <td>教师ID</td>
                <td><input type="text" name="tid"></td>
            </tr>
            <tr>
                <td>名字</td>
                <td><input type="text" name="tname"></td>
            </tr>
            <tr>
                <td>账户</td>
                <td><input type="text" name="taccount"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td>
                    <input type="password" name="tpwd" value="123456">
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

