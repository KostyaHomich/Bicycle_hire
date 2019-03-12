<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="epam.project.entity.UserRole" %>
<%@ page import="epam.project.entity.EntityType" %>
<html>
<head>
    <title>${sessionScope.signInUser.role} page</title>
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

<c:if test="${sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
    <jsp:include page="frames/admin_header.jsp"/>
</c:if>
<c:if test="${sessionScope.signInUser.role.equalsIgnoreCase(UserRole.USER.name())}">
    <jsp:include page="frames/user_header.jsp"/>
</c:if>

<div class="x_panel">
    <div class="x_content">

        <c:out value="${error}"/>
        <div class="table-responsive">
            <c:choose>

                <c:when test="${entity==EntityType.BICYCLE}">
                    <jsp:include page="frames/bicycle_list.jsp"/>
                </c:when>

                <c:when test="${entity==EntityType.USER}">
                    <jsp:include page="frames/user_list.jsp"/>
                </c:when>

                <c:when test="${entity==EntityType.ORDER}">
                    <jsp:include page="frames/order_list.jsp"/>
                </c:when>

                <c:when test="${entity==EntityType.POINT_HIRE}">
                    <jsp:include page="frames/point_hire_list.jsp"/>
                </c:when>

            </c:choose>
            <jsp:include page="frames/footer.jsp"/>

        </div>
    </div>
</div>

</body>
</html>
