<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ page import="epam.project.entity.UserRole" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags" %>


<section class="hero">
    <header>
        <div class="wrapper">
            <lang:lang/>
            <nav>
                <div>
                    <ul>
                        <c:if test="${sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
                            <li><a href="?command=${CommandType.SHOW_USER_LIST}">
                                <fmt:message key="page.header.users"/></a></li>
                        </c:if>
                        <li><a href="?command=${CommandType.SHOW_BICYCLE_LIST}">
                            <fmt:message key="page.header.bicycles"/></a></li>
                        <li><a href="?command=${CommandType.SHOW_POINT_HIRE_LIST}">
                            <fmt:message key="page.header.point_hires"/></a></li>
                        <li><a href="?command=${CommandType.SHOW_ORDER_LIST}">
                            <fmt:message key="page.header.orders"/></a></li>
                        <li><a href="?command=${CommandType.SHOW_USER_PAGE}">
                            <fmt:message key="page.header.button.account"/></a></li>
                    </ul>

                    <a href="?command=${CommandType.LOGOUT}"
                       class="login_btn">
                        <fmt:message key="page.header.button.logout"/>
                    </a>

                </div>
            </nav>
        </div>
    </header>
</section>

