package com.arcobaleno.arkinue.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.arcobaleno.arkinue.model.Articolo;
import com.arcobaleno.arkinue.model.Categoria;

public class CategoriaDao implements InterfaceDao<Categoria> 
{
	EntityManager em= null;
	
	public CategoriaDao(EntityManager em) 
	{
		this.em=em;
	}
	@Override
	public Categoria create(Categoria obj) 
	{
		em.persist(obj);
		return obj;
	}

	@Override
	public List<Categoria> retrive() 
	{
		return em.createQuery("select x from Categoria x",Categoria.class).getResultList();
		
	}

	@Override
	public Categoria update(Categoria obj) 
	{
		em.persist(obj);
		return obj;
	}

	@Override
	public void delete(Categoria obj) 
	{
		em.remove(obj);		
		
	}
	public Categoria retriveByNome(String nome) {
		return em.createQuery("select x from Categoria x where nome=:nome",Categoria.class).setParameter("nome", nome).getSingleResult() ;
	}
}
