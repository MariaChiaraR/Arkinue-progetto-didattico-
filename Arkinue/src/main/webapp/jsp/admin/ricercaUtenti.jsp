<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ricerca Utenti</title>
<jsp:include page="/jsp/stile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/jsp/header.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>
<div class="container my-4 p-4 border border-light rounded-3">
	<form action="<%=request.getContextPath()+"/ricercaUtenti"%>" method="post">
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Username</span>
		  <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" id="username" name="<%=Costanti.USERNAME%>" placeholder="Inserisci l'Username da ricercare:">
		</div>
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Nome</span>
		  <input type="text" id="nome" name="<%=Costanti.NOME%>" placeholder="es. Mario" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>		
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Cognome</span>
			<input type="text" id="cognome" name="<%=Costanti.COGNOME%>" placeholder="es. Rossi" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Indirizzo</span>
			<input type="text" id="indirizzo" name="<%=Costanti.INDIRIZZO%>" placeholder="Milano (MI), via Roma, 1" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Indirizzo eMail</span>
			<input class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" type="text" id="email" placeholder="esempio@esempio.it">
		</div>
		<div class="input-group">
			<span class="input-group-text" id="inputGroup-sizing-default">Ordine:</span>
			<div class="border rounded-end bg-white px-3 py-2">
				<input type="radio" id="nessunaSelezione" value="0" name="<%=Costanti.ORDINE%>" checked>
				<label for="nessunaSelezione">nessun ordine</label>
			</div>
			<div class="border rounded-end bg-white px-3 py-2">
				<input type="radio" id="ordinaUsernameCre" name="<%=Costanti.ORDINE%>" value="1">
	   			<label for="ordinaUsernameCre">ordina per Username in ordine ascendente</label> 
				<div class="border-top mt-2 mb-1"></div>
				<input type="radio" id="ordinaUsername" name="<%=Costanti.ORDINE %>" value="2">
			    <label for="ordinaUsername">ordina per Username in ordine discendente</label>
			    <div class="border-top border-dark mt-2 mb-1"></div>
			    <input type="radio" id="ordinaEmailCre" name="<%=Costanti.ORDINE %>" value="3">
			    <label for="ordinaEmailCre">ordina per Email in ordine ascendente</label> 
			    <div class="border-top mt-2 mb-1"></div>
			    <input type="radio" id="ordinaEmailDec" name="<%=Costanti.ORDINE %>" value="4">
			    <label for="ordinaEmailDec">ordina per Email in ordine discendente</label>
			</div>
			<div class="border rounded-end bg-white px-3 py-2">
				<input type="radio" id="ordinaNomeCre" name="<%=Costanti.ORDINE %>" value="5">
			    <label for="ordinaNomeCre">Ordina per Nome in ordine ascendente</label> 
			    <div class="border-top mt-2 mb-1"></div>
			    <input type="radio" id="ordinaNomeDec" name="<%=Costanti.ORDINE %>" value="6">
			    <label for="ordinaNomeDec">Ordina per Nome in ordine discendente</label>
			    <div class="border-top border-dark mt-2 mb-1"></div>
			    <input type="radio" id="ordinaCognomeCre" name="<%=Costanti.ORDINE %>" value="7">
			    <label for="ordinaCognomeCre">Ordina per Cognome in ordine ascendente</label> 
			    <div class="border-top mt-2 mb-1"></div>
			    <input type="radio" id="ordinaCognomeDec" name="<%=Costanti.ORDINE %>" value="8">
			    <label for="ordinaCognomeDec">Ordina per Cognome in ordine discendente</label>
			</div>
			
		</div>
		
		<div class="d-flex justify-content-center mt-4">			
			<input class="btn btn-info mb-2 btn-lg pt-1" type="submit" value="Cerca Utenti">
		</div>
	</form>
<!-- old -->

</div> <!-- container -->
<jsp:include page="/jsp/footer.jsp"></jsp:include>
<jsp:include page="/jsp/script.jsp"></jsp:include>
</body>
</html>