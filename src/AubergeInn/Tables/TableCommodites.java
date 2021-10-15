package AubergeInn.Tables;

import AubergeInn.Connexion;
import AubergeInn.Tuples.TupleClient;
import AubergeInn.Tuples.TupleCommodite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableCommodites {

    private Connexion cx;
    private PreparedStatement stmtExiste;
    private PreparedStatement stmtAfficher;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtDelete;

    public TableCommodites(Connexion cx) {
        this.cx = cx;
        stmtExiste = cx.getConnection().prepareStatement();
        stmtInsert = cx.getConnection().prepareStatement();
        stmtDelete = cx.getConnection().prepareStatement();
    }


    public Connexion getConnexion() {
        return cx;
    }

    public boolean existe(int commoditeId) throws SQLException {
        stmtExiste.setInt(1, commoditeId);
        ResultSet set = stmtExiste.executeQuery();
        boolean commoditeExiste = set.next();
        set.close();
        return commoditeExiste;
    }
    public TupleCommodite getCommodite(int commoditeId) throws SQLException{
        stmtExiste.setInt(1, commoditeId);
        ResultSet set = stmtExiste.executeQuery();
        if (set.next())
        {
            TupleCommodite tupleCommodite = new TupleCommodite();
            tupleCommodite.setId(commoditeId);
            tupleCommodite.setDescription(set.getString(2));
            tupleCommodite.setPrix(set.getDouble(3));
            set.close();
            return tupleCommodite;
        }
        else
        {
            return null;
        }
    }

    public void ajouterCommodite(int commoditeId, String desc, double surprix) throws SQLException {
        stmtInsert.setInt(1, commoditeId);
        stmtInsert.setString(2, desc);
        stmtInsert.setDouble(3, surprix);
        stmtInsert.executeUpdate();
    }
}
