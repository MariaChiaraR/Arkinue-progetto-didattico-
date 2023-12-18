package com.arcobaleno.arkinue.businessLogic;

import java.io.File;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.arcobaleno.arkinue.dao.ArticoloDao;
import com.arcobaleno.arkinue.dao.CarrelloDao;
import com.arcobaleno.arkinue.dao.CategoriaDao;
import com.arcobaleno.arkinue.dao.OrdineDao;
import com.arcobaleno.arkinue.dao.RuoloDao;
import com.arcobaleno.arkinue.dao.UtenteDao;
import com.arcobaleno.arkinue.model.Articolo;
import com.arcobaleno.arkinue.model.Carrello;
import com.arcobaleno.arkinue.model.Categoria;
import com.arcobaleno.arkinue.model.ContenitorediListe;
import com.arcobaleno.arkinue.model.Ordine;
import com.arcobaleno.arkinue.model.Ruolo;
import com.arcobaleno.arkinue.model.Utente;
import com.arcobaleno.arkinue.utility.BlobConverter;
import com.arcobaleno.arkinue.utility.Costanti;
import com.arcobaleno.arkinue.utility.EmailSender;
import com.arcobaleno.arkinue.utility.Utility;

import jakarta.servlet.http.HttpServletRequest;

public class BusinessLogic 
{
	EntityManager em;

	UtenteDao utenteDao;
	CarrelloDao carrelloDao;
	ArticoloDao articoloDao;
	CategoriaDao categoriaDao;
	RuoloDao ruoloDao;
	OrdineDao ordineDao;

	public BusinessLogic( EntityManager em, UtenteDao utenteDao, CarrelloDao carrelloDao, ArticoloDao articoloDao, CategoriaDao categoriaDao, RuoloDao ruoloDao, OrdineDao ordineDao) 
	{
		this.em = em;
		this.utenteDao = utenteDao;
		this.carrelloDao = carrelloDao;
		this.articoloDao = articoloDao;
		this.categoriaDao = categoriaDao;
		this.ruoloDao = ruoloDao;
		this.ordineDao = ordineDao;
	}

	public void createRuoli() 
	{
		try 
		{
			em.getTransaction().begin();
			if(ruoloDao.retrive().size() == 0) 
			{
				ruoloDao.create(new Ruolo(Costanti.RUOLO_UTENTE));
				ruoloDao.create(new Ruolo(Costanti.RUOLO_ADMIN));
			}
			em.getTransaction().commit();
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}

	}

