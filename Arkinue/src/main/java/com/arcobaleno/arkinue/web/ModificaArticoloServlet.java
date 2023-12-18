package com.arcobaleno.arkinue.web;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;

import com.arcobaleno.arkinue.businessLogic.BusinessLogic;
import com.arcobaleno.arkinue.model.Articolo;
import com.arcobaleno.arkinue.utility.BlobConverter;
import com.arcobaleno.arkinue.utility.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/modificaArticolo")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,maxFileSize = 1024 * 1024 * 10,maxRequestSize = 1024 * 1024 * 10 * 5)
public class ModificaArticoloServlet extends HttpServlet {
	
	BusinessLogic bl;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			bl = (BusinessLogic) getServletContext().getAttribute(Costanti.BUSINESSLOGIC);
			String idString= req.getParameter(Costanti.ID);
			String nome = req.getParameter(Costanti.NOME).trim();
			int id = Integer.parseInt(idString);
			
			
			String marca = req.getParameter(Costanti.MARCA).trim() ; 
			Integer quantita = Integer.parseInt(req.getParameter(Costanti.QUANTITA)) ;
			String colore = req.getParameter(Costanti.COLORE).trim();
			String descrizione = req.getParameter(Costanti.DESCRIZIONE).trim();
			Float dimensione = Float.parseFloat(req.getParameter(Costanti.DIMENSIONE));
			Float peso = Float.parseFloat(req.getParameter(Costanti.PESO));
			Float prezzo = Float.parseFloat(req.getParameter(Costanti.PREZZO));
			String cat= req.getParameter(Costanti.CATEGORIA);
			
			
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
			
			
			Articolo articolo = bl.modificaArticoloServlet(id, nome, marca, quantita, colore, descrizione, dimensione, peso, prezzo, cat, immagine, nomeImmagine);
			
			req.setAttribute(Costanti.ID, articolo.getId());
			req.getRequestDispatcher("/visualizzaArticolo").forward(req, resp);	
			
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute(Costanti.ERRORE_MSG, "Errore imprevisto.");
			req.getRequestDispatcher("/jsp/errore.jsp").forward(req, resp);
		}
	}
	
	
	
}
