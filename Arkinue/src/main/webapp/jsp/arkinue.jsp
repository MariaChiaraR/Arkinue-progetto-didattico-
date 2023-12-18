<%@page import="java.util.Map"%>
<%@page import="com.arcobaleno.arkinue.model.Articolo"%>
<%@page import="java.util.List"%>
<%@page import="com.arcobaleno.arkinue.model.Utente"%>
<%@page import="com.arcobaleno.arkinue.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Arkinue</title>
<jsp:include page="/jsp/stile.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/jsp/header.jsp"></jsp:include>
	<jsp:include page="/jsp/menu.jsp"></jsp:include>
	
	<% 
	List<Articolo> lista1= (List<Articolo>) request.getAttribute(Costanti.LISTA1); 
	List<Articolo> lista2= (List<Articolo>) request.getAttribute(Costanti.LISTA2);
	List<Articolo> lista3= (List<Articolo>) request.getAttribute(Costanti.LISTA3);
	List<Articolo> lista4= (List<Articolo>) request.getAttribute(Costanti.LISTA4);
	
	List<Articolo> arcobalista1= (List<Articolo>) request.getAttribute(Costanti.ARCOBALISTA1); 
	List<Articolo> arcobalista2= (List<Articolo>) request.getAttribute(Costanti.ARCOBALISTA2);
	List<Articolo> arcobalista3= (List<Articolo>) request.getAttribute(Costanti.ARCOBALISTA3);
	List<Articolo> arcobalista4= (List<Articolo>) request.getAttribute(Costanti.ARCOBALISTA4);
	List<Articolo> arcobalista5= (List<Articolo>) request.getAttribute(Costanti.ARCOBALISTA5); 
	List<Articolo> arcobalista6= (List<Articolo>) request.getAttribute(Costanti.ARCOBALISTA6);
	List<Articolo> arcobalista7= (List<Articolo>) request.getAttribute(Costanti.ARCOBALISTA7);
	Map<Integer,String> mappaImmagini = (Map<Integer,String>) request.getAttribute(Costanti.MAPPAIMMAGINI);
%>
<div class="container-fluid">
	<div class="row mt-2">
		<div class="col-12 d-flex justify-content-center">
			<div id="carouselExampleIndicators" class="carousel slide"  data-ride="carousel">
				  <ol class="carousel-indicators">
				    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
				    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
				    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
				  </ol>
				  <div class="carousel-inner" style="height: 50vh; width: 100vh;">
				    <div class="carousel-item active carosello">
				      	<img class="d-block w-100" style="object-fit: fill;" src="<%=request.getContextPath()+"/media/caro7.jpeg"%>"  alt="First slide">
				    </div>
				    <div class="carousel-item carosello">
				      <img class="d-block w-100" style="object-fit: fill;" src="<%=request.getContextPath()+"/media/caro12.jpg"%>"  alt="First slide">
				    </div>
				    <div class="carousel-item carosello" >
				      <img class="d-block w-100" style="object-fit: fill;" src="<%=request.getContextPath()+"/media/caro14.jpeg"%>"  alt="First slide">
				    </div>
				  </div>
				  <a class="carousel-control-prev" onclick="cambioImmagineIndietro()" href="#carouselExampleIndicators" role="button" data-slide="prev">
				    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
				    <span class="sr-only">Previous</span>
				  </a>
				  <a class="carousel-control-next" onclick="cambioImmagineAvanti()" href="#carouselExampleIndicators" role="button" data-slide="next">
				    <span class="carousel-control-next-icon" aria-hidden="true"></span>
				    <span class="sr-only">Next</span>
				  </a>
			</div>
		</div>
	</div>
