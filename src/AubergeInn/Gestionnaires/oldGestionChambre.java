package AubergeInn.Gestionnaires;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;

import AubergeInn.Tables.oldChambres;
import AubergeInn.Tables.oldComChambres;
import AubergeInn.Tables.oldCommodites;
import AubergeInn.Tables.oldReservations;

import java.sql.*;

public class oldGestionChambre {

    private Connexion cx;
    private oldChambres chambres;
    private oldReservations reservations;
    private oldComChambres comChambres;
    private oldCommodites commodites;

    public oldGestionChambre(oldChambres ch, oldReservations r, oldComChambres cc)
        throws IFT287Exception
    {
        this.cx = ch.getConnexion();
        if (this.cx != r.getConnexion() || this.cx != cc.getConnexion()) {
            throw new IFT287Exception("Differente connexions entre les table Chambres, Reservations, et CommoditeChambres");
        }
        this.chambres = ch;
        this.reservations = r;
        this.comChambres = cc;
    }

    /**
     *  Commande d'ajout d'une chambre.
     */
    public void ajouter(int idChambre, String nom, String typeLit, double prix)
            throws SQLException, IFT287Exception, Exception
    {
        try
        {
            if (chambres.existe(idChambre)) {
                throw new IFT287Exception("Chambre déjà présente dans la base de données.");
            }
            chambres.ajouterChambre(idChambre, nom, typeLit, prix);
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }

    /**
     *  Commande de retrait d'une chambre.
     */
    public void retirer(int id) throws SQLException, IFT287Exception, Exception
    {
        try
        {
            if (chambres.getChambre(id) == null){
                throw new IFT287Exception("Chambre inexistante: " + id);
            }
            if (reservations.getReservationsChambre(id) == null)
            {
                throw new IFT287Exception("Chambre "+ id + " présentement réservée");
            }
            if (comChambres.getAllChambre() == null)
            {
                throw new IFT287Exception("Commoditées assignée à la chambre "+ id);
            }

            int n = chambres.supprimerChambre(id);
            if (n == 0)
            {
                throw new IFT287Exception("La chambre "+ id + " n'existe pas");
            }
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }

    /**
     *  Commande d'affichage des informations sur une chambre.
     */
    public void afficher(int idChambre) throws SQLException, IFT287Exception, Exception
    {
        try
        {
            if (chambres.getChambre(idChambre) == null) {
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            }

            chambres.afficherChambre(idChambre);
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }

    /**
     *  Commande d'affichage d'une chambre libre.
     */
    public void afficherLibre() throws SQLException, IFT287Exception, Exception
    {
        try
        {
            chambres.afficherChambresLibres();
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
}
