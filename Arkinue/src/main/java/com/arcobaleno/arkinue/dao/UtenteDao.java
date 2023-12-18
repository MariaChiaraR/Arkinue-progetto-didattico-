package com.arcobaleno.arkinue.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.arcobaleno.arkinue.model.Utente;

public class UtenteDao implements InterfaceDao<Utente> {

	EntityManager em= null;

	public UtenteDao(EntityManager em) 
	{
		this.em=em;
	}
	@Override
	public Utente create(Utente obj) 
	{
		em.persist(obj);
		return obj;
	}

	@Override
	public List<Utente> retrive() 
	{
		return em.createQuery("select x from Utente x",Utente.class).getResultList();

	}

	public List<Utente> retriveByUsername(String username) 
	{
		return em.createQuery("select x from Utente x where x.username=:username",Utente.class).setParameter("username", username).getResultList();		
	}

	public List<Utente> retriveByQuery(String query) {
		return em.createQuery(query, Utente.class).getResultList() ;
	}
	
	public List<Utente> retrivePagina(String query, int pagina) {
		return em.createQuery(query, Utente.class).setFirstResult(7 * pagina).setMaxResults(7).getResultList() ;
	}

	@Override
	public Utente update(Utente obj) 
	{
		em.persist(obj);
		return obj;
	}

	@Override
	public void delete(Utente obj) 
	{
		em.remove(obj);		

	}
	
	public Utente retrieveById(Integer id) {		
		return em.createQuery("select x from Utente x where x.id=:id",Utente.class).setParameter("id", id).getSingleResult();
	}

}
