package AubergeInn;

import java.util.Date;
import org.bson.Document;

public class TupleReservation {

    private int m_idReservation;
    private int m_idClient;
    private int m_idChambre;

    private Date m_dateDebut;
    private Date m_dateFin;

    public TupleReservation(Document d)
    {
        m_idReservation = d.getInteger("idReservation");
        m_idClient = d.getInteger("idClient");
        m_idChambre = d.getInteger("idChambre");
        m_dateDebut = d.getDate("dateDebut");
        m_dateFin = d.getDate("dateFin");
    }

    public TupleReservation(int idReservation, int idClient, int idChambre, Date dateDebut, Date dateFin) {
        m_idReservation = idReservation;
        m_dateDebut = dateDebut;
        m_dateFin = dateFin;

        m_idClient = idClient;
        m_idChambre = idChambre;
    }

    public TupleReservation() {

    }

    public int getM_idReservation(){ return m_idReservation; }

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
}
