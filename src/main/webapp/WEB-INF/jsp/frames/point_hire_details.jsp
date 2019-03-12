<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ page import="epam.project.entity.UserRole" %>
<div class="container">
    <section id="content">

        <form action="${pageContext.request.contextPath}/point_hire_list" method="post">
            <h1>Point hire details</h1>
            <c:out value="${error}"/>

            <c:if test="${ not empty errorsList}">
                <c:forEach var="entry" items="${errorsList.getErrors()}">
                    <c:if test="${entry.value.size() >0}">
                        Error:<c:out value="${entry.value}"/>
                    </c:if>
                </c:forEach>
            </c:if>
            <input type="hidden" name="id" value="${pointHire.getId()}">
            <div>
                <input type="text" value="${pointHire.getLocation()}" placeholder="Location" id="location"
                       name="location"/>
            </div>
            <div>
                <input type="text" value="${pointHire.getTelephone()}" placeholder="Telephone" id="telephone"
                       name="telephone"/>
            </div>
            <div>
                <input type="text" value="${pointHire.getDescription()}" placeholder="Description" id="description"
                       name="description"/>
            </div>
            <div>
                <c:if test="${sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN.name())}">
                    <c:if test="${pointHire.getId()==0}">
                        <input type="hidden" name="command" value="${CommandType.ADD_POINT_HIRE}">
                        <input type="submit" value="Add">
                    </c:if>

                    <c:if test="${pointHire.getId()!=0}">
                        <input type="hidden" name="command" value="${CommandType.UPDATE_POINT_HIRE}">
                        <input type="submit" value="Update">
                    </c:if>
                </c:if>

            </div>

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
