package com.arcobaleno.arkinue.web;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.arcobaleno.arkinue.businessLogic.BusinessLogic;
import com.arcobaleno.arkinue.dao.ArticoloDao;
import com.arcobaleno.arkinue.dao.CarrelloDao;
import com.arcobaleno.arkinue.dao.CategoriaDao;
import com.arcobaleno.arkinue.dao.OrdineDao;
import com.arcobaleno.arkinue.dao.RuoloDao;
import com.arcobaleno.arkinue.dao.UtenteDao;
import com.arcobaleno.arkinue.model.Utente;
import com.arcobaleno.arkinue.utility.Costanti;

import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;

@WebServlet(value="/init", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

	@Override       
	public void init() throws ServletException 
	{
		EntityManager em = null;
		
		try 
		{                        
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("arkinueunit");
			em = emf.createEntityManager();   
			UtenteDao utenteDao = new UtenteDao(em);
			CarrelloDao carrelloDao = new CarrelloDao(em);
			ArticoloDao articoloDao = new ArticoloDao(em);
			CategoriaDao categoriaDao = new CategoriaDao(em);
			RuoloDao ruoloDao = new RuoloDao(em);
			OrdineDao ordineDao = new OrdineDao(em);
			
			BusinessLogic bl = new BusinessLogic(em,utenteDao, carrelloDao, articoloDao, categoriaDao, ruoloDao, ordineDao);
			

			
			String uploadPath = getServletContext().getRealPath("") + File.separator + "media"+ File.separator+ "upload";
			File uploadDir = new File(uploadPath); 
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
					
			bl.initServlet(uploadPath);
			getServletContext().setAttribute(Costanti.BUSINESSLOGIC, bl);
		
			System.out.println("Server inizializzato.");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			em.close();  
			System.exit(0); 
		} 
	
	}
	
	
}
