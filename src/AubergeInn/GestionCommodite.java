package AubergeInn;

public class GestionCommodite {

    private TableCommodites tableCommodites;

    public GestionCommodite(TableCommodites tableCommodites) {
        this.tableCommodites = tableCommodites;
    }

    public void ajouter(int idCom, String description, double prix)
            throws IFT287Exception, Exception
    {
        try {

            if (tableCommodites.existe(idCom))
            {
                throw new IFT287Exception("Commodite déjà existante: " + idCom);
            }

            tableCommodites.ajouter(new TupleCommodite(idCom, description, prix));
        }
        catch (Exception e)
        {
            throw e;
        }

    }
}
