package AubergeInn;

import java.util.Date;
import org.bson.Document;

public class TupleReservation {

    private int m_idClient;
    private int m_idChambre;

    private Date m_dateDebut;
    private Date m_dateFin;

    public TupleReservation(Document d)
    {
        m_idClient = d.getInteger("idClient");
        m_idChambre = d.getInteger("idChambre");
        m_dateDebut = d.getDate("dateDebut");
        m_dateFin = d.getDate("dateFin");
    }

    public TupleReservation(int idClient, int idChambre, Date dateDebut, Date dateFin) {
        m_idClient = idClient;
        m_idChambre = idChambre;
        m_dateDebut = dateDebut;
        m_dateFin = dateFin;
    }

    public TupleReservation() {

    }


    public int getM_client() {
        return m_idClient;
    }

    public int getM_Chambre() {
        return m_idChambre;
    }

    public Date getM_datedebut() {
        return m_dateDebut;
    }

    public Date getM_dateFin() {
        return m_dateFin;
    }

    public Document toDocument()
    {
        return new Document().append("idClient", m_idClient)
                .append("idChambre", m_idChambre)
                .append("dateDebut", m_dateDebut)
                .append("dateFin", m_dateFin);
    }
}
