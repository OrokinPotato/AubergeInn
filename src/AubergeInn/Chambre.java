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
    public String toString()
    {
        //TODO
    }
}
