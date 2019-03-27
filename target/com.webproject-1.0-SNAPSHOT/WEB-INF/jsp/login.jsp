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

<c:choose>
    <c:when test="${not empty requestScope.get('lang')}">
        <fmt:setLocale value="${requestScope.get('lang')}"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${cookie['lang'].value}"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="/text" scope="application"/>

<div class="container">
    <section id="content">

        <form action="${pageContext.request.contextPath}/login" method="post">

            <h1><fmt:message key="page.title.login_form"/></h1>
            <c:if test="${not empty requestScope.error}">
                <fmt:message key="${requestScope.error}"/>
            </c:if>
            <c:if test="${not empty requestScope.errorsList}">
                <c:forEach var="entry" items="${requestScope.errorsList.getErrors()}">
                    <c:if test="${entry.value.size() >0}">
                        <c:forEach var="value" items="${entry.value}">
                            <fmt:message key="${value}"/>
                        </c:forEach>
                    </c:if>
                </c:forEach>
            </c:if>
            <div>
                <input type="text"
                       placeholder="<fmt:message key="user.login"/>"
                       id="login"
                       name="login"
                       minlength="5"
                       maxlength="15"
                       pattern="^[a-zA-Z0-9]{5,15}$"
                       required
                       title="<fmt:message key="user.error.invalid_login"/>"
                />
            </div>
            <div>
                <input type="password"
                       placeholder="<fmt:message key="user.password"/>"
                       id="password"
                       name="password"
                       minlength="7"
                       maxlength="25"
                       required
                       title="<fmt:message key="user.error.invalid_password"/>"
                />
            </div>
            <div>
                <input type="submit" value="<fmt:message key="page.button.login"/>"/>
                <a href="${pageContext.request.contextPath}/registration ?command=${CommandType.SHOW_REGISTRATION_PAGE}"><fmt:message
                        key="page.button.register"/></a>
            </div>
            <input type="hidden" name="command" value="${CommandType.LOGIN}">
        </form>
        <!-- form -->

    </section>
    <!-- content -->
</div>
<!-- container -->
</body>

