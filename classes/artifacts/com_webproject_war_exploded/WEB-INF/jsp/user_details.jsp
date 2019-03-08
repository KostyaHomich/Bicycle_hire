<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Paper Stack</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>

<div class="container">
    <section id="content">

        <form action="${pageContext.request.contextPath}/user_list" method="post">
            <h1>Update user Form</h1>
            <c:out value="${param.error}"/>

            <c:if test="${ not empty param.errorsList}">
                <c:forEach var="entry" items="${param.errorsList.getErrors()}">
                    <c:if test="${entry.value.size() >0}">
                        Error:<c:out value="${entry.value}"/>
                    </c:if>
                </c:forEach>
            </c:if>
            <input type="hidden" name="id" value="${user.getId()}">
            <input type="hidden" value="${user.getLogin()}" name="login" }/>
            <div>
                <input type="text" value="${user.getEmail()}" placeholder="Email" id="email" name="email"/>
            </div>
            <div>
                <input type="text" value="${user.getFirstName()}" placeholder="First name" id="first_name"
                       name="first_name"/>
            </div>
            <div>
                <input type="text" value="${user.getLastName()}" placeholder="Last name" id="last_name"
                       name="last_name"/>
            </div>

            <div>
                <input type="text" value="${user.getBalance()}" placeholder="Balance" id="balance" name="balance"/>
            </div>
            <div>
                <input type="text" value="${user.getStatus()}" placeholder="Status" id="status" name="status"/>
            </div>
            <div>
                <input  type="submit" value="Update">
            </div>
            <input type="hidden" name="command" value="${CommandType.UPDATE_USER}">
        </form>
        <form action="${pageContext.request.contextPath}/registration" method="post">

            <div>
                <input type="submit" value="Back">
                <input type="hidden" name="command" value="${CommandType.SHOW_USERS_PAGE}">
            </div>
        </form>
        <!-- form -->
    </section>
    <!-- content -->
</div>
<!-- container -->
</body>
</html>

