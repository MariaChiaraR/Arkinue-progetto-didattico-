package com.arcobaleno.arkinue.ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

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

public class TestImmagine 
{
	public static void main (String[] args) throws IOException, SerialException, SQLException {
		
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
			
			
			Articolo art2 = new Articolo("Action Man", "Giochi Preziosi", 6, "snodabile", Costanti.COL_ARANCIONE, 56.9f, 70f, 33.3f, cat7);
			art2.setImmagine(caricaImg("3.jpg"));
			art2.setNomeImmagine("3.jpg");
			bl.createArticolo(art2);
			
			
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
	
	static Blob caricaImg(String nomefile) throws IOException, SerialException, SQLException {
		String percorso = "E:\\Corso\\Java Web\\upload\\" ;
		Path path = Paths.get(percorso+nomefile);

        // Leggi tutti i byte dell'immagine
        byte[] bytes = Files.readAllBytes(path);

        // Crea un Blob        
        Blob blob = new SerialBlob(bytes);
        
        return blob;
	}
}
