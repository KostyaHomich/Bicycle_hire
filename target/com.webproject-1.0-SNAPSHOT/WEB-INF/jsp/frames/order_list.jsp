<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="epam.project.command.CommandType" %>             <table class="table table-striped jambo_table bulk_action">
    <thead>
    <tr class="headings">
        <th class="column-title">ID point hire</th>
        <th class="column-title">ID bicycle</th>
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
            <td class=" ">${order.getPointHire().getId()}</td>
            <td class=" ">${order.getBicycle().getId()}</td>
            <td>
                <form style="display: inline-block;" action="${pageContext.request.contextPath}/order_details" method="post">
                    <input type="hidden" name="orderId" value="${pointHire.getId()}">
                    <input type="hidden" name="command" value="${CommandType.SHOW_ORDER_DETAILS}">
                    <input type="image" style="width: 20px;height: 20px" src="${pageContext.request.contextPath}/static/img/edit.jpg">
                </form>
                <form style="display: inline-block;" action="${pageContext.request.contextPath}/order_delete" method="post">
                    <input type="hidden" name="orderId" value="${pointHire.getId()}">
                    <input type="hidden" name="command" value="${CommandType.DELETE_ORDER}">
                    <input type="image" style="width: 20px;height: 20px" src="${pageContext.request.contextPath}/static/img/delete.jpg">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
