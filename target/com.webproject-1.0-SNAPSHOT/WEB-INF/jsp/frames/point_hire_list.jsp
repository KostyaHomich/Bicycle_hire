<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<table class="table table-striped jambo_table bulk_action">
    <thead>
    <tr class="headings">
        <th><fmt:message key="point_hire.location"/></th>
        <th><fmt:message key="point_hire.description"/></th>
        <th><fmt:message key="point_hire.telephone"/></th>
    </tr>
    </thead>
    <tbody>
<c:forEach items="${requestScope.pointHireList}" var="pointHire" varStatus="counter" >
    <tr class="even pointer">
        <td>${pointHire.getLocation()}</td>
        <td>${pointHire.getDescription()}</td>
        <td>${pointHire.getTelephone()}</td>
    </tr>
</c:forEach>
    </tbody>
</table>


