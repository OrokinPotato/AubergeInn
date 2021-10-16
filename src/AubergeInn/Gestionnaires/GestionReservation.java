package AubergeInn.Gestionnaires;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;

import AubergeInn.Tables.*;

import AubergeInn.Tuples.TupleChambre;
import AubergeInn.Tuples.TupleCommodite;
import AubergeInn.Tuples.TupleCommoditeChambre;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class GestionReservation {

    private Connexion cx;
    private TableReservations reservations;
    private TableChambres chambres;
    private TableClients clients;
    private TableComChambre comChambre;
    private TableCommodites commodites;

    public GestionReservation(TableReservations r,
                              TableClients cl,
                              TableChambres ch,
                              TableComChambre cc,
                              TableCommodites co)
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
            if (reservations.existe(clientId, chambreId, dateDebut)) {
                throw new IFT287Exception("La réservation client " + clientId + " + chambre " + chambreId + "déjà existante");
            }
            if (dateDebut.after(dateFin)) {
                throw new IFT287Exception("La date de fin est avant la date de début");
            }

            /**
            //Calcul du prix de la reservation
            TupleChambre tuChambre = chambres.getChambre(chambreId);
            if (tuChambre == null) {
                throw new IFT287Exception("La chambre " + chambreId + " n'existe pas dans la base de données");
            }
            double resPrix = tuChambre.getPrix();

            List<TupleCommoditeChambre> comListe = comChambre.getAllCommodite(chambreId);
            for (TupleCommoditeChambre i : comListe) {
                TupleCommodite tuCom = commodites.getCommodite(i.getCommoditeId());
                resPrix += tuCom.getPrix();
            }
            **/
            reservations.reserver(clientId, chambreId, dateDebut, dateFin); //, resPrix);

            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
}
