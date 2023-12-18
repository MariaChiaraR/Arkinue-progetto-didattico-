<%@page import="java.util.Map"%>
<%@page import="com.arcobaleno.arkinue.model.Utente"%>
<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<%@page import="com.arcobaleno.arkinue.model.Articolo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Risultati Ricerca</title>
<jsp:include page="/jsp/stile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/jsp/header.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>
<div class="container my-4 p-4 border border-light rounded-3">
<%  List<Articolo> risultati = (List<Articolo>) request.getAttribute(Costanti.RISULTATI);
	Utente utente = (Utente) session.getAttribute(Costanti.UTENTE_IN_SESSIONE);
	Map<Integer,String> mappaImmagini = (Map<Integer,String>) request.getAttribute(Costanti.MAPPAIMMAGINI);
if (risultati.size()==0) { %>
	<div class="row border border-secondary rounded mt-2">
		<p class="fs-3 fw-bold p-1 text-danger border border-danger border-2 rounded" style="width: fit-content;">Nessun articolo corrisponde ai criteri di ricerca.</p> 
	</div>
<% } else {	
	for (Articolo art : risultati) { %>
		<div class="row border border-secondary rounded mb-2" style="max-height: 50rem">
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
			    	<a type="button" class="btn btn-info btn-lg ms-3" href="<%=request.getContextPath() + "/visualizzaArticolo?id=" + art.getId() %>">Vai alla pagina dell'articolo</a>	
				</div>
			</div>
			<div class="row d-flex flex-row me-4 my-0 justify-content-center">
			<% if (art.getQuantita()>0) { %>
				<form action="<%= request.getContextPath() + "/carrello" %>" target="_blank" method="post" style="width: fit-content;">
					<input type="hidden" name="<%= Costanti.ID %>" value="<%= art.getId() %>">
					<input class="btn btn-success btn-lg mb-1 mx-4" type="submit" value="Aggiungi al Carrello">
				</form>
			<% } else { %>
				<p class="fs-3 fw-bold p-1 text-danger border border-danger border-2 rounded" style="width: fit-content;">Attualmente non disponibile.</p> 
			<%  }
				if(utente!= null && utente.getRuolo().getNome().equals(Costanti.RUOLO_ADMIN)) { %>
				<form action="<%= request.getContextPath() + "/iniziaModifica" %>" method="post" style="width: fit-content;">
					<input type="hidden" name="<%= Costanti.ID %>" value="<%= art.getId() %>">
					<input class="btn btn-info btn-lg mb-1 mx-4" type="submit" value="Modifica Articolo">
				</form>
				<% } %>
			</div>
		</div>	
	<% } //chiudo il for Articoli
	
int totPagine = (Integer) request.getAttribute(Costanti.TOTALE_PAGINE);
	%>
	<div class="col d-flex justify-content-center mt-3 pt-2 pb-1 border-top">
		<span class="border rounded-end bg-white px-2 pb-1"> Pagina:</span> 
	</div>
	<div class="col d-flex justify-content-center">
	<%  for (int i= 0; i<=totPagine; i++) { %>
		<form action="<%= request.getContextPath() + "/ricercaAvanzata" %>" method="post">
			<input type="hidden" name="<%= Costanti.NAME_RICERCATO %>" value="<%= request.getAttribute(Costanti.NAME_RICERCATO) %>">
			<input type="hidden" name="<%= Costanti.MARCA_RICERCATA %>" value="<%= request.getAttribute(Costanti.MARCA_RICERCATA) %>">
			<input type="hidden" name="<%= Costanti.QUANTITA_RICERCATA %>" value="<%= request.getAttribute(Costanti.QUANTITA_RICERCATA) %>">
			<input type="hidden" name="<%= Costanti.COLORE_RICERCATO %>" value="<%= request.getAttribute(Costanti.COLORE_RICERCATO) %>">
			<input type="hidden" name="<%= Costanti.CATEGORIA %>" value="<%= request.getAttribute(Costanti.CATEGORIA) %>">
			<input type="hidden" name="<%= Costanti.DIMENSIONE_RICERCATA %>" value="<%= request.getAttribute(Costanti.DIMENSIONE_RICERCATA) %>">
			<input type="hidden" name="<%= Costanti.PESO_RICERCATO %>" value="<%= request.getAttribute(Costanti.PESO_RICERCATO) %>">
			<input type="hidden" name="<%= Costanti.PREZZO_RICERCATO %>" value="<%= request.getAttribute(Costanti.PREZZO_RICERCATO) %>">
			<input type="hidden" name="<%= Costanti.TOTALE_PAGINE %>" value="<%= request.getAttribute(Costanti.TOTALE_PAGINE) %>">
			<input type="hidden" name="<%= Costanti.PAGINA_RICERCA %>" value="<%= i %>">
			<input type="submit" value="<%= i+1 %>">
		</form>
	<% } %> <!--  chiudo for -->
	</div> <!-- chiudo div elenco pagine --> <% 
} %> <!-- chiudo if -->
</div> <!-- container -->
<jsp:include page="/jsp/footer.jsp"></jsp:include>
<jsp:include page="/jsp/script.jsp"></jsp:include>
</body>
</html>