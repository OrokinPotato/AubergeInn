package AubergeInn.Gestionnaires;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import AubergeInn.Tables.*;

import java.sql.SQLException;

public class GestionComChambre {


    private Connexion cx;
    private ComChambres comChambre;
    private Commodites commodites;
    private Chambres chambres;

    public GestionComChambre(Chambres ch, ComChambres cc, Commodites co)
            throws IFT287Exception
    {
        this.cx = co.getConnexion();
        if (cc.getConnexion() != ch.getConnexion() || cc.getConnexion() != co.getConnexion())
        {
            throw new IFT287Exception("Differente connexions entre les table CommoditesChambres, Chambres, et Commodite");
        }
        this.chambres = ch;
        this.comChambre = cc;
        this.commodites = co;
    }

    public void inclure(int commoditeId, int chambreId) throws SQLException, IFT287Exception
    {
        try
        {
            if (chambres.getChambre(chambreId) == null)
            {
                throw new IFT287Exception("Chambre inexistante: " + chambreId);
            }
            if (commodites.getCommodite(commoditeId) == null) {
                throw new IFT287Exception("Commodité inexistante: " + commoditeId);
            }
            comChambre.inclureCommodite(commoditeId, chambreId);
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }

    public void enlever(int chambreId, int commoditeId)
            throws SQLException, IFT287Exception
    {
        try
        {
            if (chambres.getChambre(chambreId) == null) {
                throw new IFT287Exception("Chambre inexistante: " + chambreId);
            }
            if (commodites.getCommodite(commoditeId) == null) {
                throw new IFT287Exception("Commodité inexistante: " + commoditeId);
            }

            int n = comChambre.enleverCommodite(commoditeId, chambreId);
            if (n == 0) {
                throw new IFT287Exception("La combinaison Commodité/Chambre"+ commoditeId +"/" + chambreId +" n'existait pas");
            }
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
}
