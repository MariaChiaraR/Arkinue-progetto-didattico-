<%@page import="com.arcobaleno.arkinue.model.Utente"%>
<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<header class="container-fluid d-flex justify-content-between" style="max-height: 60rem; background: linear-gradient(90deg, rgba(255,93,93,1) 0%, rgba(255,188,66,1) 11%, rgba(255,242,88,1) 26%, rgba(188,255,96,1) 45%, rgba(82,140,255,1) 70%, rgba(126,93,255,1) 86%, rgba(183,95,251,1) 100%) ;">

	<div class="col">
		  <a class="navbar-brand" href="#">
		    <img class="mt-1" src="<%=request.getContextPath()%>/media/logo.PNG" height="80rem"  class="d-inline-block align-center" alt="logo">
		    <img class="mt-1" src="<%=request.getContextPath()%>/media/arkinue.png" height="80rem" class="d-inline-block align-center" alt="nome azienda">
		  </a>
	</div>
	
	<div class="col d-flex align-items-end">
			
		<% Utente utente = (Utente) request.getSession().getAttribute(Costanti.UTENTE_IN_SESSIONE); 
		if (utente==null){
			%><div class="col">
				<div class="d-flex justify-content-end">				
				<p class="mb-0 pr-2 text-right">Hai già un account?</p>
				</div>			
				<div class="row d-flex flex-row justify-content-end">					
				<a class="button-73 btn btn-info  mx-1 mb-1 d-flex align-items-center justify-content-center" style="width:20%" role="button" href="<%=request.getContextPath() +"/jsp/login.jsp" %>"> Login </a>
				<a class="button-73 btn btn-info  mx-1 mb-1 d-flex align-items-center justify-content-center" style="width:20%" role="button" href="<%=request.getContextPath() +"/jsp/registrazione.jsp" %>"> Registrati </a>
				</div>
			  </div>
			<%
		} else {
			%><div class="col">
				<div class="d-flex justify-content-end">				
				<p class="mb-0 pr-2 text-right">Benvenuto, <%=utente.getUsername()%></p>
				</div>			
				<div class="row d-flex flex-row justify-content-end">					
				<a class="button-73 btn btn-info  mx-1 mb-1 d-flex align-items-center justify-content-center" style="width:20%" role="button" href="<%=request.getContextPath() +"/visualizzaProfilo" %>"> Profilo </a>
				<a class="button-73 btn btn-info  mx-1 mb-1 d-flex align-items-center justify-content-center" style="width:20%" role="button" href="<%=request.getContextPath() +"/visualizzaCarrello" %>"> Carrello </a>
				</div>
			  </div>
			<%
		}		
		%>	
	</div>

</header>