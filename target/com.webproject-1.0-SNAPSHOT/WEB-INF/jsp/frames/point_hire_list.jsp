<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ page import="epam.project.entity.UserRole" %>


<c:out value="${error}"/>
<c:if test="${sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
    <form style="text-align:right;" action="${pageContext.request.contextPath}/point_hire_details"
          method="post">
        <input type="hidden" name="pointHireId" value="0">
        <input type="hidden" name="command" value="${CommandType.SHOW_POINT_HIRE_DETAILS}">
        <input type="image" style="text-align:right;"
               src="${pageContext.request.contextPath}/static/img/addbutton.jpg">
    </form>
</c:if>
<table class="table table-striped jambo_table bulk_action">

    <c:forEach items="${pointHireList}" var="pointHire">
        <thead>
        <tr class="headings">
            <th class="column-title">Location</th>
            <th class="column-title">Telephone</th>
            <th class="column-title">Description</th>
        </tr>
        </thead>
        <tbody>
        <tr class="even pointer">
            <td class=" ">${pointHire.getLocation()}</td>
            <td class=" ">${pointHire.getTelephone()}</td>
            <td class=" ">${pointHire.getDescription()}</td>
            <td>
                <c:if test="${sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
                    <form style="display: inline-block;" action="${pageContext.request.contextPath}/point_hire_details"
                          method="post">
                        <input type="hidden" name="pointHireId" value="${pointHire.getId()}">
                        <input type="hidden" name="command" value="${CommandType.SHOW_POINT_HIRE_DETAILS}">
                        <input type="image" style="width: 20px;height: 20px"
                               src="${pageContext.request.contextPath}/static/img/edit.jpg">
                    </form>
                    <form style="display: inline-block;" action="${pageContext.request.contextPath}/point_hire_details"
                          method="post">
                        <input type="hidden" name="pointHireId" value="${pointHire.getId()}">
                        <input type="hidden" name="command" value="${CommandType.DELETE_POINT_HIRE}">
                        <input type="image" style="width: 20px;height: 20px"
                               src="${pageContext.request.contextPath}/static/img/delete.jpg">
                    </form>
                    <form style="display: inline-block;" action="${pageContext.request.contextPath}/point_hire_details"
                          method="post">
                        <input type="hidden" name="pointHireId" value="${pointHire.getId()}">
                        <input type="hidden" name="bicycleId" value="0">
                        <input type="hidden" name="command" value="${CommandType.SHOW_BICYCLE_DETAILS}">
                        <input type="image" style="width: 20px;height: 20px"
                               src="${pageContext.request.contextPath}/static/img/addbutton.png">
                    </form>
                </c:if>
            </td>
        </tr>
        <tr>
        <tbody>
        <thead>
        <tr class="headings">
            <th class="column-title">Name</th>
            <th class="column-title">Daily rental price</th>
            <th class="column-title">Status</th>
            <th class="column-title">Description</th>
        </tr>
        </thead>
        <c:forEach items="${pointHire.getBicycleList()}" var="bicycle">
            <tbody>
            <tr class="even pointer">
                <td class=" ">${bicycle.getName()}</td>
                <td class=" ">${bicycle.getDaily_rental_price()}</td>
                <td class=" ">${bicycle.getStatus()}</td>
                <td class=" ">${bicycle.getDescription()}</td>
                <td>
                    <c:if test="${sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
                        <form style="display: inline-block;" action="${pageContext.request.contextPath}/bicycle_details"
                              method="post">
                            <input type="hidden" name="bicycleId" value="${bicycle.getId()}">
                            <input type="hidden" name="command" value="${CommandType.SHOW_BICYCLE_DETAILS}">
                            <input type="image" style="width: 20px;height: 20px"
                                   src="${pageContext.request.contextPath}/static/img/edit.jpg">
                        </form>
                        <form style="display: inline-block;" action="${pageContext.request.contextPath}/bicycle_details"
                              method="post">
                            <input type="hidden" name="bicycleId" value="${bicycle.getId()}">
                            <input type="hidden" name="command" value="${CommandType.DELETE_BICYCLE}">
                            <input type="image" style="width: 20px;height: 20px"
                                   src="${pageContext.request.contextPath}/static/img/delete.jpg">
                        </form>
                    </c:if>
                    <c:if test="${sessionScope.signInUser.role.equalsIgnoreCase(UserRole.USER.name())}">
                        <form style="display: inline-block;" action="${pageContext.request.contextPath}/bicycle_details"
                              method="post">
                            <input type="hidden" name="orderId" value="0">
                            <input type="hidden" name="bicycleId" value="${bicycle.getId()}">
                            <input type="hidden" name="pointHireId" value="${pointHire.getId()}">
                            <input type="hidden" name="command" value="${CommandType.SHOW_ORDER_DETAILS}">
                            <input type="image" style="width: 20px;height: 20px"
                                   src="${pageContext.request.contextPath}/static/img/order_button.png">
                        </form>
                    </c:if>
                </td>
            </tr>
            </tbody>
        </c:forEach>
    </c:forEach>

</table>


