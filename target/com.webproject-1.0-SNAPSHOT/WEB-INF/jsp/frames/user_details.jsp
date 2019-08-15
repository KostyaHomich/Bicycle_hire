<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container">
    <section id="content">

        <form action="${pageContext.request.contextPath}/user_list" method="post">
            <h1><fmt:message key="page.title.user_details"/></h1>

            <c:if test="${not empty requestScope.error}">
                <fmt:message key="${requestScope.error}"/>
            </c:if>

            <c:if test="${not empty requestScope.errorsList}">
                <c:forEach var="entry" items="${requestScope.errorsList.getErrors()}">
                    <c:if test="${entry.value.size() >0}">
                        <c:forEach var="value" items="${entry.value}">
                            <fmt:message key="${value}"/>
                        </c:forEach>
                    </c:if>
                </c:forEach>
            </c:if>

            <input type="hidden" name="id" value="${requestScope.user.getId()}">

            <input type="hidden" value="${requestScope.user.getLogin()}" name="login"/>
            <div>
                <p><fmt:message key="user.email"/></p>
                <input type="text" value="${requestScope.user.getEmail()}"
                       placeholder="<fmt:message key="user.email"/>"
                       id="email" name="email"/>
            </div>
            <div>
                <p><fmt:message key="user.first_name"/></p>

                <input type="text" value="${requestScope.user.getFirstName()}"
                       placeholder="<fmt:message key="user.first_name"/>" id="first_name"
                       name="first_name"/>
            </div>
            <div>
                <p><fmt:message key="user.last_name"/></p>

                <input type="text" value="${requestScope.user.getLastName()}"
                       placeholder="<fmt:message key="user.last_name"/>"
                       id="last_name" name="last_name"/>
            </div>
            <div>
                <p><fmt:message key="user.status"/></p>
                <select id="status" name="status">
                    <option
                            <c:if test="${requestScope.user.getStatus().equalsIgnoreCase('banned')}">
                                selected
                            </c:if>>
                        banned
                    </option>

                    <option
                            <c:if test="${requestScope.user.getStatus().equalsIgnoreCase('active')}">
                                selected
                            </c:if>>
                        active
                    </option>
                    <option
                            <c:if test="${requestScope.user.getStatus().equalsIgnoreCase('conformed')}">
                                selected
                            </c:if>>
                        conformed
                    </option>
                    <option
                            <c:if test="${requestScope.user.getStatus().equalsIgnoreCase('not conformed')}">
                                selected
                            </c:if>>
                        not conformed
                    </option>
                </select>

            </div>

            <div>
                <input type="submit" value="<fmt:message key="page.button.update"/>">
            </div>
            <input type="hidden" name="command" value="${CommandType.UPDATE_USER}">

        </form>
        <form action="${pageContext.request.contextPath}/user_details" method="post">

            <div>
                <input type="submit" value="<fmt:message key="page.button.back"/>">
                <input type="hidden" name="command" value="${CommandType.SHOW_USER_LIST}">
            </div>
        </form>
        <!-- form -->
    </section>
    <!-- content -->
</div>
