package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.OeuvreventeEntity;
import com.epul.oeuvres.metier.ReservationEntity;
import com.epul.oeuvres.metier.ReservationOeuvreventeEntity;
import com.epul.oeuvres.metier.UtilisateurEntity;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class ReservationService extends EntityService{

	/* Insertion d'un adherent
	 * param Adherent unAdherent
	 * */
	public void insertReservation(ReservationOeuvreventeEntity uneReservation) throws MonException {
		try
		{

			EntityTransaction transac = startTransaction();
			transac.begin();
			entitymanager.persist(uneReservation);
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

	public void supprimerReservation(ReservationOeuvreventeEntity uneReservation) throws MonException {
		try
		{

			EntityTransaction transac = startTransaction();
			transac.begin();
			String qryString = "delete from ReservationOeuvreventeEntity s where s.idReservationOeuvrevente="+uneReservation.getIdReservationOeuvrevente();
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

	public void modifierReservation(ReservationOeuvreventeEntity reservation) throws MonException {
		try
		{
			EntityTransaction transac = startTransaction();
			transac.begin();
			entitymanager.merge(reservation);
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

	public List<ReservationOeuvreventeEntity> consulterListeReservations() throws MonException {
		List<ReservationOeuvreventeEntity> mesRes = null;
		try
		{
			EntityTransaction transac = startTransaction();
			transac.begin();
			mesRes = (List<ReservationOeuvreventeEntity>)
					entitymanager.createQuery(
							"SELECT o FROM ReservationOeuvreventeEntity o").getResultList();
			entitymanager.close();
		}
		catch (RuntimeException e)
		{
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mesRes;
	}

	public ReservationOeuvreventeEntity reservationById(int numero) throws MonException {
		List<ReservationOeuvreventeEntity> reservations = null;
		ReservationOeuvreventeEntity reservation = new ReservationOeuvreventeEntity();
		try {
			EntityTransaction transac = startTransaction();
			transac.begin();

			reservations = (List<ReservationOeuvreventeEntity>)entitymanager.createQuery("SELECT a FROM ReservationOeuvreventeEntity a WHERE a.idReservationOeuvrevente="+numero).getResultList();
			reservation = reservations.get(0);
			entitymanager.close();
		}catch (RuntimeException e)
		{
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reservation;
	}



}
