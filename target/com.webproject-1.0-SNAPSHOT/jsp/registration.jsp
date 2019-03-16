<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
<meta charset="utf-8">
<title>Paper Stack</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>

<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'EN'}"/>
<fmt:setBundle basename="/text" scope="application"/>

<div class="container">
<section id="content">
		<form action="${pageContext.request.contextPath}/registration" method="post">
			<h1><fmt:message key="page.title.registration_form"/></h1>

			<c:out value="${requestScope.error}"/>

			<c:if test="${ not empty requestScope.errorsList}">
				<c:forEach var="entry" items="${requestScope.errorsList.getErrors()}">
					<c:if test="${entry.value.size() >0}">
						Error:<c:out value="${entry.value}"/>
					</c:if>
				</c:forEach>
			</c:if>
			<div>
				<input type="text" placeholder="<fmt:message key="user.login"/>" id="login" name="login"/>
			</div>
			<div>
				<input type="password" placeholder="<fmt:message key="user.password"/>" id="password" name="password"/>
			</div>
			<div>
				<input type="password" placeholder="<fmt:message key="user.repeat_password"/>" id="repeat_password"
					   name="repeat_password"/>
			</div>
			<div>
				<input type="text" placeholder="<fmt:message key="user.first_name"/>" id="first_name"
					   name="first_name"/>
			</div>
			<div>
				<input type="text" placeholder="<fmt:message key="user.last_name"/>" id="last_name" name="last_name"/>
			</div>
			<div>
				<input type="text" placeholder="<fmt:message key="user.email"/>" id="email" name="email"/>
			</div>
			<div>
				<input type="submit" value="<fmt:message key="page.registration.button.register"/>">
			</div>
			<input type="hidden" name="command" value="${CommandType.REGISTER_USER}">
		</form>
	<form action="${pageContext.request.contextPath}/registration" method="post">
		<div>
			<input type="submit" value="<fmt:message key="page.button.back"/>">
			<input type="hidden" name="command" value="${CommandType.SHOW_MAIN_PAGE}">
		</div>
	</form>
		<!-- form -->
	</section>
	<!-- content -->
</div>
<!-- container -->
</body>
</html>

