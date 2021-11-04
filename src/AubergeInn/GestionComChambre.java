package AubergeInn;

public class GestionComChambre {

    private Connexion cx;
    private Chambres chambres;
    private Commodites commodites;

    public GestionComChambre(Commodites commodites, Chambres chambres) throws IFT287Exception {
        this.cx = commodites.getConnexion();
        if (chambres.getConnexion() != commodites.getConnexion())
            throw new IFT287Exception("Les collections d'objets n'utilisent pas la même connexion au serveur");
        this.chambres = chambres;
        this.commodites = commodites;
    }

    /**
     *  Commande d'ajout d'une commodite à une chambre.
     */
    public void inclure(int idChambre, int idCom)
        throws IFT287Exception, Exception
    {
        try {
            cx.demarreTransaction();

            if (!chambres.existe(idChambre))
            {
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            }
            if (!commodites.existe(idCom))
            {
                throw new IFT287Exception("Commodite inexistante: " + idCom);
            }
            Chambre ch = chambres.getChambre(idChambre);
            Commodite co = commodites.getCommodite(idCom);
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
}
