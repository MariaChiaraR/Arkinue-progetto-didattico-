package com.arcobaleno.arkinue.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.arcobaleno.arkinue.model.Articolo;
import com.arcobaleno.arkinue.model.Categoria;

public class ArticoloDao implements InterfaceDao<Articolo>
{
	EntityManager em= null;

	public ArticoloDao(EntityManager em) 
	{
		this.em=em;
	}
	@Override
	public Articolo create(Articolo obj) 
	{
		em.persist(obj);
		return obj;
	}

	@Override
	public List<Articolo> retrive() 
	{
		return em.createQuery("select x from Articolo x",Articolo.class).getResultList();

	}

	public List<Articolo> retriveByNome(String nome) {
		return em.createQuery("select x from Articolo x where x.nome like :nome",Articolo.class).setParameter("nome", nome).getResultList();
	}

	public Articolo retriveById(Integer id) {
		return em.createQuery("select x from Articolo x where x.id like :id",Articolo.class).setParameter("id", id).getSingleResult();
	}

	@Override
	public Articolo update(Articolo obj) 
	{
		em.persist(obj);
		return obj;
	}

	@Override
	public void delete(Articolo obj) 
	{
		em.remove(obj);		

	}

	public List<Articolo> articoliRecenti(String nomeCategoria) {
		Categoria cat = em.createQuery("select x from Categoria x where x.nome=:nome",Categoria.class).setParameter("nome", nomeCategoria).getSingleResult() ;
		Integer id = cat.getId();
		return em.createQuery("select x from Articolo x where categoria_id = :id order by x.id desc", Articolo.class).setParameter("id", id).setMaxResults(4).getResultList();
	}
	
 	public List<Articolo> retriveAvanzata(Articolo articolo, int pagina, int categoria)
{ 			
	String addQuery = "";
	int includiOrdinaDimensioni = articolo.getQuantita();
	float ordinaPrezzo = articolo.getPeso();

	if(categoria != 0)
	{
		addQuery = " and categoria_id = " + categoria;
	}
	if (articolo.getDimensione() != 0.0f)
	{
		addQuery = addQuery + " and x.dimensione "
				+ ( includiOrdinaDimensioni != 0 ? "<= " : ">= ") + articolo.getDimensione()
				+ ( includiOrdinaDimensioni != 0 ? " order by x.dimensione desc" : " order by x.dimensione asc");	
	}
	if (articolo.getPrezzo() != 0.0f) 
	{ // settiamo un prezzo maggiore ma può essere sostituito da fasce di prezzo
		addQuery =" and x.prezzo <= " + articolo.getPrezzo() + addQuery
				+ (articolo.getDimensione() != 0.0f ? " ," : " order by" )
				+ (ordinaPrezzo != 0 ? " x.prezzo desc" : " x.prezzo");
	}

	return em.createQuery("select x from Articolo x where x.nome like :nome and x.marca like :marca and x.colore like :colore" + addQuery ,Articolo.class).
			setParameter("nome", articolo.getNome()).				
			setParameter("marca", articolo.getMarca()).
			setParameter("colore", articolo.getColore()).
			setFirstResult(7 * pagina).setMaxResults(7).getResultList();
}

public int retriveTotaleRecord(Articolo articolo, int categoria)
{ 			
	String addQuery = "";
	int includiOrdinaDimensioni = articolo.getQuantita();
	float ordinaPrezzo = articolo.getPeso();
	
	if(categoria != 0)
	{
		addQuery = " and categoria_id = " + categoria;
	}
	if (articolo.getDimensione() != 0.0f)
	{
		addQuery = addQuery + " and x.dimensione "
				+ ( includiOrdinaDimensioni != 0 ? "<= " : ">= ") + articolo.getDimensione()
				+ ( includiOrdinaDimensioni != 0 ? " order by x.dimensione desc" : " order by x.dimensione asc");	
	}
	if (articolo.getPrezzo() != 0.0f) 
	{ // settiamo un prezzo maggiore ma può essere sostituito da fasce di prezzo
		addQuery =" and x.prezzo <= " + articolo.getPrezzo() + addQuery
				+ (articolo.getDimensione() != 0.0f ? " ," : " order by" )
				+ (ordinaPrezzo != 0 ? " x.prezzo desc" : " x.prezzo");
	}

	return em.createQuery("select x from Articolo x where x.nome like :nome and x.marca like :marca and x.colore like :colore" + addQuery ,Articolo.class).
			setParameter("nome", articolo.getNome()).				
			setParameter("marca", articolo.getMarca()).
			setParameter("colore", articolo.getColore()).getResultList().size();
}


}
