<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ page import="epam.project.entity.UserRole" %>

<div class="container">
    <section id="content">

        <form action="${pageContext.request.contextPath}/point_hire_details" method="post">
            <h1>Order details</h1>
            <c:out value="${error}"/>

            <c:if test="${ not empty errorsList}">
                <c:forEach var="entry" items="${errorsList.getErrors()}">
                    <c:if test="${entry.value.size() >0}">
                        Error:<c:out value="${entry.value}"/>
                    </c:if>
                </c:forEach>
            </c:if>
            <input type="hidden" name="id" value="${order.getId()}">
            <div>
                <input type="text" value="${order.getLocation()}" placeholder="Location" id="location"
                       name="location"/>
            </div>
            <div>
                <input type="text" value="${order.getTelephone()}" placeholder="Telephone" id="telephone"
                       name="telephone"/>
            </div>
            <div>
                <input type="text" value="${order.getDescription()}" placeholder="Description" id="description"
                       name="description"/>
            </div>
            <div>

                <input type="hidden" name="command" value="${CommandType.UPDATE_POINT_HIRE}">
                <input type="submit" value="Update">

            </div>

        </form>
        <form action="${pageContext.request.contextPath}/order_details" method="post">
            <div>
                <input type="submit" value="Back">
                <input type="hidden" name="command" value="${CommandType.SHOW_ORDER_LIST}">
            </div>
        </form>
        <!-- form -->
    </section>
    <!-- content -->
</div>
