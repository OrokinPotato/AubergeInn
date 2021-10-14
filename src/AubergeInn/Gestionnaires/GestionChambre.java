package AubergeInn.Gestionnaires;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import AubergeInn.Tables.tableHandlerChambres;
import AubergeInn.Tables.tableHandlerComChambre;
import AubergeInn.Tables.tableHandlerReservations;

import java.sql.*;

public class GestionChambre {

    private Connexion cx;
    private tableHandlerChambres chambres;
    private tableHandlerReservations reservations;
    private tableHandlerComChambre comChambres;

    public GestionChambre(tableHandlerChambres chambres, tableHandlerReservations reservations, tableHandlerComChambre comChambres)
        throws IFT287Exception
    {

        this.chambres = chambres;
        this.reservations = reservations;
        this.comChambres = comChambres;
    }
}
