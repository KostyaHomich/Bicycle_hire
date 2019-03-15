<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ page import="epam.project.entity.UserRole" %>
<html lang="en">
<head>
    <title>Main</title>
    <meta charset="utf-8">
    <meta name="description" content="La casa free real state fully responsive html5/css3 home page website template"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/responsive.css">
    <link rel="text/javascript" href="${pageContext.request.contextPath}/static/js/jquery.js">
    <link rel="text/javascript" href="${pageContext.request.contextPath}/static/js/main.js">

</head>
<body>
<section class="hero">
    <header>
        <div class="wrapper">
            <a href="#"><img src="${pageContext.request.contextPath}/static/img/logo.png" class="logo" alt=""
                             title=""/></a>
            <a href="#" class="hamburger"></a>
            <nav>
                <c:if test="${sessionScope.signInUser==null}">
                    <a href="${pageContext.request.contextPath}/registration ?command=${CommandType.SHOW_REGISTRATION_PAGE}"
                       class="login_btn">Registration</a>
                    <a href="${pageContext.request.contextPath}/login ?command=${CommandType.SHOW_LOGIN_PAGE}"
                       class="login_btn">Login</a>
                </c:if>

                <c:if test="${sessionScope.signInUser!=null}">
                    <a href="${pageContext.request.contextPath}/user_page ?command=${CommandType.SHOW_USER_PAGE}"
                       class="login_btn">Account</a>
                </c:if>

            </nav>
        </div>
    </header>

    <!--  end header section  -->
    <section class="caption">
        <h2 class="caption">Find You Bicycle</h2>
    </section>
</section>
<!--  end hero section  -->
<jsp:include page="footer.jsp"/>

</body>
</html>