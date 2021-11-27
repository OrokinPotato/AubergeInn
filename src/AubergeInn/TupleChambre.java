package AubergeInn;

import org.bson.Document;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class TupleChambre {

    private int m_idChambre;
    private String m_nomChambre;
    private String m_typelit;
    private double m_prix;

    private ArrayList<TupleCommodite> m_commoditechambre = new ArrayList<TupleCommodite>();

    public TupleChambre(Document d)
    {
        m_idChambre = d.getInteger("m_idChambre");
        m_nomChambre = d.getString("m_nomChambre");
        m_typelit = d.getString("m_typelit");
        m_prix = d.getDouble("m_prix");

        m_commoditechambre = (ArrayList<TupleCommodite>) d.get("m_commoditechambre");
    }

    public TupleChambre(int idChambre, String nomChambre, String typeLit, double prix) {
        m_idChambre = idChambre;
        m_nomChambre = nomChambre;
        m_typelit = typeLit;
        m_prix = prix;

        m_commoditechambre = new ArrayList<TupleCommodite>();
    }


    public int getM_idChambre() {
        return m_idChambre;
    }

    public double getPrixTotal()
    {
        double fPrix = 0;
        for (TupleCommodite c:m_commoditechambre) {
            fPrix += c.getM_prix();
        }
        return fPrix + m_prix;
    }

    public ArrayList<TupleCommodite> getM_commoditechambre() {
        return m_commoditechambre;
    }

    public void ajoutCommodite(TupleCommodite c){m_commoditechambre.add(c);}
    public void supprimerCommodite(TupleCommodite c){m_commoditechambre.remove(c);}

    /**
     *  Pour afficher une chambre
     */
    public String print()
    {
        StringBuffer toPrint = new StringBuffer("");
        toPrint.append("Identifiant de chambre: " + m_idChambre + "\n");
        toPrint.append("Nom de chambre: " + m_nomChambre + "\n");
        toPrint.append("Type de lit: " + m_typelit + "\n");
        toPrint.append("Prix de base: " + m_prix + "$\n");
        toPrint.append("Commoditées offertes: \n");
        for (TupleCommodite c:m_commoditechambre) {
            toPrint.append("------\n");
            toPrint.append("Identifiant de commodité: " + c.getM_idCom() + "\n");
            toPrint.append("Description: " + c.getM_desc() + "\n");
            toPrint.append("Prix: " + c.getM_prix() + "$\n");
            toPrint.append("------\n");
        }
        return toPrint.toString();
    }

    public Document toDocument()
    {
        return new Document().append("m_idChambre", m_idChambre)
                .append("m_nomChambre", m_nomChambre)
                .append("m_typelit", m_typelit)
                .append("m_prix", m_prix)
                .append("m_commoditechambre", m_commoditechambre);
    }
}
