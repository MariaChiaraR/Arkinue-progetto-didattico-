<%@page import="com.arcobaleno.arkinue.model.Articolo"%>
<%@page import="java.util.List"%>
<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ricerca Ordini</title>
<jsp:include page="/jsp/stile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/jsp/header.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>
<div class="container my-4 p-4 border border-light rounded-3">
	<div class="row px-4">
	<div class="col d-flex justify-content-center flex-column">
		<div class="row">
			<h3>Ricerca Ordini</h3>
		</div>	
	<form action="<%=request.getContextPath()+"/ricercaOrdini" %>" method="get" name="searchform">	
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Nome Articolo</span>
		  <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" id="nome_ric" name="<%=Costanti.NOME%>" placeholder="es. Manubrio sport 15">
		</div>
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Username Utente</span>
		  <input type="text" id="user_ric" name="<%=Costanti.USERNAME%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>		
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Categoria</span>
			<select class="form-select" aria-label="Default select example" id="sceltaCat" name="<%=Costanti.CATEGORIA%>">
				<option value="" selected>Nessuna Categoria</option>
			    <option value="<%=Costanti.CAT_ELETTRODOMESTICI%>">Elettrodomestici</option>
			    <option value="<%=Costanti.CAT_FAIDATE%>">Fai da te</option>
			    <option value="<%=Costanti.CAT_SPORTETEMPOLIBERO%>">Sport e tempo libero</option>
			    <option value="<%=Costanti.CAT_PERLACASA%>">Per la casa</option>
			    <option value="<%=Costanti.CAT_MODAEBELLEZZA%>">Moda e bellezza</option>
			    <option value="<%=Costanti.CAT_ALIMENTARIEBEVANDE%>">Alimentari e bevande</option>
			    <option value="<%=Costanti.CAT_DISPOSITIVIEDELETTRONICA%>">Dispositivi ed elettronica</option>
			</select>
		</div>
		<div class="input-group">
			<span class="input-group-text" id="inputGroup-sizing-default">Ordine cronologico</span>
			<div class="border rounded-end bg-white px-3 py-2">
				<input type="radio" id="recente" value="0" name="<%=Costanti.ORDINE%>" checked>
				<label for="recente">dal più recente</label>
				<div class="border-top mt-2 mb-1"></div>
				<input type="radio" id="vecchio" value="1" name="<%=Costanti.ORDINE%>">
				<label for="vecchio">dal più vecchio</label>
			</div>
		</div>
		<div class="d-flex justify-content-center mt-4">
			<input class="btn btn-warning mb-2 btn-lg" type="submit" value="Ricerca Ordini">
		</div>
	</form>
	</div> <!-- col adattiva -->
	</div>	<!-- row statica --> 
</div> <!-- container --> 

<jsp:include page="/jsp/footer.jsp"></jsp:include>
<jsp:include page="/jsp/script.jsp"></jsp:include>
</body>
</html>