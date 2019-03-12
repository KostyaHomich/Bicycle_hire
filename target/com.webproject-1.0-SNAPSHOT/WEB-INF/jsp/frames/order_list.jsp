<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ page import="epam.project.entity.UserRole" %>

<c:out value="${error}"/>
<table class="table table-striped jambo_table bulk_action">
    <thead>
    <tr class="headings">
        <th class="column-title">Time order</th>
        <th class="column-title">Time rental</th>
        <th class="column-title">Status</th>
        <th class="column-title">Cost</th>
        <th class="column-title no-link last"><span class="nobr">Action</span></th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${orderList}" var="order">
        <tr class="even pointer">
            <td class=" ">${order.getTimeOrder()}</td>
            <td class=" ">${order.getTimeRental()}</td>
            <td class=" ">${order.getStatus()}</td>
            <td class=" ">${order.getCost()}</td>
            <td>
                <c:if test="${sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
                <form style="display: inline-block;" action="${pageContext.request.contextPath}/order_details" method="post">
                    <input type="hidden" name="orderId" value="${order.getId()}">
                    <input type="hidden" name="command" value="${CommandType.SHOW_ORDER_DETAILS}">
                    <input type="image" style="width: 20px;height: 20px" src="${pageContext.request.contextPath}/static/img/edit.jpg">
                </form>
                <form style="display: inline-block;" action="${pageContext.request.contextPath}/order_delete" method="post">
                    <input type="hidden" name="orderId" value="${order.getId()}">
                    <input type="hidden" name="command" value="${CommandType.DELETE_ORDER}">
                    <input type="image" style="width: 20px;height: 20px" src="${pageContext.request.contextPath}/static/img/delete.jpg">
                </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
