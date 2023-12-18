package com.arcobaleno.arkinue.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.arcobaleno.arkinue.businessLogic.BusinessLogic;
import com.arcobaleno.arkinue.model.Articolo;
import com.arcobaleno.arkinue.model.ContenitorediListe;
import com.arcobaleno.arkinue.model.Utente;
import com.arcobaleno.arkinue.utility.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class homeServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try 
		{	BusinessLogic bl = (BusinessLogic) req.getServletContext().getAttribute(Costanti.BUSINESSLOGIC);
			Utente utente = (Utente)req.getSession().getAttribute(Costanti.UTENTE_IN_SESSIONE);
			List<Articolo> lista1 = new ArrayList<>();
			List<Articolo> lista2 = new ArrayList<>();
			List<Articolo> lista3 = new ArrayList<>();
			List<Articolo> lista4 = new ArrayList<>();
			
			List<Articolo> arcobalista1 = new ArrayList<>();
			List<Articolo> arcobalista2 = new ArrayList<>();
			List<Articolo> arcobalista3 = new ArrayList<>();
			List<Articolo> arcobalista4 = new ArrayList<>();
			List<Articolo> arcobalista5 = new ArrayList<>();
			List<Articolo> arcobalista6 = new ArrayList<>();
			List<Articolo> arcobalista7 = new ArrayList<>();
			
			ContenitorediListe cont = bl.homeServlet(req,utente,arcobalista1, arcobalista2, arcobalista3, arcobalista4, arcobalista5, 
					arcobalista6, arcobalista7, lista1, lista2, lista3, lista4);
			
			lista1 = cont.getLista1();
			lista2 = cont.getLista2();
			lista3 = cont.getLista3();
			lista4 = cont.getLista4();
			
			arcobalista1 = cont.getArcobalista1();
			arcobalista2 = cont.getArcobalista2();
			arcobalista3 = cont.getArcobalista3();
			arcobalista4 = cont.getArcobalista4();
			arcobalista5 = cont.getArcobalista5();
			arcobalista6 = cont.getArcobalista6();
			arcobalista7 = cont.getArcobalista7();
			
			req.setAttribute(Costanti.LISTA1, lista1);
			req.setAttribute(Costanti.LISTA2, lista2);
			req.setAttribute(Costanti.LISTA3, lista3);
			req.setAttribute(Costanti.LISTA4, lista4);
			
			req.setAttribute(Costanti.ARCOBALISTA1, arcobalista1);
			req.setAttribute(Costanti.ARCOBALISTA2, arcobalista2);
			req.setAttribute(Costanti.ARCOBALISTA3, arcobalista3);
			req.setAttribute(Costanti.ARCOBALISTA4, arcobalista4);
			req.setAttribute(Costanti.ARCOBALISTA5, arcobalista5);
			req.setAttribute(Costanti.ARCOBALISTA6, arcobalista6);
			req.setAttribute(Costanti.ARCOBALISTA7, arcobalista7);
			
			req.getRequestDispatcher("/jsp/arkinue.jsp").forward(req, resp);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/errore.jsp").forward(req, resp);
		}
	}
}
