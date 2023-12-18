<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Arkinue</title>
</head>
<body>
<form action="<%=request.getContextPath() + "/ricercaSemplice"%>">
ricerca:<input type="text" name="<%=Costanti.NAME_RICERCATO%>">

<input type="submit" value="Ricerca">
</form>
<jsp:include page="/jsp/menu.jsp"></jsp:include>
</body>
</html>