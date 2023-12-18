package com.arcobaleno.arkinue.utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.arcobaleno.arkinue.model.Articolo;
import com.arcobaleno.arkinue.model.Ordine;

import jakarta.servlet.http.HttpServletRequest;

public class Utility 
{
	 
	public static boolean isNumeric(String str){
	        return str != null && str.matches("[0-9.]+");
	    }

	public static boolean passwordValidation(String password) 
	{

		if(password.length()>=8)
		{
			Pattern letter = Pattern.compile("[a-zA-z]");
			Pattern digit = Pattern.compile("[0-9]");
			Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
			//Pattern eight = Pattern.compile (".{8}");


			Matcher hasLetter = letter.matcher(password);
			Matcher hasDigit = digit.matcher(password);
			Matcher hasSpecial = special.matcher(password);

			return hasLetter.find() && hasDigit.find() && hasSpecial.find();

		}
		else
			return false;

	}

	public static boolean nomeValidation(String nome) 
	{


		Pattern digit = Pattern.compile("[0-9]");
		Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
		//Pattern eight = Pattern.compile (".{8}");


		Matcher hasDigit = digit.matcher(nome);
		Matcher hasSpecial = special.matcher(nome);

		return !hasDigit.find() && !hasSpecial.find();



	}

	public static boolean emailValidation(String email) 
	{
		int indexchiocciola = 0;
		short contatore=0;
		for(int i = 0; i<email.length();i++) 
		{
			if(email.charAt(i)=='@') 
			{
				contatore ++;
				if(contatore ==2) 
				{
					return false;
				}
				indexchiocciola = i;
			}
		}

		if(indexchiocciola!= 0 && contatore == 1) 
		{
			for(int i = indexchiocciola; i<email.length(); i++) 
			{
				if(email.charAt(i)=='.') 
				{
					return true;
				}
			}


		}
		return false;
	}

	public static String insertPercento(String stringa) 
	{    
		if (stringa == null) { return "%";}
		stringa = "%" + stringa.replace(" ", "%") + "%";

		while (stringa.contains("%%")) {
			stringa = stringa.replace("%%", "%");
		}

		return stringa;
	}
	
public static Blob convertiImgOffline(String uploadPath, String nomefile) throws IOException, SerialException, SQLException {
		
		Path path = Paths.get(uploadPath+File.separator+nomefile);

        byte[] bytes = Files.readAllBytes(path);
    
        Blob blob = new SerialBlob(bytes);
        
        return blob;
	}

public static void mappaImmagini(HttpServletRequest req, List<Articolo> articoli) throws Exception 
	{
		Map<Integer, String> mappaImmagini = new HashMap<>();
		String uploadPath = req.getServletContext().getRealPath("")+File.separator+ Costanti.CARTELLA_APPOGGIO;
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
	}

public static void singolaImmagine(HttpServletRequest req, Articolo art) throws Exception 
{
	String uploadPath = req.getServletContext().getRealPath("")+File.separator+ Costanti.CARTELLA_APPOGGIO;
	File uploadDir = new File(uploadPath);
	if (!uploadDir.exists()) {
		uploadDir.mkdir();
	}
	String baseHTTPUrl = "Http://"+req.getServerName()+":"+ req.getServerPort()+req.getContextPath();

		Blob immagine = art.getImmagine();
		if(immagine != null) 
		{
			String filePath = uploadPath+File.separator+art.getId()+"_"+art.getNomeImmagine();
			BlobConverter.saveFile(immagine, filePath);
			req.setAttribute(Costanti.SINGOLAIMMAGINE, baseHTTPUrl+"/"+Costanti.CARTELLA_APPOGGIO+"/"+art.getId()+"_"+art.getNomeImmagine());
		}
		else 
		{
			
			req.setAttribute(Costanti.SINGOLAIMMAGINE,  baseHTTPUrl+"/media/placeholder.jpg");
		}	
}

}

