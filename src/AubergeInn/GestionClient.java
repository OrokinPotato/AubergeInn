package AubergeInn;

public class GestionClient {

    private TableClients tableClients;
    private TableReservations tableReservations;

    public GestionClient(TableClients tableClients, TableReservations tableReservations) throws IFT287Exception
    {
        if (tableClients.getConnexion() != tableReservations.getConnexion())
            throw new IFT287Exception("Les collections d'objets n'utilisent pas la même connexion au serveur");
        this.tableClients = tableClients;
        this.tableReservations = tableReservations;
    }

    /**
     *  Commande d'ajout d'un client.
     */
    public void ajouter(int idClient, String prenom, String nom, int age)
        throws IFT287Exception, Exception
    {
        try
        {
            if (tableClients.existe(idClient))
            {
                throw new IFT287Exception("Client déjà existant: " + idClient);
            }

                tableClients.ajouter(idClient, prenom, nom, age);

        }
        catch (Exception e)
        {
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
            TupleClient client = tableClients.getClient(idClient);
            if (client == null)
            {
                throw new IFT287Exception("Client inexistant: " + idClient);
            }
            if (tableReservations.getReservationClient(client) != null)
            {
                throw new IFT287Exception("Client ayant encore des réservations: " + idClient);
            }

            if (!tableClients.supprimer(client))
            {
                throw new IFT287Exception("Client inexistant: " + idClient);
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    /**
     *  Commande d'affichage des informations sur un client.
     */
    public void afficherClient(int idClient) throws IFT287Exception {
        try {
            if (!tableClients.existe(idClient))
            {
                throw new IFT287Exception("Client inexistant: " + idClient);
            }

            TupleClient client = tableClients.getClient(idClient);
            System.out.println(client.print());
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
