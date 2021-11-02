package AubergeInn;

public class GestionChambre {

    private Connexion cx;
    private Chambres chambres;
    private Reservations reservations;
    private CommoditeChambre comChambres;

    public GestionChambre(Chambres chambres, Reservations reservations, CommoditeChambre comChambres)
            throws IFT287Exception
    {
        this.cx = chambres.getConnexion();
        if (chambres.getConnexion() != reservations.getConnexion() || chambres.getConnexion() != comChambres.getConnexion())
            throw new IFT287Exception("Les collections d'objets n'utilisent pas la même connexion au serveur");
        this.chambres = chambres;
        this.reservations = reservations;
        this.comChambres = comChambres;
    }

    /**
     *  Commande d'ajout d'une chambre.
     */
    public void ajouter(int idChambre, String nomChambre, String typeLit, double prix)
            throws IFT287Exception, Exception
    {
        try
        {
            cx.demarreTransaction();
            Chambre c = new Chambre(idChambre, nomChambre, typeLit, prix);

            if (chambres.existe(idChambre))
            {
                throw new IFT287Exception("Chambre déjà existante: " + idChambre);
            }
            chambres.ajouter(c);
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }

    /**
     *  Commande de retrait d'un client.
     */
    public void supprimer(int idChambre) throws IFT287Exception, Exception
    {
        try
        {
            cx.demarreTransaction();

            Chambre c = chambres.getChambre(idChambre);
            if (c == null)
            {
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            }
            if (reservations.getReservationChambre(c) != null)
            {
                throw new IFT287Exception("Chambre encore réservée: " + idChambre);
            }
            if (comChambres.getAllChambre() != null)
            {
                throw new IFT287Exception("Commodites encore assignées à cette chambre: " + idChambre);
            }

            if (!chambres.supprimer(c))
            {
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            }
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }

    public void afficherChambresLibres() {
    }

    public void afficherChambre(int idChambre) {
    }
}
