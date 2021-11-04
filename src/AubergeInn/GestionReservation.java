package AubergeInn;

import java.util.Date;

public class GestionReservation {

    private Connexion cx;
    private Reservations reservations;
    private Clients clients;
    private Chambres chambres;

    public GestionReservation(Reservations reservations, Clients clients, Chambres chambres)
            throws IFT287Exception
    {
        this.cx = reservations.getConnexion();
        if (chambres.getConnexion() != reservations.getConnexion() || clients.getConnexion() != reservations.getConnexion())
            throw new IFT287Exception("Les collections d'objets n'utilisent pas la même connexion au serveur");
        this.reservations = reservations;
        this.chambres = chambres;
        this.clients = clients;
    }

    public void reserver(int idClient, int idChambre, Date dateDebut, Date dateFin)
        throws Exception
    {
        try {
            cx.demarreTransaction();

            if (!clients.existe(idClient))
            {
                throw new IFT287Exception("Client inexistant: " + idClient);
            }
            if (!chambres.existe(idChambre))
            {
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            }

            if (dateDebut.after(dateFin))
            {
                throw new IFT287Exception("La date de début est après la date de fin: " + dateDebut + ">" + dateFin);
            }

            Client cl = clients.getClient(idClient);
            Chambre ch = chambres.getChambre(idChambre);
            if (reservations.existe(cl, ch))
            {
                throw new IFT287Exception("Réservation déjà existante: " + idClient + "/" + idChambre);
            }

            Reservation r = new Reservation(cl, ch, dateDebut, dateFin);

            reservations.reserver(r);
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
