package com.epul.oeuvres.controle;


import com.epul.oeuvres.dao.OeuvreService;
import com.epul.oeuvres.dao.ProprietaireService;
import com.epul.oeuvres.dao.ReservationService;
import com.epul.oeuvres.dao.Service;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

///
/// Les méthode du contrôleur répondent à des sollicitations
/// des pages JSP

@Controller
public class ReservationControleur {

//	private static final Logger logger = LoggerFactory.getLogger(MultiControleur.class);

	@RequestMapping(value = "listerReservation.htm")
	public ModelAndView afficherReservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String destinationPage;
		try {
			// HttpSession session = request.getSession();
			ReservationService unService = new ReservationService();
			request.setAttribute("mesReservations", unService.consulterListeReservations());
			destinationPage = "/vues/listerReservation";
		} catch (MonException e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "Erreur";

		}
		return new ModelAndView(destinationPage);
	}



	@RequestMapping(value = "insererReservation.htm")
	public ModelAndView insererReservation(HttpServletRequest request,
										HttpServletResponse response) throws Exception {

		String destinationPage = "";
        ProprietaireService propServ = new ProprietaireService();
        OeuvreService service = new OeuvreService();

        try {
			OeuvreventeEntity oeuvre = new OeuvreventeEntity();
			oeuvre.setTitreOeuvrevente(request.getParameter("titre"));
			oeuvre.setEtatOeuvrevente(request.getParameter("etat"));
            oeuvre.setPrixOeuvrevente(Float.parseFloat(request.getParameter("prix")));
            ProprietaireEntity prop = propServ.propById(Integer.parseInt(request.getParameter("proprietaire")));

            oeuvre.setProprietaire(prop);
            service.insertOeuvre(oeuvre);
            request.setAttribute("mesOeuvres", service.consulterListeOeuvres());
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "vues/Erreur";
		}
		destinationPage = "index";
		return new ModelAndView(destinationPage);
	}


	@RequestMapping(value = "modifierReservation.htm")
	public ModelAndView modifierReservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String destinationPage = "";
		try {
		    ReservationService resServ = new ReservationService();
			Service adhService = new Service();

		    int numero = Integer.parseInt(request.getParameter("id"));
		    ReservationOeuvreventeEntity reservation = resServ.reservationById(numero);
		    OeuvreventeEntity oeuvre = reservation.getOeuvrevente();

			request.setAttribute("adherents", adhService.consulterListeAdherents());
		    request.setAttribute("oeuvre", oeuvre);
			request.setAttribute("reservation", reservation);

            destinationPage = "vues/modifierReservation";
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "vues/Erreur";
		}

		return new ModelAndView(destinationPage);
	}

	@RequestMapping(value = "ajouterModificationReservationOeuvre.htm")
	public ModelAndView ajouterModificationReservationOeuvre(HttpServletRequest request,
												 HttpServletResponse response) throws Exception {

		String destinationPage = "";

		ReservationService resServ = new ReservationService();
		Service adherentService = new Service();

		try {
			int numeroReservation = Integer.parseInt(request.getParameter("reservation"));
			Date dateReservation = Date.valueOf(request.getParameter("dateRes"));
			AdherentEntity adherent = adherentService.adherentById(Integer.parseInt(request.getParameter("adherent")));
			ReservationOeuvreventeEntity reservation = resServ.reservationById(numeroReservation);

			reservation.setAdherent(adherent);
			reservation.setDateReservation(dateReservation);

			resServ.modifierReservation(reservation);
			request.setAttribute("mesReservations", resServ.consulterListeReservations());
			destinationPage = "/vues/listerReservation";
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "vues/Erreur";
		}

		return new ModelAndView(destinationPage);
	}

	@RequestMapping(value = "reserverOeuvre.htm")
	public ModelAndView reserverOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String destinationPage = "";
		try {
			OeuvreService unService = new OeuvreService();
			Service adhService = new Service();
			int numero = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("oeuvre", unService.oeuvreById(numero));
			request.setAttribute("adherents", adhService.consulterListeAdherents());
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.now();
			String currentDate = dtf.format(localDate);
			String maxDate = dtf.format(localDate.plusYears(1));
			request.setAttribute("currentDate", currentDate);
			request.setAttribute("maxDate", maxDate);

			destinationPage = "vues/reserverOeuvre";
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "vues/Erreur";
		}

		return new ModelAndView(destinationPage);
	}

	@RequestMapping(value = "supprimerReservation.htm")
	public ModelAndView supprimerReservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String destinationPage = "";
		try {
			OeuvreService unService = new OeuvreService();
			Service adhService = new Service();
			ReservationService resServ = new ReservationService();

			int numero = Integer.parseInt(request.getParameter("id"));
			OeuvreventeEntity oeuvre = resServ.reservationById(numero).getOeuvrevente();
			oeuvre.setEtatOeuvrevente("L");

			unService.modifOeuvre(oeuvre);
			resServ.supprimerReservation(resServ.reservationById(numero));

			request.setAttribute("mesReservations", resServ.consulterListeReservations());

			destinationPage = "/vues/listerReservation";
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "vues/Erreur";
		}

		return new ModelAndView(destinationPage);
	}

	@RequestMapping(value = "confirmerReservation.htm")
	public ModelAndView confirmerReservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String destinationPage = "";
		try {
			OeuvreService unService = new OeuvreService();
			ReservationService resServ = new ReservationService();

			int numero = Integer.parseInt(request.getParameter("id"));
			OeuvreventeEntity oeuvre = resServ.reservationById(numero).getOeuvrevente();
			oeuvre.setEtatOeuvrevente("V");

			unService.modifOeuvre(oeuvre);
			ReservationOeuvreventeEntity res = resServ.reservationById(numero);
			res.setStatut("Confirmée");
			resServ.modifierReservation(res);
			request.setAttribute("mesReservations", resServ.consulterListeReservations());

			destinationPage = "/vues/listerReservation";
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "vues/Erreur";
		}

		return new ModelAndView(destinationPage);
	}

	@RequestMapping(value = "ajouterReservationOeuvre.htm")
	public ModelAndView ajouterReservationOeuvre(HttpServletRequest request,
									  HttpServletResponse response) throws Exception {

		String destinationPage = "";
		ProprietaireService propServ = new ProprietaireService();
		OeuvreService service = new OeuvreService();
		ReservationService resServ = new ReservationService();
		Service adherentService = new Service();

		try {
			ReservationOeuvreventeEntity reservation = new ReservationOeuvreventeEntity();

			reservation.setAdherent(adherentService.adherentById(Integer.parseInt(request.getParameter("adherent"))));
			reservation.setDateReservation(Date.valueOf(request.getParameter("dateRes")));
			reservation.setOeuvrevente(service.oeuvreById(Integer.parseInt(request.getParameter("oeuvre"))));
			if(request.getParameter("achat") != null){ //Oeuvre achetée immédiatement
				reservation.setStatut("Confirmée");
				reservation.getOeuvrevente().setEtatOeuvrevente("V");
			} else {
				reservation.setStatut("En attente");
				reservation.getOeuvrevente().setEtatOeuvrevente("R");
			}

			resServ.insertReservation(reservation);
			service.modifOeuvre(reservation.getOeuvrevente());
			request.setAttribute("mesOeuvres", service.consulterListeOeuvres());
			destinationPage = "/vues/listerOeuvre";
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "vues/Erreur";
		}

		return new ModelAndView(destinationPage);
	}

    @RequestMapping(value = "ajoutModificationReservation.htm")
    public ModelAndView ajoutModifReservation(HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        OeuvreService service = new OeuvreService();
        ProprietaireService propServ = new ProprietaireService();


        String destinationPage = "/vues/listerOeuvre";
        try {


            OeuvreventeEntity oeuvre = service.oeuvreById(Integer.parseInt(request.getParameter("id")));
            oeuvre.setTitreOeuvrevente(request.getParameter("txtnom"));
            oeuvre.setPrixOeuvrevente(Float.parseFloat(request.getParameter("txtprix")));
            oeuvre.setEtatOeuvrevente(request.getParameter("etat"));
            ProprietaireEntity prop = propServ.propById(Integer.parseInt(request.getParameter("proprietaire")));

            oeuvre.setProprietaire(prop);
            service.modifOeuvre(oeuvre);
            request.setAttribute("mesOeuvres", service.consulterListeOeuvres());
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }
/*
	@RequestMapping(value = "insererAdherentModif.htm")
	public ModelAndView insererAdherentModif(HttpServletRequest request,
											 HttpServletResponse response) throws Exception {

		String destinationPage = "";
		try {
			int numero= Integer.parseInt(request.getParameter("idadherent"));
			//Modification de l'adherent
			Service unService = new Service();


			Adherent unAdherent = unService.consulterAdherent(numero);
			unAdherent.setNomAdherent(request.getParameter("txtnom"));
			unAdherent.setPrenomAdherent(request.getParameter("txtprenom"));
			unAdherent.setVilleAdherent(request.getParameter("txtville"));

			unService.majAdherent(unAdherent);
			destinationPage = "/index.jsp";
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "vues/Erreur";
		}
		destinationPage = "index";
		return new ModelAndView(destinationPage);
	}
*/


}
