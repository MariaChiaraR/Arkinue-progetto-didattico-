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
<jsp:include page="/jsp/header.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>
<div class="cointainer" style="min-height: 50vh"> 
	<div class="row mt-4 align-items-center justify-content-md-center">
		<% String msg = (String) request.getAttribute(Costanti.ERRORE_MSG); 
			if (msg!=null){
				%> <div class="row align-items-end justify-content-center mb-0" ><p class="fs-3 fw-bold p-1 mb-0 text-danger border border-danger border-2 rounded" style="width: fit-content;"><%=msg%></p></div> 
			<% } %>
		<div class="col col-lg-4 d-flex flex-column align-items-center border border-white rounded p-4 mb-4">
			<form action="<%=request.getContextPath()+"/login"%>" method="post" style="width: 100%">
			 <label class="input-group-text" for="<%=Costanti.USERNAME%>">Username:</label>
			 <input class="p-2" type="text" name="<%=Costanti.USERNAME%>" required placeholder="Inserire Username" style="width: 100%">
			 <label class="input-group-text mt-4" for="<%=Costanti.PASSWORD%>">Password:</label>
			 <input class="p-2" type="password" name="<%=Costanti.PASSWORD%>" required placeholder="Password" style="width: 100%">
			 <input class="btn btn-primary mt-4" type="submit" value="Login" style="width: 100%">
			</form>
		</div>
		
	</div>
</div>
<jsp:include page="/jsp/footer.jsp"></jsp:include>
<jsp:include page="/jsp/script.jsp"></jsp:include>
</body>
</html>