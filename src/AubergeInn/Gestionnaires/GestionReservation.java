package AubergeInn.Gestionnaires;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;

import AubergeInn.Tables.*;

import AubergeInn.Tuples.tupleChambre;
import AubergeInn.Tuples.tupleCommodite;
import AubergeInn.Tuples.tupleCommoditeChambre;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class GestionReservation {

    private Connexion cx;
    private tableReservations reservations;
    private tableChambres chambres;
    private tableClients clients;
    private tableComChambre comChambre;
    private tableCommodites commodites;

    public GestionReservation(tableReservations r,
                              tableClients cl,
                              tableChambres ch,
                              tableComChambre cc,
                              tableCommodites co)
            throws IFT287Exception
    {
        this.cx = r.getConnexion();
        if (cl.getConnexion() != ch.getConnexion() || this.cx != cl.getConnexion() || cc.getConnexion() != cl.getConnexion()){
            throw new IFT287Exception("Differente connexions entre les table Reservations, Clients,  et Chambres");
        }
        this.reservations = r;
        this.chambres = ch;
        this.clients = cl;
        this.comChambre = cc;
        this.commodites = co;
    }

    /**
     *  Commande d'ajout d'une reservation.
     */
    public void reserver(int clientId, int chambreId, Date dateDebut, Date dateFin)
            throws SQLException, IFT287Exception, Exception
    {
        try {
            if (clients.getClient(clientId) == null)
            {
                throw new IFT287Exception("Le client " + clientId + " n'existe pas dans la base de données");
            }
            if (reservations.existe(clientId, chambreId)) {
                throw new IFT287Exception("La réservation client " + clientId + " + chambre " + chambreId + "déjà existante");
            }
            if (dateDebut.after(dateFin)) {
                throw new IFT287Exception("La date de fin est avant la date de début");
            }

            //Calcul du prix de la reservation
            tupleChambre tuChambre = chambres.getChambre(chambreId);
            if (tuChambre == null) {
                throw new IFT287Exception("La chambre " + chambreId + " n'existe pas dans la base de données");
            }
            double resPrix = tuChambre.getPrix();

            List<tupleCommoditeChambre> comListe = comChambre.getAllCommodite(chambreId);
            for (tupleCommoditeChambre i : comListe) {
                tupleCommodite tuCom = commodites.getCommodite(i.getCommoditeId());
                resPrix += tuCom.getPrix();
            }

            reservations.reserver(clientId, chambreId, dateDebut, dateFin, resPrix);

            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
}
