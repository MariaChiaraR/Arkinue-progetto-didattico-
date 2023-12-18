package com.arcobaleno.arkinue.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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



public class TestCreaUtenti {

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
			
// bl.createUtente(new Utente("edgar", "magico"));
//	bl.createUtente(new Utente("covir", "magico"));
// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") ;
// LocalDate date = LocalDate.parse(Costanti.DATANASCITA, DateTimeFormatter.ofPattern(Costanti.DATE_PATTERN)) ;
			
			LocalDate date1 = LocalDate.parse("1991-01-01", DateTimeFormatter.ofPattern(Costanti.DATE_PATTERN)) ;
			LocalDate date2 = LocalDate.parse("1992-02-02", DateTimeFormatter.ofPattern(Costanti.DATE_PATTERN)) ;
			LocalDate date3 = LocalDate.parse("1993-03-11", DateTimeFormatter.ofPattern(Costanti.DATE_PATTERN)) ;
			LocalDate date4 = LocalDate.parse("1994-06-09", DateTimeFormatter.ofPattern(Costanti.DATE_PATTERN)) ;
			LocalDate date5 = LocalDate.parse("1995-11-21", DateTimeFormatter.ofPattern(Costanti.DATE_PATTERN)) ;
			LocalDate date6 = LocalDate.parse("1996-09-01", DateTimeFormatter.ofPattern(Costanti.DATE_PATTERN)) ;
			LocalDate date7 = LocalDate.parse("1997-04-30", DateTimeFormatter.ofPattern(Costanti.DATE_PATTERN)) ;
			LocalDate date8 = LocalDate.parse("1998-06-07", DateTimeFormatter.ofPattern(Costanti.DATE_PATTERN)) ;
			LocalDate date9 = LocalDate.parse("1999-01-09", DateTimeFormatter.ofPattern(Costanti.DATE_PATTERN)) ;
			
			bl.createUtente(new Utente("megagir", "recmandu", "bobi@bobi.com", "Magi", "Megi", "via mGR, 3", date1)) ;
			bl.createUtente(new Utente("covir","Password0!","mimi@gmail.com","Marco","Marchi","via Culla, 4", date2));
			bl.createUtente(new Utente("megalit","Password0!","mega@gmail.com","Claudio","Claudi","via macca, 7", date3));
			bl.createUtente(new Utente("baudino","Password0!","pippo@gmail.com","Mirko","Mirki","via Culla, 21", date4));
			bl.createUtente(new Utente("twopast","Password0!","chuchu@gmail.com","Nuco","Nuci","via Vittoria, 65", date5));
			bl.createUtente(new Utente("miracolo","Password0!","mira@gmail.com","Bino","Bini","via beethoven, 76", date6));
			bl.createUtente(new Utente("mammolo","Password0!","mammo@gmail.com","Tino","Tini","via mozart, 2", date7));
			bl.createUtente(new Utente("pisolo","Password0!","piso@gmail.com","Marco","Marchi","via edison, 11", date8));
			bl.createUtente(new Utente("brontolo","Password0!","bronto@gmail.com","Bruno","Bruni","via intel, 33", date9));

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


