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
                    cname:"required",
                    tid:{
                        required:true,
                        digits:true
                    },
                    cid:{
                        required: true,
                        digits:true
                    },
                    cstock:{
                        rangeMin:40,
                        rangeMax:180
                    }
                },
            });
        });
    </script>
</head>
<body>
新增课程
<div>
    <form id="addForm" action="/course?method=add" method="post">
        <table>
            <tr>
                <td>课程ID</td>
                <td><input type="text" name="cid"></td>
            </tr>
            <tr>
                <td>课程名</td>
                <td><input type="text" name="cname"></td>
            </tr>
            <tr>
                <td>教师ID</td>
                <td><input type="text" name="tid"></td>
            </tr>
            <tr>
                <td>课程数量</td>
                <td><input type="text" name="cstock"></td>
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
