<%@page import="com.arcobaleno.arkinue.model.Articolo"%>
<%@page import="com.arcobaleno.arkinue.model.Carrello"%>
<%@page import="com.arcobaleno.arkinue.model.Utente"%>
<%@page import="java.util.List"%>
<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pagina Utente</title>
<jsp:include page="/jsp/stile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/jsp/header.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>
<div class="container border mt-2 border-info secondary rounded">

	<% Utente utente = (Utente) request.getAttribute(Costanti.VISUALIZZA_UTENTE);
	if(utente!= null) { %>

		<div class="p-3">
			 <span class="etichetta">Username:</span><p class="campi"><%=utente.getUsername()%></p>
			 <span class="etichetta">Nome:</span><p class="campi"><%=utente.getNome()%></p>
			 <span class="etichetta">Cognome:</span><p class="campi"> <%=utente.getCognome()%> </p>
			 <span class="etichetta">eMail:</span><p class="campi"><%=utente.getEmail()%></p>
			 <span class="etichetta">Indirizzo:</span><p class="campi"><%=utente.getIndirizzo()%></p>
			 <span class="etichetta">Data Nascita:</span><p class="campi"><%= utente.getDataNascita().toString()%></p>
			 <span class="etichetta">Totale Spese:</span><p class="campi"><%= (Float) request.getAttribute(Costanti.CARRELLO_COMPRATO) %> &euro;</p>
			 <% List<Articolo> car = utente.getCarrello().getArticoli() ; %>
			 <%! String elenco ="";  %>  
			 <% car.forEach(x -> elenco+=x.getNome()+"; " ); %>
			 <span class="etichetta">Oggetti in Carrello:</span>
			 <p class="campi"><%=elenco=="" ? "vuoto" : elenco%></p>
			 
			 <form action="<%= request.getContextPath()+"/ricercaOrdini" %>" method="post" class="d-flex justify-content-center">
				<input type="hidden" value="<%=utente.getUsername()%>" name="<%=Costanti.USERNAME%>">
				<input class="btn btn-info m-2" type="submit" Value="Visualizza Ordini dell'Utente" style="width: fit-content;">
			</form>
		</div> <!-- dati utente -->
	<% } else { %>
		<div class="row">
			<p class="btn btn-outline-danger m-2">L'utente non esiste o non è più iscritto.</p>
		</div>
	<% } %>
</div> <!--  container -->
<jsp:include page="/jsp/footer.jsp"></jsp:include>
<jsp:include page="/jsp/script.jsp"></jsp:include>
</body>
</html>