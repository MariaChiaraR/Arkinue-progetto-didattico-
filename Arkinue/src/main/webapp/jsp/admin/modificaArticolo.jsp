<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<%@page import="com.arcobaleno.arkinue.model.Articolo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica Articolo</title>
<jsp:include page="/jsp/stile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/jsp/header.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>
  <% Articolo articolo = (Articolo) request.getAttribute(Costanti.ARTICOLO);  %> 
<div class="container my-4 p-4 border border-light rounded-3">
	<div class="row px-4">
	<div class="col d-flex justify-content-center flex-column">
		<div class="row">
			<h3>Modifica in corso: <%=articolo.getNome() %> - ID: <%=articolo.getId() %></h3>
		</div>		
	<form action="<%=request.getContextPath()+"/modificaArticolo"%>" method="post" enctype="multipart/form-data">
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Nome Articolo</span>
		  <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" id="nome" name="<%=Costanti.NOME%>" value="<%=articolo.getNome() %>">
		</div>
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Marca</span>
		  <input type="text" id="marca" name="<%=Costanti.MARCA%>" value="<%=articolo.getMarca() %>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Colore</span>
		  <select class="form-select" aria-label="Default select example" id="sceltaColore" name="<%=Costanti.COLORE%>">
				<option value="<%=Costanti.COL_ROSSO%>" <%= articolo.getColore().equals(Costanti.COL_ROSSO)? "selected" : "" %>>Rosso</option>
				<option value="<%=Costanti.COL_VERDE%>"<%= articolo.getColore().equals(Costanti.COL_VERDE)? "selected" : "" %>>Verde</option>
				<option value="<%=Costanti.COL_BLU%>"<%= articolo.getColore().equals(Costanti.COL_BLU)? "selected": "" %>>Blu</option>
				<option value="<%=Costanti.COL_GIALLO%>" <%= articolo.getColore().equals(Costanti.COL_GIALLO)? "selected": "" %>>Giallo</option>
				<option value="<%=Costanti.COL_VIOLA%>" <%= articolo.getColore().equals(Costanti.COL_VIOLA)? "selected": "" %>>Viola</option>
				<option value="<%=Costanti.COL_ARANCIONE%>"<%= articolo.getColore().equals(Costanti.COL_ARANCIONE)? "selected": "" %>>Arancione</option>
				<option value="<%=Costanti.COL_BIANCO%>"<%= articolo.getColore().equals(Costanti.COL_BIANCO)? "selected": "" %>>Bianco</option>
				<option value="<%=Costanti.COL_NERO%>"<%= articolo.getColore().equals(Costanti.COL_NERO)? "selected": "" %>>Nero</option>
				<option value="<%=Costanti.COL_GRIGIO%>"<%= articolo.getColore().equals(Costanti.COL_GRIGIO)? "selected": "" %> >Grigio</option>
				<option value="<%=Costanti.COL_MARRONE%>"<%= articolo.getColore().equals(Costanti.COL_MARRONE)? "selected": "" %> >Marrone</option>
			</select>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Categoria</span>
			<select class="form-select" aria-label="Default select example" id="sceltaOpzione" name="<%=Costanti.CATEGORIA%>">
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
			<input type="number" id="prezzo" name="<%=Costanti.PREZZO%>" min="0" step="0.01" value="<%=articolo.getPrezzo() %>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Quantità  Disponibile</span>
			<input type="number" id="quantitaDisponibile" name="<%=Costanti.QUANTITA%>" step="1" value="<%=articolo.getQuantita() %>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Dimensione</span>
			<input class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" type="number" id="dimensione" name="<%=Costanti.DIMENSIONE%>" value="<%=articolo.getDimensione() %>">
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text" id="inputGroup-sizing-default">Peso</span>
			<input class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" type="number" id="peso" name="<%=Costanti.PESO %>" min="0" step="0.01" value="<%=articolo.getPeso()%>">
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text">Descrizione</span>
		  <textarea class="form-control" aria-label="With textarea" id="descrizioneArticolo" name="<%=Costanti.DESCRIZIONE%>"><%=articolo.getDescrizione() %></textarea>
		</div>
		
		<div class="input-group">
			<span class="input-group-text">Immagine Prodotto</span>
			<input class="form-control" aria-label="With textarea" type="file" id="descrizioneArticolo" name="<%=Costanti.IMG%>" minlength="5" maxlength="500"><br>
		</div>
		
		<div class="d-flex justify-content-center mt-4">
			<input type="hidden" value="<%=articolo.getId()%>" name="<%=Costanti.ID%>">
			<input class="btn btn-warning mb-2 btn-lg" type="submit" value="Salva Modifiche">
		</div>
	</form>
	</div> <!-- col adattiva -->
	</div>	<!-- row statica --> 
	<div class="row d-flex justify-content-evenly mt-4">
		<form action="<%= request.getContextPath() + "/visualizzaArticolo" %>" method="post" style="width: fit-content;">
			<input type="hidden" name="<%= Costanti.ID %>" value="<%= articolo.getId() %>">
			<input class="btn btn-info mb-2 btn-lg" type="submit" value="Annulla Modifica">
		</form>
		<form action="<%= request.getContextPath() + "/cancellaArticolo" %>" method="post" style="width: fit-content;">
			<input type="hidden" name="<%= Costanti.ID %>" value="<%= articolo.getId() %>">
			<input class="btn btn-danger mb-2 btn-lg" type="submit" value="Cancella Articolo">
		</form>
	</div>
</div> <!-- container --> 
<jsp:include page="/jsp/footer.jsp"></jsp:include>
<jsp:include page="/jsp/script.jsp"></jsp:include>
</body>
</html>