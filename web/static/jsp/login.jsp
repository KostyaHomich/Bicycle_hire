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
		<form action="${pageContext.request.contextPath}/login" method="post">
			<h1>Login Form</h1>
			<div>
				<input type="text" placeholder="Username" required="" id="login" name="login" />
			</div>
			<div>
				<input type="password" placeholder="Password" required="" id="password" name="password" />
			</div>
			<div>
				<input type="submit" value="Log in" />
				<a href="password_recovery.jsp">Lost your password?</a>
				<a href="registration.jsp">Register</a>
			</div>
			<input type="hidden" name="command" value="${CommandType.LOGIN}">
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
