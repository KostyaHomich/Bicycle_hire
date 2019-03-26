<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ page import="epam.project.entity.UserRole" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container">
    <section id="content">

        <form action="${pageContext.request.contextPath}/point_hire_details" method="post">
            <h1><fmt:message key="page.title.order_details"/></h1>
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
            <input type="hidden" name="orderId" value="${requestScope.order.getId()}">

            <div style="margin-bottom: 10px">
                <input type="text"
                       value="${requestScope.order.getRentalTime()}"
                       id="time_rental"
                       placeholder="<fmt:message key="order.time_rental"/>"
                       name="time_rental"
                       required
                       pattern="^[1-9]{1,10}$"
                       title="<fmt:message key="order.error.invalid_time_rental"/>"
                />

            </div>
            <div style="margin-bottom: 10px">
                <p><fmt:message key="order.time_order"/></p>
                <input type="datetime-local"
                       value="${requestScope.order.getTimeOrder()}"
                       placeholder="<fmt:message key="order.time_order"/>"
                       id="time_order"
                       required
                       name="time_order"/>
            </div>
            <c:if test="${not empty sessionScope.signInUser
                    && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
                <div>
                    <p><fmt:message key="order.status"/></p>
                    <select id="status" name="status">
                        <option
                                <c:if test="${requestScope.bicycle.getStatus().equalsIgnoreCase('finished')}">
                                    selected
                                </c:if>>
                            finished
                        </option>
                        <option
                                <c:if test="${requestScope.bicycle.getStatus().equalsIgnoreCase('in working')}">
                                    selected
                                </c:if>>
                            in working
                        </option>
                    </select>
                </div>
            </c:if>
            <c:if test="${not empty sessionScope.signInUser
                    && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
                <div>
                    <input type="text"
                           value="${requestScope.order.getCost()}"
                           placeholder="<fmt:message key="order.cost"/>"
                           id="cost"
                           name="cost"
                           required
                           pattern="^[1-9]{1,10}$"
                           title="<fmt:message key="order.error.invalid_cost"/>"
                    />
                </div>
            </c:if>
            <div>
                <c:choose>
                    <c:when test="${not empty sessionScope.signInUser
                    && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.USER.name())}">
                        <input type="hidden" name="pointHireId" value="${requestScope.order.getPointHire().getId()}">
                        <input type="hidden" name="bicycleId" value="${requestScope.order.getBicycle().getId()}">
                        <input type="hidden" name="command" value="${CommandType.SHOW_ORDER_DETAILS_SECOND_PAGE}">
                        <input type="submit" value="<fmt:message key="page.button.rent"/>">
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${requestScope.bicycle.getId()==0}">
                                <input type="hidden" name="pointHireId"
                                       value="${requestScope.order.getPointHire().getId()}">
                                <input type="hidden" name="command" value="${CommandType.ADD_ORDER}">
                                <input style="display: inline-block;" type="submit"
                                       value="<fmt:message key="page.button.add"/>">
                            </c:when>
                            <c:otherwise>
                                <input type="hidden" name="command" value="${CommandType.UPDATE_ORDER}">
                                <input type="submit" value="<fmt:message key="page.button.update"/>">
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>


            </div>

        </form>
        <form action="${pageContext.request.contextPath}/order_details" method="post">
            <div>
                <input type="submit" value="<fmt:message key="page.button.back"/>">
                <input type="hidden" name="command" value="${CommandType.SHOW_ORDER_LIST}">
            </div>
        </form>

    </section>
</div>
