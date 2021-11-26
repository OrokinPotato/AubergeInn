package AubergeInn;

import org.bson.Document;
import java.util.LinkedList;
import java.util.List;


public class TupleCommodite {


    private int m_idCom;
    private String m_desc;
    private double m_prix;

    private List<TupleChambre> m_chambre;

    public TupleCommodite(Document d)
    {
        m_idCom = d.getInteger("m_idCom");
        m_desc = d.getString("m_desc");
        m_prix = d.getDouble("m_prix");

        // TODO; Vérifier si on peut mettre des listes dans un document
        m_chambre = (List<TupleChambre>) d.get("m_chambre");
    }

    public TupleCommodite(int idCom, String description, double prix) {
        m_idCom = idCom;
        m_desc = description;
        m_prix = prix;

        // TODO; Vérifier si on peut mettre des listes dans un document
        m_chambre = new LinkedList<TupleChambre>();
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

    public Document toDocument() {
        return new Document().append("m_idCom", m_idCom)
                .append("m_desc", m_desc)
                .append("m_prix", m_prix)
                .append("m_chambre", m_chambre);
    }
}
