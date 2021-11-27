package AubergeInn;

import java.util.ArrayList;

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

                tableClients.ajouter(new TupleClient(idClient, prenom, nom, age));

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
            if (tableReservations.getReservationClient(idClient) != null)
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
            List<TupleReservation> lReservation = tableReservations.getReservationClient(idClient);
            System.out.println(print(client, lReservation));
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    /**
     *  Pour afficher un client
     */
    public String print(TupleClient client, ArrayList<TupleReservation> reservations)
    {
        StringBuffer toPrint = new StringBuffer("");
        toPrint.append("Identifiant du client: " + client.getM_idClient() + "\n");
        toPrint.append("Nom: " + client.getM_nom() + "\n");
        toPrint.append("Prenom: " + client.getM_prenom() + "\n");
        toPrint.append("Age: " + client.getM_age() + "\n");
        toPrint.append("Réservation du client: \n");

        for (TupleReservation r:reservations) {

            toPrint.append("------\n");
            //TODO: changer ça pour qu'on aille chercher les infos des chambres impliquées dans les réservations du client

            TupleChambre c = r.getM_Chambre();
            toPrint.append("Identifiant de la chambre: " + c.getM_idChambre() + "\n");
            toPrint.append("Date de début: "+ r.getM_datedebut().toString() + "\n");
            toPrint.append("Date de Fin: "+ r.getM_dateFin().toString() + "\n");
            toPrint.append("Prix: " + c.getPrixTotal() + "$\n");
        }
        return toPrint.toString();
    }
}
