package com.arcobaleno.arkinue.web;

import java.io.IOException;
import java.util.List;

import com.arcobaleno.arkinue.businessLogic.BusinessLogic;
import com.arcobaleno.arkinue.model.Articolo;
import com.arcobaleno.arkinue.utility.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ricercaSemplice")
public class RicercaSempliceServlet extends HttpServlet {
	
	BusinessLogic bl;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String txt = req.getParameter(Costanti.NAME_RICERCATO).trim() ;
			bl = (BusinessLogic) getServletContext().getAttribute(Costanti.BUSINESSLOGIC);
			List<Articolo> articoli;
			if (txt.equals("")) {
				articoli = bl.retrieveTuttiArticoli() ;
			} else {
				articoli = bl.retrieveArticoloByNome("%"+txt+"%");
			}
			
			int pagina = req.getParameter(Costanti.PAGINA_RICERCA) == null?
					0 : Integer.parseInt(req.getParameter(Costanti.PAGINA_RICERCA));
			
			int totPagine;
			if (pagina == 0) { totPagine = articoli.size() /7;}
			else { totPagine = Integer.parseInt(req.getParameter(Costanti.TOTALE_PAGINE));}
			req.setAttribute(Costanti.NAME_RICERCATO, txt);
			req.setAttribute(Costanti.PAGINA_RICERCA, pagina);
			req.setAttribute(Costanti.TOTALE_PAGINE, totPagine);
						
			req.setAttribute(Costanti.RISULTATI, articoli);
			req.getRequestDispatcher("/jsp/risultatiRicerca.jsp").forward(req, resp);			
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute(Costanti.ERRORE_MSG, "Errore imprevisto.");
			req.getRequestDispatcher("/jsp/errore.jsp").forward(req, resp);
		}
	}
	
	
}
