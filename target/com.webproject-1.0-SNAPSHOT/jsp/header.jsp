<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ page import="epam.project.entity.UserRole" %>
<section class="hero">
    <header>
        <div class="wrapper">
            <a href="#"><img src="${pageContext.request.contextPath}/static/img/logo.png" class="logo" alt=""
                             title=""/></a>
            <a href="#" class="hamburger"></a>
            <nav>
                <ul>
              <c:if test="${sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN)}">
                    <li><a href="?command=${CommandType.SHOW_USER_LIST}">Users</a></li>
              </c:if>
                    <li><a href="?command=${CommandType.SHOW_BICYCLE_LIST}">Bicycles</a></li>
                    <li><a href="?command=${CommandType.SHOW_POINT_HIRE_LIST}">Point hires</a></li>
                    <li><a href="?command=${CommandType.SHOW_ORDER_LIST}">Orders</a></li>
                </ul>
                <a href="?command=${CommandType.LOGOUT}"
                   class="login_btn">Log out</a>
            </nav>
        </div>
    </header>
</section>

