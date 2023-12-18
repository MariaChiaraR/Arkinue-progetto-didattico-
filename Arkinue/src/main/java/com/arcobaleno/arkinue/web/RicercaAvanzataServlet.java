package com.arcobaleno.arkinue.web;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arcobaleno.arkinue.businessLogic.BusinessLogic;
import com.arcobaleno.arkinue.model.Articolo;
import com.arcobaleno.arkinue.utility.BlobConverter;
import com.arcobaleno.arkinue.utility.Costanti;
import com.arcobaleno.arkinue.utility.Utility;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ricercaAvanzata")
public class RicercaAvanzataServlet extends HttpServlet {
	
	BusinessLogic bl;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			bl = (BusinessLogic) getServletContext().getAttribute(Costanti.BUSINESSLOGIC);
			
			Articolo cercato = new Articolo();
			cercato.setNome(Utility.insertPercento(req.getParameter(Costanti.NAME_RICERCATO)));
			cercato.setMarca(Utility.insertPercento(req.getParameter(Costanti.MARCA_RICERCATA)));
			cercato.setQuantita( req.getParameter(Costanti.QUANTITA_RICERCATA) != null ? Integer.parseInt(req.getParameter(Costanti.QUANTITA_RICERCATA)) : 0 ); // radio cheked che riporta 0 per minore, 1 o altro per maggiore, se non funziona il cheked setta minore in automatico
			cercato.setColore(Utility.insertPercento(req.getParameter(Costanti.COLORE_RICERCATO)));
			Integer categoria = req.getParameter(Costanti.CATEGORIA) != null ? Integer.parseInt(req.getParameter(Costanti.CATEGORIA)) : 0;
			cercato.setDimensione(req.getParameter(Costanti.DIMENSIONE_RICERCATA) != null ? Float.parseFloat(req.getParameter(Costanti.DIMENSIONE_RICERCATA)) : 0.0f );
			cercato.setPeso( req.getParameter(Costanti.PESO_RICERCATO) != null ? Float.parseFloat(req.getParameter(Costanti.PESO_RICERCATO)) : 0.0f ); // radio cheked che riporta 0 per ascendente, 1 o altro per discendente, se non funziona il cheked setta ascendente in automatico
			cercato.setPrezzo(req.getParameter(Costanti.PREZZO_RICERCATO)!= null ? Float.parseFloat(req.getParameter(Costanti.PREZZO_RICERCATO)) : 0.0f );

			int pagina = req.getParameter(Costanti.PAGINA_RICERCA) == null?
					0 : Integer.parseInt(req.getParameter(Costanti.PAGINA_RICERCA));
			
			List<Integer> totPagine = new ArrayList<Integer>();
			
			if (req.getParameter(Costanti.TOTALE_PAGINE) != null && pagina != 0)
			{
				totPagine.add(Integer.parseInt(req.getParameter(Costanti.TOTALE_PAGINE)));
			}
			
			List<Articolo> articoli = bl.retrieveArticoloAvanzata(cercato, pagina, categoria, totPagine); // il meto non recupera articoli con campi nulli (oltre id e categoria)
			
			Utility.mappaImmagini(req,articoli);
			
			req.setAttribute(Costanti.NAME_RICERCATO, cercato.getNome());
			req.setAttribute(Costanti.MARCA_RICERCATA, cercato.getMarca());
			req.setAttribute(Costanti.QUANTITA_RICERCATA, cercato.getQuantita());
			req.setAttribute(Costanti.COLORE_RICERCATO, cercato.getColore());
			req.setAttribute(Costanti.CATEGORIA, categoria);
			req.setAttribute(Costanti.DIMENSIONE_RICERCATA, cercato.getDimensione());
			req.setAttribute(Costanti.PESO_RICERCATO, cercato.getPeso());
			req.setAttribute(Costanti.PREZZO_RICERCATO, cercato.getPrezzo());
			req.setAttribute(Costanti.RISULTATI, articoli);
			req.setAttribute(Costanti.PAGINA_RICERCA, pagina);
			req.setAttribute(Costanti.TOTALE_PAGINE, totPagine.get(0));
			req.getRequestDispatcher("/jsp/risultatiRicerca.jsp?").forward(req, resp);			
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute(Costanti.ERRORE_MSG, "Errore imprevisto.");
			req.getRequestDispatcher("/jsp/errore.jsp").forward(req, resp);
		}
	}
	
	/*private void mappaImmagini(HttpServletRequest req, List<Articolo> articoli) throws Exception 
	{
		Map<Integer, String> mappaImmagini = new HashMap<>();
		String uploadPath = getServletContext().getRealPath("")+File.separator+ Costanti.CARTELLA_APPOGGIO;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		String baseHTTPUrl = "Http://"+req.getServerName()+":"+ req.getServerPort()+req.getContextPath();
		for(Articolo art: articoli) 
		{
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
	}*/
	
	
	
}