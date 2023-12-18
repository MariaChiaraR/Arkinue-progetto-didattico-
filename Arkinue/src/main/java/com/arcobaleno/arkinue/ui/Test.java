package com.arcobaleno.arkinue.ui;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.arcobaleno.arkinue.dao.UtenteDao;
import com.arcobaleno.arkinue.model.Utente;



public class Test {

	public static void main(String[] args) 
	{
		EntityManager em = null;

		try 
		{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("arkinueunit");
			em = emf.createEntityManager();

			UtenteDao utentedao = new UtenteDao(em);
			utentedao.create(new Utente("pippo","paperino"));
			
			

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


