<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ page import="epam.project.entity.UserRole" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="container">
    <section id="content">

        <form action="${pageContext.request.contextPath}/point_hire_list" method="post">
            <h1><fmt:message key="page.title.point_hire_details"/></h1>
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
            <input type="hidden"
            <c:if test="${not empty sessionScope.signInUser && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.USER)}">
                   readonly
            </c:if>
                   name="id" value="${requestScope.pointHire.getId()}">
            <div>
                <input type="text"
                        <c:if test="${not empty sessionScope.signInUser && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.USER)}">
                            readonly
                        </c:if>
                       value="${requestScope.pointHire.getLocation()}"
                       placeholder="<fmt:message key="point_hire.location"/>"
                       id="location"
                       name="location"
                       minlength="4"
                       maxlength="25"
                       required
                       pattern="^[a-zA-Z0-9]{4,25}$"
                       title="<fmt:message key="user.error.invalid_login"/>"
                />
            </div>
            <div>
                <input type="text"
                        <c:if test="${not empty sessionScope.signInUser && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.USER)}">
                            readonly
                        </c:if>
                       value="${requestScope.pointHire.getTelephone()}"
                       placeholder="<fmt:message key="point_hire.telephone"/>"
                       id="telephone"
                       name="telephone"
                       required
                       pattern="^(\s*)?(\+)?([- _():=+]?\d[- _():=+]?){10,14}(\s*)?$"
                       title="<fmt:message key="point_hire.error.invalid_telephone"/>"
                />
            </div>
            <div>
                <input type="text"
                        <c:if test="${not empty sessionScope.signInUser && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.USER)}">
                            readonly
                        </c:if>
                       value="${requestScope.pointHire.getDescription()}"
                       placeholder="<fmt:message key="point_hire.description"/>"
                       id="description"
                       name="description"
                       minlength="3"
                />
            </div>
            <c:if test="${sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
            <div>

                    <c:if test="${requestScope.pointHire.getId()==0}">
                        <input type="hidden" name="command" value="${CommandType.ADD_POINT_HIRE}">
                        <input type="submit" value="<fmt:message key="page.button.add"/>">
                    </c:if>

                    <c:if test="${requestScope.pointHire.getId()!=0}">
                        <input type="hidden" name="command" value="${CommandType.UPDATE_POINT_HIRE}">
                        <input type="submit" value="<fmt:message key="page.button.update"/>">
                    </c:if>

            </div>
            </c:if>
        </form>
        <form action="${pageContext.request.contextPath}/point_hire_details" method="post">
            <div>
                <input type="submit" value="<fmt:message key="page.button.back"/>">
                <input type="hidden" name="command" value="${CommandType.SHOW_POINT_HIRE_LIST}">
            </div>
        </form>
    </section>
</div>
