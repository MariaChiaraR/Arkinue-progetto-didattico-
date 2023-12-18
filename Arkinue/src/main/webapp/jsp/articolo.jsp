<%@page import="com.arcobaleno.arkinue.model.Utente"%>
<%@page import="com.arcobaleno.arkinue.model.Articolo"%>
<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-15"%>
    <% Articolo articolo = (Articolo)request.getAttribute(Costanti.ARTICOLO); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Articolo </title>
<jsp:include page="/jsp/stile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/jsp/header.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>
<div class="container border mt-2 border-secondary secondary rounded" style="background: linear-gradient(90deg, rgba(255,171,111,0.8) 0%, rgba(255,235,156,0.9) 100%); ">
	<div class="row" style="min-height: 25rem">
		<div class="col-6 d-flex flex-column">
			<div class="d-flex justify-content-center">
				<img class="mt-2 imgarticolo" src="<%=(String) request.getAttribute(Costanti.SINGOLAIMMAGINE)%>">
			</div>
			<p class="etichetta mt-2">Descrizione:</p>
		    <p class="campi"> <%=articolo.getDescrizione()%></p>
		<% if(articolo.getQuantita()<=0) {  %> 
			<p class="fs-3 fw-bold p-1 text-danger border border-danger border-2 rounded" style="width: fit-content;">Attualmente non disponibile.</p> 
		<% } else { %>  
			<form class="d-flex justify-content-center" action="<%=request.getContextPath()+"/carrello" %>" method="post">
				<input type="hidden" value="<%= articolo.getId() %>" name="<%=Costanti.ID %>">
				<button class="btn btn-success mb-2 btn-lg" type="submit" value="Acquista"> Aggiungi al Carrello </button>
			</form>
		<% } %>
		<% Utente utente = (Utente) request.getSession().getAttribute(Costanti.UTENTE_IN_SESSIONE); 
		   if(utente!= null && utente.getRuolo().getNome().equals(Costanti.RUOLO_ADMIN)) { %>
			<form action="<%= request.getContextPath() + "/iniziaModifica" %>" method="post">
				<input type="hidden" name="<%= Costanti.ID %>" value="<%= articolo.getId() %>">
				<input class="btn btn-warning mb-2 btn-lg" type="submit" value="Modifica Articolo">
			</form>
		<% } %>
		</div>
		
		<div class="col-6" id="txt">
			<p class="etichetta mt-2">Nome:</p>
			<p class="campi"> <%=articolo.getNome()%> </p>
		    <p class="etichetta">Marca:</p>
		    <p class="campi"> <%=articolo.getMarca()%><sup>&#169;</sup></p>
		    <p class="etichetta">Colore:</p>
		    <p class="campi"> <%=articolo.getColore()%></p>		    
		    <p class="etichetta">Dimensione:</p>
		    <p class="campi"> <%=articolo.getDimensione()%></p>
		    <p class="etichetta">Peso:</p>
		    <p class="campi"> <%=articolo.getPeso()%></p>
		    <p class="etichetta">Quantità disponibile:</p>
			<p class="campi"> <%=articolo.getQuantita()%></p>
		    <p class="etichetta">Prezzo:</p>
		    <p class="campi"> <%=articolo.getPrezzo()%> &euro;</p>
		<% if (utente!= null && utente.getRuolo().getNome().equals(Costanti.RUOLO_ADMIN)) { %>
		    <p class="etichetta">Totale acquistati:</p>
		    <p class="campi"> <%=(Integer)request.getAttribute(Costanti.CARRELLO_COMPRATO) %></p>
		<% } %>
		</div>
	</div>	
</div>
<jsp:include page="/jsp/footer.jsp"></jsp:include>
<jsp:include page="/jsp/script.jsp"></jsp:include>
</body>
</html>