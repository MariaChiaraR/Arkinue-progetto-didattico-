package com.arcobaleno.arkinue.web;

import java.io.IOException;

import com.arcobaleno.arkinue.businessLogic.BusinessLogic;
import com.arcobaleno.arkinue.model.Utente;
import com.arcobaleno.arkinue.utility.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/visualizzaProfilo")  //storico degli ordini dell'utente in sessione
public class VisualizzaProfiloServlet extends HttpServlet {
		
	BusinessLogic bl;
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			try {
				bl = (BusinessLogic) getServletContext().getAttribute(Costanti.BUSINESSLOGIC);
				
				Utente utenteInSessione =(Utente) req.getSession().getAttribute(Costanti.UTENTE_IN_SESSIONE);
				Utente utente = bl.retrieveUtenteAndList(utenteInSessione.getId(),req) ;
				req.getSession().setAttribute(Costanti.UTENTE_IN_SESSIONE, utente);
				req.getRequestDispatcher("/jsp/privata/profiloUtente.jsp").forward(req, resp);			
				
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute(Costanti.ERRORE_MSG, "Errore imprevisto.");
				req.getRequestDispatcher("/jsp/errore.jsp").forward(req, resp);
			}
		}
		
		
}

