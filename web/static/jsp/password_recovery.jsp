<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.controller.command.CommandType" %>
<html lang="en" >
<head>
    <meta charset="utf-8">
    <title>Paper Stack</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<div class="container">
    <section id="content">
        <form action="${pageContext.request.contextPath}/password_recovery" method="post">
            <h1>Recovery Form</h1>
            <p style="font-size: 13px; font-style:italic; color: #004a80" >New password will send you to your email.</p>
            <div>
                <input type="text" placeholder="Username" required="" id="login" name="login" />
            </div>
            <div>
                <input type="text" placeholder="Email" required="" id="email" name="email" />
            </div>
            <div>
                <input type="submit" value="Recovery" />
            </div>
            <input type="hidden" name="command" value="${CommandType.RECOVERY_PASSWORD}">
        </form>
        <!-- form -->

    </section>
    <!-- content -->
</div>
<!-- container -->
</body>
</html>



</body>

</html>
