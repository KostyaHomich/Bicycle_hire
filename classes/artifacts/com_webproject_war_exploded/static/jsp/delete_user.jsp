<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
</head>
<body>
<td>Delete user with id = ${param.id}?</td>


<form action="${pageContext.request.contextPath}/delete_user" method="post">

    <input type="submit" value="Delete">
    <input type="hidden" name="id" value="${param.id}">
    <input type="hidden" name="command" value="${CommandType.DELETE_USER}">
</form>
</body>
</html>
