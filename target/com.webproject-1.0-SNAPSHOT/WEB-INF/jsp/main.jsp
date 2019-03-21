<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags" %>

<head>
    <title>Main</title>
    <meta charset="utf-8">
    <meta name="description" content="La casa free real state fully responsive html5/css3 home page website template"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/responsive.css">
    <link rel="text/javascript" href="${pageContext.request.contextPath}/static/js/jquery.js">
    <link rel="text/javascript" href="${pageContext.request.contextPath}/static/js/main.js">

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

<section class="hero">
    <header>
        <div class="wrapper">
            <lang:lang/>
            <nav>
                <c:if test="${sessionScope.signInUser==null}">
                    <a href="${pageContext.request.contextPath}/registration ?command=${CommandType.SHOW_REGISTRATION_PAGE}"
                       class="login_btn"><fmt:message key="page.main.button.registration"/> </a>

                    <a href="${pageContext.request.contextPath}/login ?command=${CommandType.SHOW_LOGIN_PAGE}"
                       class="login_btn"><fmt:message key="page.button.login"/> </a>
                </c:if>

                <c:if test="${sessionScope.signInUser!=null}">
                    <a href="${pageContext.request.contextPath}/user_page ?command=${CommandType.SHOW_USER_PAGE}"
                       class="login_btn"><fmt:message key="page.header.button.account"/> </a>
                </c:if>

            </nav>
        </div>
    </header>

    <!--  end header section  -->
    <section class="caption">
        <h2 class="caption"><fmt:message key="page.main.welcome"/></h2>
    </section>
</section>
<!--  end hero section  -->
<jsp:include page="footer.jsp"/>

</body>
