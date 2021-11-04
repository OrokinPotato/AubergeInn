package AubergeInn;

import javax.persistence.TypedQuery;
import java.util.List;

public class Commodites {

    private TypedQuery<Commodite> stmtExiste;
    private Connexion cx;

    public Commodites(Connexion cx) {
        this.cx = cx;
        stmtExiste = cx.getConnection().createQuery("select c from Commodite c where c.m_idCom = :idCom", Commodite.class);
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
    public Commodite getCommodite(int idCom) {
        stmtExiste.setParameter("idCom", idCom);
        List<Commodite> lCom = stmtExiste.getResultList();
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
    public Commodite ajouter(Commodite com) {
        cx.getConnection().persist(com);
        return com;
    }
}
