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
                        "inner join CommoditesChambres on Chambres.id = CommoditesChambres.chambre_id where Chambres.id = ?");
        stmtExiste = cx.getConnection().prepareStatement("select id, nom, type_lit, prix from Chambres where id = ?");
        stmtInsert = cx.getConnection().prepareStatement("insert into Chambres (id, nom, type_lit, prix) " + "values (?,?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from Chambres where id = ?");

        stmtAfficherLibre = cx.getConnection()
                .prepareStatement("select distinct ch.id, ch.nom, ch.type_lit, (ch.prix + Total.total) as TotalPrix from (select CH.id ,sum(co.prix) As total from Commodites CO inner join CommoditesChambres CC on CO.id = CC.commodite_id inner join Chambres CH on CH.id = CC.chambre_id group by CH.id) as Total inner join Chambres as ch on ch.id = Total.id inner join CommoditesChambres as co on ch.id = co.chambre_id where ch.id not in (select chambre_id from Reservations)");
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
        stmtAfficher.setInt(1, idChambre);
        stmtAfficher.executeUpdate();
    }
    public void afficherChambresLibres() throws SQLException
    {
        stmtAfficherLibre.executeUpdate();
    }

}
