package com.arcobaleno.arkinue.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.persistence.PersistenceException;

import com.arcobaleno.arkinue.businessLogic.BusinessLogic;
import com.arcobaleno.arkinue.model.Utente;
import com.arcobaleno.arkinue.utility.Costanti;
import com.arcobaleno.arkinue.utility.EmailSender;
import com.arcobaleno.arkinue.utility.Utility;
import com.mysql.cj.result.LocalDateTimeValueFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registrazione")
public class RegistrazioneServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		try {

			String nome = req.getParameter(Costanti.NOME);//nome
			nome = nome.trim();
			String cognome =req.getParameter(Costanti.COGNOME);//cognome
			cognome = cognome.trim();
			String indirizzo =req.getParameter(Costanti.INDIRIZZO);//indirizzo
			indirizzo = indirizzo.trim();
			String email =req.getParameter(Costanti.EMAIL);//email
			email = email.trim();
			String data =req.getParameter(Costanti.DATANASCITA);//data di nascita
			data = data.trim();
			String username =req.getParameter(Costanti.USERNAME);//username
			username = username.trim();
			String password =req.getParameter(Costanti.PASSWORD);//password
			password = password.trim();

				if(Utility.nomeValidation(nome) && Utility.nomeValidation(cognome)) {
					if(Utility.passwordValidation(password)) {
						if(Utility.emailValidation(email)) {
							SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							LocalDate date = LocalDate.parse(data);
							LocalDate now = LocalDate.now();
							if(date.getYear() < now.getYear()-10) {
								BusinessLogic bl = (BusinessLogic) req.getServletContext().getAttribute(Costanti.BUSINESSLOGIC);
								Utente user = bl.createUtente(new Utente(username, password, email, nome, cognome, indirizzo, date));
								EmailSender.sendEmail(user);  //invio email
								req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
							} else {
								req.setAttribute(Costanti.ERRORE_MSG, "Non è permessa l'iscrizione a chi ha meno di 10 anni.") ;
								req.getRequestDispatcher("/jsp/registrazione.jsp").forward(req, resp);
							}
						} else {
							req.setAttribute(Costanti.ERRORE_MSG, "Formato email non valido.") ;
							req.getRequestDispatcher("/jsp/registrazione.jsp").forward(req, resp);
						}
					} else {
						req.setAttribute(Costanti.ERRORE_MSG, "Password non conforme; troppo semplice.") ;
						req.getRequestDispatcher("/jsp/registrazione.jsp").forward(req, resp);
					}
				} else {
					req.setAttribute(Costanti.ERRORE_MSG, "Nome e/o cognome includono numeri o simboli.") ;
					req.getRequestDispatcher("/jsp/registrazione.jsp").forward(req, resp);
				}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			req.setAttribute(Costanti.ERRORE_MSG, "username già in uso") ;
			req.getRequestDispatcher("/jsp/registrazione.jsp").forward(req, resp);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/errore.jsp").forward(req, resp);
		}


		//data deve essere retrocedente al giorno odierno, mettiamo dei limiti?



	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		doGet(req, resp);
	}
}
