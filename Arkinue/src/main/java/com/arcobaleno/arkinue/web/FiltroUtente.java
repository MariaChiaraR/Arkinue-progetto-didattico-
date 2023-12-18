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

@WebFilter("/jsp/privata/*")
public class FiltroUtente implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest httpReq = (HttpServletRequest) req;  
			
			Object obj = httpReq.getSession().getAttribute(Costanti.UTENTE_IN_SESSIONE) ;
			
			if(obj!=null && obj instanceof Utente) {
				chain.doFilter(req, resp);
			} 
			else {
				// qui ci va il messaggio d'errore "Non loggato"
				req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/errore.jsp").forward(req, resp);
		}

	}

}


