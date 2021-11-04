package AubergeInn;

import javax.persistence.TypedQuery;
import java.util.List;

public class Chambres {

    private TypedQuery<Chambre> stmtExiste;
    private TypedQuery<Chambre> stmtSelectAll;
    private Connexion cx;

    public Chambres(Connexion cx) {
        this.cx = cx;
        stmtExiste = cx.getConnection().createQuery("select c from Chambre c where c.m_idChambre = :idChambre", Chambre.class);
        stmtSelectAll = cx.getConnection().createQuery("select c from Chambre c", Chambre.class);
    }

    public Connexion getConnexion() {
        return cx;
    }

    public Chambre getChambre(int idChambre) {
        stmtExiste.setParameter("idChambre", idChambre);
        List<Chambre> lChambre = stmtExiste.getResultList();
        if (!lChambre.isEmpty())
        {
            return lChambre.get(0);
        }
        else
        {
            return null;
        }
    }

    public List<Chambre> getAllChambre(){
        List<Chambre> lChambre = stmtSelectAll.getResultList();
        if (!lChambre.isEmpty())
        {
            return lChambre;
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

    public Chambre ajouter(Chambre c) {
        cx.getConnection().persist(c);
        return c;
    }

    public boolean supprimer(Chambre c) {
        if (c != null)
        {
            cx.getConnection().remove(c);
            return true;
        }
        return false;
    }
}
