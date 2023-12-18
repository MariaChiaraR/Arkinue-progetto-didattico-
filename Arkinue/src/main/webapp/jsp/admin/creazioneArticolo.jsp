<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Creazione Articolo</title>
<jsp:include page="/jsp/stile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/jsp/header.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>
<div class="container my-4 p-4 border border-light rounded-3">
<div class="row px-4">
<% String msg = (String) request.getAttribute(Costanti.ERRORE_MSG); 
			if (msg!=null){
				%> <div class="row align-items-end justify-content-center mb-0" ><p class="fs-3 fw-bold p-1 mb-0 text-danger border border-danger border-2 rounded" style="width: fit-content;"><%=msg%></p></div> 
			<% } %>
	<div class="col d-flex justify-content-center flex-column">

<form action="<%=request.getContextPath()+"/creaArticolo"%>" method="post" enctype="multipart/form-data">

		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Nome Articolo</span>
			<input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" id="nome" name="<%=Costanti.NOME%>" required> <br><br>
		</div>
	<div class="input-group mb-3">
<span class="input-group-text" id="inputGroup-sizing-default">Marca</span>
<input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" id="marca" name="<%=Costanti.MARCA%>" required><br><br>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Peso</span>
<input class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" type="number" id="peso" name="<%=Costanti.PESO %>" min="0" step="0.01" required><br><br>
</div>
<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Dimensione</span>
<input class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" type="number" id="dimensione" name="<%=Costanti.DIMENSIONE%>" required>
</div>
	<div class="input-group mb-3">
<span class="input-group-text" id="inputGroup-sizing-default">Colore</span>
<select class="form-select" aria-label="Default select example" id="sceltaColore" name="<%= Costanti.COLORE%>" required>
	<option value="<%=Costanti.COL_ROSSO%>">Rosso</option>
	<option value="<%=Costanti.COL_VERDE%>">Verde</option>
	<option value="<%=Costanti.COL_BLU%>">Blu</option>
	<option value="<%=Costanti.COL_GIALLO%>">Giallo</option>
	<option value="<%=Costanti.COL_VIOLA%>">Viola</option>
	<option value="<%=Costanti.COL_ARANCIONE%>">Arancione</option>
	<option value="<%=Costanti.COL_BIANCO%>">Bianco</option>
	<option value="<%=Costanti.COL_NERO%>">Nero</option>
	<option value="<%=Costanti.COL_GRIGIO%>">Grigio</option>		
</select>
</div>
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Categoria</span>
<select class="form-select" aria-label="Default select example" id="sceltaOpzione" name="<%= Costanti.CATEGORIA%>" required>
    <option value="<%=Costanti.CAT_ELETTRODOMESTICI%>">Elettrodomestici</option>
    <option value="<%=Costanti.CAT_FAIDATE%>">Fai da te</option>
    <option value="<%=Costanti.CAT_SPORTETEMPOLIBERO%>">Sport e tempo libero</option>
    <option value="<%=Costanti.CAT_PERLACASA%>">Per la casa</option>
    <option value="<%=Costanti.CAT_MODAEBELLEZZA%>">Moda e bellezza</option>
    <option value="<%=Costanti.CAT_ALIMENTARIEBEVANDE%>">Alimentari e bevande</option>
    <option value="<%=Costanti.CAT_DISPOSITIVIEDELETTRONICA%>">Dispositivi ed elettronica</option>
</select>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Prezzo</span>
			<input type="number" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" id="prezzo" name="<%=Costanti.PREZZO%>" min="0" step="0.01" required>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Quantità  Disponibile</span>
			<input type="number" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" id="quantitaDisponibile" name="<%=Costanti.QUANTITA%>" step="1" required>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Descrizione</span>
			<textarea class="form-control" aria-label="With textarea" type="text" id="descrizioneArticolo" name="<%=Costanti.DESCRIZIONE%>" minlength="5" maxlength="500" required></textarea><br>
		</div>

		<div class="input-group mb-3">
			<span class="input-group-text">Immagine Prodotto</span>
			<input class="form-control" aria-label="With textarea" type="file" id="immagineArticolo" name="<%=Costanti.IMG%>" minlength="5" maxlength="500" required><br>
		</div>

		<div class="d-flex justify-content-center mt-4 mb-2">
			<input class="btn btn-warning btn-lg" type="submit" value="Metti in Vendita">
		</div>
	</form>
		</div>
	</div>
</div> <!-- container -->
<jsp:include page="/jsp/footer.jsp"></jsp:include>
<jsp:include page="/jsp/script.jsp"></jsp:include>
</body>
</html>