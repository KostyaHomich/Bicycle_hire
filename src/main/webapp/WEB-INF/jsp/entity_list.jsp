<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="epam.project.command.CommandType" %>
<html>
<head>
    <title>Admin Page</title>
    <meta charset="utf-8">
    <meta name="description" content="La casa free real state fully responsive html5/css3 home page website template"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/responsive.css">

    <link rel="text/javascript" href="${pageContext.request.contextPath}/static/js/jquery.js">
    <link rel="text/javascript" href="${pageContext.request.contextPath}/static/js/main.js">

    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/nprogress.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/green.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/custom.min.css" rel="stylesheet">

</head>
<body>
<jsp:include page="admin_header.jsp"/>

<div class="x_panel">
    <div class="x_content">

        <c:out value="${error}"/>
        <div class="table-responsive">
            <c:if test="${entity=='user'}">
                <jsp:include page="frames/user_list.jsp"/>
            </c:if>
            <c:if test="${entity=='point_hire'}">
                <jsp:include page="frames/point_hire_list.jsp"/>
            </c:if>
            <c:if test="${entity=='order'}">
                <jsp:include page="frames/order_list.jsp"/>
            </c:if>
            <c:if test="${entity=='bicycle'}">
                <jsp:include page="frames/bicycle_list.jsp"/>
            </c:if>
        </div>

    </div>
</div>

</body>
</html>
