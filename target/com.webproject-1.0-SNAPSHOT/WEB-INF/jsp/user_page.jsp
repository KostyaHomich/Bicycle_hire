<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags" %>

<html lang="en">
<head>
    <title>${sessionScope.signInUser.role.toLowerCase()} page</title>
    <meta charset="utf-8">
    <meta name="description" content="La casa free real state fully responsive html5/css3 home page website template"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/responsive.css">

    <link rel="text/javascript" href="${pageContext.request.contextPath}/static/js/jquery.js">
    <link rel="text/javascript" href="${pageContext.request.contextPath}/static/js/main.js">

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

<jsp:include page="../../jsp/header.jsp"/>
<jsp:include page="../../jsp/footer.jsp"/>
</body>
</html>