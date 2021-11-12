package AubergeInn;

import java.util.Date;

public class GestionReservation {

    private Connexion cx;
    private TableReservations tableReservations;
    private TableClients tableClients;
    private TableChambres tableChambres;

    public GestionReservation(TableReservations tableReservations, TableClients tableClients, TableChambres tableChambres)
            throws IFT287Exception
    {
        this.cx = tableReservations.getConnexion();
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
            cx.demarreTransaction();

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

            TupleClient cl = tableClients.getClient(idClient);
            TupleChambre ch = tableChambres.getChambre(idChambre);
            if (tableReservations.existe(cl, ch))
            {
                throw new IFT287Exception("Réservation déjà existante: " + idClient + "/" + idChambre);
            }

            TupleReservation r = new TupleReservation(cl, ch, dateDebut, dateFin);

            tableReservations.reserver(r);
            cl.ajoutReservation(r);
            ch.ajoutReservation(r);

            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
}
