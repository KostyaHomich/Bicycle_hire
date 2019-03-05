<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<td>Update user with id = ${param.id}?</td>

<form action="${pageContext.request.contextPath}/update_user" method="post">
    <input type="hidden" name="id" value="${param.id}">
    <input type="hidden" name="login" value="${param.login}">
    <input type="text" name="email" value="${param.email}" placeholder=${param.email}>
    <input type="text" name="first_name" value="${param.first_name}" placeholder=${param.first_name}>
    <input type="text" name="last_name" value="${param.last_name}" placeholder=${param.last_name}>
    <input type="text" name="status" value="${param.status}" placeholder=${param.status}>
    <input type="text" name="balance" value="${param.balance}" placeholder=${param.balance}>
    <input type="text" name="role" value="${param.role}" placeholder=${param.role}>

    <input type="hidden" name="command" value="${CommandType.UPDATE_USER}">
    <input type="submit" value="Update">
</form>

</body>
</html>
