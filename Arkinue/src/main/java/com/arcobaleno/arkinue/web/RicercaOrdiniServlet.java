package com.arcobaleno.arkinue.web;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

@WebServlet("/ricercaOrdini")
public class RicercaOrdiniServlet extends HttpServlet {
	
	BusinessLogic bl;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			bl = (BusinessLogic) getServletContext().getAttribute(Costanti.BUSINESSLOGIC);
			
			String username = req.getParameter(Costanti.USERNAME).trim() ;
			String nome = Utility.insertPercento(req.getParameter(Costanti.NOME)) ;
			String categoria = Utility.insertPercento(req.getParameter(Costanti.CATEGORIA)) ; 
			String scelta1 = req.getParameter(Costanti.ORDINE);  //0 è "dal più recente" 1 è "dal più vecchio"
			int scelta;
			if(scelta1 != null) {scelta = Integer.parseInt(scelta1);}
			else {scelta = 0;}
			
			int pagina = req.getParameter(Costanti.PAGINA_RICERCA) == null?
					0 : Integer.parseInt(req.getParameter(Costanti.PAGINA_RICERCA));
			
			List<Integer> totPagine = new ArrayList<Integer>();
			
			if (req.getParameter(Costanti.TOTALE_PAGINE) != null && pagina != 0)
			{
				totPagine.add(Integer.parseInt(req.getParameter(Costanti.TOTALE_PAGINE)));
			}
			
			List<Ordine> ordini = bl.retrievePaginaOrdine(req,username, nome, categoria, scelta, pagina, totPagine);
			
			req.setAttribute(Costanti.USERNAME, username);
			req.setAttribute(Costanti.NOME, nome);
			req.setAttribute(Costanti.CATEGORIA, categoria);
			req.setAttribute(Costanti.ORDINE, scelta);
			req.setAttribute(Costanti.PAGINA_RICERCA, pagina);
			req.setAttribute(Costanti.TOTALE_PAGINE, totPagine.get(0));
			req.setAttribute(Costanti.RISULTATI, ordini);
			req.getRequestDispatcher("/jsp/admin/risultatiRicercaOrdini.jsp").forward(req, resp);			
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute(Costanti.ERRORE_MSG, "Errore imprevisto.");
			req.getRequestDispatcher("/jsp/errore.jsp").forward(req, resp);
		}
	}
	
	
}
