package AubergeInn;

import org.bson.Document;
import java.util.LinkedList;
import java.util.List;


public class TupleClient {

    private int m_idClient;
    private String m_prenom;
    private String m_nom;
    private int m_age;

    private List<TupleReservation> m_clientreservation;

    public TupleClient(Document d)
    {
        m_idClient = d.getInteger("m_idClient");
        m_prenom = d.getString("m_prenom");
        m_nom = d.getString("m_nom");
        m_age = d.getInteger("m_age");

        // TODO; Vérifier si on peut mettre des listes dans un document
        m_clientreservation = (List<TupleReservation>) d.get("m_clientreservation");
    }

    public TupleClient(int idClient, String prenom, String nom, int age) {
        m_idClient = idClient;
        m_prenom = prenom;
        m_nom = nom;
        m_age = age;

        // TODO; Vérifier si on peut mettre des listes dans un document
        m_clientreservation = new LinkedList<TupleReservation>();
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

    public void ajoutReservation(TupleReservation r){m_clientreservation.add(r);}
    public void supprimerReservation(TupleReservation r){m_clientreservation.remove(r);}

    public Document toDocument() {
        return new Document().append("m_idClient", m_idClient)
                .append("m_prenom", m_prenom)
                .append("m_nom", m_nom)
                .append("m_age", m_age)
                .append("m_clientreservation", m_clientreservation);
    }

    //TODO: mettre ça dans tableClient
    /**
     *  Pour afficher un client
     */
    public String print()
    {
        StringBuffer toPrint = new StringBuffer("");
        toPrint.append("Identifiant du client: " + m_idClient + "\n");
        toPrint.append("Nom: " + m_nom + "\n");
        toPrint.append("Prenom: " + m_prenom + "\n");
        toPrint.append("Age: " + m_age + "\n");
        toPrint.append("Réservation du client: \n");
        for (TupleReservation r:m_clientreservation) {
            toPrint.append("------\n");
            TupleChambre c = r.getM_Chambre();
            toPrint.append("Identifiant de la chambre: " + c.getM_idChambre() + "\n");
            toPrint.append("Date de début: "+ r.getM_datedebut().toString() + "\n");
            toPrint.append("Date de Fin: "+ r.getM_dateFin().toString() + "\n");
            toPrint.append("Prix: " + c.getPrixTotal() + "$\n");
        }
        return toPrint.toString();
    }
}
