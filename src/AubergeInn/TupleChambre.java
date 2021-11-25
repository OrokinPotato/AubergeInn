package AubergeInn;

import org.bson.Document;


import java.util.LinkedList;
import java.util.List;


public class TupleChambre {

    private int m_idChambre;
    private String m_nomChambre;
    private String m_typelit;
    private double m_prix;

    private List<TupleReservation> m_chambrereservation;

    private List<TupleCommodite> m_commoditechambre;

    public TupleChambre() {
    }


    public TupleChambre(Document d)
    {
        m_idChambre = d.getInteger("idChambre");
        m_nomChambre = d.getString("nomChambre");
        m_typelit = d.getString("typeLit");
        m_prix = d.getDouble("prix");
        m_chambrereservation = new LinkedList<TupleReservation>();
        m_commoditechambre = new LinkedList<TupleCommodite>();
    }

    public TupleChambre(int idChambre, String nomChambre, String typeLit, double prix) {
        m_idChambre = idChambre;
        m_nomChambre = nomChambre;
        m_typelit = typeLit;
        m_prix = prix;
        m_chambrereservation = new LinkedList<TupleReservation>();
        m_commoditechambre = new LinkedList<TupleCommodite>();
    }


    public int getM_idChambre() {
        return m_idChambre;
    }

    public String getM_nomChambre() {
        return m_nomChambre;
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
        for (TupleCommodite c:m_commoditechambre) {
            fPrix += c.getM_prix();
        }
        return fPrix + m_prix;
    }

    public List<TupleReservation> getM_chambrereservation() {
        return m_chambrereservation;
    }
    public List<TupleCommodite> getM_commoditechambre() {
        return m_commoditechambre;
    }

    public void ajoutReservation(TupleReservation r){m_chambrereservation.add(r);}
    public void supprimerReservation(TupleReservation r){m_chambrereservation.remove(r);}

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
        return new Document().append("idChambre", m_idChambre)
                .append("nomChambre", m_nomChambre)
                .append("typeLit", m_typelit)
                .append("prix", m_prix);
    }
}
