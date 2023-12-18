<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<jsp:include page="/jsp/stile.jsp"></jsp:include>
</head>
<body>

<form action="<%=request.getContextPath()+"/logout"%>" method="post">
	<input type="submit" value="Logout">
</form>

</body>
</html>