</div>
	<div style="display: flex; overflow: hidden; width: 100%">
		<div class="arcobaleno">
			<div class="divicona1" style="background-color: #e81416;">
				<img src="<%=request.getContextPath()+"/media/cibi_bevande_nero.png"%>"><h4>Novità in <%=arcobalista1.get(0).getCategoria().getNome() %></h4>
				<div class="oggetto1 arcoprodotti">
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista1.get(0).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit">
							<img alt="prodotto"  src="<%=mappaImmagini.get(arcobalista1.get(0).getId())%>" >
							<p style="margin: 0%"><%=arcobalista1.get(0).getNome()%></p>
						</button>
					</form>
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista1.get(1).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit">
							<img alt="prodotto"  src="<%=mappaImmagini.get(arcobalista1.get(1).getId())%>">
							<p style="margin: 0%"><%=arcobalista1.get(1).getNome()%></p>
						</button>
					</form>
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista1.get(2).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit">
							<img alt="prodotto" src="<%=mappaImmagini.get(arcobalista1.get(2).getId())%>">
							<p style="margin: 0%"><%=arcobalista1.get(2).getNome()%></p>
						</button>
					</form>				
				</div>
			</div>
			<div class="divicona2" style="background-color: #ffa500;">
				<img src="<%=request.getContextPath()+"/media/dispositivi_elettronica.png"%>"><h4>Novità in <%=arcobalista2.get(0).getCategoria().getNome() %></h4>
				<div class="oggetto2 arcoprodotti">
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista2.get(0).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit" >
							<img alt="prodotto"  src="<%=mappaImmagini.get(arcobalista2.get(0).getId())%>">
							<p style="margin: 0%"><%=arcobalista2.get(0).getNome()%></p>
						</button>
					</form>
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista2.get(1).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit" >
							<img alt="prodotto"  src="<%=mappaImmagini.get(arcobalista2.get(1).getId())%>">
							<p style="margin: 0%"><%=arcobalista2.get(1).getNome()%></p>
						</button>
					</form>
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista2.get(2).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit">
							<img alt="prodotto"  src="<%=mappaImmagini.get(arcobalista2.get(2).getId())%>">
							<p style="margin: 0%"><%=arcobalista2.get(2).getNome()%></p>
						</button>
					</form>
				</div>
			</div>
			<div class="divicona3" style="background-color: #faeb36;">
				<img src="<%=request.getContextPath()+"/media/elettrodomestici.png"%>"><h4>Novità in <%=arcobalista3.get(0).getCategoria().getNome() %></h4>
				<div class="oggetto3 arcoprodotti">
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista3.get(0).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit" >
							<img alt="prodotto"  src="<%=mappaImmagini.get(arcobalista3.get(0).getId())%>">
							<p style="margin: 0%"><%=arcobalista3.get(0).getNome()%></p>
						</button>
					</form>
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista3.get(1).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit">
							<img alt="prodotto"  src="<%=mappaImmagini.get(arcobalista3.get(1).getId())%>">
							<p style="margin: 0%"><%=arcobalista3.get(1).getNome()%></p>
						</button>
					</form>
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista3.get(2).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit" >
							<img alt="prodotto"  src="<%=mappaImmagini.get(arcobalista3.get(2).getId())%>">
							<p style="margin: 0%"><%=arcobalista3.get(2).getNome()%></p>
						</button>
					</form>
				</div>
			</div>
			<div class="divicona4" style="background-color: #79c314;">
				<img src="<%=request.getContextPath()+"/media/fai_da_te.png"%>"><h4>Novità in <%=arcobalista4.get(0).getCategoria().getNome() %></h4>
				<div class="oggetto4 arcoprodotti">
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista4.get(0).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit">
							<img alt="prodotto"  src="<%=mappaImmagini.get(arcobalista4.get(0).getId())%>">
							<p style="margin: 0%"><%=arcobalista4.get(0).getNome()%></p>
						</button>
					</form>
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista4.get(1).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit" >
							<img alt="prodotto"  src="<%=mappaImmagini.get(arcobalista4.get(1).getId())%>">
							<p style="margin: 0%"><%=arcobalista4.get(1).getNome()%></p>
						</button>
					</form>
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista4.get(2).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit" >
							<img alt="prodotto"  src="<%=mappaImmagini.get(arcobalista4.get(2).getId())%>">
							<p style="margin: 0%"><%=arcobalista4.get(2).getNome()%></p>
						</button>
					</form>
				</div>
			</div>
			<div class="divicona5" style="background-color: #487de7;">
				<img src="<%=request.getContextPath()+"/media/moda_bellezza.png"%>"><h4>Novità in <%=arcobalista5.get(0).getCategoria().getNome() %></h4>
				<div class="oggetto5 arcoprodotti">
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista5.get(0).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit" >
							<img alt="prodotto"  src="<%=mappaImmagini.get(arcobalista5.get(0).getId())%>">
							<p style="margin: 0%"><%=arcobalista5.get(0).getNome()%></p>
						</button>
					</form>
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista5.get(1).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit" >
							<img alt="prodotto"  src="<%=mappaImmagini.get(arcobalista5.get(1).getId())%>">
							<p style="margin: 0%"><%=arcobalista5.get(1).getNome()%></p>
						</button>
					</form>
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista5.get(2).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit" >
							<img alt="prodotto"  src="<%=mappaImmagini.get(arcobalista5.get(2).getId())%>">
							<p style="margin: 0%"><%=arcobalista5.get(2).getNome()%></p>
						</button>
					</form>
				</div>
			</div>
				<div class="divicona6" style="background-color: #7a00ff;">
				<img src="<%=request.getContextPath()+"/media/casa.png"%>"><h4>Novità in <%=arcobalista6.get(0).getCategoria().getNome() %></h4>
				<div class="oggetto6 arcoprodotti">
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista6.get(0).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit" >
							<img alt="prodotto"  src="<%=mappaImmagini.get(arcobalista6.get(0).getId())%>">
							<p style="margin: 0%"><%=arcobalista6.get(0).getNome()%></p>
						</button>
					</form>
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista6.get(1).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit" >
							<img alt="prodotto"  src="<%=mappaImmagini.get(arcobalista6.get(1).getId())%>">
							<p style="margin: 0%"><%=arcobalista6.get(1).getNome()%></p>
						</button>
					</form>
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista6.get(2).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit" >
							<img alt="prodotto"  src="<%=mappaImmagini.get(arcobalista6.get(2).getId())%>">
							<p style="margin: 0%"><%=arcobalista6.get(2).getNome()%></p>
						</button>
					</form>
				
				</div>
			</div>
			<div class="divicona7" style="background-color: #ff00c8;">
				<img src="<%=request.getContextPath()+"/media/sport_tempo_libero.png"%>"><h4>Novità in <%=arcobalista7.get(0).getCategoria().getNome() %></h4>
				<div class="oggetto7 arcoprodotti">
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista7.get(0).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit" >
							<img alt="prodotto"  src="<%=mappaImmagini.get(arcobalista7.get(0).getId())%>">
							<p style="margin: 0%"><%=arcobalista7.get(0).getNome()%></p>
						</button>
					</form>
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista7.get(1).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit" >
							<img alt="prodotto"  src="<%=mappaImmagini.get(arcobalista7.get(1).getId())%>">
							<p style="margin: 0%"><%=arcobalista7.get(1).getNome()%></p>
						</button>
					</form>
					<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
						<input type="hidden" value="<%=arcobalista7.get(2).getId()%>" name ="<%=Costanti.ID%>">
						<button type="submit" >
							<img alt="prodotto"  src="<%=mappaImmagini.get(arcobalista7.get(2).getId())%>">
							<p style="margin: 0%"><%=arcobalista7.get(2).getNome()%></p>
						</button>
					</form>
				</div>
			</div>
		</div>
		<div class="boxconsigliati">
			<h2>Consigliati per te dalla categoria <%=lista1.get(0).getCategoria().getNome() %></h2>
			<div class="consigliati">
				<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
					<input type="hidden" value="<%=lista1.get(0).getId()%>" name ="<%=Costanti.ID%>">
					<button style="margin: 1rem" type="submit"><img alt="prodotto" src="<%=mappaImmagini.get(lista1.get(0).getId())%>"></button>
				</form>
				<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
					<input type="hidden" value="<%=lista1.get(1).getId()%>" name ="<%=Costanti.ID%>">
					<button style="margin: 1rem"type="submit"><img alt="prodotto" src="<%=mappaImmagini.get(lista1.get(1).getId())%>"></button>
				</form>
				<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
					<input type="hidden" value="<%=lista1.get(2).getId()%>" name ="<%=Costanti.ID%>">
					<button style="margin: 1rem"type="submit"><img alt="prodotto" src="<%=mappaImmagini.get(lista1.get(2).getId())%>"></button>
				</form>
				<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
					<input type="hidden" value="<%=lista1.get(3).getId()%>" name ="<%=Costanti.ID%>">
					<button style="margin: 1rem"type="submit"><img alt="prodotto" src="<%=mappaImmagini.get(lista1.get(3).getId())%>"></button>
				</form>
			</div>
			<h2>Consigliati per te dalla categoria <%=lista2.get(0).getCategoria().getNome() %></h2>
			<div class="consigliati">
				<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
					<input type="hidden" value="<%=lista2.get(0).getId()%>" name ="<%=Costanti.ID%>">
					<button style="margin: 1rem"type="submit"><img alt="prodotto" src="<%=mappaImmagini.get(lista2.get(0).getId())%>"></button>
				</form>
				<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
					<input type="hidden" value="<%=lista2.get(1).getId()%>" name ="<%=Costanti.ID%>">
					<button style="margin: 1rem"type="submit"><img alt="prodotto" src="<%=mappaImmagini.get(lista2.get(1).getId())%>"></button>
				</form>
				<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
					<input type="hidden" value="<%=lista2.get(2).getId()%>" name ="<%=Costanti.ID%>">
					<button style="margin: 1rem"type="submit"><img alt="prodotto" src="<%=mappaImmagini.get(lista2.get(2).getId())%>"></button>
				</form>
				<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
					<input type="hidden" value="<%=lista2.get(3).getId()%>" name ="<%=Costanti.ID%>">
					<button style="margin: 1rem" type="submit"><img alt="prodotto" src="<%=mappaImmagini.get(lista2.get(3).getId())%>"></button>
				</form>
			</div>
			<h2>Consigliati per te dalla categoria <%=lista3.get(0).getCategoria().getNome() %></h2>
			<div class="consigliati">
				<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
					<input type="hidden" value="<%=lista3.get(0).getId()%>" name ="<%=Costanti.ID%>">
					<button style="margin: 1rem"type="submit"><img alt="prodotto" src="<%=mappaImmagini.get(lista3.get(0).getId())%>"></button>
				</form>
				<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
					<input type="hidden" value="<%=lista3.get(1).getId()%>" name ="<%=Costanti.ID%>">
					<button style="margin: 1rem"type="submit"><img alt="prodotto" src="<%=mappaImmagini.get(lista3.get(1).getId())%>"></button>
				</form>
				<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
					<input type="hidden" value="<%=lista3.get(2).getId()%>" name ="<%=Costanti.ID%>">
					<button style="margin: 1rem"type="submit"><img alt="prodotto" src="<%=mappaImmagini.get(lista3.get(2).getId())%>"></button>
				</form>
				<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
					<input type="hidden" value="<%=lista3.get(3).getId()%>"name ="<%=Costanti.ID%>">
					<button style="margin: 1rem"type="submit"><img alt="prodotto" src="<%=mappaImmagini.get(lista3.get(3).getId())%>"></button>
				</form>
			</div>
			<h2>Consigliati per te dalla categoria <%=lista4.get(0).getCategoria().getNome() %></h2>
			<div class="consigliati">
				<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
					<input type="hidden" value="<%=lista4.get(0).getId()%>" name ="<%=Costanti.ID%>">
					<button style="margin: 1rem"type="submit"><img alt="prodotto" src="<%=mappaImmagini.get(lista4.get(0).getId())%>"></button>
				</form>
				<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
					<input type="hidden" value="<%=lista4.get(1).getId()%>" name ="<%=Costanti.ID%>">
					<button style="margin: 1rem"type="submit"><img alt="prodotto" src="<%=mappaImmagini.get(lista4.get(1).getId())%>"></button>
				</form>
				<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
					<input type="hidden" value="<%=lista4.get(2).getId()%>" name ="<%=Costanti.ID%>">
					<button style="margin: 1rem"type="submit"><img alt="prodotto" src="<%=mappaImmagini.get(lista4.get(2).getId())%>"></button>
				</form>
				<form method="post" action="<%=request.getContextPath()+"/visualizzaArticolo"%>">
					<input type="hidden" value="<%=lista4.get(3).getId()%>" name ="<%=Costanti.ID%>">
					<button style="margin: 1rem"type="submit"><img alt="prodotto" src="<%=mappaImmagini.get(lista4.get(3).getId())%>"></button>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="/jsp/footer.jsp"></jsp:include>
	<jsp:include page="/jsp/script.jsp"></jsp:include>
	<script src="<%=request.getContextPath()+"/js/carousel.js"%>"></script>
</body>
</html>
</body>
</html>