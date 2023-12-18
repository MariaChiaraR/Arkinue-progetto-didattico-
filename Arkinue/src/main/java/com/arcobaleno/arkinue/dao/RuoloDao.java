package com.arcobaleno.arkinue.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.arcobaleno.arkinue.model.Ruolo;

public class RuoloDao implements InterfaceDao<Ruolo> 
{
	EntityManager em= null;
	
	public RuoloDao(EntityManager em) 
	{
		this.em=em;
	}
	@Override
	public Ruolo create(Ruolo obj) 
	{
		em.persist(obj);
		return obj;
	}

	@Override
	public List<Ruolo> retrive() 
	{
		return em.createQuery("select x from Ruolo x",Ruolo.class).getResultList();
		
	}
	public Ruolo retriveByName(String n) 
	{
		List<Ruolo> list = em.createQuery("select x from Ruolo x where x.nome = :n",Ruolo.class).setParameter("n", n).getResultList();
		return list.get(0);
	}

	@Override
	public Ruolo update(Ruolo obj) 
	{
		em.persist(obj);
		return obj;
	}

	@Override
	public void delete(Ruolo obj) 
	{
		em.remove(obj);		
		
	}
}
