package AubergeInn;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TupleReservation {


    @Id
    @GeneratedValue
    private long m_id;

    //private int m_idClient;
    @ManyToOne(fetch = FetchType.LAZY)
    private TupleClient m_client;

    //private int m_idChambre;
    @ManyToOne(fetch = FetchType.LAZY)
    private TupleChambre m_chambre;

    private Date m_datedebut;
    private Date m_dateFin;


    public TupleReservation(TupleClient cl, TupleChambre ch, Date dateDebut, Date dateFin) {
        m_datedebut = dateDebut;
        m_dateFin = dateFin;

        m_client = cl;
        m_chambre = ch;
    }

    public TupleReservation() {

    }

    public long getM_id() {
        return m_id;
    }

    public TupleClient getM_client() {
        return m_client;
    }

    public TupleChambre getM_chambre() {
        return m_chambre;
    }

    public Date getM_datedebut() {
        return m_datedebut;
    }

    public Date getM_dateFin() {
        return m_dateFin;
    }
}
