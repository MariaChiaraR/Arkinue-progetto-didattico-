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
import com.arcobaleno.arkinue.model.Categoria;
import com.arcobaleno.arkinue.model.Utente;
import com.arcobaleno.arkinue.utility.Costanti;



public class TestCreaArticoli {

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
					
			Categoria cat1 = bl.retrieveCategoria(Costanti.CAT_ALIMENTARIEBEVANDE);
			Categoria cat2 = bl.retrieveCategoria(Costanti.CAT_DISPOSITIVIEDELETTRONICA);
			Categoria cat3 = bl.retrieveCategoria(Costanti.CAT_ELETTRODOMESTICI);
			Categoria cat4 = bl.retrieveCategoria(Costanti.CAT_FAIDATE);
			Categoria cat5 = bl.retrieveCategoria(Costanti.CAT_MODAEBELLEZZA);
			Categoria cat6 = bl.retrieveCategoria(Costanti.CAT_PERLACASA);
			Categoria cat7 = bl.retrieveCategoria(Costanti.CAT_SPORTETEMPOLIBERO);
			
			Articolo art2 = bl.createArticolo(new Articolo("Action Man", "Giochi Preziosi", 6, "snodabile", Costanti.COL_ARANCIONE, 56.9f, 70f, 33.3f, cat7));
			Articolo art3 = bl.createArticolo(new Articolo("Mirko", "memole", 6,"bambolotto", Costanti.COL_ARANCIONE, 56.9f, 70f, 33.3f, cat7));
			Articolo art4 = bl.createArticolo(new Articolo("Teddy Bear", "Giochi Preziosi", 6, "peluche", Costanti.COL_MARRONE, 56.9f, 70f, 33.3f, cat7));
			Articolo art5 = bl.createArticolo(new Articolo("Camo Milla", "Giochi Preziosi", 6,"bambola neonata",Costanti.COL_ARANCIONE, 56.9f, 70f, 33.3f, cat7));
			Articolo art6 = bl.createArticolo(new Articolo("Kratos", "bandai", 6,"action figure",Costanti.COL_ARANCIONE, 56.9f, 70f, 33.3f, cat7));
			Articolo a1 = bl.createArticolo(new Articolo("Action Man", "gig nico", 4, "snodabile", Costanti.COL_BLU, 34f, 35f, 56f, cat7));
			Articolo a2 = bl.createArticolo(new Articolo("Succo pesca", "skipper", 55, "bibita frutta", Costanti.COL_ARANCIONE, 34f, 35f, 56f, cat1));
			Articolo a3 = bl.createArticolo(new Articolo("Gelatina di frutta", "valsoia", 0, "Buona quanto dannosa per l'intestino", Costanti.COL_VERDE, 34f, 35f, 56f, cat1));
			Articolo b1 = bl.createArticolo(new Articolo("Panino al tonno", "Piero il salumiere", 55, "Panino della panineria da Piero qua sotto", Costanti.COL_ARANCIONE, 34f, 35f, 56f, cat1));
			Articolo b2 = bl.createArticolo(new Articolo("Polpa mela", "valfrutta", 0, "polpa di frutta", Costanti.COL_VERDE, 34f, 35f, 56f, cat1));
			Articolo a4 = bl.createArticolo(new Articolo("Poco X3", "xiaomi", 1, "smartphone economico", Costanti.COL_BLU, 34f, 35f, 56f, cat2));	
			Articolo b4 = bl.createArticolo(new Articolo("Ichusa", "eCig", 4, "sigaretta elettronica ", Costanti.COL_NERO, 34f, 35f, 56f, cat2));	
			Articolo b3 = bl.createArticolo(new Articolo("Tanto X3", "xiaomi", 1, "smartphone Costoso", Costanti.COL_BLU, 34f, 35f, 56f, cat2));	
			Articolo b6 = bl.createArticolo(new Articolo("Iaperta", "eCig", 4, "sigaretta a benzina ", Costanti.COL_NERO, 34f, 35f, 56f, cat2));
			Articolo a5 = bl.createArticolo(new Articolo("Typhoon 7", "Hoover", 2, "aspirapolvere a batteria", Costanti.COL_VERDE, 34f, 35f, 56f, cat3));
			Articolo a6 = bl.createArticolo(new Articolo("LCD 22 inch", "Panasonic", 4, "televisore smart", Costanti.COL_NERO, 22f, 35f, 56f, cat3));
			Articolo b9 = bl.createArticolo(new Articolo("Uragano forza 7", "Hoover", 2, "aspirapolvere un poco troppo potente", Costanti.COL_VERDE, 34f, 35f, 56f, cat3));
			Articolo c1 = bl.createArticolo(new Articolo("LCD 22 pollici", "Pannasonic", 4, "televisore poco smart", Costanti.COL_NERO, 22f, 35f, 56f, cat3));
			Articolo a7 = bl.createArticolo(new Articolo("balsa 16 lastre", "leroymerlin", 67, "materiale bricolage", Costanti.COL_MARRONE, 34f, 35f, 56f, cat4));
			Articolo a8 = bl.createArticolo(new Articolo("scrivania ebano", "foppa", 4, "mobile da ufficio", Costanti.COL_NERO, 34f, 35f, 56f, cat4));
			Articolo c6 = bl.createArticolo(new Articolo("Martello", "Martello", 67, "Martello", Costanti.COL_MARRONE, 34f, 35f, 56f, cat4));
			Articolo c3 = bl.createArticolo(new Articolo("leggivania ebano", "poffa", 4, "mobile da casa", Costanti.COL_NERO, 34f, 35f, 56f, cat4));
			Articolo a9 = bl.createArticolo(new Articolo("crema mani", "neutrogena", 13, "crema idratante", Costanti.COL_BIANCO, 1f, 10f, 1f, cat5));
			Articolo a0 = bl.createArticolo(new Articolo("aquacolor", "sfiesta", 4, "trucco contatto viso", Costanti.COL_ARANCIONE, 1f, 10f, 1f, cat5));
			Articolo c9 = bl.createArticolo(new Articolo("crema piedi", "neutrogena", 13, "crema seccante, in senso che ti da fastidio", Costanti.COL_BIANCO, 1f, 10f, 1f, cat5));
			Articolo d2 = bl.createArticolo(new Articolo("Lapide di bellezza", "nofiesta", 4, "Indicata per figuranti in film di tim burton", Costanti.COL_ARANCIONE, 1f, 10f, 1f, cat5));
			Articolo ab = bl.createArticolo(new Articolo("tende", "tempotest", 8, "tende opache", Costanti.COL_ROSSO, 34f, 35f, 56f, cat6));
			Articolo ac = bl.createArticolo(new Articolo("candeggina", "ACE", 78, "formula delicata", Costanti.COL_BIANCO, 34f, 35f, 56f, cat6));
			Articolo al = bl.createArticolo(new Articolo("tende che sfidano la logica", "tempoprova", 8, "tende trasparenti", Costanti.COL_ROSSO, 34f, 35f, 56f, cat6));
			Articolo ar = bl.createArticolo(new Articolo("gina", "ACE", 78, "persona delicata, squisita", Costanti.COL_BIANCO, 34f, 35f, 56f, cat6));
			
			
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


