package AubergeInn;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue
    private long m_id;

    private int m_idClient;
    private String m_prenom;
    private String m_nom;
    private int m_age;

    @OneToMany(mappedBy = "m_client")
    @OrderBy("m_datedebut")
    private List<Reservation> m_clientreservation;

    public Client(){}

    public Client(int idClient, String prenom, String nom, int age) {
        m_idClient = idClient;
        m_prenom = prenom;
        m_nom = nom;
        m_age = age;
        m_clientreservation = new LinkedList<Reservation>();
    }

    public long getM_id() {
        return m_id;
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

    public void ajoutReservation(Reservation r){m_clientreservation.add(r);}
    public void supprimerReservation(Reservation r){m_clientreservation.remove(r);}

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
        for (Reservation r:m_clientreservation) {
            toPrint.append("------\n");
            Chambre c = r.getM_chambre();
            toPrint.append("Identifiant de la chambre: " + c.getM_idChambre() + "$\n");
            toPrint.append("Date de début: "+ r.getM_datedebut().toString());
            toPrint.append("Date de Fin: "+ r.getM_dateFin().toString());
            toPrint.append("Prix: " + c.getPrixTotal() + "$\n");
            toPrint.append("------\n");
        }
        return toPrint.toString();
    }
}
