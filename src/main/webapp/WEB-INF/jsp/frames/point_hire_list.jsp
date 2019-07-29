<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<table class="table table-responsive table-hover">
    <thead>
    <tr>
        <th></th>
        <th><fmt:message key="point_hire.location"/></th>
        <th><fmt:message key="point_hire.description"/></th>
        <th><fmt:message key="point_hire.telephone"/></th>
    </tr>
    </thead>
    <tbody>
<c:forEach items="${requestScope.pointHireList}" var="pointHire" varStatus="counter" >
    <tr class="clickable" data-toggle="collapse" id="row${counter.count}" data-target=".row${counter.count}">
        <td><img style="width: 32px;height: 32px;" src="${pageContext.request.contextPath}/static/img/down.png"></td>
        <td>${pointHire.getLocation()}</td>
        <td>${pointHire.getDescription()}</td>
        <td>${pointHire.getTelephone()}</td>
    </tr>

    <c:forEach items="${pointHire.getBicycleList()}" var="bicycle">
    <tr class="collapse row${counter.count}">
        <td></td>
        <td>${bicycle.getName()}</td>
        <td>${bicycle.getDescription()}</td>
        <td><input value="<fmt:message key="page.button.show"/>" type="button" onclick="location.href='${bicycle.getLink()}'" /></td>
    </tr>
    </c:forEach>
</c:forEach>
    </tbody>
</table>


