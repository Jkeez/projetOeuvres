package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.OeuvreventeEntity;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class OeuvreService extends EntityService{


	public void insertOeuvre(OeuvreventeEntity uneOeuvre) throws MonException {
		try
		{
			EntityTransaction transac = startTransaction();
			transac.begin();
			entitymanager.persist(uneOeuvre);
			transac.commit();
			entitymanager.close();
		}
		catch (RuntimeException e)
		{
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<OeuvreventeEntity> consulterListeOeuvres() throws MonException {
		List<OeuvreventeEntity> mesOeuvres = null;
		try
		{
			EntityTransaction transac = startTransaction();
			transac.begin();
			mesOeuvres = (List<OeuvreventeEntity>)
					entitymanager.createQuery(
							"SELECT o FROM OeuvreventeEntity o").getResultList();
			entitymanager.close();
		}
		catch (RuntimeException e)
		{
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mesOeuvres;
	}

	public void supprimerOeuvre(OeuvreventeEntity oeuvre) throws MonException {
		try
		{

			EntityTransaction transac = startTransaction();
			transac.begin();
			String qryString = "delete from OeuvreventeEntity s where s.idOeuvrevente="+oeuvre.getIdOeuvrevente();
			Query query = entitymanager.createQuery(qryString);
			int count = query.executeUpdate();
			transac.commit();
			entitymanager.close();
		}
		catch (RuntimeException e)
		{
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modifOeuvre(OeuvreventeEntity oeuvre) throws MonException {
		try
		{
			EntityTransaction transac = startTransaction();
			transac.begin();
			entitymanager.merge(oeuvre);
			entitymanager.flush();
			transac.commit();
		}
		catch (RuntimeException e)
		{
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public OeuvreventeEntity oeuvreById(int numero) throws MonException {
		List<OeuvreventeEntity> oeuvres = null;
		OeuvreventeEntity oeuvre = new OeuvreventeEntity();
		try {
			EntityTransaction transac = startTransaction();
			transac.begin();

			oeuvres = (List<OeuvreventeEntity>)entitymanager.createQuery("SELECT a FROM OeuvreventeEntity a WHERE a.idOeuvrevente="+numero).getResultList();
			oeuvre = oeuvres.get(0);
			entitymanager.close();
		}catch (RuntimeException e)
		{
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oeuvre;
	}

}
