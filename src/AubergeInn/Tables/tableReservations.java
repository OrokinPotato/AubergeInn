package AubergeInn.Tables;

import AubergeInn.Connexion;
import AubergeInn.Tuples.tupleReservation;

import java.sql.*;

public class tableReservations {

    private Connexion cx;
    private PreparedStatement stmtExiste;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtDelete;


    public boolean existe(int clientId, int chambreId)
    {

    }

    public Connexion getConnexion() {
        return cx;
    }

    public tupleReservation getReservationClient(int id) {
        if () {

        }
        else {
            return null;
        }
    }
    public tupleReservation getReservationChambre(int id) {
        if () {

        }
        else {
            return null;
        }
    }

    public void reserver(int clientId, int chambreId, Date dateDebut, Date dateFin, double resPrix)
    {
    }
}
