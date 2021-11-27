package AubergeInn;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class TableReservations {

    private Connexion cx;
    private MongoCollection<Document> reservationsCollection;


    public TableReservations(Connexion cx) {
        this.cx = cx;
        reservationsCollection = cx.getDatabase().getCollection("Reservations");
    }

    public Connexion getConnexion() {
        return cx;
    }

    public TupleReservation getReservationClient(TupleClient c) {
        Document d = reservationsCollection.find(eq("m_idClient", c.getM_idClient())).first();
        if (d != null)
        {
            return new TupleReservation(d);
        }
        return null;
    }

    public List<TupleReservation> getReservationChambre(int idChambre) {
        List<TupleReservation> reservationList = new LinkedList<>();
        MongoCursor<Document> d = reservationsCollection.find(eq("m_idChambre", idChambre)).iterator();
        try {
            while (d.hasNext()) {
                reservationList.add(new TupleReservation((d.next())));
            }
        }
        finally {
            d.close();
        }
        return reservationList;
    }

    public boolean existe(int idClient, int idChambre) {
        // TODO: Changer ça. Il faudrait chercher avec la date de début aussi?
        return reservationsCollection.find(eq("m_idClient", idClient)).first() != null || reservationsCollection.find(eq("m_idChambre", idChambre)).first() != null;
    }

    public void reserver(int idClient, int idChambre, Date dateDebut, Date dateFin) {
        TupleReservation r = new TupleReservation(idClient, idChambre, dateDebut, dateFin);
        reservationsCollection.insertOne(r.toDocument());
    }
}