	public Utente createUtente(Utente utente)
	{
		try 
		{
			em.getTransaction().begin();
			utente.setRuolo(ruoloDao.retriveByName(Costanti.RUOLO_UTENTE));
			utente.setCarrello(carrelloDao.create(new Carrello()));
			utenteDao.create(utente);
			em.getTransaction().commit();
			return utente;
		} 
		catch (PersistenceException e1) 
		{
			PersistenceException e2 = new PersistenceException("Nome utente già presente");
			em.getTransaction().rollback();
			throw e2;

		}
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}

	}

	public void createFirstAdmin()
	{
		try 
		{
			em.getTransaction().begin();
			Utente admin;
			if (utenteDao.retriveByUsername ("admin").size()==0) {
				admin = new Utente("admin", "admin");
				admin.setRuolo(ruoloDao.retriveByName(Costanti.RUOLO_ADMIN));
				admin.setCarrello(carrelloDao.create(new Carrello()));
				utenteDao.create(admin);
			}
			em.getTransaction().commit();
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}

	}

	public Utente promuoviAdAdmin(Utente utente)
	{
		try 
		{
			em.getTransaction().begin();
			utente.setRuolo(ruoloDao.retriveByName(Costanti.RUOLO_ADMIN));
			utenteDao.create(utente);
			em.getTransaction().commit();
			return utente;
		} 
		catch (PersistenceException e1) 
		{
			PersistenceException e2 = new PersistenceException("Nome utente già presente");
			em.getTransaction().rollback();
			throw e2;

		}
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}

	}

	public Utente demotaAdUser(Utente utente)
	{
		try 
		{
			em.getTransaction().begin();
			utente.setRuolo(ruoloDao.retriveByName(Costanti.RUOLO_UTENTE));
			utenteDao.update(utente);
			em.getTransaction().commit();
			return utente;
		} 
		catch (PersistenceException e1) 
		{
			PersistenceException e2 = new PersistenceException("Nome utente già presente");
			em.getTransaction().rollback();
			throw e2;

		}
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}

	}

	public Articolo retrieveArticoloById(Integer id) {
		try 
		{
			em.getTransaction().begin();
			Articolo art = articoloDao.retriveById(id);
			em.getTransaction().commit();
			return art;
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;

		}
	}


	public List<Articolo> retrieveTuttiArticoli()
	{
		try 
		{
			em.getTransaction().begin();
			List<Articolo> list = articoloDao.retrive();
			em.getTransaction().commit();
			return list;
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;

		}
	}

	public List<Articolo> retrieveArticoloByNome(String nome) {
		try 
		{
			em.getTransaction().begin();
			List<Articolo> list = articoloDao.retriveByNome(nome);
			em.getTransaction().commit();
			return list;
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;

		}
	}


	public List<Utente> retrievePaginaUtenti(String query, int pagina, List<Integer> totPagine) {
		try 
		{
			em.getTransaction().begin();
			
			if (totPagine.size()==0)
			{ 
				int numRisultati = utenteDao.retriveByQuery(query).size();
				totPagine.add(numRisultati == 0 ? 0 :numRisultati%7==0? (numRisultati/7)-1 : numRisultati/7);
			}
			
			List<Utente> list = utenteDao.retrivePagina(query, pagina);
			em.getTransaction().commit();
			return list;
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;

		}
	}

	public List<Utente> retrieveUtentiByQuery(String query) {
		try 
		{
			em.getTransaction().begin();
			List<Utente> list = utenteDao.retriveByQuery(query);
			em.getTransaction().commit();
			return list;
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;

		}
	}



	//metodi admin

	public Articolo createArticolo(Articolo articolo) 
	{
		try 
		{
			em.getTransaction().begin();
			articoloDao.create(articolo);
			em.getTransaction().commit();
			return articolo;
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}

	}

	public Articolo updateArticolo(Articolo articolo) 
	{
		try 
		{
			em.getTransaction().begin();
			articoloDao.create(articolo);
			em.getTransaction().commit();
			return articolo;
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}
	}

	public List<Utente> retrieveUtenti() 
	{
		try 
		{
			em.getTransaction().begin();
			List<Utente> list = utenteDao.retrive();
			em.getTransaction().commit();
			return list;

		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}

	}

	public Utente retrieveSingoloUtente(String username) 
	{
		try 
		{
			em.getTransaction().begin();
			List<Utente> utenti = utenteDao.retriveByUsername(username) ;
			Utente utente = null;
			if(utenti.size()==1) {
				utente = utenti.get(0);
			}
			em.getTransaction().commit();
			return utente;
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}

	}

	public Articolo deleteArticolo(Articolo articolo) 
	{
		try 
		{
			em.getTransaction().begin();

			articoloDao.delete(articolo);
			em.getTransaction().commit();
			return articolo;
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}
	}

	public Utente deleteUtente(Utente utente) 
	{
		try 
		{
			em.getTransaction().begin();
			carrelloDao.delete(utente.getCarrello());
			utenteDao.delete(utente);
			em.getTransaction().commit();
			return utente;
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}
	}



	public Ordine createOrdine(Ordine ordine) 
	{
		try 
		{
			em.getTransaction().begin();
			ordineDao.create(ordine);
			em.getTransaction().commit();
			return ordine;
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}

	}

	public Ordine acquisto(Utente utente, Articolo articolo) 
	{
		try {

			em.getTransaction().begin();
			if(articolo.getQuantita()>=1) 
			{
				articolo.setQuantita(articolo.getQuantita()-1);
				Ordine ordine = ordineDao.create(new Ordine(utente, articolo));
				utente.getCarrello().getArticoli().remove(articolo);
				em.getTransaction().commit();
				return ordine;
			}
			else 
			{
				em.getTransaction().commit();
				return null;
			}	
		} 
		catch (Exception e) 
		{	
			em.getTransaction().rollback();
			throw e;		
		}
	}
	public Categoria retrieveCategoria(String nome_cat) {
		try 
		{
			em.getTransaction().begin();
			Categoria cat = categoriaDao.retriveByNome(nome_cat);
			em.getTransaction().commit();
			return cat;
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}
	}

	public void aggiungiACategoria(Articolo articolo, Categoria categoria) {
		try 
		{
			em.getTransaction().begin();

			articolo.setCategoria(categoria);

			em.getTransaction().commit();
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}

	}

	public void createCategoria(Categoria categoria) {
		try 
		{
			em.getTransaction().begin();

			categoriaDao.create(categoria);

			em.getTransaction().commit();
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}

	}

	public void createAllCategorie() {
		try 
		{
			em.getTransaction().begin();
			if(categoriaDao.retrive().size() == 0) 
			{
				categoriaDao.create(new Categoria(Costanti.CAT_ALIMENTARIEBEVANDE));
				categoriaDao.create(new Categoria(Costanti.CAT_DISPOSITIVIEDELETTRONICA));
				categoriaDao.create(new Categoria(Costanti.CAT_ELETTRODOMESTICI));
				categoriaDao.create(new Categoria(Costanti.CAT_FAIDATE));
				categoriaDao.create(new Categoria(Costanti.CAT_MODAEBELLEZZA));
				categoriaDao.create(new Categoria(Costanti.CAT_PERLACASA));
				categoriaDao.create(new Categoria(Costanti.CAT_SPORTETEMPOLIBERO));
			}
			em.getTransaction().commit();
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}

	}

	public List<String> recuperaCategorieRecenti(Utente user){
		try {
			List<String> cate = new ArrayList<String>() ;
			List<Ordine> ordini = utenteDao.retrieveById(user.getId()).getOrdini();
			String nometemp;
			for(int i=ordini.size(); i>0; i--) {
				nometemp = ordini.get(i-1).getNomeCategoria() ;
				if(!cate.contains(nometemp)) {
					cate.add(nometemp) ;
					if (cate.size()>=4) {
						return cate;
					}
				} 
			}
			return cate;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}


	public List<Articolo> ultimiPerCategoria(String nomeCategoria){
		return articoloDao.articoliRecenti(nomeCategoria);
	}

	public List<Ordine> retrievePaginaOrdine(HttpServletRequest req,String username, String nome, String categoria, int scelta, int pagina, List<Integer> totPagine) throws Exception {
		try 
		{
			em.getTransaction().begin();
			String querybase = "select x from Ordine x where " ;
			String query = querybase+"nomeArticolo like '%"+nome+"%' and username like '%"+username+"%' and nomeCategoria like '%"+categoria+"%' ";
			switch(scelta) {
			case 0:
				query += "order by id desc ";
				break;
			case 1:
				break;
			default:
				break; 
			}
			
			if (totPagine.size()==0)
			{ 
				int numRisultati = ordineDao.retrieveByQuery(query).size();
				totPagine.add(numRisultati == 0 ? 0 :numRisultati%7==0? (numRisultati/7)-1 : numRisultati/7);
			}
			
			List<Ordine> ordini = ordineDao.retrievePagina(query, pagina);
			mappaOrdini(req, ordini);
			em.getTransaction().commit();				
			return ordini;
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}
	}
	
	public List<Ordine> retrieveOrdiniByQuery(String username, String nome, String categoria, int scelta) {
		try 
		{
			em.getTransaction().begin();
			String querybase = "select x from Ordine x where " ;
			String query = querybase+"nomeArticolo like '%"+nome+"%' and username like '%"+username+"%' and nomeCategoria like '%"+categoria+"%' ";
			switch(scelta) {
			case 0:
				query += "order by id desc ";
				break;
			case 1:
				break;
			default:
				break; 
			}
			List<Ordine> ordini = ordineDao.retrieveByQuery(query); ;
			em.getTransaction().commit();				
			return ordini;
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}
	}
	
	public List<Ordine> retrieveOrdineByUtente(int userId) 
	{
		try 
		{
			em.getTransaction().begin();
			List<Ordine> ordini = ordineDao.retriveByUser(userId) ;
			em.getTransaction().commit();
			return ordini;
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}

	}

	public Utente retrieveUtenteById(Integer id) {
		try 
		{
			em.getTransaction().begin();
			Utente user = utenteDao.retrieveById(id) ;
			em.getTransaction().commit();				
			return user;
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}
	}
	
	public Utente retrieveUtenteAndList(Integer id, HttpServletRequest req) {
		try 
		{
			em.getTransaction().begin();
			Utente user = utenteDao.retrieveById(id) ;
			List<Ordine> ordini = ordineDao.retriveByUser(id);
			em.getTransaction().commit();				
			req.setAttribute(Costanti.LISTA_ORDINI, ordini);
			return user;
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}
	}
	
	public Utente retrieveUtenteETotaleById(Integer id, HttpServletRequest req) {
		try 
		{
			em.getTransaction().begin();
			Utente user = utenteDao.retrieveById(id) ;
			Float totaleSpesa = 0.0f ;
			for (Ordine ord : ordineDao.retriveByUser(id)) {
				totaleSpesa += ord.getPrezzoArticolo() ;
			}
			
			
			req.setAttribute(Costanti.CARRELLO_COMPRATO, totaleSpesa);
			em.getTransaction().commit();				
			return user;
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}
	}
	
	public Articolo aggiungiACarrello(Utente utente, Articolo articolo) 
	{
		try 
		{
			
			if(articolo.getQuantita()>=1 || !utente.getCarrello().getArticoli().contains(articolo)) 
			{
				utente.getCarrello().getArticoli().add(articolo);	
				return articolo;
			}
			else 
			{
				return null;
			}			
		} 
		catch (Exception e) 
		{
			throw e;	
		}
	}
	

	public void rimuoviDalCarrello(Utente utente, Articolo articolo) {
		try 
		{
			em.getTransaction().begin();
			utente.getCarrello().getArticoli().remove(articolo);
			em.getTransaction().commit();				
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}
	}

	public List<Articolo> retrieveArticoloAvanzata(Articolo cercato, int pagina, int categoria, List<Integer> totPagine) {
		try 
		{
			em.getTransaction().begin();
			
			if (totPagine.size()==0)
			{ 
				int numRisultati = articoloDao.retriveTotaleRecord(cercato, categoria);
				totPagine.add(numRisultati == 0 ? 0 :numRisultati%7==0? (numRisultati/7)-1 : numRisultati/7);
			}
			
			List<Articolo> list = articoloDao.retriveAvanzata(cercato, pagina, categoria);
			em.getTransaction().commit();
			return list;
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;

		}
	}

public int retrieveTotaleArticoli(Articolo cercato, int categoria) {
	try 
	{
		em.getTransaction().begin();
		int totale = articoloDao.retriveTotaleRecord(cercato, categoria);
		em.getTransaction().commit();
		return totale;
	} 
	catch (Exception e) 
	{
		em.getTransaction().rollback();
		throw e;

	}
}





//funzioni specifiche delle servlet in cui conteniamo le connessioni

public Articolo cancellaArticoloServlet(Integer id) 
{
	try 
	{ 	
		em.getTransaction().begin();
		
		Articolo art = articoloDao.retriveById(id);
		if(art == null) 
		{
			return null;
		}
		List<Carrello> carrelli = carrelloDao.retrive();
		carrelli.forEach(x -> x.getArticoli().remove(art));
		articoloDao.delete(art);
		em.getTransaction().commit();
		return art;
	} 
catch (Exception e) 
	{
		em.getTransaction().rollback();
		throw e;

	}
	
}

public Articolo carrelloServlet(Utente u,Integer id) 
{
	try 
	{ 	
		em.getTransaction().begin();		
		Articolo art = articoloDao.retriveById(id);
		if(art == null) 
		{
			return null;
		}
		Articolo art2 =aggiungiACarrello(u, art);
		if(art2!=null) 
			{
				em.getTransaction().commit();
				return art2;
			}
		else 
			{
				em.getTransaction().commit();
				return null;
			}
	} 
	catch (Exception e) 
	{
		em.getTransaction().rollback();
		throw e;

	}
	
}

public Articolo creaArticoloServlet(Articolo articolo, String categoria) 
{
	try 
	{ 	
	em.getTransaction().begin();
		
		Categoria cat = categoriaDao.retriveByNome(categoria);
		articolo.setCategoria(cat);
		articoloDao.create(articolo);
		

		em.getTransaction().commit();
		return articolo;

	
	} 
catch (Exception e) 
	{
		em.getTransaction().rollback();
		throw e;

	}
	
}

public Articolo modificaArticoloServlet(int id, String nome, String marca, Integer quantita,String colore, String descrizione, Float dimensione, Float peso, Float prezzo, String nome_cat, Blob immagine, String nomeImmagine)
	{
	try {
		em.getTransaction().begin();
		Articolo articolo = articoloDao.retriveById(id);
		articolo.setNome(nome) ;
		articolo.setMarca(marca) ; 
		articolo.setQuantita(quantita) ;
		articolo.setColore(colore);
		articolo.setDescrizione(descrizione);
		articolo.setDimensione(dimensione);
		articolo.setPeso(peso);
		articolo.setPrezzo(prezzo);
		Categoria cat = categoriaDao.retriveByNome(nome_cat);
		articolo.setCategoria(cat);
		if(immagine != null) {
		articolo.setImmagine(immagine);}
		if(nomeImmagine!= null){
		articolo.setNomeImmagine(nomeImmagine);}
		articoloDao.update(articolo);
		articolo = articoloDao.retriveById(id);
		em.getTransaction().commit();
		return articolo;
	}
	catch(Exception e)
	{
		em.getTransaction().rollback();
		throw e;
	}
	}

public Articolo ordineServlet(Integer articoloid, Utente utente) throws Exception 
	{	
		try {
			em.getTransaction().begin();
			Articolo articolo = articoloDao.retriveById(articoloid);
			if(articolo.getQuantita()>=1) 
			{
				articolo.setQuantita(articolo.getQuantita()-1);
				ordineDao.create(new Ordine(utente, articolo));
				utente.getCarrello().getArticoli().remove(articolo);
				em.getTransaction().commit();
				return articolo;
			}
			else 
			{
				em.getTransaction().commit();
				return null;
			}	
		}
		catch(Exception e)
		{
			em.getTransaction().rollback();
			throw e;
		}
	}


public Articolo rimuoviDaCarrelloServlet(Integer articoloid, Utente utente) 
	{	
		try {
			em.getTransaction().begin();
			Articolo articolo = articoloDao.retriveById(articoloid);
			utente.getCarrello().getArticoli().remove(articolo);
			em.getTransaction().commit();
			return articolo;
		}
		catch(Exception e)
		{
			em.getTransaction().rollback();
			throw e;
		}
	}

public ContenitorediListe homeServlet(HttpServletRequest req, Utente utente,List<Articolo> arcobalista1,List<Articolo> arcobalista2,List<Articolo> arcobalista3,List<Articolo> arcobalista4,
		List<Articolo> arcobalista5,List<Articolo> arcobalista6,List<Articolo> arcobalista7,
		List<Articolo> lista1, List<Articolo> lista2,List<Articolo> lista3,List<Articolo> lista4) throws Exception 
{
	try {
		em.getTransaction().begin();
		//nel caso si ripresenti questa situazione
		/*
		 * List<Articolo> temp = ultimiperCategoria(Costanti.CAT_ALIMENTARIEBEVANDE);
		 * arcobalista1.addAll(temp);
		 * */
		arcobalista1 = ultimiPerCategoria(Costanti.CAT_ALIMENTARIEBEVANDE);
		arcobalista2 = ultimiPerCategoria(Costanti.CAT_DISPOSITIVIEDELETTRONICA);
		arcobalista3 = ultimiPerCategoria(Costanti.CAT_ELETTRODOMESTICI);
		arcobalista4 = ultimiPerCategoria(Costanti.CAT_FAIDATE);
		arcobalista5 = ultimiPerCategoria(Costanti.CAT_MODAEBELLEZZA);
		arcobalista6 = ultimiPerCategoria(Costanti.CAT_PERLACASA);
		arcobalista7 = ultimiPerCategoria(Costanti.CAT_SPORTETEMPOLIBERO);
		
		if(utente!=null) 
		{
			List<String>   lista = recuperaCategorieRecenti(utente);
			
			if(lista.size()==4) 
			{
				lista1 = ultimiPerCategoria(lista.get(0));
				lista2 = ultimiPerCategoria(lista.get(1));
				lista3 = ultimiPerCategoria(lista.get(2));
				lista4 = ultimiPerCategoria(lista.get(3));
			}
			else 
			{
				lista1 = ultimiPerCategoria(Costanti.CAT_DISPOSITIVIEDELETTRONICA);
				lista2 = ultimiPerCategoria(Costanti.CAT_ELETTRODOMESTICI);
				lista3 = ultimiPerCategoria(Costanti.CAT_SPORTETEMPOLIBERO);
				lista4 = ultimiPerCategoria(Costanti.CAT_FAIDATE);
			} 
		}
		else 
		{
			lista1 = ultimiPerCategoria(Costanti.CAT_DISPOSITIVIEDELETTRONICA);
			lista2 = ultimiPerCategoria(Costanti.CAT_ELETTRODOMESTICI);
			lista3 = ultimiPerCategoria(Costanti.CAT_SPORTETEMPOLIBERO);
			lista4 = ultimiPerCategoria(Costanti.CAT_FAIDATE);
		}
		List<Articolo> listaarticoli = articoloDao.retrive();
		Utility.mappaImmagini(req, listaarticoli);
		em.getTransaction().commit();
		ContenitorediListe cont = new ContenitorediListe(lista1, lista2, lista3, lista4, arcobalista1, arcobalista2, arcobalista3, arcobalista4, arcobalista5, arcobalista6, arcobalista7);
		return cont;
	} 
	catch (Exception e) 
	{;
		em.getTransaction().rollback();
		e.printStackTrace();
		throw e;
	}

}


public void createArticoliIniziali(String uploadPath) throws Exception {
	try {
		
		if(articoloDao.retrive() == null || articoloDao.retrive().size() <1) 
		{
			Categoria cat1 = categoriaDao.retriveByNome(Costanti.CAT_ALIMENTARIEBEVANDE);
			Categoria cat2 = categoriaDao.retriveByNome(Costanti.CAT_DISPOSITIVIEDELETTRONICA);
			Categoria cat3 = categoriaDao.retriveByNome(Costanti.CAT_ELETTRODOMESTICI);
			Categoria cat4 = categoriaDao.retriveByNome(Costanti.CAT_FAIDATE);
			Categoria cat5 = categoriaDao.retriveByNome(Costanti.CAT_MODAEBELLEZZA);
			Categoria cat6 = categoriaDao.retriveByNome(Costanti.CAT_PERLACASA);
			Categoria cat7 = categoriaDao.retriveByNome(Costanti.CAT_SPORTETEMPOLIBERO);
			
			Articolo art2 = articoloDao.create(new Articolo("Action Man 2", "Giochi Preziosi", 6, Costanti.COL_ARANCIONE, 56.9f, 70f, 30f, "snodabile", BlobConverter.generateBlob(uploadPath+File.separator+"3.jpg"), "3.jpg", cat7));
			Articolo art1 = articoloDao.create(new Articolo("Action Man", "Giochi Preziosi", 6, Costanti.COL_ARANCIONE, 56.9f, 70f, 30f, "snodabile", BlobConverter.generateBlob(uploadPath+File.separator+"3.jpg"),"3.jpg", cat7));
			Articolo art3 = articoloDao.create(new Articolo("Mirko", "memole", 6, Costanti.COL_ARANCIONE, 56.9f, 70f, 33.3f,"bambolotto", Utility.convertiImgOffline(uploadPath, "memole.jpg"),"memole.jpg", cat7));
			Articolo art4 = articoloDao.create(new Articolo("Teddy Bear", "Giochi Preziosi", 6, "peluche", Costanti.COL_MARRONE, 56.9f, 70f, 33.3f, BlobConverter.generateBlob(uploadPath+File.separator+"teddy.jpg"),"teddy.jpg",cat7));
			Articolo art5 = articoloDao.create(new Articolo("Camo Milla", "Giochi Preziosi", 6,"bambola neonata",Costanti.COL_ARANCIONE, 56.9f, 70f, 33.3f,BlobConverter.generateBlob(uploadPath+File.separator+"0.jpg"),"0.jpg", cat7));
			Articolo art6 = articoloDao.create(new Articolo("Kratos", "bandai", 6,"action figure",Costanti.COL_ARANCIONE, 56.9f, 70f, 33.3f,BlobConverter.generateBlob(uploadPath+File.separator+"5.jpg"),"5.jpg", cat7));
			Articolo a1 = articoloDao.create( new Articolo("Action Man3", "gig nico", 4, "snodabile", Costanti.COL_BLU, 34f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"3.jpg"),"3.jpg", cat7));
			Articolo a2 = articoloDao.create(new Articolo("Succo pesca", "skipper", 55, "bibita frutta", Costanti.COL_ARANCIONE, 34f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"pesca.jpg"),"pesca.jpg", cat1));
			Articolo a3 = articoloDao.create(new Articolo("Gelatina di frutta", "valsoia", 0, "Buona quanto dannosa per l'intestino", Costanti.COL_VERDE, 34f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"gelfrutta.jpg"),"gelfrutta.jpg", cat1));
			Articolo b1 = articoloDao.create(new Articolo("Panino al tonno", "Piero il salumiere", 55, "Panino della panineria da Piero qua sotto", Costanti.COL_ARANCIONE, 34f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"panino.jpg"),"panino.jpg", cat1));
			Articolo b2 = articoloDao.create(new Articolo("Polpa mela", "valfrutta", 0, "polpa di frutta", Costanti.COL_VERDE, 34f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"polpamela.jpg"),"polpamela.jpg", cat1));
			Articolo a4 = articoloDao.create(new Articolo("Poco X3", "xiaomi", 1, "smartphone economico", Costanti.COL_BLU, 34f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"pocox3.jpg"),"pocox3.jpg", cat2));	
			Articolo b4 = articoloDao.create(new Articolo("Ichusa", "eCig", 4, "sigaretta elettronica ", Costanti.COL_NERO, 34f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"ecig.jpg"),"ecig.jpg", cat2));	
			Articolo b3 = articoloDao.create(new Articolo("Tanto X3", "xiaomi", 1, "smartphone Costoso", Costanti.COL_BLU, 34f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"0.jpg"),"0.jpg", cat2));	
			Articolo b6 = articoloDao.create(new Articolo("Iaperta", "eCig", 4, "sigaretta a benzina ", Costanti.COL_NERO, 34f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"ecig2.jpg"),"ecig2.jpg", cat2));
			Articolo a5 = articoloDao.create(new Articolo("Typhoon 7", "Hoover", 2, "aspirapolvere a batteria", Costanti.COL_VERDE, 34f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"aspira.jpg"),"aspira.jpg", cat3));
			Articolo a6 = articoloDao.create(new Articolo("LCD 22 inch", "Panasonic", 4, "televisore smart", Costanti.COL_NERO, 22f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"lcd.jpg"),"lcd.jpg", cat3));
			Articolo b9 = articoloDao.create(new Articolo("Uragano forza 7", "Hoover", 2, "aspirapolvere un poco troppo potente", Costanti.COL_VERDE, 34f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"aspira2.jpg"),"aspira2.jpg", cat3));
			Articolo c1 = articoloDao.create(new Articolo("LCD 22 pollici", "Pannasonic", 4, "televisore poco smart", Costanti.COL_NERO, 22f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"tv.jpg"),"tv.jpg", cat3));
			Articolo a7 = articoloDao.create(new Articolo("balsa 16 lastre", "leroymerlin", 67, "materiale bricolage", Costanti.COL_MARRONE, 34f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"balsa.jpg"),"balsa.jpg", cat4));
			Articolo a8 = articoloDao.create(new Articolo("scrivania ebano", "foppa", 4, "mobile da ufficio", Costanti.COL_NERO, 34f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"scrivania.jpg"),"scrivania.jpg", cat4));
			Articolo c6 = articoloDao.create(new Articolo("Martello", "Martello", 67, "Martello", Costanti.COL_MARRONE, 34f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"martello.jpg"),"martello.jpg", cat4));
			Articolo c3 = articoloDao.create(new Articolo("leggivania ebano", "poffa", 4, "mobile da casa", Costanti.COL_NERO, 34f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"leggio.jpg"),"leggio.jpg", cat4));
			Articolo a9 = articoloDao.create(new Articolo("crema mani", "neutrogena", 13, "crema idratante", Costanti.COL_BIANCO, 1f, 10f, 1f,BlobConverter.generateBlob(uploadPath+File.separator+"cremamani.jpg"),"cremamani.jpg", cat5));
			Articolo a0 = articoloDao.create(new Articolo("aquacolor", "sfiesta", 4, "trucco contatto viso", Costanti.COL_ARANCIONE, 1f, 10f, 1f,BlobConverter.generateBlob(uploadPath+File.separator+"aqua.jpg"),"aqua.jpg", cat5));
			Articolo c9 = articoloDao.create(new Articolo("crema piedi", "neutrogena", 13, "crema seccante, in senso che ti da fastidio", Costanti.COL_BIANCO, 1f, 10f, 1f,BlobConverter.generateBlob(uploadPath+File.separator+"crema.jpg"),"crema.jpg", cat5));
			Articolo d2 = articoloDao.create(new Articolo("Lapide di bellezza", "nofiesta", 4, "Indicata per figuranti in film di tim burton", Costanti.COL_ARANCIONE, 1f, 10f, 1f,BlobConverter.generateBlob(uploadPath+File.separator+"lapide.jpg"),"lapide.jpg", cat5));
			Articolo ab = articoloDao.create(new Articolo("tende", "tempotest", 8, "tende opache", Costanti.COL_ROSSO, 34f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"tenda.jpg"),"tenda.jpg", cat6));
			Articolo ac = articoloDao.create(new Articolo("candeggina", "ACE", 78, "formula delicata", Costanti.COL_BIANCO, 34f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"14.jpg"),"14.jpg", cat6));
			Articolo al = articoloDao.create(new Articolo("tende che sfidano la logica", "tempoprova", 8, "tende trasparenti", Costanti.COL_ROSSO, 34f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"tende.jpg"),"tende.jpg", cat6));
			Articolo ar = articoloDao.create(new Articolo("gina", "ACE", 78, "persona delicata, squisita", Costanti.COL_BIANCO, 34f, 35f, 56f,BlobConverter.generateBlob(uploadPath+File.separator+"gina.jpg"),"gina.jpg", cat6));}

	} 
	catch (Exception e) 
	{

		throw e;
	}
	
}

