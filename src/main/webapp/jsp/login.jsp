<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">

</head>
<body>

<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'EN'}"/>
<fmt:setBundle basename="/text" scope="application"/>

<div class="container">
    <section id="content">

        <form action="${pageContext.request.contextPath}/login" method="post">

            <h1><fmt:message key="page.title.login_form"/></h1>
            <c:out value="${requestScope.error}"/>

                <c:if test="${ not empty requestScope.errorsList}">
                    <c:forEach var="entry" items="${requestScope.errorsList.getErrors()}">
                        <c:if test="${entry.value.size()>0}">
                            Error:<c:out value="${entry.value}"/>
                        </c:if>
                    </c:forEach>
                </c:if>
            <div>
                <input type="text" placeholder="<fmt:message key="user.login"/>" required="" id="login" name="login"/>
            </div>
            <div>
                <input type="password" placeholder="<fmt:message key="user.password"/>" required="" id="password" name="password"/>
            </div>
            <div>
                <input type="submit" value="<fmt:message key="page.button.login"/>"/>
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

