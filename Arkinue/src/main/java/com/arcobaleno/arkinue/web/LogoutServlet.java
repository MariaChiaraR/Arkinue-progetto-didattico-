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



@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	BusinessLogic bl;	
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doPost(req, resp);
    }
       
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	try {
    		bl = (BusinessLogic) getServletContext().getAttribute(Costanti.BUSINESSLOGIC) ;
 
    		req.getSession().removeAttribute(Costanti.UTENTE_IN_SESSIONE);
    		req.getRequestDispatcher("/home").forward(req, resp);
  
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/errore.jsp").forward(req, resp);
		}
    }
    

}
