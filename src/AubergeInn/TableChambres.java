package AubergeInn;

import javax.persistence.TypedQuery;
import java.util.List;

public class TableChambres {

    private TypedQuery<TupleChambre> stmtExiste;
    private TypedQuery<TupleChambre> stmtSelectAll;
    private Connexion cx;

    public TableChambres(Connexion cx) {
        this.cx = cx;
        stmtExiste = cx.getConnection().createQuery("select c from TupleChambre c where c.m_idChambre = :idChambre", TupleChambre.class);
        stmtSelectAll = cx.getConnection().createQuery("select c from TupleChambre c", TupleChambre.class);
    }

    public Connexion getConnexion() {
        return cx;
    }

    public TupleChambre getChambre(int idChambre) {
        stmtExiste.setParameter("idChambre", idChambre);
        List<TupleChambre> lTupleChambre = stmtExiste.getResultList();
        if (!lTupleChambre.isEmpty())
        {
            return lTupleChambre.get(0);
        }
        else
        {
            return null;
        }
    }

    public List<TupleChambre> getAllChambre(){
        List<TupleChambre> lTupleChambre = stmtSelectAll.getResultList();
        if (!lTupleChambre.isEmpty())
        {
            return lTupleChambre;
        }
        else
        {
            return null;
        }
    }

    public boolean existe(int idChambre) {
        stmtExiste.setParameter("idChambre", idChambre);
        return !stmtExiste.getResultList().isEmpty();
    }

    public TupleChambre ajouter(TupleChambre c) {
        cx.getConnection().persist(c);
        return c;
    }

    public boolean supprimer(TupleChambre c) {
        if (c != null)
        {
            cx.getConnection().remove(c);
            return true;
        }
        return false;
    }
}
