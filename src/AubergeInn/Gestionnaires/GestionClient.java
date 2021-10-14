package AubergeInn.Gestionnaires;

import AubergeInn.*;
import AubergeInn.Tables.tableHandlerClients;
import AubergeInn.Tables.tableHandlerReservations;

import java.sql.*;

public class GestionClient {

    private Connexion cx;
    private tableHandlerClients clients;
    private tableHandlerReservations reservations;

    public GestionClient(tableHandlerClients c, tableHandlerReservations r) throws IFT287Exception {
        this.cx = c.getConnexion();
        if (c.getConnexion() != r.getConnexion()) {
            throw new IFT287Exception("Differente connexions entre la table Clients et la table Reservation");
        }
        this.clients = c;
        this.reservations = r;
    }

    // Ajoute un nouveau client, si déjà présent, renvoi exception
    public void ajout(int id, String prenom, String nom, int age)
        throws SQLException, IFT287Exception, Exception
    {
        try {

            if (clients.existe(id)) {
                throw new IFT287Exception("Client déjà présent dans la base de données.");
            }
            clients.ajouterClient(id, prenom, nom, age);
            cx.commit();
        }
        catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }


    public void retire(){

    }

}
