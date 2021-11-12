package AubergeInn;

import javax.persistence.TypedQuery;
import java.util.List;

public class TableReservations {

    private Connexion cx;
    private TypedQuery<TupleReservation> stmtExiste;
    private TypedQuery<TupleReservation> stmtExisteClient;
    private TypedQuery<TupleReservation> stmtExisteChambre;


    public TableReservations(Connexion cx) {
        this.cx = cx;
        stmtExiste = cx.getConnection().createQuery("select r from TupleReservation r where r.m_client = :client and r.m_chambre = :chambre", TupleReservation.class);
        stmtExisteClient = cx.getConnection().createQuery("select r from TupleReservation r where r.m_client = :client", TupleReservation.class);
        stmtExisteChambre = cx.getConnection().createQuery("select r from TupleReservation r where r.m_chambre = :chambre", TupleReservation.class);
    }

    public Connexion getConnexion() {
        return cx;
    }

    public TupleReservation getReservationClient(TupleClient c) {
        stmtExisteClient.setParameter("client", c);
        List<TupleReservation> lRes = stmtExisteClient.getResultList();
        if (!lRes.isEmpty())
        {
            return lRes.get(0);
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

    public boolean existe(TupleClient tupleClient, TupleChambre tupleChambre) {
        stmtExiste.setParameter("client", tupleClient);
        stmtExiste.setParameter("chambre", tupleChambre);
        return !stmtExiste.getResultList().isEmpty();
    }

    public void reserver(TupleReservation r) {
        cx.getConnection().persist(r);
    }
}
