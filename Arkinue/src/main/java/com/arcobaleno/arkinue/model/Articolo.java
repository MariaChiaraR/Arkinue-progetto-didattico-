package com.arcobaleno.arkinue.model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Articolo
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	

	private String nome;
	private String marca;
	private Integer quantita;
	private String colore;
	private Float dimensione;
	private Float peso;
	private Float prezzo;
	private String descrizione;
	private Blob immagine; //da mettere del costruttore
	private String nomeImmagine;
	
	//immagine da capire come mettere
	


	@ManyToMany(mappedBy = "articoli")
	private List<Carrello> carrelli = new ArrayList<>();
	
	@ManyToOne
	private Categoria categoria;
	
	public Articolo() {}
	
	public Articolo(String nome, String marca, Integer quantita, String colore, Float dimensione, Float peso,
			Float prezzo, String descrizione, Blob immagine, String nomeImmagine, Categoria categoria) {
		super();
		this.nome = nome;
		this.marca = marca;
		this.quantita = quantita;
		this.colore = colore;
		this.dimensione = dimensione;
		this.peso = peso;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.immagine = immagine;
		this.nomeImmagine = nomeImmagine;
		this.categoria = categoria;
	}

	public Articolo(String nome, String marca, Integer quantita, String descrizione, String colore, Float dimensione, Float peso, Float prezzo, Blob immagine,String nomeImmagine) {
		super();
		this.nome = nome;
		this.marca = marca;
		this.quantita = quantita;
		this.descrizione = descrizione;
		this.colore = colore;
		this.dimensione = dimensione;
		this.peso = peso;
		this.prezzo = prezzo;
		this.immagine = immagine;
		this.nomeImmagine = nomeImmagine;
	}
	
	public Articolo(String nome, String marca, Integer quantita, String descrizione, String colore, Float dimensione, Float peso, Float prezzo, Blob immagine,String nomeImmagine, Categoria categoria) {
		super();
		this.nome = nome;
		this.marca = marca;
		this.quantita = quantita;
		this.descrizione = descrizione;
		this.colore = colore;
		this.dimensione = dimensione;
		this.peso = peso;
		this.prezzo = prezzo;
		this.immagine = immagine;
		this.nomeImmagine = nomeImmagine;
		this.categoria = categoria;
	}
	
	public Articolo(String nome, String marca, Integer quantita, String descrizione, String colore, Float dimensione, Float peso, Float prezzo, Categoria categoria) {
		super();
		this.nome = nome;
		this.marca = marca;
		this.quantita = quantita;
		this.descrizione = descrizione;
		this.colore = colore;
		this.dimensione = dimensione;
		this.peso = peso;
		this.prezzo = prezzo;
		this.categoria = categoria;
	}

	public Articolo(String nome, String marca, Integer quantita, String colore, Float dimensione, Float peso,
			Float prezzo) {
		super();
		this.nome = nome;
		this.marca = marca;
		this.quantita = quantita;
		this.colore = colore;
		this.dimensione = dimensione;
		this.peso = peso;
		this.prezzo = prezzo;
	}

	public Articolo(String nome, Integer quantita) {
		super();
		this.nome = nome;
		this.quantita = quantita;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public Float getDimensione() {
		return dimensione;
	}

	public void setDimensione(Float dimensione) {
		this.dimensione = dimensione;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	public Float getPrezzo() {
		return prezzo;
	}

	public Blob getImmagine() {
		return immagine;
	}

	public void setImmagine(Blob immagine) {
		this.immagine = immagine;
	}

	public String getNomeImmagine() {
		return nomeImmagine;
	}

	public void setNomeImmagine(String nomeImmagine) {
		this.nomeImmagine = nomeImmagine;
	}

	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}

	public List<Carrello> getCarrelli() {
		return carrelli;
	}

	public void setCarrelli(List<Carrello> carrelli) {
		this.carrelli = carrelli;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
		Articolo other = (Articolo) obj;
		return Objects.equals(id, other.id);
	}	
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
	
}
