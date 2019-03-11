<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="epam.project.command.CommandType" %>
<table class="table table-striped jambo_table bulk_action">

    <thead>
    <tr class="headings">
        <th class="column-title">Name</th>
        <th class="column-title">Daily rental price</th>
        <th class="column-title">Status</th>
        <th class="column-title">Description</th>
        <th class="column-title no-link last"><span class="nobr">Action</span></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${bicycles}" var="bicycle">
        <tr class="even pointer">
            <td class=" ">${bicycle.getName()}</td>
            <td class=" ">${bicycle.getDaily_rental_price()}</td>
            <td class=" ">${bicycle.getStatus()}</td>
            <td class=" ">${bicycle.getDescription()}</td>
            <td>
                <form style="display: inline-block;" action="${pageContext.request.contextPath}/bicycle_details" method="post">
                    <input type="hidden" name="bicycleId" value="${bicycle.getId()}">
                    <input type="hidden" name="command" value="${CommandType.SHOW_BICYCLE_DETAILS}">
                    <input type="image" style="width: 20px;height: 20px" src="${pageContext.request.contextPath}/static/img/edit.jpg">
                </form>
                <form style="display: inline-block;" action="${pageContext.request.contextPath}/bicycle_details" method="post">
                    <input type="hidden" name="bicycleId" value="${bicycle.getId()}">
                    <input type="hidden" name="command" value="${CommandType.DELETE_BICYCLE}">
                    <input type="image" style="width: 20px;height: 20px" src="${pageContext.request.contextPath}/static/img/delete.jpg">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

