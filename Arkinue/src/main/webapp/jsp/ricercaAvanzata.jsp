<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<jsp:include page="/jsp/stile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/jsp/header.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>
<div class="container my-4 p-4 border border-light rounded-3">
	<form class="px-4" action="<%=request.getContextPath()+"/ricercaAvanzata" %>" method="get" name="searchform">
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Nome</span>
			<input type="text" id="nome" name="<%=Costanti.NAME_RICERCATO%>" placeholder="es. Cicciobello" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>	
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Marca</span>
			<input type="text" id="nome" name="<%=Costanti.MARCA_RICERCATA%>" placeholder="es. Giochi Preziosi" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>
<!-- aggiunta di categorie e sottoricerche ad esse correlate? div che si scambiano in base alla categoria di prodotto selezionata? -->
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Scegli un colore</span>
		  <select class="form-select" aria-label="Default select example" id="sceltaColore" name="<%=Costanti.COLORE_RICERCATO%>">
				<option value="" >Non Selezionato</option>
		  		<option value="<%=Costanti.COL_ARANCIONE %>">Arancione</option>
		  		<option value="<%=Costanti.COL_BIANCO %>">Bianco</option>
		  		<option value="<%=Costanti.COL_BLU %>">Blu</option>
		  		<option value="<%=Costanti.COL_GIALLO %>">Giallo</option>
		  		<option value="<%=Costanti.COL_GRIGIO %>">Grigio</option>
		  		<option value="<%=Costanti.COL_MARRONE %>">Marrone</option>
		  		<option value="<%=Costanti.COL_NERO %>">Nero</option>
		  		<option value="<%=Costanti.COL_ROSSO %>">Rosso</option>
		  		<option value="<%=Costanti.COL_VERDE %>">Verde</option>
		  		<option value="<%=Costanti.COL_VIOLA %>">Viola</option>		
			</select>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Categoria</span>
			<select class="form-select" aria-label="Default select example" id="sceltaCategoria" name="<%=Costanti.CATEGORIA%>">
				<option value="0" selected>Non Selezionato</option>
			    <option value="<%=Costanti.ID_CAT_ALIMENTARIEBEVANDE%>">Alimentazione</option>
		  		<option value="<%=Costanti.ID_CAT_DISPOSITIVIEDELETTRONICA%>">Elettronica</option>
		  		<option value="<%=Costanti.ID_CAT_ELETTRODOMESTICI%>">Elettrodomestici</option>
		  		<option value="<%=Costanti.ID_CAT_FAIDATE%>">Fai-da-Te</option>
		  		<option value="<%=Costanti.ID_CAT_MODAEBELLEZZA%>">Moda&Bellezza</option>
		  		<option value="<%=Costanti.ID_CAT_PERLACASA%>">Casa&Casalinghi</option>
		  		<option value="<%=Costanti.ID_CAT_SPORTETEMPOLIBERO%>">Sport&Divertimento</option>	
			</select>
		</div>
	<!-- il radio dimensioni maggiori/minori si attiva solo se selezionata una dimensione? -->
		<div class="input-group mb-0">
			<span class="input-group-text" id="inputGroup-sizing-default">Dimensione</span>
			<input class="form-control" type="number" name="<%=Costanti.DIMENSIONE_RICERCATA%>" value="0" />
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text border-top-0" id="inputGroup-sizing-default">Mostra anche</span>
			<div class="d-flex flex-row border rounded-bottom border-top-0 bg-white px-3 py-1">
				<input type="radio" id="maggiori" value="0" name="<%=Costanti.QUANTITA_RICERCATA%>" checked>
				<label class="ms-1" for="maggiori"> dimensioni maggiori</label>
				<div class="border-start mx-4"></div>
				<input type="radio" id="minori" value="1" name="<%=Costanti.QUANTITA_RICERCATA%>">
				<label class="ms-1" for="minori"> dimensioni minori</label>
			</div>
		</div>
		
		<div class="input-group mb-0">
			<span class="input-group-text" id="inputGroup-sizing-default">Prezzo massimo</span>
			<input class="form-control" type="number" name="<%=Costanti.PREZZO_RICERCATO%>" value="0" />
		</div>
		<div class="input-group mb-2">
			<span class="input-group-text border-top-0" id="inputGroup-sizing-default">Ordina per prezzo</span>
			<div class="d-flex flex-row border rounded-end border-top-0 bg-white px-3 py-1">
				<input type="radio" id="ascendente" value="0" name="<%=Costanti.PESO_RICERCATO%>" checked>
				<label class="ms-1" for="ascendente"> ascendente</label>
				<div class="border-start mx-4"></div>
				<input type="radio" id="discendente" value="1.0" name="<%=Costanti.PESO_RICERCATO%>">
				<label class="ms-1" for="discendente"> discendente</label>
			</div>
		</div>
		<div class="d-flex justify-content-center mt-4">			
			<input class="btn btn-info mb-2 btn-lg pt-1" type="submit" value="Cerca">
		</div>
	</form>
</div> <!-- container -->
<jsp:include page="/jsp/footer.jsp"></jsp:include>
<jsp:include page="/jsp/script.jsp"></jsp:include>
</body>
</html>