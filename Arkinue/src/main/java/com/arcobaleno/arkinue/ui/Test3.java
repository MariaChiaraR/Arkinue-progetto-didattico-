package com.arcobaleno.arkinue.ui;

import java.util.List;

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
import com.arcobaleno.arkinue.model.Articolo;
import com.arcobaleno.arkinue.model.Utente;
import com.arcobaleno.arkinue.utility.Costanti;

import javassist.compiler.ast.NewExpr;



public class Test3 {

	public static void main(String[] args) 
	{
		EntityManager em = null;

		try 
		{
			UtenteDao utenteDao;
			CarrelloDao carrelloDao;
			ArticoloDao articoloDao;
			CategoriaDao categoriaDao;
			RuoloDao ruoloDao;
			OrdineDao ordineDao;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("arkinueunit");
			em = emf.createEntityManager();
			utenteDao = new UtenteDao(em);
			carrelloDao = new CarrelloDao(em);
			articoloDao = new ArticoloDao(em);
			categoriaDao = new CategoriaDao(em);
			ruoloDao =new RuoloDao(em);
			ordineDao = new OrdineDao(em);
			BusinessLogic bl = new BusinessLogic(em,utenteDao,carrelloDao,articoloDao,categoriaDao, ruoloDao, ordineDao);
			

			Articolo art1 = bl.retrieveTuttiArticoli().get(0);
			Articolo art2 = bl.retrieveTuttiArticoli().get(1);
			Articolo art3 = bl.retrieveTuttiArticoli().get(2);
			
			
			
			



			System.out.println("ciao");
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		finally 
		{
			em.close();
			System.exit(0);
		}

	}

}


