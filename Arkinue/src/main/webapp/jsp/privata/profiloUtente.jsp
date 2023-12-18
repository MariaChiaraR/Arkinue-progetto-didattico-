<%@page import="java.util.List"%>
<%@page import="com.arcobaleno.arkinue.model.Ordine"%>
<%@page import="com.arcobaleno.arkinue.model.Utente"%>
<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profilo</title>
<jsp:include page="/jsp/stile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/jsp/header.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>
	<%!Utente utente = null; %>
<div class="container">
	<div class="row">
		<div class="col-1"></div>
		<div class="col-9 d-flex flex-column contenitoreProfilo">
		<%utente = (Utente) request.getSession().getAttribute(Costanti.UTENTE_IN_SESSIONE);
		  List<Ordine> lista = (List<Ordine>) request.getAttribute(Costanti.LISTA_ORDINI);
		%> 
			<div class="d-flex flex-row align-items-center">
				<p class="etichetta pb-2">nome:</p><p class="campi"><%=utente.getNome()%></p>
			</div>
			<div class="d-flex flex-row align-items-center">
				<p class="etichetta pb-2">cognome:</p><p class="campi"><%=utente.getCognome()%></p>
			</div>
			<div class="d-flex flex-row align-items-center">
				<p class="etichetta pb-2">username:</p><p class="campi"><%=utente.getUsername()%></p>
			</div>
			<div class="d-flex flex-row align-items-center">
				<p class="etichetta pb-2">email:</p><p class="campi"><%=utente.getEmail()%></p>
			</div>
			<div class="d-flex flex-row align-items-center">
				<p class="etichetta pb-2">indirizzo:</p><p class="campi"><%=utente.getIndirizzo()%></p>
			</div>	
			<div class="d-flex flex-row align-items-center">
				<p class="etichetta pb-2">nascita:</p><p class="campi"><%=utente.getDataNascita()%></p>
			</div>	
		</div>
		<div class="col-2 d-flex flex-column align-items-center justify-content-center" >
		<% if (utente.getRuolo().getNome().equals(Costanti.RUOLO_ADMIN)) {	%>
			<a class="btn btn-danger m-2 pulsanteAdmin"  role="button" href="<%=request.getContextPath() + "/jsp/admin/creazioneArticolo.jsp"%>"> Crea Articolo</a> <br>
			<a class="btn btn-danger m-2 pulsanteAdmin"  role="button" href="<%=request.getContextPath() + "/jsp/admin/ricercaUtenti.jsp"%>"> Ricerca Utenti</a> <br>
			<a class="btn btn-danger m-2 pulsanteAdmin"  role="button" href="<%=request.getContextPath() + "/jsp/admin/ricercaOrdini.jsp"%>"> Ricerca Ordini</a> <br>
		<% } %>
		</div>
	</div> <!-- row utente e admin -->
	
	<div class="row mt-2">
		<div class="col-1"></div>
		<div class="col-10 pt-2"><p class="btn btn-outline-primary mb-1">Ordini recenti</p></div>
		<div class="col-1"></div>
	</div>	
	<div class="row d-flex flex-wrap justify-content-center">
		<%  if(lista.size()>0) {
				for(int i = 0; i< lista.size(); i++) {	%>
			<div class="col-5 scheda-ordine">
				<div class="row bg-primary text-white" style="border-radius: 1rem 1rem 0 0;">
					<p class="m-2">Ordine</p>
				</div>
				<div class="row border d-flex justify-content-between" >
					<p class="m-2" style="width: fit-content">Nome Articolo:</p>
					<p class="m-2" style="width: fit-content"><%=lista.get(i).getNomeArticolo()%></p>
				</div>				
				<div class="row border d-flex justify-content-between" >
					<p class="m-2" style="width: fit-content">Marca:</p>
					<p class="m-2" style="width: fit-content"><%=lista.get(i).getMarcaArticolo()%></p>					
				</div>
				<div class="row border d-flex justify-content-between" >
					<p class="m-2" style="width: fit-content">Colore:</p>
					<p class="m-2" style="width: fit-content"><%=lista.get(i).getColoreArticolo() %></p>
				</div>
				<div class="row border d-flex justify-content-between" >
					<p class="m-2" style="width: fit-content">Prezzo Articolo:</p>
					<p class="m-2" style="width: fit-content"><%=lista.get(i).getPrezzoArticolo()%> &euro;</p>			
				</div><div class="row border d-flex justify-content-between" style="border-radius: 0 0 1rem 1rem;">
					<p class="m-2" style="width: fit-content">Data Acquisto:</p>
					<p class="m-2" style="width: fit-content"><%=lista.get(i).getData()%></p>
				</div>	
			</div>
			<%
				if(i==3){break;}}}
				else {	%>
				<div>
					<div class="row border d-flex justify-content-between" >
							<p class="m-2 btn btn-outline-dark" style="width: fit-content">Nessun ordine disponibile. Acquista qualcosa e la vedrai qui.</p>
					</div>
				</div>
				<% } %>
	</div> <!-- row ordini -->
</div> <!-- container -->
<jsp:include page="/jsp/footer.jsp"></jsp:include>
<jsp:include page="/jsp/script.jsp"></jsp:include>
</body>
</html>