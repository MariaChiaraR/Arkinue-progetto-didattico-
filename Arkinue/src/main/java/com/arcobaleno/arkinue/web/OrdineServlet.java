package com.arcobaleno.arkinue.web;

import java.io.IOException;

import com.arcobaleno.arkinue.businessLogic.BusinessLogic;
import com.arcobaleno.arkinue.model.Articolo;
import com.arcobaleno.arkinue.model.Utente;
import com.arcobaleno.arkinue.utility.Costanti;
import com.arcobaleno.arkinue.utility.EmailSender;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ordine")
public class OrdineServlet extends HttpServlet
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
			// qui fondamentalmente andiamo a comprare le cose, dobbiamo quindi prendere un prodotto, 
			//l'utetente che lo acquista e rimuoverlo sia dal carrello che dalla lista per inserirlo invece nell ordine...
			BusinessLogic bl = (BusinessLogic) req.getServletContext().getAttribute(Costanti.BUSINESSLOGIC);
			
			Utente utenteloggato = (Utente)req.getSession().getAttribute(Costanti.UTENTE_IN_SESSIONE);
			Integer articoloid = Integer.parseInt(req.getParameter(Costanti.ARTICOLO_NELCARRELLO));
			
			//bl qui crea l'ordine
			Articolo art = bl.ordineServlet(articoloid, utenteloggato);
			if( art != null) 
			{
				EmailSender.sendEmail(utenteloggato, art);
				resp.sendRedirect(req.getContextPath() + "/ordiniUtente");
			}
			else 
			{
				req.setAttribute(Costanti.ERRORE_MSG, "Oggetto non disponibile, in attesa di restock");
				req.getRequestDispatcher("/jsp/errore.jsp").forward(req, resp); //andrà allo storico degli ordini
			}
			
			
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/errore.jsp").forward(req, resp);
		}
	}
}
