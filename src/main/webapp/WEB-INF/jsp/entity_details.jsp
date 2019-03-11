<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
<c:if test="${bicycle!=null}">
    <jsp:include page="frames/bicycle_details.jsp"/>
</c:if>
<c:if test="${user!=null}">
    <jsp:include page="frames/user_details.jsp"/>
</c:if>
<c:if test="${order!=null}">
    <jsp:include page="frames/order_details.jsp"/>
</c:if>
<c:if test="${point_hire!=null}">
    <jsp:include page="frames/point_hire_details.jsp"/>
</c:if>
</body>
</html>
