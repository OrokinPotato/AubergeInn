package AubergeInn.Tables;

import AubergeInn.Connexion;

import java.sql.*;

public class tableHandlerClients {

    private PreparedStatement stmtExiste;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtUpdateIncrNbPret;
    private PreparedStatement stmtUpdateDecNbPret;
    private PreparedStatement stmtDelete;
    private Connexion cx;

}
