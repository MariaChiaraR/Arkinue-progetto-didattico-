package com.arcobaleno.arkinue.model;

import java.util.ArrayList;
import java.util.List;

public class ContenitorediListe 
{
	public List<Articolo> getLista1() {
		return lista1;
	}
	public void setLista1(List<Articolo> lista1) {
		this.lista1 = lista1;
	}
	public List<Articolo> getLista2() {
		return lista2;
	}
	public void setLista2(List<Articolo> lista2) {
		this.lista2 = lista2;
	}
	public List<Articolo> getLista3() {
		return lista3;
	}
	public void setLista3(List<Articolo> lista3) {
		this.lista3 = lista3;
	}
	public List<Articolo> getLista4() {
		return lista4;
	}
	public void setLista4(List<Articolo> lista4) {
		this.lista4 = lista4;
	}
	public List<Articolo> getArcobalista1() {
		return arcobalista1;
	}
	public void setArcobalista1(List<Articolo> arcobalista1) {
		this.arcobalista1 = arcobalista1;
	}
	public List<Articolo> getArcobalista2() {
		return arcobalista2;
	}
	public void setArcobalista2(List<Articolo> arcobalista2) {
		this.arcobalista2 = arcobalista2;
	}
	public List<Articolo> getArcobalista3() {
		return arcobalista3;
	}
	public void setArcobalista3(List<Articolo> arcobalista3) {
		this.arcobalista3 = arcobalista3;
	}
	public List<Articolo> getArcobalista4() {
		return arcobalista4;
	}
	public void setArcobalista4(List<Articolo> arcobalista4) {
		this.arcobalista4 = arcobalista4;
	}
	public List<Articolo> getArcobalista5() {
		return arcobalista5;
	}
	public void setArcobalista5(List<Articolo> arcobalista5) {
		this.arcobalista5 = arcobalista5;
	}
	public List<Articolo> getArcobalista6() {
		return arcobalista6;
	}
	public void setArcobalista6(List<Articolo> arcobalista6) {
		this.arcobalista6 = arcobalista6;
	}
	public List<Articolo> getArcobalista7() {
		return arcobalista7;
	}
	public void setArcobalista7(List<Articolo> arcobalista7) {
		this.arcobalista7 = arcobalista7;
	}
	public ContenitorediListe(List<Articolo> lista1, List<Articolo> lista2, List<Articolo> lista3,
			List<Articolo> lista4, List<Articolo> arcobalista1, List<Articolo> arcobalista2,
			List<Articolo> arcobalista3, List<Articolo> arcobalista4, List<Articolo> arcobalista5,
			List<Articolo> arcobalista6, List<Articolo> arcobalista7) {
		super();
		this.lista1 = lista1;
		this.lista2 = lista2;
		this.lista3 = lista3;
		this.lista4 = lista4;
		this.arcobalista1 = arcobalista1;
		this.arcobalista2 = arcobalista2;
		this.arcobalista3 = arcobalista3;
		this.arcobalista4 = arcobalista4;
		this.arcobalista5 = arcobalista5;
		this.arcobalista6 = arcobalista6;
		this.arcobalista7 = arcobalista7;
	}
	private List<Articolo> lista1 = new ArrayList<>();
	private List<Articolo> lista2 = new ArrayList<>();
	private List<Articolo> lista3 = new ArrayList<>();
	private List<Articolo> lista4 = new ArrayList<>();
	
	private List<Articolo> arcobalista1 = new ArrayList<>();
	private List<Articolo> arcobalista2 = new ArrayList<>();
	private List<Articolo> arcobalista3 = new ArrayList<>();
	private List<Articolo> arcobalista4 = new ArrayList<>();
	private List<Articolo> arcobalista5 = new ArrayList<>();
	private List<Articolo> arcobalista6 = new ArrayList<>();
	private List<Articolo> arcobalista7 = new ArrayList<>();
	
	private Integer generico1;
	private Integer generico2;
	
	private List<Ordine> ordinelista1 = new ArrayList<>();
	private List<Ordine> ordinelista2 = new ArrayList<>();
	private List<Ordine> ordinelista3 = new ArrayList<>();
	private List<Ordine> ordinelista4 = new ArrayList<>();
	
	public Integer getGenerico1() {
		return generico1;
	}
	public void setGenerico1(Integer generico1) {
		this.generico1 = generico1;
	}
	public Integer getGenerico2() {
		return generico2;
	}
	public void setGenerico2(Integer generico2) {
		this.generico2 = generico2;
	}
	public List<Ordine> getOrdinelista1() {
		return ordinelista1;
	}
	public void setOrdinelista1(List<Ordine> ordinelista1) {
		this.ordinelista1 = ordinelista1;
	}
	public List<Ordine> getOrdinelista2() {
		return ordinelista2;
	}
	public void setOrdinelista2(List<Ordine> ordinelista2) {
		this.ordinelista2 = ordinelista2;
	}
	public List<Ordine> getOrdinelista3() {
		return ordinelista3;
	}
	public void setOrdinelista3(List<Ordine> ordinelista3) {
		this.ordinelista3 = ordinelista3;
	}
	public List<Ordine> getOrdinelista4() {
		return ordinelista4;
	}
	public void setOrdinelista4(List<Ordine> ordinelista4) {
		this.ordinelista4 = ordinelista4;
	}

	
}
