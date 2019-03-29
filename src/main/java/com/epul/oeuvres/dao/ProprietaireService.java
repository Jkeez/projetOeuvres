package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.OeuvreventeEntity;
import com.epul.oeuvres.metier.ProprietaireEntity;
import com.epul.oeuvres.metier.UtilisateurEntity;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class ProprietaireService extends EntityService{


	public List<ProprietaireEntity> consulterListeProps() throws MonException {
		List<ProprietaireEntity> mesProps = null;
		try
		{
			EntityTransaction transac = startTransaction();
			transac.begin();
			mesProps = (List<ProprietaireEntity>)
					entitymanager.createQuery(
							"SELECT a FROM ProprietaireEntity a " +
									"ORDER BY a.nomProprietaire").getResultList();
			entitymanager.close();
		}
		catch (RuntimeException e)
		{
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mesProps;
	}

	/* Consultation d'une adherent par son numéro
	*/
	public ProprietaireEntity propById(int numero) throws MonException {
		List<ProprietaireEntity> props = null;
		ProprietaireEntity prop = new ProprietaireEntity();
		try {
			EntityTransaction transac = startTransaction();
			transac.begin();

			props = (List<ProprietaireEntity>)entitymanager.createQuery("SELECT a FROM ProprietaireEntity a WHERE a.idProprietaire="+numero).getResultList();
			prop = props.get(0);
			entitymanager.close();
		}catch (RuntimeException e)
		{
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}


}
