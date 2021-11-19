package AubergeInn;

import java.util.Date;

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

            TupleClient clients = tableClients.getClient(idClient);
            TupleChambre chambre = tableChambres.getChambre(idChambre);
            if (tableReservations.existe(clients, chambre))
            {
                throw new IFT287Exception("Réservation déjà existante: " + idClient + "/" + idChambre);
            }

            TupleReservation reservation = new TupleReservation(clients, chambre, dateDebut, dateFin);

            tableReservations.reserver(reservation);
            clients.ajoutReservation(reservation);
            chambre.ajoutReservation(reservation);
            chambre.ajoutReservation(reservation);
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
