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

@WebServlet("/rimuoviCarrello")
public class RimuoviDaCarrelloServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{	
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		try 
		{
			BusinessLogic bl = (BusinessLogic) req.getServletContext().getAttribute(Costanti.BUSINESSLOGIC);
			Utente utenteloggato = (Utente)req.getSession().getAttribute(Costanti.UTENTE_IN_SESSIONE);
			Integer articoloid = Integer.parseInt(req.getParameter(Costanti.ARTICOLO_NELCARRELLO));
			
			
			Articolo articolo = bl.rimuoviDaCarrelloServlet(articoloid,utenteloggato);
			

			req.setAttribute(Costanti.ARTICOLO, articolo);			
			resp.sendRedirect(req.getContextPath() + "/visualizzaCarrello");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/errore.jsp").forward(req, resp);
		}
	}
	

	
	
	
	
	
	
}
