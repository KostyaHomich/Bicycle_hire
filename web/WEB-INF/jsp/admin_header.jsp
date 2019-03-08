<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<section class="hero">
    <header>
        <div class="wrapper">
            <a href="#"><img src="${pageContext.request.contextPath}/static/img/logo.png" class="logo" alt=""
                             title=""/></a>
            <a href="#" class="hamburger"></a>
            <nav>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/user ?command=${CommandType.SHOW_USERS_PAGE}">Users</a></li>
                    <li><a href="${pageContext.request.contextPath}/bicycle ?command=${CommandType.SHOW_BICYCLES_PAGE}">Bicycles</a></li>
                    <li><a href="${pageContext.request.contextPath}/point_hire?command=${CommandType.SHOW_POINT_HIRE_PAGE}">Point hires</a></li>
                    <!-- <li><a href="${pageContext.request.contextPath}/WEB-INF/jsp/order.jsp ?command=${CommandType.SHOW_LOGIN_PAGE}">Orders</a></li> -->
                </ul>
                <a href="${pageContext.request.contextPath}/main ?command=${CommandType.LOGOUT}"
                   class="login_btn">Log out</a>
            </nav>
        </div>
    </header>
</section>

