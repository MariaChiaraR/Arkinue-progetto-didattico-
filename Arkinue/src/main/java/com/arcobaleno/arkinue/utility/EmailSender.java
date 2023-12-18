package com.arcobaleno.arkinue.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.arcobaleno.arkinue.model.Articolo;
import com.arcobaleno.arkinue.model.Utente;

public class EmailSender {
	
	public static void sendEmail(Utente utente, Articolo art) throws IOException, AddressException, MessagingException {
		//properties simile alla Map
		Properties prop = new Properties();
		//carico il file in lettura a partire dal ClassPath
		InputStream is = EmailSender.class.getClassLoader().getResourceAsStream("email.properties") ;
		
		prop.load(is);  //l'oggetto prop ha ora le coppie chiave-valore
		
		String enabled = prop.getProperty("enabled");  //recupero un valore dalla chiave
		if (enabled!=null && enabled.equalsIgnoreCase("true")) {
			//aggiungiamo una coppia che verranno caricate per l'avvio criptato
			prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			//Session e Authentication di javaxmail
			Session session = Session.getInstance(prop,   //prop passerà le sue properties
					new javax.mail.Authenticator() {      //authenticator implementa al volo il metodo getPswAuth
					protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(prop.getProperty("username"), prop.getProperty("password"));
				}
			});
			//javax mail
			Message msg = new MimeMessage(session);  //il messaggio è creato già vincolato alla sessione
			
			msg.setFrom(new InternetAddress(prop.getProperty("from"))); //set del mittente(noi)
			
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(utente.getEmail())); //set del destinatario(singolo)
			
			msg.setSubject("Arkinue: acquisto effettuato"); //set dell'oggetto dell'email
			
			String body = "Gentile, "+utente.getNome()+"\ngrazie per aver acquistato: "+art.getNome() ;
			body+= "\nDati dell'oggetto:"+"\nMarca: "+art.getMarca()+"\nColore: "+art.getColore()+"\nPrezzo: "+art.getPrezzo()+"\n" ;
			
			body+="\nLa invitiamo a monitorare il suo ordine nel Profilo sul nostro sito.\n"
				+ "\nCordiali saluti e buona continuazione."  ;	
			
			msg.setText(body); //set del corpo/testo
			
			Transport.send(msg); //invio dell'email
		}//if senza else
	}
	
	public static void sendEmail(Utente user) throws IOException, AddressException, MessagingException {
		
		Properties prop = new Properties(); //properties simile alla Map
		
		InputStream is = EmailSender.class.getClassLoader().getResourceAsStream("email.properties") ; //carico il file in lettura a partire dal ClassPath
		
		prop.load(is);  //l'oggetto prop ha ora le coppie chiave-valore
		
		String enabled = prop.getProperty("enabled");  //recupero un valore dalla chiave
		if (enabled!=null && enabled.equalsIgnoreCase("true")) {
			
			prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //aggiungiamo una coppia che verranno caricate per l'avvio criptato
															//Session e Authentication di javaxmail
			Session session = Session.getInstance(prop,    //prop passerà le sue properties
					new javax.mail.Authenticator() {      //authenticator implementa al volo il metodo getPswAuth
					protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(prop.getProperty("username"), prop.getProperty("password"));
				}
			});
			//javax mail
			Message msg = new MimeMessage(session);  //il messaggio è creato già vincolato alla sessione
			
			msg.setFrom(new InternetAddress(prop.getProperty("from"))); //set del mittente(noi)
			
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail())); //set del destinatario(singolo)
			
			msg.setSubject("Notifica di registrazione."); //set dell'oggetto dell'email
			
			String body = "Benvenuto, "+user.getNome()+" "+user.getCognome()+"\n" ;
			
			body+="\nIl tuo username per il login è : "+user.getUsername() ;
			
			body+="\nGrazie per esserti registrato ad Arkinue, la tua piattaforma di shopping semplice e veloce.\n" ;
			
			body+="\nSe vuoi chiedere chiarimenti su questa email, ripondi al mittente e avvierai una conversazione con un nostro admin." ;
			
			msg.setText(body); //set del corpo/testo
			
			Transport.send(msg); //invio dell'email
		}//if senza else
	}
	
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		try {
			// sendEmail("nicolvitti@gmail.com", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
