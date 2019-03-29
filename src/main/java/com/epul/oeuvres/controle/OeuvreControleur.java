package com.epul.oeuvres.controle;


import com.epul.oeuvres.dao.OeuvreService;
import com.epul.oeuvres.dao.ProprietaireService;
import com.epul.oeuvres.dao.Service;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.AdherentEntity;
import com.epul.oeuvres.metier.OeuvreventeEntity;
import com.epul.oeuvres.metier.ProprietaireEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

///
/// Les méthode du contrôleur répondent à des sollicitations
/// des pages JSP

@Controller
public class OeuvreControleur {

//	private static final Logger logger = LoggerFactory.getLogger(MultiControleur.class);

	@RequestMapping(value = "listerOeuvre.htm")
	public ModelAndView afficherOeuvres(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String destinationPage;
		try {
			// HttpSession session = request.getSession();
			OeuvreService unService = new OeuvreService();
			request.setAttribute("mesOeuvres", unService.consulterListeOeuvres());
			destinationPage = "/vues/listerOeuvre";
		} catch (MonException e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "Erreur";

		}
		return new ModelAndView(destinationPage);
	}



	@RequestMapping(value = "insererOeuvre.htm")
	public ModelAndView insererOeuvre(HttpServletRequest request,
										HttpServletResponse response) throws Exception {

		String destinationPage = "";
        ProprietaireService propServ = new ProprietaireService();
        OeuvreService service = new OeuvreService();

        try {
			OeuvreventeEntity oeuvre = new OeuvreventeEntity();
			oeuvre.setTitreOeuvrevente(request.getParameter("titre"));
			oeuvre.setEtatOeuvrevente("L");
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

	@RequestMapping(value = "ajouterOeuvre.htm")
	public ModelAndView ajouterOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String destinationPage = "";
		try {
            ProprietaireService propService = new ProprietaireService();
            request.setAttribute("props", propService.consulterListeProps());
			destinationPage = "vues/ajouterOeuvre";
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "vues/Erreur";
		}

		return new ModelAndView(destinationPage);
	}


	@RequestMapping(value = "modifierOeuvre.htm")
	public ModelAndView modifierOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String destinationPage = "";
		try {
		    OeuvreService unService = new OeuvreService();
            ProprietaireService propService = new ProprietaireService();
		    int numero = Integer.parseInt(request.getParameter("id"));
		    request.setAttribute("oeuvre", unService.oeuvreById(numero));
		    request.setAttribute("props", propService.consulterListeProps());

            destinationPage = "vues/modifierOeuvre";
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "vues/Erreur";
		}

		return new ModelAndView(destinationPage);
	}


    @RequestMapping(value = "ajoutModificationOeuvre.htm")
    public ModelAndView ajoutModifOeuvre(HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        OeuvreService service = new OeuvreService();
        ProprietaireService propServ = new ProprietaireService();


        String destinationPage = "/vues/listerOeuvre";
        try {


            OeuvreventeEntity oeuvre = service.oeuvreById(Integer.parseInt(request.getParameter("id")));
            oeuvre.setTitreOeuvrevente(request.getParameter("txtnom"));
            oeuvre.setPrixOeuvrevente(Float.parseFloat(request.getParameter("txtprix")));
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
	@RequestMapping(value = "supprimerOeuvre.htm")
	public ModelAndView supprimerOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String destinationPage = "";
		try {
			OeuvreService unService = new OeuvreService();

			int numero = Integer.parseInt(request.getParameter("id"));
			OeuvreventeEntity oeuvre = unService.oeuvreById(numero);

			unService.supprimerOeuvre(oeuvre);

			request.setAttribute("mesOeuvres", unService.consulterListeOeuvres());

			destinationPage = "/vues/listerOeuvre";
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "vues/Erreur";
		}

		return new ModelAndView(destinationPage);
	}


}
