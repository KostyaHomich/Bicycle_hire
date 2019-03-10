<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="epam.project.command.CommandType" %>
<html>
<head>
    <title>Admin Page</title>
    <meta charset="utf-8">
    <meta name="description" content="La casa free real state fully responsive html5/css3 home page website template"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/responsive.css">

    <link rel="text/javascript" href="${pageContext.request.contextPath}/static/js/jquery.js">
    <link rel="text/javascript" href="${pageContext.request.contextPath}/static/js/main.js">

    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/nprogress.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/green.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/custom.min.css" rel="stylesheet">

</head>
<body>
<jsp:include page="admin_header.jsp"/>

<div class="x_panel">
    <div class="x_content">

        <c:out value="${error}"/>

        <div class="table-responsive">
            <c:out value="${error}"/>
            <c:if test="${entity=='user'}">
                <table class="table table-striped jambo_table bulk_action">
                    <thead>

                    <tr class="headings">
                        <th class="column-title">ID</th>
                        <th class="column-title">Login</th>
                        <th class="column-title">Email</th>
                        <th class="column-title">First name</th>
                        <th class="column-title">Last name</th>
                        <th class="column-title">Status</th>
                        <th class="column-title">Balance</th>
                        <th class="column-title"></th>

                        <th class="bulk-actions" colspan="7">
                            <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span
                                    class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr class="even pointer">
                            <td class=" ">${user.getId()}</td>
                            <td class=" ">${user.getLogin()}</td>
                            <td class=" ">${user.getEmail()}</td>
                            <td class=" ">${user.getFirstName()}</td>
                            <td class=" ">${user.getLastName()}</td>
                            <td class=" ">${user.getStatus()}</td>
                            <td class=" ">${user.getBalance()}</td>
                            <td>
                                <form style="display: inline-block;"
                                      action="${pageContext.request.contextPath}/user_details" method="post">
                                    <input type="hidden" name="userId" value="${user.getId()}">
                                    <input type="hidden" name="command" value="${CommandType.SHOW_USER_DETAILS}">
                                    <input type="image" style="width: 20px;height: 20px"
                                           src="${pageContext.request.contextPath}/static/img/edit.jpg">
                                </form>
                                <form style="display: inline-block;"
                                      action="${pageContext.request.contextPath}/user_details" method="post">
                                    <input type="hidden" name="userId" value="${user.getId()}">
                                    <input type="hidden" name="command" value="${CommandType.DELETE_USER}">
                                    <input type="image" style="width: 20px;height: 20px"
                                           src="${pageContext.request.contextPath}/static/img/delete.jpg">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${entity=='point_hire'}">
                <table class="table table-striped jambo_table bulk_action">
                    <thead>
                    <tr class="headings">
                        <th class="column-title">ID</th>
                        <th class="column-title">Location</th>
                        <th class="column-title">Telephone</th>
                        <th class="column-title">Description</th>
                        <th class="column-title no-link last"><span class="nobr">Action</span>
                        </th>
                        <th class="bulk-actions" colspan="7">
                            <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span
                                    class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pointHireList}" var="pointHire">
                        <tr class="even pointer">
                            <td class=" ">${pointHire.getId()}</td>
                            <td class=" ">${pointHire.getLocation()}</td>
                            <td class=" ">${pointHire.getTelephone()}</td>
                            <td class=" ">${pointHire.getDescription()}</td>
                            <td>
                                <form style="display: inline-block;" action="${pageContext.request.contextPath}/point_hire_details" method="post">
                                    <input type="hidden" name="pointHireId" value="${pointHire.getId()}">
                                    <input type="hidden" name="command" value="${CommandType.SHOW_POINT_HIRE_DETAILS}">
                                    <input type="image" style="width: 20px;height: 20px" src="${pageContext.request.contextPath}/static/img/edit.jpg">
                                </form>
                                <form style="display: inline-block;" action="${pageContext.request.contextPath}/point_hire_details" method="post">
                                    <input type="hidden" name="pointHireId" value="${pointHire.getId()}">
                                    <input type="hidden" name="command" value="${CommandType.DELETE_POINT_HIRE}">
                                    <input type="image" style="width: 20px;height: 20px" src="${pageContext.request.contextPath}/static/img/delete.jpg">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${entity=='order'}">
                <table class="table table-striped jambo_table bulk_action">
                    <thead>
                    <tr class="headings">
                        <th class="column-title">ID</th>
                        <th class="column-title">ID user</th>
                        <th class="column-title">ID point hire</th>
                        <th class="column-title">ID bicycle</th>
                        <th class="column-title">Time order</th>
                        <th class="column-title">Time rental</th>
                        <th class="column-title">Status</th>
                        <th class="column-title">Cost</th>
                        <th class="column-title no-link last"><span class="nobr">Action</span></th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orderList}" var="order">
                        <tr class="even pointer">
                            <td class=" ">${order.getId()}</td>
                            <td class=" ">${order.getUser().getId()}</td>
                            <td class=" ">${order.getPointHire().getId()}</td>
                            <td class=" ">${order.getBicycle().getId()}</td>
                            <td>
                                <form style="display: inline-block;" action="${pageContext.request.contextPath}/order_details" method="post">
                                    <input type="hidden" name="orderId" value="${pointHire.getId()}">
                                    <input type="hidden" name="command" value="${CommandType.SHOW_ORDER_DETAILS}">
                                    <input type="image" style="width: 20px;height: 20px" src="${pageContext.request.contextPath}/static/img/edit.jpg">
                                </form>
                                <form style="display: inline-block;" action="${pageContext.request.contextPath}/order_delete" method="post">
                                    <input type="hidden" name="orderId" value="${pointHire.getId()}">
                                    <input type="hidden" name="command" value="${CommandType.DELETE_ORDER}">
                                    <input type="image" style="width: 20px;height: 20px" src="${pageContext.request.contextPath}/static/img/delete.jpg">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </c:if>
            <c:if test="${entity=='bicycle'}">
                <table class="table table-striped jambo_table bulk_action">

                    <thead>
                    <tr class="headings">
                        <th class="column-title">ID</th>
                        <th class="column-title">Name</th>
                        <th class="column-title">Daily rental price</th>
                        <th class="column-title">Status</th>
                        <th class="column-title">Description</th>
                        <th class="column-title no-link last"><span class="nobr">Action</span></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${bicycles}" var="bicycle">
                        <tr class="even pointer">
                            <td class=" ">${bicycle.getId()}</td>
                            <td class=" ">${bicycle.getName()}</td>
                            <td class=" ">${bicycle.getDaily_rental_price()}</td>
                            <td class=" ">${bicycle.getStatus()}</td>
                            <td class=" ">${bicycle.getDescription()}</td>
                            <td>
                                <form style="display: inline-block;" action="${pageContext.request.contextPath}/bicycle_details" method="post">
                                    <input type="hidden" name="bicycleId" value="${bicycle.getId()}">
                                    <input type="hidden" name="command" value="${CommandType.SHOW_BICYCLE_DETAILS}">
                                    <input type="image" style="width: 20px;height: 20px" src="${pageContext.request.contextPath}/static/img/edit.jpg">
                                </form>
                                <form style="display: inline-block;" action="${pageContext.request.contextPath}/bicycle_details" method="post">
                                    <input type="hidden" name="bicycleId" value="${bicycle.getId()}">
                                    <input type="hidden" name="command" value="${CommandType.DELETE_BICYCLE}">
                                    <input type="image" style="width: 20px;height: 20px" src="${pageContext.request.contextPath}/static/img/delete.jpg">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>


    </div>
</div>

</body>
</html>
