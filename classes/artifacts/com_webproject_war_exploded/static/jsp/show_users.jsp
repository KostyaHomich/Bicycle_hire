<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table border="2">
    <tr>
        <td>ID</td>
        <td>Login</td>
        <td>Email</td>
        <td>First name</td>
        <td>Last name</td>
        <td>Status</td>
        <td>Balance</td>
        <td>Role</td>

    </tr>
    <c:forEach items="${users}" var = "user">
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getLogin()}</td>
            <td>${user.getEmail()}</td>
            <td>${user.getFirstName()}</td>
            <td>${user.getLastName()}</td>
            <td>${user.getStatus()}</td>
            <td>${user.getBalance()}</td>
            <td>${user.getRole()}</td>

            <td>
                <form action = "update_user.jsp" method="post">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <input type="hidden" name="login" value="${user.getLogin()}">
                    <input type="hidden" name="email" value="${user.getEmail()}">
                    <input type="hidden" name="first_name" value="${user.getFirstName()}">
                    <input type="hidden" name="last_name" value="${user.getLastName()}">
                    <input type="hidden" name="status" value="${user.getStatus()}">
                    <input type="hidden" name="balance" value="${user.getBalance()}">
                    <input type="hidden" name="role" value="${user.getRole()}">
                    <input type="submit" value="Change" style="float:left">
                </form>
                <form action="delete_user.jsp" method="post">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <input type="submit" value="Удалить" style="float:left">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
