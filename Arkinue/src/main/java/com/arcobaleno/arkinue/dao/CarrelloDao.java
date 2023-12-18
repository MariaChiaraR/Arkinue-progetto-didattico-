package com.arcobaleno.arkinue.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.arcobaleno.arkinue.model.Carrello;

public class CarrelloDao implements InterfaceDao<Carrello> 
{

	EntityManager em= null;
	
	public CarrelloDao(EntityManager em) 
	{
		this.em=em;
	}
	@Override
	public Carrello create(Carrello obj) 
	{
		em.persist(obj);
		return obj;
	}

	@Override
	public List<Carrello> retrive() 
	{
		return em.createQuery("select x from Carrello x",Carrello.class).getResultList();
		
	}
	
	/*public List<Carrello> retriveByArticolo(Integer id) 
	{
		return em.createQuery("select x from Carrello x where articolo_id = :id",Carrello.class).setParameter("id", id).getResultList();
		
	}*/

	@Override
	public Carrello update(Carrello obj) 
	{
		em.persist(obj);
		return obj;
	}

	@Override
	public void delete(Carrello obj) 
	{
		em.remove(obj);		
		
	}

}
