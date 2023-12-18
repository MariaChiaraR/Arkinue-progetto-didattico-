package com.arcobaleno.arkinue.web;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import com.arcobaleno.arkinue.businessLogic.BusinessLogic;
import com.arcobaleno.arkinue.model.Articolo;
import com.arcobaleno.arkinue.utility.BlobConverter;
import com.arcobaleno.arkinue.utility.Costanti;
import com.arcobaleno.arkinue.utility.Utility;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/creaArticolo")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,maxFileSize = 1024 * 1024 * 10,maxRequestSize = 1024 * 1024 * 10 * 5) //indica che la servlet è multipart (fondamentale per il form)
public class CreaArticoloServlet extends HttpServlet {
	
	BusinessLogic bl;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			bl = (BusinessLogic) getServletContext().getAttribute(Costanti.BUSINESSLOGIC);
			
			String nome = req.getParameter(Costanti.NOME).trim() ;
			String marca = req.getParameter(Costanti.MARCA).trim() ; 
			Integer quantita  = Integer.parseInt(req.getParameter(Costanti.QUANTITA)) ;
			String colore = req.getParameter(Costanti.COLORE);
			Float dimensione = Float.parseFloat(req.getParameter(Costanti.DIMENSIONE)) ; 
			Float peso  = Float.parseFloat(req.getParameter(Costanti.PESO)) ; 
			Float prezzo  = Float.parseFloat(req.getParameter(Costanti.PREZZO)) ;
			String descrizione = req.getParameter(Costanti.DESCRIZIONE).trim() ; 
			
			if(nome.isBlank()|| marca.isBlank()|| colore.isBlank() || descrizione.isBlank()) 
			{
				req.setAttribute(Costanti.ERRORE_MSG, "Uno o più campi sono vuoti");
				req.getRequestDispatcher("/jsp/admin/creazioneArticolo.jsp").forward(req, resp);
			}
			String uploadPath = getServletContext().getRealPath("") /* cartella tomcat*/ + File.separator /* / */+ Costanti.CARTELLA_APPOGGIO; //<-- nome cartella di appoggio
			File uploadDir = new File(uploadPath); // creo oggetto associato al percorso
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			Blob immagine = null;
			String nomeImmagine = null;
			
			String filePath = null;
			for ( Part part : req.getParts() ) {
				String fileName = part.getSubmittedFileName();
				if ( fileName!=null && !fileName.isEmpty() ) {
					filePath = uploadPath + File.separator + fileName;
					part.write(filePath);
					immagine=BlobConverter.generateBlob(filePath);
					nomeImmagine = fileName;
				}
			}
			
			String nome_cat = req.getParameter(Costanti.CATEGORIA);
			
			Articolo articolo = bl.creaArticolo(nome,marca,quantita,descrizione,colore,dimensione,peso,prezzo,nome_cat, immagine, nomeImmagine);	
			if(articolo == null) 
			{
				req.setAttribute(Costanti.ERRORE_MSG, "Nome articolo già presente");
				req.getRequestDispatcher("/jsp/admin/creazioneArticolo.jsp").forward(req, resp);
			}
			req.setAttribute(Costanti.ARTICOLO, articolo);
			Utility.singolaImmagine(req, articolo);
			req.getRequestDispatcher("/jsp/articolo.jsp").forward(req, resp);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute(Costanti.ERRORE_MSG, "Errore imprevisto.");
			req.getRequestDispatcher("/jsp/errore.jsp").forward(req, resp);
		}
	}
	
	
	
}

