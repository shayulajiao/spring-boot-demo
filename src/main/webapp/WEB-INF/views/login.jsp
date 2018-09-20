<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>登录页面</title>
</head>
<body>
    <form action="/doLogin" method="post">
        <table>
            <tr>
                <td>
                    用户名  <input type="text" name="username">
                </td>
            </tr>
            <tr>
                <td>
                    密码  <input type="password" name="password">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="登录">
                </td>
            </tr>
        </table>

    </form>
</body>
</html>
