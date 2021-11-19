package AubergeInn;

import org.bson.Document;
import java.util.LinkedList;
import java.util.List;


public class TupleCommodite {


    private int m_idCom;
    private String m_desc;
    private double m_prix;

    @ManyToMany(mappedBy = "m_commoditechambre", cascade = CascadeType.ALL)
    private List<TupleChambre> m_chambre;

    public TupleCommodite(Document d)
    {
        m_idCom = d.getInteger("idReservation");
        m_desc = d.getString("description");
        m_prix = d.getDouble("prix");
        m_chambre = d.get("chambre");
    }

    public TupleCommodite(int idCom, String description, double prix) {
        m_idCom = idCom;
        m_desc = description;
        m_prix = prix;
        m_chambre = new LinkedList<TupleChambre>();
    }

    public TupleCommodite() {

    }

    public int getM_idCom() {
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
