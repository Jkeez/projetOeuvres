package com.epul.oeuvres.metier;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "reservation_oeuvrevente", schema = "baseoeuvre", catalog = "")
public class ReservationOeuvreventeEntity {
    private int idReservationOeuvrevente;
    private Date dateReservation;
    private String statut;
    private AdherentEntity adherent;
    private OeuvreventeEntity oeuvrevente;

    @Id
    @Column(name = "id_reservation_oeuvrevente")
    public int getIdReservationOeuvrevente() {
        return idReservationOeuvrevente;
    }

    public void setIdReservationOeuvrevente(int idReservationOeuvrevente) {
        this.idReservationOeuvrevente = idReservationOeuvrevente;
    }

    @Basic
    @Column(name = "date_reservation")
    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    @Basic
    @Column(name = "statut")
    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @ManyToOne
    @JoinColumn(name = "id_oeuvrevente", referencedColumnName = "id_oeuvrevente", nullable = false)
    public OeuvreventeEntity getOeuvrevente() {
        return oeuvrevente;
    }

    public void setOeuvrevente(OeuvreventeEntity oeuvre) {
        this.oeuvrevente = oeuvre;
    }

    @ManyToOne
    @JoinColumn(name = "id_adherent", referencedColumnName = "id_adherent", nullable = false)
    public AdherentEntity getAdherent() {
        return adherent;
    }

    public void setAdherent(AdherentEntity adherent) {
        this.adherent = adherent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationOeuvreventeEntity that = (ReservationOeuvreventeEntity) o;
        return idReservationOeuvrevente == that.idReservationOeuvrevente &&
                Objects.equals(dateReservation, that.dateReservation) &&
                Objects.equals(statut, that.statut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReservationOeuvrevente, dateReservation, statut);
    }
}
