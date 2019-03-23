<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ page import="epam.project.entity.UserRole" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:choose>
    <c:when test="${not empty requestScope.orderList}">
        <table class="table table-striped jambo_table bulk_action">
            <thead>
            <tr class="headings">
                <th class="column-title"><fmt:message key="order.time_order"/></th>
                <th class="column-title"><fmt:message key="order.time_rental"/></th>
                <th class="column-title"><fmt:message key="order.status"/></th>
                <th class="column-title"><fmt:message key="order.cost"/></th>
                <th class="column-title no-link last"><span class="nobr">
                    <fmt:message key="page.button.action"/></span>
                </th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.orderList}" var="order">


                <tr class="even pointer">
                    <td class=" ">${order.getTimeOrder()}</td>
                    <td class=" ">${order.getRentalTime()}</td>
                    <td class=" ">${order.getStatus()}</td>
                    <td class=" ">${order.getCost()}&#36;</td>
                    <td>
                        <c:choose>
                            <c:when test="${sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
                                <form style="display: inline-block;"
                                      action="${pageContext.request.contextPath}/order_details"
                                      method="post">
                                    <input type="hidden" name="orderId" value="${order.getId()}">
                                    <input type="hidden" name="command" value="${CommandType.SHOW_ORDER_DETAILS}">
                                    <input type="submit" value="<fmt:message key="page.button.show"/>">
                                </form>
                                <c:if test="${!order.getStatus().equalsIgnoreCase('in working')}">
                                <form style="display: inline-block;"
                                      action="${pageContext.request.contextPath}/order_delete"
                                      method="post">
                                    <input type="hidden" name="orderId" value="${order.getId()}">
                                    <input type="hidden" name="command" value="${CommandType.DELETE_ORDER}">
                                    <input type="submit" value="<fmt:message key="page.button.delete"/>">
                                </form>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${!order.getStatus().equalsIgnoreCase('finished')}">

                                <form style="display: inline-block;"
                                      action="${pageContext.request.contextPath}/order_delete"
                                      method="post">
                                    <input type="hidden" name="orderId" value="${order.getId()}">
                                    <input type="hidden" name="command" value="${CommandType.CANCEL_ORDER}">
                                    <input type="submit" value="<fmt:message key="page.button.cancel_order"/>">
                                </form>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>


            </c:forEach>
            </tbody>
        </table>
        <form style="text-align:right;" action="${pageContext.request.contextPath}/order_list"
              method="post">
            <!--5 default count orders-->
            <input type="hidden" name="amountOrders" value="${5 + requestScope.amountOrders}">
            <input type="hidden" name="command" value="${CommandType.SHOW_ORDER_LIST}">
            <input type="submit" value="<fmt:message key="page.button.show_more"/>">
        </form>
    </c:when>
    <c:otherwise>
        <p style="text-align: center; font-size:25px;"><fmt:message key="order.message.not_orders"/></p>
    </c:otherwise>
</c:choose>


