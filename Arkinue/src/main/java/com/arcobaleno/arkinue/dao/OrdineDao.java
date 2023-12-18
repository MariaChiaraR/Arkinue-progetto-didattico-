package com.arcobaleno.arkinue.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.arcobaleno.arkinue.model.Ordine;
import com.arcobaleno.arkinue.model.Utente;

public class OrdineDao implements InterfaceDao<Ordine>
{
	EntityManager em = null;


	public OrdineDao(EntityManager em) 
	{this.em= em;}


	@Override
	public Ordine create(Ordine obj) 
	{
		em.persist(obj);
		return obj;
	}

	@Override
	public List<Ordine> retrive() 
	{
		return em.createQuery("select x from ordine x",Ordine.class).getResultList();		
	}

	@Override
	public Ordine update(Ordine obj) 
	{
		return create(obj);
	}

	@Override
	public void delete(Ordine obj) 
	{	
		em.remove(obj);		
	}

	public List<Ordine> retriveByUser(int userId)
	{
		return em.createQuery("select x from Ordine x where utente_id = :u",Ordine.class).setParameter("u", userId).getResultList();	
	}

	public List<Ordine> retrievePagina(String query, int pagina) {
		return em.createQuery(query,Ordine.class)
				.setFirstResult(7 * pagina).setMaxResults(7).getResultList();
	}

	public List<Ordine> retrieveByQuery(String query) {
		return em.createQuery(query,Ordine.class).getResultList();
	}

	public List<Ordine> retriveByArticolo(int id) {
		return em.createQuery("select x from Ordine x where idArticolo = :u",Ordine.class).setParameter("u", id).getResultList();
	}
}
