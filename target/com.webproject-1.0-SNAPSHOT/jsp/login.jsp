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

        <form action="${pageContext.request.contextPath}/login" method="post">

            <h1>Login Form</h1>
            <c:out value="${error}"/>

                <c:if test="${ not empty errorsList}">
                    <c:forEach var="entry" items="${errorsList.getErrors()}">
                        <c:if test="${entry.value.size() >0}">
                            Error:<c:out value="${entry.value}"/>
                        </c:if>
                    </c:forEach>
                </c:if>
            <div>
                <input type="text" placeholder="Username" required="" id="login" name="login"/>
            </div>
            <div>
                <input type="password" placeholder="Password" required="" id="password" name="password"/>
            </div>
            <div>
                <input type="submit" value="Log in"/>
                <a href="${pageContext.request.contextPath}/registration ?command=${CommandType.SHOW_REGISTRATION_PAGE}">Register</a>
            </div>
            <input type="hidden" name="command" value="${CommandType.LOGIN}">
        </form>
        <!-- form -->

    </section>
    <!-- content -->
</div>
<!-- container -->
</body>
</html>


</body>

</html>
