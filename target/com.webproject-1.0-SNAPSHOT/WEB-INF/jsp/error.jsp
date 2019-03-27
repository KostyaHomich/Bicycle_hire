<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>

</head>
<body>
<h3 class="has-text-danger">Error occurs, cause: <fmt:message key="${requestScope.error}"/></h3>
</body>
</html>
