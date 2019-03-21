<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ page import="epam.project.entity.UserRole" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.signInUser
&& sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
    <form style="text-align:right;" action="${pageContext.request.contextPath}/point_hire_details"
          method="post">
        <input type="hidden" name="pointHireId" value="0">
        <input type="hidden" name="command" value="${CommandType.SHOW_POINT_HIRE_DETAILS}">
        <input type="submit" value="<fmt:message key="page.button.add_point_hire"/>">
    </form>
</c:if>
<table class="table table-striped jambo_table bulk_action">

    <c:forEach items="${requestScope.pointHireList}" var="pointHire">
        <thead>
        <tr class="headings">
            <th class="column-title"><fmt:message key="point_hire.location"/></th>
            <th class="column-title"><fmt:message key="point_hire.telephone"/></th>
            <th class="column-title"><fmt:message key="point_hire.description"/></th>

            <c:if test="${not empty sessionScope.signInUser
            && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
                <th class="column-title no-link last"><span class="nobr"><fmt:message key="page.button.action"/></span>
                </th>
            </c:if>

        </tr>
        </thead>
        <tbody>
        <tr class="even pointer">
            <td class=" ">${pointHire.getLocation()}</td>
            <td class=" ">${pointHire.getTelephone()}</td>
            <td class=" ">${pointHire.getDescription()}</td>
            <c:if test="${not empty sessionScope.signInUser
                && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
                <td>
                    <form style="display: inline-block;" action="${pageContext.request.contextPath}/point_hire_details"
                          method="post">
                        <input type="hidden" name="pointHireId" value="${pointHire.getId()}">
                        <input type="hidden" name="command" value="${CommandType.SHOW_POINT_HIRE_DETAILS}">
                        <input type="submit" value="<fmt:message key="page.button.show"/>">
                    </form>
                    <form style="display: inline-block;" action="${pageContext.request.contextPath}/point_hire_details"
                          method="post">
                        <input type="hidden" name="pointHireId" value="${pointHire.getId()}">
                        <input type="hidden" name="command" value="${CommandType.DELETE_POINT_HIRE}">
                        <input type="submit" value="<fmt:message key="page.button.delete"/>">
                    </form>
                    <form style="display: inline-block;" action="${pageContext.request.contextPath}/add_bicycle"
                          method="post">
                        <input type="hidden" name="pointHireId" value="${pointHire.getId()}">
                        <input type="hidden" name="bicycleId" value="0">
                        <input type="hidden" name="command" value="${CommandType.SHOW_BICYCLE_DETAILS}">
                        <input type="submit" value="<fmt:message key="page.button.add_bicycle"/>">
                    </form>

                </td>
            </c:if>
        </tr>
        <c:if test="${not empty pointHire.getBicycleList()}">
            <tbody>
            <thead>
            <tr class="headings">
                <th class="column-title"><fmt:message key="bicycle.name"/></th>
                <th class="column-title"><fmt:message key="bicycle.daily_rental_price"/></th>
                <c:if test="${not empty sessionScope.signInUser
                    && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
                    <th class="column-title"><fmt:message key="bicycle.status"/></th>
                </c:if>
                <th class="column-title"><fmt:message key="bicycle.description"/></th>
                <th class="column-title no-link last"><span class="nobr"><fmt:message key="page.button.action"/></span>
                </th>

            </tr>
            </thead>
            <c:forEach items="${pointHire.getBicycleList()}" var="bicycle">
                <tbody>
                <tr class="even pointer">
                    <td class=" ">${bicycle.getName()}</td>
                    <td class=" ">${bicycle.getDaily_rental_price()}&#36;</td>
                    <c:if test="${not empty sessionScope.signInUser
                    && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
                    <td class=" ">${bicycle.getStatus()}</td>
                    </c:if>
                    <td class=" ">${bicycle.getDescription()}</td>

                    <td>
                        <form style="display: inline-block;" action="${pageContext.request.contextPath}/bicycle_details"
                              method="post">
                            <input type="hidden" name="bicycleId" value="${bicycle.getId()}">
                            <input type="hidden" name="command" value="${CommandType.SHOW_BICYCLE_DETAILS}">
                            <input type="submit" value="<fmt:message key="page.button.show"/>">
                        </form>

                        <c:if test="${not empty sessionScope.signInUser
                    && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.USER.name())}">
                            <form style="display: inline-block;"
                                  action="${pageContext.request.contextPath}/bicycle_details"
                                  method="post">
                                <input type="hidden" name="pointHireId" value="${pointHire.getId()}">
                                <input type="hidden" name="bicycleId" value="${bicycle.getId()}">
                                <input type="hidden" name="orderId" value="0">
                                <input type="hidden" name="command" value="${CommandType.SHOW_ORDER_DETAILS}">
                                <input type="submit" value="<fmt:message key="page.button.rent"/>">
                        </form>
                        </c:if>

                        <c:if test="${not empty sessionScope.signInUser
                    && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
                            <form style="display: inline-block;"
                                  action="${pageContext.request.contextPath}/bicycle_details"
                                  method="post">
                                <input type="hidden" name="bicycleId" value="${bicycle.getId()}">
                                <input type="hidden" name="command" value="${CommandType.DELETE_BICYCLE}">
                                <input type="submit" value="<fmt:message key="page.button.delete"/>">
                            </form>
                        </c:if>

                    </td>
                </tr>
                </tbody>
            </c:forEach>
        </c:if>

    </c:forEach>

</table>


