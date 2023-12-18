<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Errore</title>
<jsp:include page="/jsp/stile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/jsp/header.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>

<div class="container" >
	<div class="row d-flex align-items-center justify-content-md-center" style="min-height: 50vh">
		<% String msg = (String) request.getAttribute(Costanti.ERRORE_MSG); 
		if (msg==null){
			msg = "Errore non identificato.";
		} %>
		<p class="text-center font-weight-bold h4 pb-2 mb-4 text-danger border-bottom border-danger" style="font-size: 3em;"><%=msg%></p>	
	</div>
</div>

<jsp:include page="/jsp/footer.jsp"></jsp:include>
<jsp:include page="/jsp/script.jsp"></jsp:include>
</body>
</html>