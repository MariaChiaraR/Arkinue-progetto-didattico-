<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<%@page import="com.arcobaleno.arkinue.model.Articolo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<jsp:include page="/jsp/stile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/jsp/header.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>
<div class="container" style="background: linear-gradient(73deg, rgba(255,125,125,1) 0%, rgba(255,253,180,1) 19%, rgba(180,255,186,1) 38%, rgba(173,227,255,1) 60%, rgba(240,171,255,1) 85%, rgba(255,173,173,1) 100%); ">
	<div class="row d-flex justify-content-center align-items-center my-4 border-top border-secondary" style="height: 50vh;">
		<img class="mt-1" src="<%=request.getContextPath()%>/media/arkinue.png" alt="Arkinue" style="height:8vh; width:39vh;">
		<p class="text-center" style="font-style: italic; font-family: Comic Sans MS, cursive, sans-serif; font-size: 2rem;">lo shopping alla fine dell'arcobaleno...</p>
		<img class="border border-info mb-1 p-0" src="<%=request.getContextPath()%>/media/bugia.png" alt="Arkinue" style="height:25vh; width:120vh;">
	</div>
</div>
<jsp:include page="/jsp/footer.jsp"></jsp:include>
<jsp:include page="/jsp/script.jsp"></jsp:include>
</body>
</html>