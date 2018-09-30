<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <script src="<c:url value='/js/jquery-1.11.1.min.js' />"></script>
    <head>
        <title>用户列表</title>

    </head>

    <body>
        <form id="listForm">
            <table  width="50%" cellspacing="0" cellpadding="0" border="0" align="center">
                <tr align="center">
                    <td>编号</td>
                    <td>姓名</td>
                    <td>密码</td>
                    <td colspan="2">操作</td>
                </tr>
                <c:forEach items="${userList}" var="user">
                    <tr align="center">
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.password}</td>
                        <td>
                            <input type="button" value="编辑" id="${user.id}" class="edit">
                        </td>
                        <td>
                            <input type="button" value="删除" id="${user.id}" class="delete">
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </body>
    <script type="text/javascript">
        $(function () {
            $(".edit").click(function () {
                var id = $(this).attr("id");
                window.location.href = "<c:url value='/user/userInfo?id='/>"+id;
            });

            $(".delete").click(function () {
                var id = $(this).attr("id");
                var url = "<c:url value='/user/delete/'/>"+id;
                $.ajax({
                    async:false,
                    url:url,
                    type:"get",
                    success:function (data) {
                        if(data.success){
                            alert("删除成功！");
                            window.location.href = "<c:url value='/user/list'/>";
                        }else{
                            alert("删除失败！");
                        }
                    }
                });
            });
        });
    </script>
</html>