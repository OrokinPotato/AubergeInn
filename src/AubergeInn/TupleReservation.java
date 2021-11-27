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
        m_idClient = d.getInteger("m_idClient");
        m_idChambre = d.getInteger("m_idChambre");
        m_dateDebut = d.getDate("m_dateDebut");
        m_dateFin = d.getDate("m_dateFin");
    }

    public TupleReservation(int idClient, int idChambre, Date dateDebut, Date dateFin) {
        m_idClient = idClient;
        m_idChambre = idChambre;
        m_dateDebut = dateDebut;
        m_dateFin = dateFin;
    }

    public int getM_idChambre() {
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
        return new Document().append("m_idClient", m_idClient)
                .append("m_idChambre", m_idChambre)
                .append("m_dateDebut", m_dateDebut)
                .append("m_dateFin", m_dateFin);
    }
}
