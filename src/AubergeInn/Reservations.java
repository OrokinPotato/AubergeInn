package AubergeInn;

import javax.persistence.TypedQuery;
import java.util.List;

public class Reservations {

    private Connexion cx;
    private TypedQuery<Reservation> stmtExiste;
    private TypedQuery<Reservation> stmtExisteClient;
    private TypedQuery<Reservation> stmtExisteChambre;


    public Reservations(Connexion cx) {
        this.cx = cx;
        stmtExiste = cx.getConnection().createQuery("select r from Reservation r where r.m_client = :client and r.m_chambre = :chambre", Reservation.class);
        stmtExisteClient = cx.getConnection().createQuery("select r from Reservation r where r.m_client = :client", Reservation.class);
        stmtExisteChambre = cx.getConnection().createQuery("select r from Reservation r where r.m_chambre = :chambre", Reservation.class);
    }

    public Connexion getConnexion() {
        return cx;
    }

    public Reservation getReservationClient(Client c) {
        stmtExisteClient.setParameter("client", c);
        List<Reservation> lRes = stmtExisteClient.getResultList();
        if (!lRes.isEmpty())
        {
            return lRes.get(0);
        }
        return null;
    }

    public Reservation getReservationChambre(Chambre c) {
        stmtExisteChambre.setParameter("chambre", c);
        List<Reservation> lRes = stmtExisteChambre.getResultList();
        if (!lRes.isEmpty())
        {
            return lRes.get(0);
        }
        return null;
    }

    public boolean existe(Client client, Chambre chambre) {
        stmtExiste.setParameter("client", client);
        stmtExiste.setParameter("chambre", chambre);
        return !stmtExiste.getResultList().isEmpty();
    }

    public void reserver(Reservation r) {
        cx.getConnection().persist(r);
    }
}
