package com.epul.oeuvres.controle;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epul.oeuvres.utilitaires.FonctionsUtiles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.epul.oeuvres.dao.Service;
import com.epul.oeuvres.meserreurs.*;
import com.epul.oeuvres.metier.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

///
/// Les méthode du contrôleur répondent à des sollicitations
/// des pages JSP

@Controller
public class MultiControleur {

//	private static final Logger logger = LoggerFactory.getLogger(MultiControleur.class);

	@RequestMapping(value = "listerAdherent.htm")
	public ModelAndView afficherLesStages(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String destinationPage;
		try {
			// HttpSession session = request.getSession();
			Service unService = new Service();
			request.setAttribute("mesAdherents", unService.consulterListeAdherents());
			destinationPage = "vues/listerAdherent";
		} catch (MonException e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "Erreur";

		}
		return new ModelAndView(destinationPage);
	}



	@RequestMapping(value = "insererAdherent.htm")
	public ModelAndView insererAdherent(HttpServletRequest request,
										HttpServletResponse response) throws Exception {

		String destinationPage = "";
		try {
			AdherentEntity unAdherent = new AdherentEntity();
			unAdherent.setNomAdherent(request.getParameter("txtnom"));
			unAdherent.setPrenomAdherent(request.getParameter("txtprenom"));
			unAdherent.setVilleAdherent(request.getParameter("txtville"));
			Service unService = new Service();
			unService.insertAdherent(unAdherent);
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "vues/Erreur";
		}
		destinationPage = "index";
		return new ModelAndView(destinationPage);
	}

	@RequestMapping(value = "ajouterAdherent.htm")
	public ModelAndView ajouterAdherent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String destinationPage = "";
		try {
			destinationPage = "vues/ajouterAdherent";
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "vues/Erreur";
		}

		return new ModelAndView(destinationPage);
	}


	@RequestMapping(value = "modifierAdherent.htm")
	public ModelAndView modifierAdherent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String destinationPage = "";
		try {
		Service unService = new Service();
		int numero = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("item", unService.adherentById(numero));



			destinationPage = "vues/modifierAdherent";
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
	// /
	// / Affichage de la page d'accueil
	// /
	@RequestMapping(value = "index.htm", method = RequestMethod.GET)
	public ModelAndView Afficheindex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("index");
	}

	// /
		// / Affichage de la page d'accueil
		// /
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public ModelAndView Afficheindex2(HttpServletRequest request, HttpServletResponse response) throws Exception {
			return new ModelAndView("index");
		}
	// /
	// / Affichage de la page d'accueil
	// /
	@RequestMapping(value = "erreur.htm", method = RequestMethod.GET)
	public ModelAndView AfficheErreur(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("vues/Erreur");
	}


}
