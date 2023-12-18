<%@page import="com.arcobaleno.arkinue.model.Utente"%>
<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<nav class="navbar navbar-expand-lg navbar-light border-bottom border-3" style="background: linear-gradient(90deg, rgba(255,93,93,1) 0%, rgba(255,188,66,1) 11%, rgba(255,242,88,1) 26%, rgba(188,255,96,1) 45%, rgba(82,140,255,1) 70%, rgba(126,93,255,1) 86%, rgba(183,95,251,1) 100%) ;">
  <div class="container-fluid">
  	<div class="d-flex flex-row align-items-center">
  	<% Utente utente = (Utente) request.getSession().getAttribute(Costanti.UTENTE_IN_SESSIONE); %>
		<a href="<%=request.getContextPath() +"/home" %>"><button class="button-85" role="button">Home</button></a>
		<a href="<%=request.getContextPath() +"/jsp/ricercaAvanzata.jsp" %>"><button class="button-85" role="button">Ricerca Avanzata</button></a>
	<% if (utente==null){ %>
		<a href="<%=request.getContextPath() +"/jsp/login.jsp" %>"><button class="button-85" role="button">Login</button></a>
		<a href="<%=request.getContextPath() +"/jsp/registrazione.jsp" %>"><button class="button-85" role="button">Registrazione</button></a>
	<% } else { %>	
		<a href="<%=request.getContextPath() +"/visualizzaProfilo" %>"><button class="button-85" role="button">Il Mio Profilo</button></a>
		<a href="<%=request.getContextPath() +"/visualizzaCarrello" %>"><button class="button-85" role="button">Carrello</button></a>
		<a href="<%=request.getContextPath() +"/ordiniUtente" %>"><button class="button-85" role="button">Storico Ordini</button></a>
		<a href="<%=request.getContextPath() +"/jsp/privata/assistenza.jsp" %>"><button class="button-85" role="button">Assistenza</button></a>
		<a href="<%=request.getContextPath() +"/logout" %>"><button class="button-85" role="button">Logout</button></a>
	<% } %>
	</div>
   	<form class="d-flex input-group" action="<%=request.getContextPath() +"/ricercaAvanzata" %>" method="get" style="min-width: 10rem; max-width: 30rem">
		<input class="form-control" type="text" name="<%=Costanti.NAME_RICERCATO%>" placeholder="ricerca semplice">
		<input class="btn btn-dark" data-mdb-ripple-color="dark" type="submit" value="Cerca">
	</form>
<!-- style="height:2.3em; width: 2.3em; border-radius: 2rem; background: radial-gradient(circle, rgba(28,43,204,1) 0%, rgba(199,38,207,1) 40%, rgba(209,35,35,1) 57%, rgba(211,146,32,1) 70%, rgba(153,213,29,1) 82%, rgba(26,215,89,1) 92%, rgba(21,214,218,1) 100%);"></button> -->
  </div>
</nav>