package AubergeInn;

public class GestionClient {

    private Connexion cx;
    private TableClients tableClients;
    private TableReservations tableReservations;

    public GestionClient(TableClients tableClients, TableReservations tableReservations) throws IFT287Exception
    {
        this.cx = tableClients.getConnexion();
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
            cx.demarreTransaction();
            TupleClient c = new TupleClient(idClient, prenom, nom, age);

            if (tableClients.existe(idClient))
            {
                System.out.println("Client déjà existant: " + idClient);
            }
            else
            {
                tableClients.ajouter(c);
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

            TupleClient c = tableClients.getClient(idClient);
            if (c == null)
            {
                throw new IFT287Exception("Client inexistant: " + idClient);
            }
            if (tableReservations.getReservationClient(c) != null)
            {
                throw new IFT287Exception("Client ayant encore des réservations: " + idClient);
            }

            if (!tableClients.supprimer(c))
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

            if (!tableClients.existe(idClient))
            {
                throw new IFT287Exception("Client inexistant: " + idClient);
            }

            TupleClient c = tableClients.getClient(idClient);
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
