<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <title>User page</title>
    <meta charset="utf-8">
    <meta name="description" content="La casa free real state fully responsive html5/css3 home page website template"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/responsive.css">

    <link rel="text/javascript" href="${pageContext.request.contextPath}/static/js/jquery.js">
    <link rel="text/javascript" href="${pageContext.request.contextPath}/static/js/main.js">

</head>
<body>
<jsp:include page="frames/user_header.jsp"/>
<jsp:include page="frames/footer.jsp"/>
</body>
</html>