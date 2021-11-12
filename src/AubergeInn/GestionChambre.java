package AubergeInn;

import java.util.List;

public class GestionChambre {

    private Connexion cx;
    private TableChambres tableChambres;
    private TableReservations tableReservations;

    public GestionChambre(TableChambres tableChambres, TableReservations tableReservations)
            throws IFT287Exception
    {
        this.cx = tableChambres.getConnexion();
        if (tableChambres.getConnexion() != tableReservations.getConnexion())
            throw new IFT287Exception("Les collections d'objets n'utilisent pas la même connexion au serveur");
        this.tableChambres = tableChambres;
        this.tableReservations = tableReservations;
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
            TupleChambre c = new TupleChambre(idChambre, nomChambre, typeLit, prix);

            if (tableChambres.existe(idChambre))
            {
                throw new IFT287Exception("Chambre déjà existante: " + idChambre);
            }
            tableChambres.ajouter(c);
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

            TupleChambre c = tableChambres.getChambre(idChambre);
            if (c == null)
            {
    // TODO: else pour ne pas fermer l'app si la chamvre existe pas
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            }

            List<TupleCommodite> lCom = c.getM_commoditechambre();
            if (tableReservations.getReservationChambre(c) != null)
            {
                throw new IFT287Exception("Chambre encore réservée: " + idChambre);
            }
            if (!lCom.isEmpty())
            {
                throw new IFT287Exception("Commodites encore assignées à cette chambre: " + idChambre);
            }

            if (!tableChambres.supprimer(c))
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

            if (!tableChambres.existe(idChambre))
            {
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            }

            TupleChambre c = tableChambres.getChambre(idChambre);
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
            List<TupleChambre> lTupleChambre = tableChambres.getAllChambre();

            for (TupleChambre c: lTupleChambre)
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
