package com.arcobaleno.arkinue.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.arcobaleno.arkinue.businessLogic.BusinessLogic;
import com.arcobaleno.arkinue.model.Articolo;
import com.arcobaleno.arkinue.model.ContenitorediListe;
import com.arcobaleno.arkinue.model.Ordine;
import com.arcobaleno.arkinue.model.Utente;
import com.arcobaleno.arkinue.utility.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ordiniUtente")  //storico degli ordini dell'utente in sessione
public class VisualizzaOrdiniServlet extends HttpServlet {
	
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
			

			ContenitorediListe cont = bl.visualizzaOrdiniServlet(utente.getId(), pagina, req);
			//bisogna accorparla o trovare il modo di separarla dalla business logic
			pagina = cont.getGenerico1();
			int totPagine = cont.getGenerico2();
			List<Ordine> ordiniPagina = cont.getOrdinelista1();
			
			req.setAttribute(Costanti.PAGINA_RICERCA, pagina);
			req.setAttribute(Costanti.TOTALE_PAGINE, totPagine);
			req.setAttribute(Costanti.LISTA_ORDINI , ordiniPagina);
			req.getRequestDispatcher("/jsp/privata/listaOrdini.jsp").forward(req, resp);			
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute(Costanti.ERRORE_MSG, "Errore imprevisto.");
			req.getRequestDispatcher("/jsp/errore.jsp").forward(req, resp);
		}
	}
}
