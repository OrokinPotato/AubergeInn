package AubergeInn;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;
import java.util.List;

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

    public TupleReservation getReservationChambre(TupleChambre c) {
        Document d = reservationsCollection.find(eq("m_idChambre", c.getM_idChambre())).first();
        if (d != null)
        {
            return new TupleReservation(d);
        }
        return null;
    }

    public boolean existe(int idClient, int idChambre) {
        return reservationsCollection.find(eq("m_idClient", idClient)).first() != null || reservationsCollection.find(eq("m_idChambre", idChambre)).first() != null;
    }

    public void reserver(TupleReservation r) {
        reservationsCollection.insertOne(r.toDocument());
    }
}
