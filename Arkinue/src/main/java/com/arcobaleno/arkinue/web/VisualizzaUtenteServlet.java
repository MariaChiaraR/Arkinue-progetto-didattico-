package com.arcobaleno.arkinue.web;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.arcobaleno.arkinue.businessLogic.BusinessLogic;
import com.arcobaleno.arkinue.model.Articolo;
import com.arcobaleno.arkinue.model.Ordine;
import com.arcobaleno.arkinue.model.Utente;
import com.arcobaleno.arkinue.utility.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 
 */
@WebServlet("/visualizzaUtente")  //storico degli ordini dell'utente in sessione
public class VisualizzaUtenteServlet extends HttpServlet {
	
	BusinessLogic bl;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			bl = (BusinessLogic) getServletContext().getAttribute(Costanti.BUSINESSLOGIC);
			
			Integer id = Integer.parseInt(req.getParameter(Costanti.ID)) ;
			
			Utente utente = bl.retrieveUtenteETotaleById(id, req) ;
			req.setAttribute(Costanti.VISUALIZZA_UTENTE , utente);
			req.getRequestDispatcher("/jsp/admin/paginaUtente.jsp").forward(req, resp);			
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute(Costanti.ERRORE_MSG, "Errore imprevisto.");
			req.getRequestDispatcher("/jsp/errore.jsp").forward(req, resp);
		}
	}
	
	
}
