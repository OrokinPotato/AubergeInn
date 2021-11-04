package AubergeInn.Tables;

import AubergeInn.Connexion;
import AubergeInn.Tuples.oldReservation;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class oldReservations {

    private Connexion cx;
    private PreparedStatement stmtExiste;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtDelete;

    public oldReservations(Connexion cx) throws SQLException{
        this.cx = cx;

        stmtExiste = cx.getConnection().prepareStatement("select client_Id, chambre_Id, date_Debut, date_Fin "
                + "from Reservations where client_Id = ? and chambre_Id= ? and date_debut=?");
        stmtInsert = cx.getConnection().prepareStatement("insert into Reservations (client_Id, chambre_Id, date_Debut, date_Fin) "
                + "values (?,?,to_date(?,'YYYY-MM-DD'),to_date(?,'YYYY-MM-DD'))");
        stmtDelete = cx.getConnection().prepareStatement("delete from Reservations where client_Id = ? and chambre_Id = ? and date_Debut = ?");
    }

    public boolean existe(int clientId, int chambreId, Date dateDebut) throws SQLException
    {
        stmtExiste.setInt(1, clientId);
        stmtExiste.setInt(2, chambreId);
        stmtExiste.setDate(3, dateDebut);
        ResultSet set = stmtExiste.executeQuery();
        boolean reservationExiste = set.next();
        set.close();
        return reservationExiste;
    }

    public Connexion getConnexion() {
        return cx;
    }


    /**
     * Choisir les réservations faites par un client
     */
    public List<oldReservation> getReservationsClient(int idClient) throws Exception {

        stmtExiste.setInt(1, idClient);
        ResultSet rset = stmtExiste.executeQuery();

       return getReservationsListFromResultSet(rset);
    }


    /**
     * Choisir les réservations faites pour une chambre
     */
    public List<oldReservation> getReservationsChambre(int idChambre) throws SQLException{
        stmtExiste.setInt(2, idChambre);
        ResultSet rset = stmtExiste.executeQuery();

        return getReservationsListFromResultSet(rset);
    }

    private List<oldReservation> getReservationsListFromResultSet(ResultSet rset) throws SQLException{

        List<oldReservation> listeReservations = new LinkedList<>();

        while (rset.next()) {

            oldReservation reservation = new oldReservation(
                    rset.getInt(1),
                    rset.getInt(2),
                    rset.getDate(3),
                    rset.getDate(4)
            );
            //        rset.getDouble(5)
            //);
            listeReservations.add(reservation);
        }
        rset.close();
        if(!listeReservations.isEmpty()){
            return listeReservations;
        }
        else{
            return null;
        }
    }

    public void reserver(int clientId, int chambreId, Date dateDebut, Date dateFin) throws SQLException
    {
        stmtInsert.setInt(1, clientId);
        stmtInsert.setInt(2, chambreId);
        stmtInsert.setDate(3, dateDebut);
        stmtInsert.setDate(4, dateFin);
        //stmtInsert.setDouble(5, resPrix);
        stmtInsert.executeUpdate();
    }
}