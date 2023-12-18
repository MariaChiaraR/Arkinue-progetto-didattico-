<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.arcobaleno.arkinue.model.Articolo"%>
<%@page import="com.arcobaleno.arkinue.model.Utente"%>
<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrello</title>
<jsp:include page="/jsp/stile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/jsp/header.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>
<div class="container my-4 p-4 border border-light rounded-3">
	<% List<Articolo> carrello = (List<Articolo>) request.getAttribute(Costanti.LISTA_ARTICOLI);
		Map<Integer,String> mappaImmagini = (Map<Integer,String>) request.getAttribute(Costanti.MAPPAIMMAGINI);
	if(carrello.size()!= 0) {
		for (Articolo art : carrello) { %>
	<div class="row border border-secondary rounded my-2" style="max-height: 50rem">
		<div class="row">
			<div class="col-3">
				<img class="mt-2 imgarticolo" src="<%=mappaImmagini.get(art.getId())%>" style="max-width: 100%; max-height: 15rem">
			</div>
			<div class="col-3">
				<p class="etichetta mt-2">Nome:</p>
		    	<p class="campi"> <%= art.getNome() %></p>
				<p class="etichetta mt-2">Marca:</p>
		    	<p class="campi"> <%= art.getMarca() %><sup>&#169;</sup></p>
				<p class="etichetta mt-2">Categoria:</p>
		    	<p class="campi"> <%= art.getNome() %></p>				
			</div>
			<div class="col-3">
				<p class="etichetta mt-2">Colore:</p>
		    	<p class="campi"> <%= art.getColore() %></p>
				<p class="etichetta mt-2">Dimensioni:</p>
		    	<p class="campi"> <%=art.getDimensione() %></p>
				<p class="etichetta mt-2">Peso:</p>
		    	<p class="campi"> <%= art.getPeso() %></p>				
			</div>
			<div class="col-3 pe-0">
				<p class="etichetta mt-2">Quantit&#225; disponibile:</p>
		    	<p class="campi"> <%= art.getQuantita() %> </p>			
				<p class="etichetta mt-2">Prezzo:</p>
		    	<p class="campi"> <%= art.getPrezzo() %> &euro; </p>	
		    </div>
		</div> <!-- row dati -->
		<div class="row d-flex my-2 justify-content-evenly">
			<a type="button" class="btn btn-info btn-lg ms-3" href="<%=request.getContextPath() + "/visualizzaArticolo?id=" + art.getId() %>" style="width: fit-content;">Visualizza Articolo</a>	
			<% if (art.getQuantita()>0) { %>
				<form action="<%=request.getContextPath() + "/ordine"%>" method="post" style="width: fit-content;">
					<input type="hidden" value="<%=art.getId()%>" name="<%=Costanti.ARTICOLO_NELCARRELLO%>"> 
					<input type="submit" class="btn btn-success btn-lg ms-3 px-3" role="button" value="Procedi all'Acquisto">
				</form>
			<% } else { %>
				<p class="fs-3 fw-bold p-1 text-danger border border-danger border-2 rounded" style="width: fit-content;">Attualmente non disponibile.</p> 
			<% } %> 
			<form action="<%=request.getContextPath() + "/rimuoviCarrello"%>" method="post" style="width: fit-content;">
				<input type="hidden" value="<%=art.getId()%>" name="<%=Costanti.ARTICOLO_NELCARRELLO%>"> 
				<input type="submit" class="btn btn-warning btn-lg ms-3" role="button" value="Rimuovi dal Carrello">
			</form>
		</div> <!-- row bottoni -->
	</div>
	<% } //for
	} else { %>
	<div class="row mt-2 p-2 d-flex justify-content-center align-content-center">
		<p class="fs-3 fw-bold p-1 px-3 my-4 text-info border border-info border-2 rounded" style="width: fit-content;">Nessun articolo nel carrello.</p> 
	</div>
<% }
	
	int totPagine =(Integer) request.getAttribute(Costanti.TOTALE_PAGINE);
	%>  <div class="col d-flex justify-content-center mt-3 pt-2 pb-1 border-top">
			<span class="border rounded-end bg-white px-2 pb-1"> Pagina:</span> 
		</div> 
		<div class="col d-flex justify-content-center">
	<% 
		for (int i= 0; i<=totPagine; i++) {
	%>  <form action="<%= request.getContextPath() + "/visualizzaCarrello" %>" method="post">
			<input type="hidden" name="<%= Costanti.TOTALE_PAGINE %>" value="<%= request.getAttribute(Costanti.TOTALE_PAGINE) %>">
			<input type="hidden" name="<%= Costanti.PAGINA_RICERCA %>" value="<%= i %>">
			<input type="submit" value="<%= i+1 %>">
		</form> 
	<%  } %> 
		</div> 
</div> <!-- container -->
<jsp:include page="/jsp/footer.jsp"></jsp:include>
<jsp:include page="/jsp/script.jsp"></jsp:include>
</body>
</html>