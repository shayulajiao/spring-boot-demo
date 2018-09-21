<%--
  Created by IntelliJ IDEA.
  User: hewc
  Date: 2018/8/30
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script src="<c:url value='/js/jquery-1.11.1.min.js' />"></script>
<head>
    <title>用户详情</title>
</head>
<body>
    <form id="infoForm">
        <%--<div style="margin: 0 auto;height: 500px;width: 1200px;text-align:center;">--%>
        <table align="center">
            <tr style="font-size: 50px;">
                <td colspan=2>
                    用户详情
                </td>
            <tr>
                <td>
                    <input type="hidden" id="userId" name="userId" value="${user.id}"/>
                </td>
            </tr>
            <tr>
                <td style="font-size: 15px;">姓名</td>
                <td>
                    <input type="text" id="userName" name="userName" value="${user.username}" required="true"/>
                </td>
            </tr>
            <tr>
                <td style="font-size: 15px;">密码</td>
                <td>
                    <input type="text" id="password" name="password" value="${user.password}" required="true"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="button" value="保存" id="saveInfo">
                </td>
            </tr>
        </table>
        <%--</div>--%>
    </form>
</body>

<script type="text/javascript">
    $(function () {
        $("#saveInfo").click(function () {
            var data = $("#infoForm").serialize();
            var url = "/user/saveInfo";
            $.ajax({
                async:false,
                data:data,
                url:url,
                success:function (data) {
                    if(data.success){
                        alert("保存成功！");
                        window.location.href = "/user/list";
                    }else {
                        alert("保存失败！");
                    }
                }
            });
        });
    });
</script>
</html>
