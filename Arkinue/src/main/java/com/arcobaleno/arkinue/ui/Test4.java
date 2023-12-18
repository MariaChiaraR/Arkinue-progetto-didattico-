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
import com.arcobaleno.arkinue.model.Ordine;
import com.arcobaleno.arkinue.model.Utente;

public class Test4 
{

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
			ruoloDao = new RuoloDao(em);
			ordineDao = new OrdineDao(em);
			BusinessLogic bl = new BusinessLogic(em,utenteDao,carrelloDao,articoloDao,categoriaDao, ruoloDao, ordineDao);
			
			Articolo art1 = new Articolo("cicciobello",2);
			Articolo art2 = new Articolo("cane a molla",2);
			Articolo art3 = new Articolo("kinder cereali",2);
			Articolo art4 = new Articolo("panino",2);
			Articolo art5 = new Articolo("1up",2);
			
			bl.createArticolo(art1);
			bl.createArticolo(art2);
			bl.createArticolo(art3);
			bl.createArticolo(art4);
			bl.createArticolo(art5);
			
			List<Articolo> articoli = bl.retrieveTuttiArticoli();
			articoli.forEach(c-> System.out.println(c.getNome()));
			bl.createRuoli();
			Utente u = bl.createUtente(new Utente("Gianni","Fantoni"));
			
			
			
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
