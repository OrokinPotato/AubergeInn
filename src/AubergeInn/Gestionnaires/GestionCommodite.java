package AubergeInn.Gestionnaires;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import AubergeInn.Tables.tableComChambre;
import AubergeInn.Tables.tableCommodites;

import java.sql.SQLException;

public class GestionCommodite {

    private Connexion cx;
    private tableCommodites commodites;
    private tableComChambre comChambre;

    public GestionCommodite(tableCommodites c, tableComChambre ch) throws IFT287Exception {
        this.cx = c.getConnexion();
        if (c.getConnexion() != ch.getConnexion()) {
            throw new IFT287Exception("Differente connexions entre la table Commodites et la table CommoditeChambre");
        }
        this.commodites = c;
        this.comChambre = ch;
    }

    /**
     *  Commande d'ajout d'une commodité.
     */
    public void ajouter(int idCommodite, String desc, double surprix)
            throws SQLException, IFT287Exception, Exception
    {
        try {
            if (commodites.existe(idCommodite)) {
                throw new IFT287Exception("Commodité déjà présente dans la base de données.");
            }
            commodites.ajouterCommodite(idCommodite, desc, surprix);
            cx.commit();
        }
        catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }
}
