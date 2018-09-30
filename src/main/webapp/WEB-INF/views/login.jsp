<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<script src="<c:url value='/js/jquery-1.11.1.min.js' />"></script>
<head>
    <title>登录页面</title>
</head>
<body>
    <form action="/doLogin" method="post">
        <table align="center">
            <tr>
                <td>
                    用户名  <input type="text" name="userName">
                </td>
            </tr>
            <tr>
                <td>
                    密码  <input type="password" name="passWord">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="登录">
                </td>
                <td>
                    <input type="button" id="register" value="注册">
                </td>
            </tr>
        </table>

    </form>

    <script type="text/javascript">
        $(function () {
            $("#register").click(function () {
                window.location.href = "<c:url value='/register'/>";
            });
        });
    </script>
</body>
</html>
