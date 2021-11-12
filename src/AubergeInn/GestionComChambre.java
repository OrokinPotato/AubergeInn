package AubergeInn;

public class GestionComChambre {

    private Connexion cx;
    private TableChambres tableChambres;
    private TableCommodites tableCommodites;

    public GestionComChambre(TableCommodites tableCommodites, TableChambres tableChambres) throws IFT287Exception {
        this.cx = tableCommodites.getConnexion();
        if (tableChambres.getConnexion() != tableCommodites.getConnexion())
            throw new IFT287Exception("Les collections d'objets n'utilisent pas la même connexion au serveur");
        this.tableChambres = tableChambres;
        this.tableCommodites = tableCommodites;
    }

    /**
     *  Commande d'ajout d'une commodite à une chambre.
     */
    public void inclure(int idChambre, int idCom)
        throws IFT287Exception, Exception
    {
        try {
            cx.demarreTransaction();

            if (!tableChambres.existe(idChambre))
            {
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            }
            if (!tableCommodites.existe(idCom))
            {
                throw new IFT287Exception("Commodite inexistante: " + idCom);
            }
            TupleChambre ch = tableChambres.getChambre(idChambre);
            TupleCommodite co = tableCommodites.getCommodite(idCom);
            if (ch.getM_commoditechambre().contains(co))
            {
                throw new IFT287Exception("Commodite déjà inclue: " + idCom);
            }

            ch.ajoutCommodite(co);
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }


    public void enlever(int idChambre, int idCom)
            throws IFT287Exception, Exception
    {
        try {
            cx.demarreTransaction();

            if (!tableChambres.existe(idChambre))
            {
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            }
            if (!tableCommodites.existe(idCom))
            {
                throw new IFT287Exception("Commodite inexistante: " + idCom);
            }
            TupleChambre ch = tableChambres.getChambre(idChambre);
            TupleCommodite co = tableCommodites.getCommodite(idCom);
            if (!ch.getM_commoditechambre().contains(co))
            {
                throw new IFT287Exception("Commodite non inclue dans la chambre: " + idCom);
            }

            ch.supprimerCommodite(co);
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
}
