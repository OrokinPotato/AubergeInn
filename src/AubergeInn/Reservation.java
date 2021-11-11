package AubergeInn;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservation {


    @Id
    @GeneratedValue
    private long m_id;

    //private int m_idClient;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client m_client;

    //private int m_idChambre;
    @ManyToOne(fetch = FetchType.LAZY)
    private Chambre m_chambre;

    private Date m_datedebut;
    private Date m_dateFin;


    public Reservation(Client cl, Chambre ch, Date dateDebut, Date dateFin) {
        m_datedebut = dateDebut;
        m_dateFin = dateFin;

        m_client = cl;
        m_chambre = ch;
    }

    public Reservation() {

    }

    public long getM_id() {
        return m_id;
    }

    public Client getM_client() {
        return m_client;
    }

    public Chambre getM_chambre() {
        return m_chambre;
    }

    public Date getM_datedebut() {
        return m_datedebut;
    }

    public Date getM_dateFin() {
        return m_dateFin;
    }
}
