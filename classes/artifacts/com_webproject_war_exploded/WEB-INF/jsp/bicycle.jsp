<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="epam.project.command.CommandType" %>
<html>
<head>
    <title>Bicycles</title>
    <meta charset="utf-8">
    <meta name="description" content="La casa free real state fully responsive html5/css3 home page website template"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/responsive.css">

    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/nprogress.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/green.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/custom.min.css" rel="stylesheet">


    <link rel="text/javascript" href="${pageContext.request.contextPath}/static/js/jquery.js">
    <link rel="text/javascript" href="${pageContext.request.contextPath}/static/js/main.js">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style-modal.css">



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
                    <th class="column-title">Name</th>
                    <th class="column-title">Daily rental price</th>
                    <th class="column-title">Status</th>
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
                <c:forEach items="${bicycles}" var="bicycle">
                    <tr class="even pointer">
                        <td class=" ">${bicycle.getId()}</td>
                        <td class=" ">${bicycle.getName()}</td>
                        <td class=" ">${bicycle.getDaily_rental_price()}</td>
                        <td class=" ">${bicycle.getStatus()}</td>
                        <td class=" ">${bicycle.getDescription()}</td>
                        <td>

                            <a href="#update"><img src="${pageContext.request.contextPath}/static/img/edit.jpg"
                                                   style="width: 20px;height: 20px;"/></a>
                            <a href="#x" class="overlay" id="update"></a>
                            <div class="popup">
                                <form  action="${pageContext.request.contextPath}/bicycle.jsp" method="post">
                                    <h1 style="text-align: center">Update form</h1>
                                    <input style="margin-left: 10%" type="hidden" name="id" value="${bicycle.getId()}">
                                    <h2>Name:</h2>
                                    <input style="margin-left: 10%" type="text" name="name" value="${bicycle.getName()}"
                                           placeholder="${bicycle.getName()}">
                                    <h2>Daily rental price:</h2>
                                    <input style="margin-left: 10%" type="text" name="daily_rental_price"
                                           value="${bicycle.getDaily_rental_price()}"
                                           placeholder="${bicycle.getDaily_rental_price()}">
                                    <h2>Status:</h2>

                                    <input style="margin-left: 10%" type="text" name="status" value="${bicycle.getStatus()}"
                                           placeholder="${bicycle.getStatus()}">
                                    <h2 >Description:</h2>

                                    <input style="margin-left: 10%;height: 30%;width: 85%" type="text" name="description" value="${bicycle.getDescription()}"
                                           placeholder="${bicycle.getDescription()}">

                                    <h2></h2>
                                    <input style="padding-left: 10%; align-content: center" type="submit" value="Update">
                                    <input type="hidden" name="command" value="${CommandType.UPDATE_BICYCLE}">
                                </form>
                                <a class="close" title="Close" href="#close"></a>
                            </div>


                            <a href="#delete"><img src="${pageContext.request.contextPath}/static/img/delete.jpg"
                                                   style="width: 20px;height: 20px;"/></a>
                            <a href="#x" class="overlay" id="delete"></a>
                            <div class="popup">
                                <form action="${pageContext.request.contextPath}/bicycle.jsp" method="post">
                                    <input type="hidden" name="id" value="${bicycle.getId()}">
                                    <input type="hidden" name="command" value="${CommandType.DELETE_BICYCLE}">
                                    <input type="submit" value="Delete">
                                </form>
                                <a class="close" title="Close" href="#close"></a>
                            </div>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>


    </div>
</div>

</body>
</html>
