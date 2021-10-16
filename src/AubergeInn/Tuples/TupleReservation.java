package AubergeInn.Tuples;

import java.sql.*;
// Permet de représenter un tuple de la table reservation.

public class TupleReservation {

    private int clientId;
    private int chambreId;
    private Date dateDebut;
    private Date dateFin;
    //private double prixTotal;

    public TupleReservation() {}

    public TupleReservation(int clientId, int chambreId, Date dateDebut, Date dateFin) {
        this.clientId = clientId;
        this.chambreId = chambreId;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        //this.prixTotal = prixTotal;
    }

    // Getters/Setters...

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getChambreId() {
        return chambreId;
    }

    public void setChambreId(int chambreId) {
        this.chambreId = chambreId;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    /**
    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }
     **/
}