public void mappaOrdini(HttpServletRequest req, List<Ordine> ordini) throws Exception 
{
	try {
		Map<Integer, String> mappaImmagini = new HashMap<>();
		String uploadPath = req.getServletContext().getRealPath("")+File.separator+ Costanti.CARTELLA_APPOGGIO;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		String baseHTTPUrl = "Http://"+req.getServerName()+":"+ req.getServerPort()+req.getContextPath();
		for(Ordine ord: ordini) 
		{	
			Articolo art = articoloDao.retriveById(ord.getIdArticolo());
			Blob immagine = art.getImmagine();
			if(immagine != null) 
			{
				String filePath = uploadPath+File.separator+art.getId()+"_"+art.getNomeImmagine();
				BlobConverter.saveFile(immagine, filePath);
				mappaImmagini.put(art.getId(), baseHTTPUrl+"/"+Costanti.CARTELLA_APPOGGIO+"/"+art.getId()+"_"+art.getNomeImmagine());
			}
			else 
			{
				mappaImmagini.put(art.getId(), baseHTTPUrl+"/media/placeholder.jpg");
			}
		}	
		req.setAttribute(Costanti.MAPPAIMMAGINI, mappaImmagini);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void initServlet(String uploadPath) throws Exception 
	{
	try {
		
		em.getTransaction().begin();
		if(ruoloDao.retrive().size() == 0) 
		{
			ruoloDao.create(new Ruolo(Costanti.RUOLO_UTENTE));
			ruoloDao.create(new Ruolo(Costanti.RUOLO_ADMIN));
		}
		
		Utente admin;
		if (utenteDao.retriveByUsername ("admin").size()==0) 
		{
			admin = new Utente("admin", "admin");
			admin.setRuolo(ruoloDao.retriveByName(Costanti.RUOLO_ADMIN));
			admin.setCarrello(carrelloDao.create(new Carrello()));
			utenteDao.create(admin);
		}
		
		if(categoriaDao.retrive().size() == 0)
		{
		categoriaDao.create(new Categoria(Costanti.CAT_ALIMENTARIEBEVANDE));
		categoriaDao.create(new Categoria(Costanti.CAT_DISPOSITIVIEDELETTRONICA));
		categoriaDao.create(new Categoria(Costanti.CAT_ELETTRODOMESTICI));
		categoriaDao.create(new Categoria(Costanti.CAT_FAIDATE));
		categoriaDao.create(new Categoria(Costanti.CAT_MODAEBELLEZZA));
		categoriaDao.create(new Categoria(Costanti.CAT_PERLACASA));
		categoriaDao.create(new Categoria(Costanti.CAT_SPORTETEMPOLIBERO));
		}	
		
		if(articoloDao.retrive().size()== 0) 
		{
			createArticoliIniziali(uploadPath);
		}
		em.getTransaction().commit();
	} 
	catch (Exception e) 
	{
		em.getTransaction().rollback();
		e.printStackTrace();
	}
	

	}

	public ContenitorediListe visualizzaOrdiniServlet(int userId, int pagina, HttpServletRequest req) throws Exception
	{
		try 
		{
			em.getTransaction().begin();
			List<Ordine> ordini = ordineDao.retriveByUser(userId) ;;
			int numOrdini = ordini.size();
			
			int totPagine = numOrdini == 0 ? 0 :numOrdini%7==0? (numOrdini/7)-1 : numOrdini/7;
			
			List<Ordine> ordiniPagina = new ArrayList<Ordine>();
			int indicePartenza = numOrdini - pagina*7;
			int indiceArrivo = pagina == totPagine ? 0 : numOrdini - (pagina+1)*7;
			
			while ( indicePartenza != indiceArrivo)
			{
				ordiniPagina.add(ordini.get((indicePartenza--)-1));
			}
			
			
			//decidere come chiamare la businesslogic 	
			mappaOrdini(req, ordiniPagina);
			ContenitorediListe cont = new ContenitorediListe(null, null, null, null, null, null, null, null, null, null, null);
			cont.setGenerico1(pagina);
			cont.setGenerico2(totPagine);
			cont.setOrdinelista1(ordini);
			
			em.getTransaction().commit();
			return cont;
		}
		catch(Exception e) 
		{
			em.getTransaction().rollback();
			throw e;
		}
	}
	
	public Articolo retrieveArticoloETotaleById(int id, HttpServletRequest req) {
		try 
		{
			em.getTransaction().begin();
			Articolo art = articoloDao.retriveById(id);
			
			Integer totOrdini = 0;
			totOrdini += ordineDao.retriveByArticolo(id).size() ;
			
			em.getTransaction().commit();
			req.setAttribute(Costanti.CARRELLO_COMPRATO, totOrdini);
			return art;
		} 
		catch (Exception e) 
		{
			em.getTransaction().rollback();
			throw e;

		}
	}
	
	public Articolo creaArticolo(String nome, String marca, Integer quantita, String descrizione, String colore, Float dimensione, Float peso, Float prezzo, String nome_cat, Blob immagine, String nomeImmagine) 
	{
		try 
		{
			em.getTransaction().begin();
			
			
			List<Articolo> articoli = articoloDao.retriveByNome(nome);
			if(articoli.size()>0) 
			{
				em.getTransaction().commit();
				return null;				
			}
			Categoria cat = categoriaDao.retriveByNome(nome_cat);
			Articolo articolo = new Articolo(nome, marca, quantita, descrizione, colore, dimensione, peso, prezzo, cat);
			
			articolo.setImmagine(immagine);
			articolo.setNomeImmagine(nomeImmagine);
			
			articoloDao.create(articolo);
			
			em.getTransaction().commit();
			return articolo;
		
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}

	}
	
	
}