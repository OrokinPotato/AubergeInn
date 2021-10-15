package AubergeInn.Tables;

import AubergeInn.Connexion;
import AubergeInn.Tuples.tupleCommoditeChambre;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class tableComChambre {

    private Connexion cx;
    private final PreparedStatement stmtListeComParChambre;
    private final PreparedStatement stmtListeChambreWithCom;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtDelete;

    public tableComChambre(Connexion cx) throws SQLException
    {
        this.cx = cx;

        stmtListeComParChambre = cx.getConnection()
                .prepareStatement("select commodite_id, chambre_id"
                        + "from Auberge-Inn.CommoditesChambres where chambre_id = ?");
        stmtListeChambreWithCom = cx.getConnection().prepareStatement(
                "select distinct chambre_id from Auberge-Inn.CommoditesChambres");

        stmtInsert = cx.getConnection().prepareStatement(
                "insert into CommoditesChambres (commodite_id, chambre_id) " + "values (?,?)");

        stmtDelete = cx.getConnection().prepareStatement("delete from CommoditesChambres " +
                "where commodite_id = ? and chambre_id = ?");

    }

    public Connexion getConnexion()
    {
        return cx;
    }

    public List<Integer> getAllChambre() throws SQLException
    {
        ResultSet set = stmtListeChambreWithCom.executeQuery();

        List<Integer> chambreListe = new ArrayList<>();
        while (set.next())
        {
            chambreListe.add(set.getInt("chambre_id"));
        }
        set.close();
        return chambreListe;
    }

    public List<tupleCommoditeChambre> getAllCommodite(int chambreId)
            throws SQLException
    {
        stmtListeComParChambre.setInt(1, chambreId);
        ResultSet set = stmtListeComParChambre.executeQuery();

        List<tupleCommoditeChambre> comListe = new LinkedList<tupleCommoditeChambre>();

        while (set.next())
        {
            tupleCommoditeChambre tuComChambre = new tupleCommoditeChambre(
                    set.getInt("commodite_id"),
                    set.getInt("chambre_id"));
            comListe.add(tuComChambre);
        }
        set.close();
        return comListe;
    }

    /**
     * Inclusion d'une commodité dans une chambre.
     */
    public void inclureCommodite(int chambreId, int commoditeId) throws SQLException
    {
        stmtInsert.setInt(1, commoditeId);
        stmtInsert.setInt(2, chambreId);
        stmtInsert.executeUpdate();
    }

    /**
     * Suppression d'une commodité d'une chambre.
     */
    public int retirerCommodite(int commoditeId, int chambreId) throws SQLException
    {
        stmtDelete.setInt(1, commoditeId);
        stmtDelete.setInt(2, chambreId);
        return stmtDelete.executeUpdate();
    }

}
