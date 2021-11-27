package AubergeInn;

import static com.mongodb.client.model.Filters.*;
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

    public List<TupleReservation> getReservationClient(int idClient) {
        List<TupleReservation> reservationList = new LinkedList<>();
        MongoCursor<Document> d = reservationsCollection.find(eq("m_idClient", idClient)).iterator();
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

    public boolean existe(int idClient, int idChambre, Date dateDebut) {
        return reservationsCollection.find(and (eq("m_idClient", idClient), eq("m_idChambre", idChambre), eq("m_dateDebut", dateDebut))).first() !=null;
    }

    public void reserver(int idClient, int idChambre, Date dateDebut, Date dateFin) {
        TupleReservation r = new TupleReservation(idClient, idChambre, dateDebut, dateFin);
        reservationsCollection.insertOne(r.toDocument());
    }
}
