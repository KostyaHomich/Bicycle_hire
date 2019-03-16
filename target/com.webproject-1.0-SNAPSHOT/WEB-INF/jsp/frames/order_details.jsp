<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags" %>

<div class="container">
    <section id="content">

        <form action="${pageContext.request.contextPath}/point_hire_details" method="post">
            <h1>Order details</h1>
            <c:out value="${requestScope.error}"/>

            <c:if test="${ not empty requestScope.errorsList}">
                <c:forEach var="entry" items="${requestScope.errorsList.getErrors()}">
                    <c:if test="${entry.value.size() >0}">
                        Error:<c:out value="${entry.value}"/>
                    </c:if>
                </c:forEach>
            </c:if>
            <input type="hidden" name="orderId" value="${requestScope.order.getId()}">
            <div>
                <input type="text" value="${requestScope.order.getRentalTime()}" placeholder="Rental time" id="rental_time"
                       name="rental_time"/>
            </div>
            <div>
                <input type="text" value="${requestScope.order.getStatus()}" placeholder="status" id="status"
                       name="status"/>
            </div>
            <div>
                <input type="text" value="${requestScope.order.getTimeOrder()}" placeholder="Time order" id="time_order"
                       name="time_order"/>
            </div>
            <div>
                <input type="text" value="${requestScope.order.getCost()}" placeholder="Cost" id="cost"
                       name="cost"/>
            </div>
            <div>

                <input type="hidden" name="command" value="${CommandType.UPDATE_POINT_HIRE}">
                <input type="submit" value="Update">

            </div>

        </form>
        <form action="${pageContext.request.contextPath}/order_details" method="post">
            <div>
                <input type="submit" value="Back">
                <input type="hidden" name="command" value="${CommandType.SHOW_ORDER_LIST}">
            </div>
        </form>

    </section>

</div>
