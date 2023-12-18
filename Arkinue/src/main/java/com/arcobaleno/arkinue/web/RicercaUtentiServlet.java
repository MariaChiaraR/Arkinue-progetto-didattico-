package com.arcobaleno.arkinue.web;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.arcobaleno.arkinue.businessLogic.BusinessLogic;
import com.arcobaleno.arkinue.model.Articolo;
import com.arcobaleno.arkinue.model.Utente;
import com.arcobaleno.arkinue.utility.Costanti;
import com.arcobaleno.arkinue.utility.Utility;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ricercaUtenti")
public class RicercaUtentiServlet extends HttpServlet {
	
	BusinessLogic bl;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Utente> users;
			Utente obj = (Utente) req.getSession().getAttribute(Costanti.UTENTE_IN_SESSIONE) ; 
			if (obj.getRuolo().getNome().equals(Costanti.RUOLO_ADMIN)) 
			{
				bl = (BusinessLogic) getServletContext().getAttribute(Costanti.BUSINESSLOGIC);
				String username = Utility.insertPercento(req.getParameter(Costanti.USERNAME)) ;
				String email = Utility.insertPercento(req.getParameter(Costanti.EMAIL)) ;
				String nome = Utility.insertPercento(req.getParameter(Costanti.NOME)) ;
				String cognome = Utility.insertPercento(req.getParameter(Costanti.COGNOME)) ;
				int scelta = Integer.parseInt(req.getParameter(Costanti.ORDINE));
				// String dataString = req.getParameter(Costanti.DATANASCITA) ; LocalDate datanascita = LocalDate.parse(dataString);
				String querybase = "select x from Utente x where ";
				String query = querybase + "nome like '%" + nome + "%' and cognome like '" + cognome + "' and email like '%"
						+ email + "%' and username like '%" + username + "%' ";
				switch (scelta) {
				case 0:
					break;
				case 1:
					query += "order by username";
					break;
				case 2:
					query += "order by username desc";
					break;
				case 3:
					query += "order by email";
					break;
				case 4:
					query += "order by email desc";
					break;
				case 5:
					query += "order by nome";
					break;
				case 6:
					query += "order by nome desc";
					break;
				case 7:
					query += "order by cognome";
					break;
				case 8:
					query += "order by cognome desc";
					break;
				default:
					break;
				}
				
				int pagina = req.getParameter(Costanti.PAGINA_RICERCA) == null?
						0 : Integer.parseInt(req.getParameter(Costanti.PAGINA_RICERCA));
				
				List<Integer> totPagine = new ArrayList<Integer>();
				
				if (req.getParameter(Costanti.TOTALE_PAGINE) != null && pagina != 0)
				{
					totPagine.add(Integer.parseInt(req.getParameter(Costanti.TOTALE_PAGINE)));
				}
				
				users = bl.retrievePaginaUtenti(query, pagina, totPagine);
				
				req.setAttribute(Costanti.USERNAME, username);
				req.setAttribute(Costanti.EMAIL, email);
				req.setAttribute(Costanti.NOME, nome);
				req.setAttribute(Costanti.COGNOME, cognome);
				req.setAttribute(Costanti.ORDINE, scelta);
				req.setAttribute(Costanti.PAGINA_RICERCA, pagina);
				req.setAttribute(Costanti.TOTALE_PAGINE, totPagine.get(0));
				req.setAttribute(Costanti.RISULTATI, users);
				req.getRequestDispatcher("/jsp/admin/risultatiRicercaUtenti.jsp").forward(req, resp);
			}
			else 
			{
				req.setAttribute(Costanti.ERRORE_MSG, "Errore imprevisto.");
				req.getRequestDispatcher("/jsp/errore.jsp").forward(req, resp);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute(Costanti.ERRORE_MSG, "Errore imprevisto.");
			req.getRequestDispatcher("/jsp/errore.jsp").forward(req, resp);
		}
	}
	
	
	
}
