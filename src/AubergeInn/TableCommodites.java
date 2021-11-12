package AubergeInn;

import javax.persistence.TypedQuery;
import java.util.List;

public class TableCommodites {

    private TypedQuery<TupleCommodite> stmtExiste;
    private Connexion cx;

    public TableCommodites(Connexion cx) {
        this.cx = cx;
        stmtExiste = cx.getConnection().createQuery("select c from TupleCommodite c where c.m_idCom = :idCom", TupleCommodite.class);
    }

    public Connexion getConnexion() {
        return cx;
    }

    public boolean existe(int idCom) {
        stmtExiste.setParameter("idCom", idCom);
        return !stmtExiste.getResultList().isEmpty();

    }

    /**
     * Lecture d'une commodite.
     */
    public TupleCommodite getCommodite(int idCom) {
        stmtExiste.setParameter("idCom", idCom);
        List<TupleCommodite> lCom = stmtExiste.getResultList();
        if (!lCom.isEmpty())
        {
            return lCom.get(0);
        }
        else
        {
            return null;
        }
    }

    /**
     * Ajout d'une nouvelle commodite.
     */
    public TupleCommodite ajouter(TupleCommodite com) {
        cx.getConnection().persist(com);
        return com;
    }
}
