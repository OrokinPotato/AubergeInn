package AubergeInn.Tables;

import AubergeInn.Connexion;
import AubergeInn.Tuples.TupleChambre;

import java.sql.*;

public class TableChambres {

    private Connexion cx;
    private PreparedStatement stmtExiste;
    private PreparedStatement stmtAfficher;
    private PreparedStatement stmtAfficherLibre;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtDelete;

    public TableChambres(Connexion cx) throws SQLException
    {
        this.cx = cx;

        stmtAfficher = cx.getConnection()
                .prepareStatement("select Chambres.id, Chambres.nom, Chambres.type_lit, Chambres.prix, " +
                        "CommoditesChambres.commodite_id from Chambres " +
                        "inner join CommoditesChambres on Chambres.id = CommoditesChambres.chambre_id");
        stmtExiste = cx.getConnection().prepareStatement("select id, nom, type_lit, prix from Chambres where idmembre = ?");
        stmtInsert = cx.getConnection().prepareStatement("insert into Chambres (id, nom, type_lit, prix) " + "values (?,?,?,0)");
        stmtDelete = cx.getConnection().prepareStatement("delete from Chambres where id = ?");


        //TODO
        // Si on peut avoir une requête qui a de l'allure
        stmtAfficherLibre = cx.getConnection()
                .prepareStatement("");
    }


    public Connexion getConnexion() {
        return cx;
    }

    public TupleChambre getChambre(int idChambre) throws SQLException
    {
        stmtExiste.setInt(1, idChambre);
        ResultSet set = stmtExiste.executeQuery();
        if (set.next())
        {
            TupleChambre tuChambre = new TupleChambre();
            tuChambre.setId(idChambre);
            tuChambre.setNom(set.getString(2));
            tuChambre.setType_lit(set.getString(3));
            tuChambre.setPrix(set.getDouble(4));
            set.close();
            return tuChambre;
        }
        else
        {
            return null;
        }
    }

    public boolean existe(int idChambre) throws SQLException {
        stmtExiste.setInt(1, idChambre);
        ResultSet set = stmtExiste.executeQuery();
        boolean chambreExiste = set.next();
        set.close();
        return chambreExiste;
    }


    public void ajouterChambre(int idChambre, String nom, String typeLit, double prix) throws SQLException
    {
        stmtInsert.setInt(1, idChambre);
        stmtInsert.setString(2, nom);
        stmtInsert.setString(3, typeLit);
        stmtInsert.setDouble(4, prix);
        stmtInsert.executeUpdate();
    }

    public int supprimerChambre(int idChambre) throws SQLException
    {
        stmtDelete.setInt(1, idChambre);
        return stmtDelete.executeUpdate();
    }

    /**
     * Affichage de toutes les informations sur une chambre.
     */
    public void afficherChambre(int idChambre) throws SQLException
    {
        stmtAfficher.executeUpdate();
    }
    public void afficherChambresLibres() throws SQLException
    {
        //TODO
        // Pas sure qu'on puisse faire ça sans avoir une grosse requête dégeux
    }

}
