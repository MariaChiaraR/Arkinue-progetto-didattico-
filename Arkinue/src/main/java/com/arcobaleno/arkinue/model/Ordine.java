package com.arcobaleno.arkinue.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ordine 
{	

	private Integer idArticolo;
	private String nomeArticolo;
	private String marcaArticolo;
	private Integer quantitaArticolo;
	private String coloreArticolo;
	private Float dimensioneArticolo;
	private Float pesoArticolo;
	private Float prezzoArticolo;


	private String nomeCategoria;
	
	@ManyToOne()
	private Utente utente;
	
	private String username;
	
	private LocalDate data;
	
	public Ordine() 
	{
		
	}
	
	public Ordine(Utente utente, Articolo articolo) 
	{
		this.utente = utente;
		this.idArticolo = articolo.getId();
		this.nomeArticolo = articolo.getNome();
		this.marcaArticolo = articolo.getMarca();
		this.quantitaArticolo = articolo.getQuantita();
		this.coloreArticolo = articolo.getColore();
		this.dimensioneArticolo = articolo.getDimensione();
		this.pesoArticolo = articolo.getPeso();
		this.prezzoArticolo = articolo.getPrezzo();
		this.username = utente.getUsername();
		this.setNomeCategoria(articolo.getCategoria().getNome());
		data = LocalDate.now();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeArticolo() {
		return nomeArticolo;
	}

	public void setNomeArticolo(String nomeArticolo) {
		this.nomeArticolo = nomeArticolo;
	}

	public String getMarcaArticolo() {
		return marcaArticolo;
	}

	public void setMarcaArticolo(String marcaArticolo) {
		this.marcaArticolo = marcaArticolo;
	}

	public Integer getQuantitaArticolo() {
		return quantitaArticolo;
	}

	public void setQuantitaArticolo(Integer quantitaArticolo) {
		this.quantitaArticolo = quantitaArticolo;
	}

	public String getColoreArticolo() {
		return coloreArticolo;
	}

	public void setColoreArticolo(String coloreArticolo) {
		this.coloreArticolo = coloreArticolo;
	}

	public Float getDimensioneArticolo() {
		return dimensioneArticolo;
	}

	public void setDimensioneArticolo(Float dimensioneArticolo) {
		this.dimensioneArticolo = dimensioneArticolo;
	}

	public Float getPesoArticolo() {
		return pesoArticolo;
	}

	public void setPesoArticolo(Float pesoArticolo) {
		this.pesoArticolo = pesoArticolo;
	}

	public Float getPrezzoArticolo() {
		return prezzoArticolo;
	}

	public void setPrezzoArticolo(Float prezzoArticolo) {
		this.prezzoArticolo = prezzoArticolo;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordine other = (Ordine) obj;
		return Objects.equals(id, other.id);
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public Integer getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(Integer idArticolo) {
		this.idArticolo = idArticolo;
	}

}
