package AubergeInn;

public class GestionCommodite {

    private Connexion cx;
    private TableCommodites tableCommodites;

    public GestionCommodite(TableCommodites tableCommodites) {
        this.cx = tableCommodites.getConnexion();
        this.tableCommodites = tableCommodites;
    }

    public void ajouter(int idCom, String description, double prix)
            throws IFT287Exception, Exception
    {
        try {
            cx.demarreTransaction();

            TupleCommodite com = new TupleCommodite(idCom, description, prix);

            if (tableCommodites.existe(idCom))
            {
                throw new IFT287Exception("Commodite déjà existante: " + idCom);
            }

            tableCommodites.ajouter(com);
            cx.commit();
        }
        catch (Exception e) {
            cx.rollback();
            throw e;
        }

    }
}
