<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ page import="epam.project.entity.UserRole" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<tbody>
<div class="container">
    <section id="content">



        <form action="${pageContext.request.contextPath}/point_hire_details" method="post">
            <h1><fmt:message key="page.title.order_details"/></h1>
            <c:out value="${requestScope.error}"/>

            <c:if test="${not empty requestScope.errorsList}">
                <c:forEach var="entry" items="${requestScope.errorsList.getErrors()}">
                    <c:if test="${entry.value.size() >0}">
                        <c:forEach var="value" items="${entry.value}">
                            <fmt:message key="${value}"/>
                        </c:forEach>
                    </c:if>
                </c:forEach>
            </c:if>

            <div>
            <p>Cost</p>
                <input type="text"
                        <c:if test="${not empty sessionScope.signInUser && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.USER)}">
                            readonly
                        </c:if>
                       value="${requestScope.order.getCost()}"
                       placeholder="<fmt:message key="order.cost"/>" id="cost"
                       name="cost"/>
            </div>
            <div>
                <input type="hidden" name="orderId" value="0">
                <input type="hidden" name="time_order" value="${requestScope.order.getTimeOrder()}">
                <input type="hidden" name="time_rental" value="${requestScope.order.getRentalTime()}">
                <input type="hidden" name="pointHireId" value="${requestScope.order.getPointHire().getId()}">
                <input type="hidden" name="bicycleId" value="${requestScope.order.getBicycle().getId()}">

                <input type="hidden" name="command" value="${CommandType.ADD_ORDER}">

                <input type="submit" value="<fmt:message key="page.button.rent"/>">

            </div>

        </form>
        <form action="${pageContext.request.contextPath}/order_details" method="post">
            <div>
                <input type="hidden" name="orderId" value="0">
                <input type="hidden" name="pointHireId" value="${requestScope.order.getPointHire().getId()}">
                <input type="hidden" name="bicycleId" value="${requestScope.order.getBicycle().getId()}">
                <input type="hidden" name="command" value="${CommandType.SHOW_ORDER_DETAILS}">
                <input type="submit" value="<fmt:message key="page.button.back"/>">
            </div>
        </form>

    </section>

</div>
</tbody>