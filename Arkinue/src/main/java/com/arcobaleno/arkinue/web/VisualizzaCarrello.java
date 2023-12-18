package com.arcobaleno.arkinue.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.arcobaleno.arkinue.businessLogic.BusinessLogic;
import com.arcobaleno.arkinue.model.Articolo;
import com.arcobaleno.arkinue.model.Ordine;
import com.arcobaleno.arkinue.model.Utente;
import com.arcobaleno.arkinue.utility.Costanti;
import com.arcobaleno.arkinue.utility.Utility;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/visualizzaCarrello")
public class VisualizzaCarrello extends HttpServlet {
	
	BusinessLogic bl;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			bl = (BusinessLogic) getServletContext().getAttribute(Costanti.BUSINESSLOGIC);
			
			Utente utente = (Utente) req.getSession().getAttribute(Costanti.UTENTE_IN_SESSIONE);
			
			int pagina = req.getParameter(Costanti.PAGINA_RICERCA) == null?
					0 : Integer.parseInt(req.getParameter(Costanti.PAGINA_RICERCA));
			
			List<Articolo> articoli = bl.retrieveSingoloUtente(utente.getUsername()).getCarrello().getArticoli();
			
			int numOrdini = articoli.size();
			
			int totPagine = numOrdini == 0 ? 0 :numOrdini%7==0? (numOrdini/7)-1 : numOrdini/7;
		
			List<Articolo> articoliPagina = new ArrayList<Articolo>();
			
			int indicePartenza = numOrdini - pagina*7;
			int indiceArrivo = pagina == totPagine ? 0 : numOrdini - (pagina+1)*7;
			
			while ( indicePartenza != indiceArrivo)
			{	
				//qui diventa null
				articoliPagina.add(articoli.get((indicePartenza--)-1));
			}
			
			Utility.mappaImmagini(req,articoliPagina);
			
			req.setAttribute(Costanti.PAGINA_RICERCA, pagina);
			req.setAttribute(Costanti.TOTALE_PAGINE, totPagine);
			req.setAttribute(Costanti.LISTA_ARTICOLI , articoliPagina);
			req.getRequestDispatcher("/jsp/privata/carrello.jsp").forward(req, resp);		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}