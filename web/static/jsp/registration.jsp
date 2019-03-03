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
		<form action="${pageContext.request.contextPath}/registration" method="post">

			<h1>Registration Form</h1>

			<%if(request.getParameter("error_message")!=null) {
			    out.println("Error: input data is not valid");
			}%>

			<div>
				<input type="text" placeholder="Login" required="" id="login" name="login" />
			</div>
			<div>
				<input type="password" placeholder="Password" required="" id="password" name="password"/>
			</div>
			<div>
				<input type="password" placeholder="Repeat password" required="" id="repeat_password" name="repeat_password"/>
			</div>
			<div>
				<input type="text" placeholder="First name" required="" id="first_name" name="first_name" />
			</div>
			<div>
				<input type="text" placeholder="Last name" required="" id="last_name" name="last_name"/>
			</div>
			<div>
				<input type="text" placeholder="Email" required="" id="email" name="email" />
			</div>
			<div>
                <input type="submit" value="Register">
			</div>
			<input type="hidden" name="command" value="${CommandType.REGISTER_USER}">
		</form>
		<!-- form -->
	</section>
	<!-- content -->
</div>
<!-- container -->
</body>
</html>

