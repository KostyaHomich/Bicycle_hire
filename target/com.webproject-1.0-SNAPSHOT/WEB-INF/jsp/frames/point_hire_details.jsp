<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ page import="epam.project.entity.UserRole" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags" %>

<div class="container">
    <section id="content">

        <form action="${pageContext.request.contextPath}/point_hire_list" method="post">
            <h1>Point hire details</h1>
            <c:out value="${requestScope.error}"/>

            <c:if test="${ not empty requestScope.errorsList}">
                <c:forEach var="entry" items="${requestScope.errorsList.getErrors()}">
                    <c:if test="${entry.value.size() >0}">
                        Error:<c:out value="${entry.value}"/>
                    </c:if>
                </c:forEach>
            </c:if>
            <input type="hidden" name="id" value="${requestScope.pointHire.getId()}">
            <div>
                <input type="text" value="${requestScope.pointHire.getLocation()}" placeholder="Location" id="location"
                       name="location"/>
            </div>
            <div>
                <input type="text" value="${requestScope.pointHire.getTelephone()}" placeholder="Telephone" id="telephone"
                       name="telephone"/>
            </div>
            <div>
                <input type="text" value="${requestScope.pointHire.getDescription()}" placeholder="Description" id="description"
                       name="description"/>
            </div>
            <c:if test="${sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
            <div>

                    <c:if test="${requestScope.pointHire.getId()==0}">
                        <input type="hidden" name="command" value="${CommandType.ADD_POINT_HIRE}">
                        <input type="submit" value="Add">
                    </c:if>

                    <c:if test="${requestScope.pointHire.getId()!=0}">
                        <input type="hidden" name="command" value="${CommandType.UPDATE_POINT_HIRE}">
                        <input type="submit" value="Update">
                    </c:if>

            </div>
            </c:if>

        </form>
        <form action="${pageContext.request.contextPath}/point_hire_details" method="post">
            <div>
                <input type="submit" value="Back">
                <input type="hidden" name="command" value="${CommandType.SHOW_POINT_HIRE_LIST}">
            </div>
        </form>
        <!-- form -->
    </section>
    <!-- content -->
</div>
