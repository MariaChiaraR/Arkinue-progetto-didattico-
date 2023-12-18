<%@page import="com.arcobaleno.arkinue.model.Utente"%>
<%@page import="java.util.List"%>
<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Risultati ricerca utenti</title>
<jsp:include page="/jsp/stile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/jsp/header.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>
	<div class="container my-4 p-4 border border-light rounded-3">
	
	<% 
		List<Utente> lista = (List<Utente>) request.getAttribute(Costanti.RISULTATI);
	
	if(lista.size()==0)
	{
%>
	<div>
	<p> Nessun risultato corrispondente. </p>
</div>
	<% } else {
		
	for(Utente u: lista){ %>
<div class="row border border-secondary rounded mt-2" style="background: linear-gradient(48deg, rgba(222,240,204,1) 0%, rgba(241,233,200,1) 100%);">
		<div class="row d-flex flex-row justify-content-between">
				 <div class="d-flex"><h2 class="m-2 fw-bold"><%=u.getUsername()%></h2></div>
			</div>
	<div class="row" style="max-height: 50rem">
		<div class="col-3">
			<p class="etichetta mt-2">Nome:</p>
			<p class="campi"> <%=u.getNome()%></p>
		</div>
		<div class="col-3">
			<p class="etichetta mt-2">Cognome:</p>
			<p class="campi"> <%=u.getCognome()%></p>
		</div>
		<div class="col-3">
			<p class="etichetta mt-2">Cognome:</p>
			<p class="campi"> <%=u.getEmail()%></p>
		</div>
		<div class="col-3 d-flex justify-content-center">
				<form action="<%= request.getContextPath() + "/visualizzaUtente" %>" method="post">
					<input type="hidden" value="<%=u.getId()%>" name="<%= Costanti.ID %>">
					<button type="submit" value="Vai"> Vai al profilo </button>
				</form>
		</div>
	</div>
</div>
		<%} 
	
	int totPagine =(Integer) request.getAttribute(Costanti.TOTALE_PAGINE);
	
	%>
		<div class="col d-flex justify-content-center mt-3 pt-2 pb-1 border-top">
			<span class="border rounded-end bg-white px-2 pb-1"> Pagina:</span> 
		</div>
		<div class="col d-flex justify-content-center">
	<% 
		for (int i= 0; i<=totPagine; i++) {
	%>
		<form action="<%= request.getContextPath() + "/ricercaUtenti" %>" method="post">
			<input type="hidden" name="<%= Costanti.USERNAME%>" value="<%= request.getAttribute(Costanti.USERNAME) %>">
			<input type="hidden" name="<%= Costanti.EMAIL %>" value="<%= request.getAttribute(Costanti.EMAIL) %>">
			<input type="hidden" name="<%= Costanti.NOME %>" value="<%= request.getAttribute(Costanti.NOME) %>">
			<input type="hidden" name="<%= Costanti.COGNOME %>" value="<%= request.getAttribute(Costanti.COGNOME) %>">
			<input type="hidden" name="<%= Costanti.ORDINE %>" value="<%= request.getAttribute(Costanti.ORDINE) %>">
			<input type="hidden" name="<%= Costanti.TOTALE_PAGINE %>" value="<%= request.getAttribute(Costanti.TOTALE_PAGINE) %>">
			<input type="hidden" name="<%= Costanti.PAGINA_RICERCA %>" value="<%= i %>">
			<input type="submit" value="<%= i+1 %>">
		</form>
	<%
		}
	%>
	</div>
<%
		}
	%>
</div>
<jsp:include page="/jsp/footer.jsp"></jsp:include>
<jsp:include page="/jsp/script.jsp"></jsp:include>
</body>
</html>