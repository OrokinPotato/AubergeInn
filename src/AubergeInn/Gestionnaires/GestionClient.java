package AubergeInn.Gestionnaires;

import AubergeInn.*;
import AubergeInn.Tables.Clients;
import AubergeInn.Tables.Reservations;

import java.sql.*;

public class GestionClient {

    private Connexion cx;
    private Clients clients;
    private Reservations reservations;

    public GestionClient(Clients c, Reservations r) throws IFT287Exception {
        this.cx = c.getConnexion();
        if (c.getConnexion() != r.getConnexion()) {
            throw new IFT287Exception("Differente connexions entre la table Clients et la table Reservation");
        }
        this.clients = c;
        this.reservations = r;
    }

    /**
     *  Commande d'ajout d'un client.
     */
    public void ajouter(int id, String prenom, String nom, int age)
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


    /**
     *  Commande de retrait d'un client.
     */
    public void retirer(int id) throws SQLException, IFT287Exception, Exception
    {
        try {

            if (clients.getClient(id) == null) {
                throw new IFT287Exception("Client inexistant: " + id);
            }
            if (reservations.getReservationsClient(id) != null) {
                throw new IFT287Exception("Client "+ id + " a encore des reservations");
            }

            int n = clients.supprimerClient(id);
            if (n == 0) {
                throw new IFT287Exception("Le client "+ id + " n'existe pas");
            }
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }

    /**
     *  Commande d'affichage des informations sur un client.
     */
    public void afficher(int id) throws SQLException, IFT287Exception, Exception
    {
        try
        {
            if (clients.getClient(id) == null) {
                throw new IFT287Exception("Client inexistant: " + id);
            }

            clients.afficherClient(id);
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
}
