<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>

<c:out value="${error}"/>
<table class="table table-striped jambo_table bulk_action">
    <thead>

    <tr class="headings">

        <th class="column-title">Login</th>
        <th class="column-title">Email</th>
        <th class="column-title">First name</th>
        <th class="column-title">Last name</th>
        <th class="column-title">Status</th>
        <th class="column-title">Balance</th>
        <th class="column-title no-link last"><span class="nobr">Action</span></th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.users}" var="user">
        <tr class="even pointer">
            <td class=" ">${user.getLogin()}</td>
            <td class=" ">${user.getEmail()}</td>
            <td class=" ">${user.getFirstName()}</td>
            <td class=" ">${user.getLastName()}</td>
            <td class=" ">${user.getStatus()}</td>
            <td class=" ">${user.getBalance()}</td>
            <td>
                <form style="display: inline-block;"
                      action="${pageContext.request.contextPath}/user_details" method="post">
                    <input type="hidden" name="userId" value="${user.getId()}">
                    <input type="hidden" name="command" value="${CommandType.SHOW_USER_DETAILS}">
                    <input type="image" style="width: 20px;height: 20px"
                           src="${pageContext.request.contextPath}/static/img/edit.jpg">
                </form>
                <form style="display: inline-block;"
                      action="${pageContext.request.contextPath}/user_details" method="post">
                    <input type="hidden" name="userId" value="${user.getId()}">
                    <input type="hidden" name="command" value="${CommandType.DELETE_USER}">
                    <input type="image" style="width: 20px;height: 20px"
                           src="${pageContext.request.contextPath}/static/img/delete.jpg">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
