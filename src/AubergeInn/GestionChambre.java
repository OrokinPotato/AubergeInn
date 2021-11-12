package AubergeInn;

import java.util.List;

public class GestionChambre {

    private Connexion cx;
    private Chambres chambres;
    private Reservations reservations;

    public GestionChambre(Chambres chambres, Reservations reservations)
            throws IFT287Exception
    {
        this.cx = chambres.getConnexion();
        if (chambres.getConnexion() != reservations.getConnexion())
            throw new IFT287Exception("Les collections d'objets n'utilisent pas la même connexion au serveur");
        this.chambres = chambres;
        this.reservations = reservations;
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
    // TODO: else pour ne pas fermer l'app si la chamvre existe pas
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            }

            List<Commodite> lCom = c.getM_commoditechambre();
            if (reservations.getReservationChambre(c) != null)
            {
                throw new IFT287Exception("Chambre encore réservée: " + idChambre);
            }
            if (!lCom.isEmpty())
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

    public void afficherChambre(int idChambre) throws IFT287Exception
    {
        try {
            cx.demarreTransaction();

            if (!chambres.existe(idChambre))
            {
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            }

            Chambre c = chambres.getChambre(idChambre);
            System.out.println(c.print());

            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }

    public void afficherChambresLibres() {
        try {
            cx.demarreTransaction();
            List<Chambre> lChambre = chambres.getAllChambre();

            for (Chambre c:lChambre)
            {
                if (c.getM_chambrereservation().isEmpty())
                {
                    System.out.println(c.print());
                }
            }
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw  e;
        }
    }
}
