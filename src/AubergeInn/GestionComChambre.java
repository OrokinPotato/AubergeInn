package AubergeInn;

public class GestionComChambre {

    private TableChambres tableChambres;
    private TableCommodites tableCommodites;

    public GestionComChambre(TableCommodites tableCommodites, TableChambres tableChambres) throws IFT287Exception
    {
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

            if (!tableChambres.existe(idChambre))
            {
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            }
            if (!tableCommodites.existe(idCom))
            {
                throw new IFT287Exception("Commodite inexistante: " + idCom);
            }
            TupleChambre chambre = tableChambres.getChambre(idChambre);
            TupleCommodite commodite = tableCommodites.getCommodite(idCom);
            if (chambre.getM_commoditechambre().contains(commodite))
            {
                throw new IFT287Exception("Commodite déjà inclue: " + idCom);
            }

            chambre.ajoutCommodite(commodite);
        }
        catch (Exception e)
        {
            throw e;
        }
    }


    public void enlever(int idChambre, int idCom)
            throws IFT287Exception, Exception
    {
        try {

            if (!tableChambres.existe(idChambre))
            {
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            }
            if (!tableCommodites.existe(idCom))
            {
                throw new IFT287Exception("Commodite inexistante: " + idCom);
            }
            TupleChambre chambre = tableChambres.getChambre(idChambre);
            TupleCommodite commodite = tableCommodites.getCommodite(idCom);
            if (!chambre.getM_commoditechambre().contains(commodite))
            {
                throw new IFT287Exception("Commodite non inclue dans la chambre: " + idCom);
            }

            chambre.supprimerCommodite(commodite);
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
