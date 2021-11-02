package AubergeInn;

import javax.persistence.TypedQuery;

public class Commodites {

    private TypedQuery<Commodite> stmtExiste;
    private Connexion cx;

    public Commodites(Connexion cx) {
    }
}
