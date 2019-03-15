<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ page import="epam.project.entity.UserRole" %>

<div class="container">
    <section id="content">

        <form action="${pageContext.request.contextPath}/bicycle_list" method="post">

            <h1>Bicycles details</h1>
            <c:out value="${requestScope.error}"/>

            <c:if test="${not empty requestScope.errorsList}">
                <c:forEach var="entry" items="${requestScope.errorsList.getErrors()}">
                    <c:if test="${entry.value.size() >0}">
                        Error:<c:out value="${entry.value}"/>
                    </c:if>
                </c:forEach>
            </c:if>
            <input type="hidden" name="bicycleId" value="${requestScope.bicycle.getId()}">
            <div>
                <input type="text" value="${requestScope.bicycle.getName()}" placeholder="Name" id="name" name="name"/>
            </div>
            <div>
                <input type="text" value="${requestScope.bicycle.getDaily_rental_price()}" placeholder="Daily rental price"
                       id="daily_rental_price" name="daily_rental_price"/>
            </div>
            <div>
                <input type="text" value="${requestScope.bicycle.getStatus()}" placeholder="Status" id="status" name="status"/>
            </div>
            <div>
                <input type="text" value="${requestScope.bicycle.getDescription()}" placeholder="Description" id="description"
                       name="description"/>
            </div>
            <div>
                <c:if test="${not empty sessionScope.signInUser
            && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN)}">
                    <c:if test="${requestScope.bicycle.getId()==0}">
                        <input type="hidden" name="pointHireId" value="${requestScope.bicycle.getPoint_hire_id()}">
                        <input type="hidden" name="command" value="${CommandType.ADD_BICYCLE}">
                        <input style="display: inline-block;" type="submit" value="Add">
                    </c:if>

                    <c:if test="${requestScope.bicycle.getId()!=0}">
                        <input type="hidden" name="command" value="${CommandType.UPDATE_BICYCLE}">
                        <input style="display: inline-block;"  type="submit" value="Update">
                    </c:if>

                </c:if>
                <form style="display: inline-block; text-align:right;" action="${pageContext.request.contextPath}/bicycle_details" method="post">
                    <div>
                        <input type="submit" value="Back">
                        <input type="hidden" name="command" value="${CommandType.SHOW_BICYCLE_LIST}">
                    </div>
                </form>
            </div>

        </form>

        <!-- form -->
    </section>
    <!-- content -->
</div>

