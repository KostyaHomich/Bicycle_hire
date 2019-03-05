<%@ page import="epam.project.command.CommandType" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
</head>
<body>

<td>Show all users.</td>
<a href="${pageContext.request.contextPath}/static/jsp/show_users.jsp ?command=${CommandType.TAKE_ALL_USERS}"
   class="login_btn">Show</a>
</body>
</html>
