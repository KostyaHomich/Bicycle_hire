<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
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
        <div class="table-responsive">
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
                    <th class="column-title no-link last"><span class="nobr">Action</span></th>
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
                            <button type="button"><img style="height: 20px; width: 20px" src="${pageContext.request.contextPath}/static/img/edit.jpg"></button>
                            <button type="button"><img style="height: 20px; width: 20px" src="${pageContext.request.contextPath}/static/img/delete.jpg"></button>
                        </td>
                        <td class=" last"><a href="#">View</a>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>


    </div>
</div>

</body>
</html>
