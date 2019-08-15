<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ page import="epam.project.entity.UserRole" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<table class="table table-striped jambo_table bulk_action">
    <form action="${pageContext.request.contextPath}/point_hire_details" method="post">

        <thead>
        <tr class="headings">
            <th class="column-title"><fmt:message key="bicycle.name"/></th>
            <th class="column-title"><fmt:message key="bicycle.description"/></th>
            <th class="column-title no-link last"><span class="nobr"><fmt:message key="page.button.action"/></span></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${requestScope.bicycles}" var="bicycle">
            <tr class="even pointer">

                <td class=" ">${bicycle.getName()}</td>
                <td class=" ">${bicycle.getDescription()}</td>
                <td>
                    <input value="<fmt:message key="page.button.show"/>" type="button" onclick="location.href='${bicycle.getLink()}'" />


                    <c:if test="${sessionScope.signInUser.role.equalsIgnoreCase(UserRole.USER)}">
                        <form style="display: inline-block;"
                              action="${pageContext.request.contextPath}/bicycle_list"
                              method="post">
                            <input type="hidden" name="bicycleId" value="${bicycle.getId()}">
                            <input type="hidden" name="command" value="${CommandType.ADD_BEST_BICYCLE}">
                            <input type="submit" value="<fmt:message key="page.button.add_best_bicycle"/>">

                        </form>
                    </c:if>

                </td>

            </tr>
        </c:forEach>

</table>
<div style="float: right">
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${requestScope.amountPages}" var="i">
                <c:if test="${requestScope.page != i}">
                    <form action="${pageContext.request.contextPath}/bicycle_list"
                          method="post">
                        <input type="hidden" name="page" value="${i}">
                        <input type="hidden" name="command" value="${CommandType.SHOW_BICYCLE_LIST}">
                        <input type="submit" value="${i}">
                    </form>
                </c:if>
            </c:forEach>
        </tr>
    </table>

    <c:if test="${requestScope.page != 1}">
        <form action="${pageContext.request.contextPath}/bicycle_list"
              method="post">
            <input type="hidden" name="page" value="${requestScope.page-1}">
            <input type="hidden" name="command" value="${CommandType.SHOW_BICYCLE_LIST}">
            <input type="submit" value="<fmt:message key="page.button.previous"/>">
        </form>
    </c:if>

    <c:if test="${requestScope.page  lt requestScope.amountPages}">
        <form action="${pageContext.request.contextPath}/bicycle_list"
              method="post">
            <input type="hidden" name="page" value="${requestScope.page+1}">
            <input type="hidden" name="command" value="${CommandType.SHOW_BICYCLE_LIST}">
            <input type="submit" value="<fmt:message key="page.button.next"/>">
        </form>

    </c:if>
</div>



