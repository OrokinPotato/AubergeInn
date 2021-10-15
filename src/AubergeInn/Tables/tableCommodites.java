package AubergeInn.Tables;

import AubergeInn.Connexion;
import AubergeInn.Tuples.tupleCommodite;

import java.sql.*;

public class tableCommodites {

    private Connexion cx;
    private PreparedStatement stmtExiste;
    private PreparedStatement stmtAfficher;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtDelete;


    public Connexion getConnexion() {
        return cx;
    }

    public boolean existe(int id) {
    }
    public tupleCommodite getCommodite(int commoditeId) {
    }

    public void ajouterCommodite(int commoditeId, String desc, double surprix) {

    }
}
