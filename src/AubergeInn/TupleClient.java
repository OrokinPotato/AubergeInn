package AubergeInn;

import org.bson.Document;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class TupleClient {

    private int m_idClient;
    private String m_prenom;
    private String m_nom;
    private int m_age;

    private List<TupleReservation> m_clientReservation = new ArrayList<TupleReservation>();

    public TupleClient(Document d)
    {
        m_idClient = d.getInteger("m_idClient");
        m_prenom = d.getString("m_prenom");
        m_nom = d.getString("m_nom");
        m_age = d.getInteger("m_age");

        // TODO; Vérifier si on peut mettre des listes dans un document
        m_clientReservation = (List<TupleReservation>) d.get("m_clientreservation");
    }

    public TupleClient(int idClient, String prenom, String nom, int age) {
        m_idClient = idClient;
        m_prenom = prenom;
        m_nom = nom;
        m_age = age;

        // TODO; Vérifier si on peut mettre des listes dans un document
        m_clientReservation = new ArrayList<TupleReservation>();
    }

    public int getM_idClient() {
        return m_idClient;
    }

    public String getM_prenom() {
        return m_prenom;
    }

    public String getM_nom() {
        return m_nom;
    }

    public int getM_age() {
        return m_age;
    }

    public void ajoutReservation(TupleReservation r){m_clientReservation.add(r);}
    public void supprimerReservation(TupleReservation r){m_clientReservation.remove(r);}

    public Document toDocument() {
        return new Document().append("m_idClient", m_idClient)
                .append("m_prenom", m_prenom)
                .append("m_nom", m_nom)
                .append("m_age", m_age)
                .append("m_clientreservation", m_clientReservation);
    }
}
