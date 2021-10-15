package AubergeInn.Tables;

import AubergeInn.Connexion;
import AubergeInn.Tuples.tupleChambre;

import java.sql.*;

public class tableChambres {

    private Connexion cx;
    private PreparedStatement stmtExiste;
    private PreparedStatement stmtAfficher;
    private PreparedStatement stmtAfficherLibre;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtDelete;

    public tableChambres(Connexion cx) throws SQLException
    {
        this.cx = cx;

        stmtAfficher = cx.getConnection()
                .prepareStatement("select Chambres.id, Chambres.nom, Chambres.type_lit, Chambres.prix, " +
                        "CommoditesChambres.commodite_id from Chambres " +
                        "inner join CommoditesChambres on Chambres.id = CommoditesChambres.chambre_id");

        //TODO
        // Si on peut avoir une requête qui a de l'allure
        stmtAfficherLibre = cx.getConnection()
                .prepareStatement("");
    }


    public Connexion getConnexion() {
        return cx;
    }

    public tupleChambre getChambre(int id)
    {
    }

    public boolean existe(int id) {
    }


    public void ajouterChambre(int idChambre, String nom, String typeLit, double prix)
    {
    }

    public int supprimerChambre(int id)
    {
    }

    /**
     * Affichage de toutes les informations sur une chambre.
     */
    public void afficherChambre(int idClient) throws SQLException
    {
        stmtAfficher.executeUpdate();
    }
    public void afficherChambreLibre() throws SQLException
    {
        //TODO
        // Pas sure qu'on puisse faire ça sans avoir une grosse requête dégeux
    }

}
