<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<%@page import="com.arcobaleno.arkinue.model.Utente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Richiedi Supporto</title>
<jsp:include page="/jsp/stile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/jsp/header.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>
<% Utente utente = (Utente) request.getSession().getAttribute(Costanti.UTENTE_IN_SESSIONE);%>
<div class="container my-4 p-4 border border-light rounded-3">
	<div class="row ms-2">
		<p>Piacere, <%=utente.getNome() %></p>
		<p>Ci dispiace che stai avendo problemi con un tuo ordine.</p>
		<p>Inviaci una breve descrizione del problema e ci impegneremo ad aiutarti.</p>
	</div>
	<form action="<%=request.getContextPath()+"/creaArticolo"%>" method="post" enctype="multipart/form-data">
		<div class="input-group my-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Oggetto</span>
			<input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" id="nome" name="<%=Costanti.NOME%>"> 
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Data Ordine</span>
			<input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" id="nome" name="<%=Costanti.COGNOME%>"> 
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Descrizione del problema</span>
			<textarea class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" id="nome" name="<%=Costanti.NOME%>"></textarea> 
		</div>
		
		<div class="d-flex justify-content-center mt-2 mb-2">
			<input class="btn btn-warning btn-lg" type="submit" value="Invia Messaggio">
		</div>
	</form>
</div>
<jsp:include page="/jsp/footer.jsp"></jsp:include>
<jsp:include page="/jsp/script.jsp"></jsp:include>
</body>
</html>