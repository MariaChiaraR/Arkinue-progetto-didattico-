<%@page import="java.util.Map"%>
<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<%@page import="com.arcobaleno.arkinue.model.Ordine"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cronologia Ordini</title>
<jsp:include page="/jsp/stile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/jsp/header.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>
<div class="container my-4 p-4 border border-light rounded-3">

	<% List<Ordine> risultati = (List<Ordine>) request.getAttribute(Costanti.LISTA_ORDINI);
		Map<Integer,String> mappaImmagini = (Map<Integer,String>) request.getAttribute(Costanti.MAPPAIMMAGINI);
	if(risultati.size()>0){
		for ( Ordine ord: risultati) { %>
	<div class="row border border-secondary rounded mt-2" style="background: linear-gradient(48deg, rgba(222,240,204,1) 0%, rgba(241,233,200,1) 100%);">
		<div class="row d-flex flex-row justify-content-between">
			<div><p class="m-1" >Data: <%= ord.getData() %></p></div>
		</div>
		<div class="row" style="max-height: 50rem">
			<div class="col-3">
				<img class="m-2 imgarticolo" src="<%=mappaImmagini.get(ord.getIdArticolo())%>" style="max-width: 100%; max-height: 15rem">
			</div>
			<div class="col-3">
				<p class="etichetta mt-2">Nome:</p>
		    	<p class="campi"> <%= ord.getNomeArticolo() %></p>
				<p class="etichetta mt-2">Marca:</p>
		    	<p class="campi"> <%= ord.getMarcaArticolo() %><sup>&#169;</sup></p>
				<p class="etichetta mt-2">Categoria:</p>
		    	<p class="campi"> <%= ord.getNomeCategoria() %></p>				
			</div>
			<div class="col-3">
				<p class="etichetta mt-2">Colore:</p>
		    	<p class="campi"> <%= ord.getColoreArticolo() %></p>
				<p class="etichetta mt-2">Dimensioni:</p>
		    	<p class="campi"> <%=ord.getDimensioneArticolo() %></p>
				<p class="etichetta mt-2">Peso:</p>
		    	<p class="campi"> <%= ord.getPesoArticolo() %></p>				
			</div>
			<div class="col-3 pe-0">
				<p class="etichetta mt-2">Quantit&#225;:</p>
		    	<p class="campi"> 1 </p>			
				<p class="etichetta mt-2">Prezzo:</p>
		    	<p class="campi"> <%= ord.getPrezzoArticolo() %>&euro; </p>	
		    	<a type="button" class="btn btn-info btn-lg ms-3" href="<%=request.getContextPath() + "/visualizzaArticolo?id=" + ord.getIdArticolo() %>">Vai alla pagina dell'articolo</a>	
			</div>
		</div>		
	</div>		
		
<% } //for
	} //if ordini non vuota
	else{
	%> <div class="row border d-flex justify-content-center" >
				<p class="m-2 btn btn-outline-dark" style="font-size: 1.6rem; width: fit-content">Nessun ordine disponibile. Acquista qualcosa e la vedrai qui.</p>
		</div> <%
	}
		int totPagine = (Integer) request.getAttribute(Costanti.TOTALE_PAGINE); %>
		<div class="col d-flex justify-content-center mt-3 pt-2 pb-1 border-top">
			<span class="border rounded-end bg-white px-2 pb-1"> Pagina:</span> 
		</div>
		<div class="col d-flex justify-content-center">
		<% for (int i= 0; i<=totPagine; i++) {	%>
			<form action="<%= request.getContextPath() + "/ordiniUtente" %>" method="post">
				<input type="hidden" name="<%= Costanti.TOTALE_PAGINE %>" value="<%= request.getAttribute(Costanti.TOTALE_PAGINE) %>">
				<input type="hidden" name="<%= Costanti.PAGINA_RICERCA %>" value="<%= i %>">
				<input type="submit" value="<%= i+1 %>">
			</form>
		<% } %>
		</div> <!-- elenco pagine -->
</div> <!-- container -->
<jsp:include page="/jsp/footer.jsp"></jsp:include>
<jsp:include page="/jsp/script.jsp"></jsp:include>
</body>
</html>