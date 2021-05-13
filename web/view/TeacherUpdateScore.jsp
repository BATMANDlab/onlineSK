<%--
  Created by IntelliJ IDEA.
  User: BATMAN
  Date: 2021/5/2
  Time: 21:39
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
                    score:{
                        range:[0,100],
                        digits:true
                    },
                },
            });
        });
    </script>
</head>
<body>
录入成绩
<div>
    <form id="updateForm" action="/teacherupdatescore?method=updateSubmit" method="post">
        <table>
            <tr>
                <td>课表ID:</td>
                <td>${sc.scid}</td>
            </tr>
            <tr>
                <td>学生ID:</td>
                <td>${sc.sid}</td>
            </tr>
            <tr>
                <td>课程ID:</td>
                <td>${sc.cid}</td>
            </tr>
            <tr>
                <td>成绩</td>
                <td>
                    <input type="text" name="score" value="${sc.score}">
                    <input type="hidden" name="scid" value="${sc.scid}">
                    <input type="hidden" name="tid" value="${teacher.tid}">
                </td>
            </tr>
            <tr>
                <td colspan="4">
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