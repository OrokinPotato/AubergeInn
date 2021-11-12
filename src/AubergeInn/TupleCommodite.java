package AubergeInn;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class TupleCommodite {

    @Id
    @GeneratedValue
    private long m_id;

    private int m_idCom;
    private String m_desc;
    private double m_prix;

    @ManyToMany(mappedBy = "m_commoditechambre", cascade = CascadeType.ALL)
    private List<TupleChambre> m_chambre;

    public TupleCommodite(int idCom, String description, double prix) {
        m_idCom = idCom;
        m_desc = description;
        m_prix = prix;
        m_chambre = new LinkedList<TupleChambre>();
    }

    public TupleCommodite() {

    }

    public long getM_id() {
        return m_id;
    }

    public int getM_idcom() {
        return m_idCom;
    }

    public String getM_desc() {
        return m_desc;
    }

    public double getM_prix() {
        return m_prix;
    }

    public List<TupleChambre> getM_chambre() {
        return m_chambre;
    }
}
