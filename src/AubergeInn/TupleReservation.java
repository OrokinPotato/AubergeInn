package AubergeInn;

import java.util.Date;
import org.bson.Document;

public class TupleReservation {

    //private int m_idReservation;
    private int m_idClient;
    private int m_idChambre;

    // TODO: Verifier l'utilisation d'objet non standard (pas un int, char, etc.) dans un doc bson
    private TupleClient m_client;
    private TupleChambre m_chambre;

    private Date m_dateDebut;
    private Date m_dateFin;

    public TupleReservation(Document d)
    {
        //m_idReservation = d.getInteger("m_idReservation");
        m_idClient = d.getInteger("m_idClient");
        m_idChambre = d.getInteger("m_idChambre");
        m_dateDebut = d.getDate("m_dateDebut");
        m_dateFin = d.getDate("m_dateFin");
    }

    public TupleReservation(int idClient, int idChambre, Date dateDebut, Date dateFin) {
        //m_idReservation = idReservation;
        m_dateDebut = dateDebut;
        m_dateFin = dateFin;

        m_idClient = idClient;
        m_idChambre = idChambre;
    }

    //public int getM_idReservation(){ return m_idReservation; }

    public TupleClient getM_client() {
        return m_client;
    }

    public TupleChambre getM_Chambre() {
        return m_chambre;
    }

    public Date getM_datedebut() {
        return m_dateDebut;
    }

    public Date getM_dateFin() {
        return m_dateFin;
    }

    public Document toDocument()
    {
        return new Document().append("m_idClient", m_idClient)
                .append("m_client", m_client)
                .append("m_idChambre", m_idChambre)
                .append("m_chambre", m_chambre)
                .append("m_dateDebut", m_dateDebut)
                .append("m_dateFin", m_dateFin);
    }
}
