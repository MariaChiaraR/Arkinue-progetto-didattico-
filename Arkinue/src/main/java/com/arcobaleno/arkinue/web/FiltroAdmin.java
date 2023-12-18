package com.arcobaleno.arkinue.web;

import java.io.IOException;

import com.arcobaleno.arkinue.model.Utente;
import com.arcobaleno.arkinue.utility.Costanti;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("/jsp/admin/*")
public class FiltroAdmin implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException 
	{
		try 
		{
			HttpServletRequest httpReq = (HttpServletRequest) req;  //cast della request
			
			Object obj = httpReq.getSession().getAttribute(Costanti.UTENTE_IN_SESSIONE) ;  //recupero dell'utente dalla sessione o null in caso non ci sia
			
			if(obj!=null) {
				Utente utente = (Utente) obj;
				if(utente.getRuolo().getNome().equals(Costanti.RUOLO_ADMIN)) 
				{
					chain.doFilter(req, resp);
				} 
				else
				{
					req.getRequestDispatcher("/home").forward(req, resp);
				}
			} 
			else {
				req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
			}
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/errore.jsp").forward(req, resp);
		}

	}

}
