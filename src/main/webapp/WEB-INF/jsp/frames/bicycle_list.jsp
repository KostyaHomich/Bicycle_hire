<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ page import="epam.project.entity.UserRole" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<table class="table table-striped jambo_table bulk_action">

    <thead>
    <tr class="headings">
        <th class="column-title"><fmt:message key="bicycle.name"/></th>
        <th class="column-title"><fmt:message key="bicycle.daily_rental_price"/></th>
        <th class="column-title"><fmt:message key="bicycle.status"/></th>
        <th class="column-title"><fmt:message key="bicycle.description"/></th>
        <th class="column-title no-link last"><span class="nobr"><fmt:message key="page.button.action"/></span></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${requestScope.bicycles}" var="bicycle">
        <tr class="even pointer">

            <td class=" ">${bicycle.getName()}</td>
            <td class=" ">${bicycle.getDaily_rental_price()}&#36;</td>
            <td class=" ">${bicycle.getStatus()}</td>
            <td class=" ">${bicycle.getDescription()}</td>
            <td>
                <form style="display: inline-block;" action="${pageContext.request.contextPath}/bicycle_details"
                      method="post">
                    <input type="hidden" name="lastPage" value="bicycle_list">
                    <input type="hidden" name="bicycleId" value="${bicycle.getId()}">
                    <input type="hidden" name="command" value="${CommandType.SHOW_BICYCLE_DETAILS}">
                    <input type="submit" value="<fmt:message key="page.button.show"/>">
                </form>
                <c:if test="${not empty sessionScope.signInUser
            && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())
            && !bicycle.getStatus().equalsIgnoreCase('rented')}">
                    <form style="display: inline-block;" action="${pageContext.request.contextPath}/bicycle_details"
                          method="post">
                        <input type="hidden" name="lastPage" value="bicycle_list">
                        <input type="hidden" name="bicycleId" value="${bicycle.getId()}">
                        <input type="hidden" name="command" value="${CommandType.DELETE_BICYCLE}">
                        <input type="submit" value="<fmt:message key="page.button.delete"/>">
                    </form>
                </c:if>
            </td>

        </tr>
    </c:forEach>

    </tbody>
</table>
<form style="text-align:right;" action="${pageContext.request.contextPath}/bicycle_list"
      method="post">
    <input type="hidden" name="amountBicycles" value="${5 + requestScope.amountBicycles}">
    <input type="hidden" name="command" value="${CommandType.SHOW_BICYCLE_LIST}">
    <input type="submit" value="<fmt:message key="page.button.show_more"/>">
</form>

