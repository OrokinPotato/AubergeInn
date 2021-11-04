package AubergeInn;

public class GestionCommodite {

    private Connexion cx;
    private Commodites commodites;

    public GestionCommodite(Commodites commodites) {
        this.cx = commodites.getConnexion();
        this.commodites = commodites;
    }

    public void ajouter(int idCom, String description, double prix)
            throws IFT287Exception, Exception
    {
        try {
            cx.demarreTransaction();

            Commodite com = new Commodite(idCom, description, prix);

            if (commodites.existe(idCom))
            {
                throw new IFT287Exception("Commodite déjà existante: " + idCom);
            }

            commodites.ajouter(com);
            cx.commit();
        }
        catch (Exception e) {
            cx.rollback();
            throw e;
        }

    }
}
