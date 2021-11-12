package AubergeInn;

import javax.persistence.TypedQuery;
import java.util.List;

public class TableClients {

    private Connexion cx;
    private TypedQuery<TupleClient> stmtExiste;

    public TableClients(Connexion cx) {
        this.cx = cx;
        stmtExiste = cx.getConnection().createQuery("select c from TupleClient c where c.m_idClient = :idClient", TupleClient.class);
    }

    public Connexion getConnexion() {
        return cx;
    }

    public boolean existe(int idClient) {
        stmtExiste.setParameter("idClient", idClient);
        return !stmtExiste.getResultList().isEmpty();
    }

    /**
     * Lecture d'un client.
     */
    public TupleClient getClient(int idClient) {
        stmtExiste.setParameter("idClient", idClient);
        List<TupleClient> lTupleClient = stmtExiste.getResultList();
        if (!lTupleClient.isEmpty())
        {
            return lTupleClient.get(0);
        }
        else
        {
            return null;
        }
    }

    /**
     * Ajout d'un nouveau tupleClient.
     */
    public TupleClient ajouter(TupleClient tupleClient) {
        cx.getConnection().persist(tupleClient);
        return tupleClient;
    }

    /**
     * Suppression d'un tupleClient.
     */
    public boolean supprimer(TupleClient tupleClient) {
        if (tupleClient != null)
        {
            cx.getConnection().remove(tupleClient);
            return true;
        }
        return false;
    }
}
