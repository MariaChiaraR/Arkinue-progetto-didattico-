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
import com.arcobaleno.arkinue.model.Categoria;
import com.arcobaleno.arkinue.utility.Costanti;

public class TestCercaArticoli {

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
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("arkinueunit");
			em = emf.createEntityManager();
			utenteDao = new UtenteDao(em);
			carrelloDao = new CarrelloDao(em);
			articoloDao = new ArticoloDao(em);
			categoriaDao = new CategoriaDao(em);
			ruoloDao =new RuoloDao(em);
			OrdineDao ordineDao = new OrdineDao(em);
			BusinessLogic bl = new BusinessLogic(em,utenteDao,carrelloDao,articoloDao,categoriaDao, ruoloDao, ordineDao);
			
			
			
			List<Articolo> results = bl.ultimiPerCategoria(Costanti.CAT_SPORTETEMPOLIBERO);
			
			for(Articolo x : results) {
				System.out.println(x.getNome());
			}
			
			System.out.println("ciao");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			em.close();
			System.exit(0);
		}

	}
}
