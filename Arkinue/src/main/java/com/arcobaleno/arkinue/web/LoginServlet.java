package com.arcobaleno.arkinue.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

import com.arcobaleno.arkinue.businessLogic.BusinessLogic;
import com.arcobaleno.arkinue.model.Utente;
import com.arcobaleno.arkinue.utility.Costanti;



@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	BusinessLogic bl;	
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doPost(req, resp);
    }
       
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	try {
    		bl = (BusinessLogic) getServletContext().getAttribute(Costanti.BUSINESSLOGIC) ;
    		String username = req.getParameter(Costanti.USERNAME).trim() ;
    		String password = req.getParameter(Costanti.PASSWORD).trim() ;
    		
    		Utente utente = bl.retrieveSingoloUtente(username);
    		if(utente!=null) {
    			if (password.equals(utente.getPassword())) {
    				req.getSession().setAttribute(Costanti.UTENTE_IN_SESSIONE, utente);
        			req.getRequestDispatcher("/home").forward(req, resp);
    			} else {
    				req.setAttribute(Costanti.ERRORE_MSG, "password errata");
        			req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
    			}
    		} else {
    			req.setAttribute(Costanti.ERRORE_MSG, "username errato");
    			req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
    		}
    			
			//Utente utente = (Utente) request.getSession().getAttribute(Costanti.UTENTE_IN_SESSIONE) ; //copiare da qui il recupero dell'utente dalla sessione
    		
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/errore.jsp").forward(req, resp);
		}
    }
    

}
