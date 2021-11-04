package AubergeInn;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Chambre {

    @Id
    @GeneratedValue
    private long m_id;

    private int m_idChambre;
    private String m_nomchambre;
    private String m_typelit;
    private double m_prix;

    @OneToMany(mappedBy = "m_chambre")
    @OrderBy("m_datedebut")
    private List<Reservation> m_chambrereservation;

    @ManyToMany(targetEntity = Commodite.class, cascade = {CascadeType.ALL})
    @JoinTable(name = "comChambre", joinColumns = { @JoinColumn(name = "idChambre") },
            inverseJoinColumns = { @JoinColumn(name = "idCommodite") })
    private List<Commodite> m_commoditechambre;


    public Chambre(int idChambre, String nomChambre, String typeLit, double prix) {
        m_idChambre = idChambre;
        m_nomchambre = nomChambre;
        m_typelit = typeLit;
        m_prix = prix;
        m_chambrereservation = new LinkedList<Reservation>();
        m_commoditechambre = new LinkedList<Commodite>();
    }

    public long getM_id() {
        return m_id;
    }

    public int getM_idChambre() {
        return m_idChambre;
    }

    public String getM_nomchambre() {
        return m_nomchambre;
    }

    public String getM_typelit() {
        return m_typelit;
    }

    public double getM_prix() {
        return m_prix;
    }
    public double getPrixTotal()
    {
        double fPrix = 0;
        for (Commodite c:m_commoditechambre) {
            fPrix += c.getM_prix();
        }
        return fPrix + m_prix;
    }

    public List<Reservation> getM_chambrereservation() {
        return m_chambrereservation;
    }
    public List<Commodite> getM_commoditechambre() {
        return m_commoditechambre;
    }

    public void ajoutReservation(Reservation r){m_chambrereservation.add(r);}
    public void supprimerReservation(Reservation r){m_chambrereservation.remove(r);}

    public void ajoutCommodite(Commodite c){m_commoditechambre.add(c);}
    public void supprimerCommodite(Commodite c){m_commoditechambre.remove(c);}

    /**
     *  Pour afficher une chambre
     */
    public String print()
    {
        StringBuffer toPrint = new StringBuffer("");
        toPrint.append("Identifiant de chambre: " + m_idChambre + "\n");
        toPrint.append("Nom de chambre: " + m_nomchambre + "\n");
        toPrint.append("Type de lit: " + m_typelit + "\n");
        toPrint.append("Prix de base: " + m_prix + "$\n");
        toPrint.append("Commoditées offertes: \n");
        for (Commodite c:m_commoditechambre) {
            toPrint.append("------\n");
            toPrint.append("Identifiant do commodité: " + c.getM_idcom() + "\n");
            toPrint.append("Description: " + c.getM_desc() + "\n");
            toPrint.append("Prix: " + c.getM_prix() + "$\n");
            toPrint.append("------\n");
        }
        return toPrint.toString();
    }
}
