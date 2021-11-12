package AubergeInn;

public class GestionClient {

    private Connexion cx;
    private Clients clients;
    private Reservations reservations;

    public GestionClient(Clients clients, Reservations reservations) throws IFT287Exception
    {
        this.cx = clients.getConnexion();
        if (clients.getConnexion() != reservations.getConnexion())
            throw new IFT287Exception("Les collections d'objets n'utilisent pas la même connexion au serveur");
        this.clients = clients;
        this.reservations = reservations;
    }

    /**
     *  Commande d'ajout d'un client.
     */
    public void ajouter(int idClient, String prenom, String nom, int age)
        throws IFT287Exception, Exception
    {
        try
        {
            cx.demarreTransaction();
            Client c = new Client(idClient, prenom, nom, age);

            if (clients.existe(idClient))
            {
                System.out.println("Client déjà existant: " + idClient);
            }
            else
            {
                clients.ajouter(c);
                cx.commit();
            }
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }

    }

    /**
     *  Commande de retrait d'un client.
     */
    public void supprimer(int idClient) throws IFT287Exception, Exception
    {
        try
        {
            cx.demarreTransaction();

            Client c = clients.getClient(idClient);
            if (c == null)
            {
                throw new IFT287Exception("Client inexistant: " + idClient);
            }
            if (reservations.getReservationClient(c) != null)
            {
                throw new IFT287Exception("Client ayant encore des réservations: " + idClient);
            }

            if (!clients.supprimer(c))
            {
                throw new IFT287Exception("Client inexistant: " + idClient);
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
    public void afficherClient(int idClient) throws IFT287Exception {
        try {
            cx.demarreTransaction();

            if (!clients.existe(idClient))
            {
                throw new IFT287Exception("Client inexistant: " + idClient);
            }

            Client c = clients.getClient(idClient);
            System.out.println(c.print());

            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
}
