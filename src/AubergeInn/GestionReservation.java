package AubergeInn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestionReservation {

    private TableReservations tableReservations;
    private TableClients tableClients;
    private TableChambres tableChambres;

    public GestionReservation(TableReservations tableReservations, TableClients tableClients, TableChambres tableChambres)
            throws IFT287Exception
    {
        if (tableChambres.getConnexion() != tableReservations.getConnexion() || tableClients.getConnexion() != tableReservations.getConnexion())
            throw new IFT287Exception("Les collections d'objets n'utilisent pas la même connexion au serveur");
        this.tableReservations = tableReservations;
        this.tableChambres = tableChambres;
        this.tableClients = tableClients;
    }

    public void reserver(int idClient, int idChambre, Date dateDebut, Date dateFin)
        throws Exception
    {
        try {

            if (!tableClients.existe(idClient))
            {
                throw new IFT287Exception("Client inexistant: " + idClient);
            }
            if (!tableChambres.existe(idChambre))
            {
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            }

            if (dateDebut.after(dateFin))
            {
                throw new IFT287Exception("La date de début est après la date de fin: " + dateDebut + ">" + dateFin);
            }

            if (tableReservations.existe(idClient, idChambre, dateDebut))
            {
                throw new IFT287Exception("Réservation déjà existante: " + idClient + "/" + idChambre + "/" + dateDebut);
            }

            TupleReservation reservation = new TupleReservation(idClient, idChambre, dateDebut, dateFin);

            tableReservations.reserver(idClient, idChambre, dateDebut, dateFin);
            tableClients.getClient(idClient).ajoutReservation(reservation);

           // tableChambres.getChambre(idChambre).ajoutReservation(reservation);
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
            List<TupleReservation> lRes = tableReservations.getReservationClient(client);

            //Affichage
            StringBuffer toPrint = new StringBuffer("");
            toPrint.append("Identifiant du client: " + client.getM_idClient() + "\n");
            toPrint.append("Nom: " + client.getM_nom() + "\n");
            toPrint.append("Prenom: " + client.getM_prenom() + "\n");
            toPrint.append("Age: " + client.getM_age() + "\n");
            toPrint.append("Réservation du client: \n");


        }
        catch (Exception e)
        {
            throw e;
        }
    }

    /**
     *  Pour afficher un client
     */
    public String print(TupleClient client, List<TupleReservation> reservations)
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
