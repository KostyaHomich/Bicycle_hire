<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ page import="epam.project.entity.UserRole" %>

<c:if test="${not empty sessionScope.signInUser
&& sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
    <form style="text-align:right;" action="${pageContext.request.contextPath}/point_hire_details"
          method="post">
        <input type="hidden" name="pointHireId" value="0">
        <input type="hidden" name="command" value="${CommandType.SHOW_POINT_HIRE_DETAILS}">
        <input type="submit" value="Add point hire">
    </form>
</c:if>
<table class="table table-striped jambo_table bulk_action">

    <c:forEach items="${requestScope.pointHireList}" var="pointHire">
        <thead>
        <tr class="headings">
            <th class="column-title">Location</th>
            <th class="column-title">Telephone</th>
            <th class="column-title">Description</th>

            <c:if test="${not empty sessionScope.signInUser
            && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
                <th class="column-title no-link last"><span class="nobr">Action</span></th>
            </c:if>

        </tr>
        </thead>
        <tbody>
        <tr class="even pointer">
            <td class=" ">${pointHire.getLocation()}</td>
            <td class=" ">${pointHire.getTelephone()}</td>
            <td class=" ">${pointHire.getDescription()}</td>
            <td>
                <c:if test="${not empty sessionScope.signInUser
                && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
                    <form style="display: inline-block;" action="${pageContext.request.contextPath}/point_hire_details"
                          method="post">
                        <input type="hidden" name="pointHireId" value="${pointHire.getId()}">
                        <input type="hidden" name="command" value="${CommandType.SHOW_POINT_HIRE_DETAILS}">
                        <input type="submit" value="Show">
                    </form>
                    <form style="display: inline-block;" action="${pageContext.request.contextPath}/point_hire_details"
                          method="post">
                        <input type="hidden" name="pointHireId" value="${pointHire.getId()}">
                        <input type="hidden" name="command" value="${CommandType.DELETE_POINT_HIRE}">
                        <input type="submit" value="Delete">
                    </form>
                    <form style="display: inline-block;" action="${pageContext.request.contextPath}/point_hire_details"
                          method="post">
                        <input type="hidden" name="pointHireId" value="${pointHire.getId()}">
                        <input type="hidden" name="bicycleId" value="0">
                        <input type="hidden" name="command" value="${CommandType.SHOW_BICYCLE_DETAILS}">
                        <input type="submit" value="Add bicycle">
                    </form>
                </c:if>
            </td>

        </tr>
        <c:if test="${not empty pointHire.getBicycleList()}">
            <tr>


            <tbody>
            <thead>
            <tr class="headings">
                <th class="column-title">Name</th>
                <th class="column-title">Daily rental price</th>
                <th class="column-title">Status</th>
                <th class="column-title">Description</th>

                <c:if test="${not empty sessionScope.signInUser
            && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
                    <th class="column-title no-link last"><span class="nobr">Action</span></th>
                </c:if>
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

                        <form style="display: inline-block;" action="${pageContext.request.contextPath}/bicycle_details"
                              method="post">
                            <input type="hidden" name="bicycleId" value="${bicycle.getId()}">
                            <input type="hidden" name="command" value="${CommandType.SHOW_BICYCLE_DETAILS}">
                            <input type="submit" value="Show">
                        </form>
                        <c:if test="${not empty sessionScope.signInUser
                    && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">

                            <form style="display: inline-block;"
                                  action="${pageContext.request.contextPath}/bicycle_details"
                                  method="post">
                                <input type="hidden" name="bicycleId" value="${bicycle.getId()}">
                                <input type="hidden" name="command" value="${CommandType.DELETE_BICYCLE}">
                                <input type="submit" value="Delete">
                            </form>
                        </c:if>

                    </td>
                </tr>
                </tbody>
            </c:forEach>
            </tr>
        </c:if>

    </c:forEach>

</table>


