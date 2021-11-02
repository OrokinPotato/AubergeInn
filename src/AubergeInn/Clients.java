package AubergeInn;

import javax.persistence.TypedQuery;
import java.util.List;

public class Clients {

    private Connexion cx;
    private TypedQuery<Client> stmtExiste;

    public Clients(Connexion cx) {
        this.cx = cx;
        stmtExiste = cx.getConnection().createQuery("select c from Client c where c.m_idClient = :idClient", Client.class);
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
    public Client getClient(int idClient) {
        stmtExiste.setParameter("idClient", idClient);
        List<Client> lClient = stmtExiste.getResultList();
        if (!lClient.isEmpty())
        {
            return lClient.get(0);
        }
        else
        {
            return null;
        }
    }

    /**
     * Ajout d'un nouveau client.
     */
    public Client ajouter(Client client) {
        cx.getConnection().persist(client);
        return client;
    }

    /**
     * Suppression d'un client.
     */
    public boolean supprimer(Client client) {
        if (client != null)
        {
            cx.getConnection().remove(client);
            return true;
        }
        return false;
    }
}
