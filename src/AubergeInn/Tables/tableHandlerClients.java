package AubergeInn.Tables;

import AubergeInn.Connexion;

import java.sql.*;

public class tableHandlerClients {

    private PreparedStatement stmtExiste;
    private PreparedStatement stmtAfficher;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtDelete;
    private Connexion cx;


    public Connexion getConnexion() {
        return cx;
    }

    public boolean existe(int id) {
        return true;
    /**
     * Creation d'une instance. Précompilation d'énoncés SQL.
     */
    public tableHandlerClients(Connexion cx) throws SQLException
    {
        this.cx = cx;
        stmtExiste = cx.getConnection()
                .prepareStatement("select id, prenom, nom, age from Clients where id = ?");
        stmtAfficher = cx.getConnection()
                .prepareStatement("select id, prenom, nom, age from Clients");
        stmtInsert = cx.getConnection().prepareStatement(
                "insert into Clients (id, prenom, nom, age) " + "values (?,?,?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from Clients where id = ?");
    }


    /**
     * Ajout d'un nouveau client.
     */
    public void ajouterClient(int idClient, String prenom, String nom, int age) throws SQLException
    {
        /* Ajout du client. */
        stmtInsert.setInt(1, idClient);
        stmtInsert.setString(2, prenom);
        stmtInsert.setString(3, nom);
        stmtInsert.setInt(4, age);
        stmtInsert.executeUpdate();
    }

    /**
     * Suppression d'un client.
     */
    public void supprimerClient(int idClient) throws SQLException
    {
        stmtDelete.setInt(1, idClient);
        stmtDelete.executeUpdate();
    }

    /**
     * Affichage de toutes les informations sur un client.
     */
    public void afficherClient(int idClient) throws SQLException
    {
        /* TO DO */
        // Je me demande si ça va vraiment ici ou dans le handler de Reservations
    }
}
