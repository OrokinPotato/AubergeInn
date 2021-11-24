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
     //   stmtExiste = cx.getConnection().createQuery("select r from TupleReservation r where r.m_client = :client and r.m_chambre = :chambre", TupleReservation.class);
      //  stmtExisteClient = cx.getConnection().createQuery("select r from TupleReservation r where r.m_client = :client", TupleReservation.class);
      //  stmtExisteChambre = cx.getConnection().createQuery("select r from TupleReservation r where r.m_chambre = :chambre", TupleReservation.class);
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
        stmtExisteChambre.setParameter("chambre", c);
        List<TupleReservation> lRes = stmtExisteChambre.getResultList();
        if (!lRes.isEmpty())
        {
            return lRes.get(0);
        }
        return null;
    }

    public boolean existe(int idReservation) {
        return reservationsCollection.find(eq("idReservation", idReservation)).first() != null;
    }

    public void reserver(TupleReservation r) {
        cx.getConnection().persist(r);
    }
}
