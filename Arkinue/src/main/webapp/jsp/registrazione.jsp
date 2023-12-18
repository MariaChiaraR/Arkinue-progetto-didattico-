<%@page import="com.arcobaleno.arkinue.model.Utente"%>
<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrazione</title>
<jsp:include page="/jsp/stile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/jsp/header.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>
	<%	Utente utente = (Utente) request.getSession().getAttribute(Costanti.UTENTE_IN_SESSIONE); %>
<div class="container">
<% String msg = (String) request.getAttribute(Costanti.ERRORE_MSG); 
			if (msg!=null){
				%> <div class="row align-items-end justify-content-center mb-0" ><p class="fs-3 fw-bold p-1 mb-0 text-danger border border-danger border-2 rounded" style="width: fit-content;"><%=msg%></p></div> 
			<% } %>
<% if(utente == null) {%>	
	<form class="my-4 p-4 border border-info rounded" action="<%=request.getContextPath()+"/registrazione"%>" method="post">
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Username</span>
		  <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" id="username" name="<%=Costanti.USERNAME%>" placeholder="il tuo username unico" required>
		</div>
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Nome</span>
		  <input type="text" id="nome" name="<%=Costanti.NOME%>" placeholder="nome di battesimo" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" required>
		</div>		
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Cognome</span>
			<input type="text" id="cognome" name="<%=Costanti.COGNOME%>" placeholder="nome di famiglia" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" required>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Indirizzo</span>
			<input type="text" id="indirizzo" name="<%=Costanti.INDIRIZZO%>" placeholder="Milano (MI), via Roma, 1" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" required>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Data di Nascita</span>
			<input class="form-control" id="data" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" type="date" name="<%=Costanti.DATANASCITA %>" required>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Indirizzo eMail</span>
			<input class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" type="text" id="email" placeholder="esempio@esempio.it" name="<%=Costanti.EMAIL %>" required>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Password</span>
			<input class="form-control" id="password" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" type="password" name="<%=Costanti.PASSWORD %>" required>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Verifica Password</span>
			<input class="form-control" id="password2" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" type="password" name="<%=Costanti.PASSWORD_VERIFICA %>" required>
		</div>
		<div class="d-flex justify-content-center mt-4">			
			<input class="btn btn-primary mb-2 btn-lg" type="submit" value="Registrati">
		</div>
	</form>
<% } else { %>
	<div class="m-4 d-flex justify-content-center align-items-center" style="min-height: 20rem">
		<h3 class="fs-3 fw-bold p-4 text-danger border border-danger border-2 rounded" style="width: fit-content;">Non puoi registrarti se sei già loggato.</h3>
	</div>
<% } %>
</div>
<jsp:include page="/jsp/footer.jsp"></jsp:include>
<jsp:include page="/jsp/script.jsp"></jsp:include>
<script src="<%=request.getContextPath()+"/js/controlloRegistrazione.js"%>"></script>
</body>
</html>