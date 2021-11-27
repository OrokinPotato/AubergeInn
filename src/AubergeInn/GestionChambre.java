package AubergeInn;

import java.util.Date;
import java.util.List;

public class GestionChambre {

    private TableChambres tableChambres;
    private TableReservations tableReservations;

    public GestionChambre(TableChambres tableChambres, TableReservations tableReservations)
            throws IFT287Exception
    {
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
            if (tableChambres.existe(idChambre))
            {
                //Vérifie si la chambre existe déjà
                throw new IFT287Exception("Chambre déjà existante: " + idChambre);
            }
            //Ajout de la chambre dans la table des chambres
            tableChambres.ajouter(idChambre, nomChambre, typeLit, prix);
        }
        catch (Exception e)
        {
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
            TupleChambre chambre = tableChambres.getChambre(idChambre);
            if (chambre == null)
            {
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            }

            if (tableReservations.getReservationChambre(idChambre) != null)
            {
                throw new IFT287Exception("Chambre encore réservée: " + idChambre);
            }
            List<TupleCommodite> lCom = chambre.getM_commoditechambre();
            if (!lCom.isEmpty())
            {
                throw new IFT287Exception("Commodite(s) encore assignée(s) à cette chambre: " + idChambre);
            }

            if (!tableChambres.supprimer(idChambre))
            {
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    // TODO: déplacer cette méthode dans TableChambres?
    public void afficherChambre(int idChambre) throws IFT287Exception
    {
        try {
            if (!tableChambres.existe(idChambre))
            {
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            }
            TupleChambre chambre = tableChambres.getChambre(idChambre);
            System.out.println(chambre.print());
        }
        catch (Exception e)
        {
            throw e;
        }
    }


    public void afficherChambresLibres() throws IFT287Exception {
        try {
            List<TupleChambre> lTupleChambre = tableChambres.getAllChambre();

            for (TupleChambre c: lTupleChambre)
            {
                if (c.getM_chambrereservation().isEmpty())
                {
                    System.out.println(c.print());
                }
            }
        }
        catch (Exception e)
        {
            throw  e;
        }
    }
}
