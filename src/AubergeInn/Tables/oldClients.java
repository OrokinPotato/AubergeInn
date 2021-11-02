package AubergeInn.Tables;

import AubergeInn.Connexion;
import AubergeInn.Tuples.oldClient;

import java.sql.*;

public class oldClients {

    private PreparedStatement stmtExiste;
    private PreparedStatement stmtAfficher;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtDelete;
    private Connexion cx;

    /**
     * Creation d'une instance. Précompilation d'énoncés SQL.
     */
    public oldClients(Connexion cx) throws SQLException
    {
        this.cx = cx;
        stmtExiste = cx.getConnection()
                .prepareStatement("select id, prenom, nom, age from Clients where id = ?");
        stmtAfficher = cx.getConnection()
                .prepareStatement("select Clients.id, Clients.prenom, Clients.nom, Clients.age, " +
                "Reservations.chambre_id, Reservations.date_debut, Reservations.date_fin" +
                " from Clients inner join Reservations on Clients.id = Reservations.client_id where Clients.id = ?");
        stmtInsert = cx.getConnection().prepareStatement(
                "insert into Clients (id, prenom, nom, age) " + "values (?,?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from Clients where id = ?");
    }


    public Connexion getConnexion() {
        return cx;
    }

    public boolean existe(int id) throws SQLException
    {
        stmtExiste.setInt(1, id);
        ResultSet set = stmtExiste.executeQuery();
        boolean clientExiste = set.next();
        set.close();
        return clientExiste;
    }

    public oldClient getClient(int id) throws SQLException
    {
        stmtExiste.setInt(1, id);
        ResultSet set = stmtExiste.executeQuery();
        if (set.next())
        {
            oldClient client = new oldClient();
            client.setId(id);
            client.setPrenom(set.getString(2));
            client.setNom(set.getString(3));
            client.setAge(set.getInt(4));
            set.close();
            return client;
        }
        else
        {
            return null;
        }
    }

    /**
     * Ajout d'un nouveau client.
     */
    public void ajouterClient(int idClient, String prenom, String nom, int age) throws SQLException
    {
        stmtInsert.setInt(1, idClient);
        stmtInsert.setString(2, prenom);
        stmtInsert.setString(3, nom);
        stmtInsert.setInt(4, age);
        stmtInsert.executeUpdate();
    }

    /**
     * Suppression d'un client.
     */
    public int supprimerClient(int idClient) throws SQLException
    {
        stmtDelete.setInt(1, idClient);
        return stmtDelete.executeUpdate();
    }

    /**
     * Affichage de toutes les informations sur un client.
     */
    public void afficherClient(int idClient) throws SQLException
    {
        stmtAfficher.setInt(1, idClient);
        stmtAfficher.executeUpdate();
    }

}
