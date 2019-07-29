<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags" %>


<head>
    <title>${sessionScope.signInUser.role.toLowerCase()} page</title>
    <meta charset="utf-8">
    <meta name="description" content="La casa free real state fully responsive html5/css3 home page website template"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/responsive.css">

    <link rel="text/javascript" href="${pageContext.request.contextPath}/static/js/jquery.js">
    <link rel="text/javascript" href="${pageContext.request.contextPath}/static/js/main.js">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link href='https://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="http://fontawesome.io/assets/font-awesome/css/font-awesome.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style_locale.css">


    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/static/css/nprogress.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/green.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/custom.min.css" rel="stylesheet">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link href='https://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style_locale.css">

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

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/frames/header.jsp"/>

<table class="table table-striped jambo_table bulk_action">
    <thead>
    <tr class="headings">
        <th class="column-title">Login</th>
        <th class="column-title">Email</th>
        <th class="column-title">First name</th>
        <th class="column-title">Last name</th>
    </tr>
    </thead>
    <tbody>
        <tr class="even pointer">
            <td class=" ">${sessionScope.signInUser.getLogin()}</td>
            <td class=" ">${sessionScope.signInUser.getEmail()}</td>
            <td class=" ">${sessionScope.signInUser.getFirstName()}</td>
            <td class=" ">${sessionScope.signInUser.getLastName()}</td>
        </tr>
    </tbody>
</table>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/footer.jsp"/>
</body>
