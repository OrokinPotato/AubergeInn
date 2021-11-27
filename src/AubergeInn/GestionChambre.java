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

            //TODO: vérifier avec la date de réservation > datenow
            if (tableReservations.getReservationChambre(idChambre) != null)
            {
                throw new IFT287Exception("Chambre encore réservée: " + idChambre);
            }

            List<TupleReservation> tupleReservationList = tableReservations.getReservationChambre(idChambre);
            Date dateNow = new Date(System.currentTimeMillis());

            for (TupleReservation tupleReservation : tupleReservationList) {
                if (tupleReservation.getM_dateFin().after(dateNow))
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

            Date dateNow = new Date(System.currentTimeMillis());

            for (TupleChambre c: lTupleChambre)
            {
                List<TupleReservation> tupleReservationList = tableReservations.getReservationChambre(c.getM_idChambre());

                if(tupleReservationList == null){
                    System.out.println(c.print());
                }

                else {
                    for (TupleReservation r : tupleReservationList) {

                        if(r.getM_datedebut().before(dateNow) && r.getM_dateFin().after(dateNow)){
                            throw new IFT287Exception("La chambre demandée est réservée durant cette période");
                        }
                        else if(r.getM_datedebut().after(dateNow) && r.getM_dateFin().before(dateNow)){
                            throw new IFT287Exception("La chambre demandée est réservée durant cette période");
                        }
                        else{
                            System.out.println(c.print());
                            break;
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            throw  e;
        }
    }
}
