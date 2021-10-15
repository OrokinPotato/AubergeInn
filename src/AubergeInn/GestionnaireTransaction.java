package AubergeInn;

import AubergeInn.Gestionnaires.*;
import AubergeInn.Tables.tableChambres;
import AubergeInn.Tables.TableClients;
import AubergeInn.Tables.TableCommodites;
import AubergeInn.Tables.TableReservations;
import AubergeInn.Tables.TableComChambre;

import java.sql.SQLException;

public class GestionnaireTransaction {

    private Connexion cx;

    private tableChambres chambre;
    private tableClients client;
    private tableCommodites commodite;
    private tableReservations reservations;
    private tableComChambre comChambre;

    private GestionClient gClient;
    private GestionChambre gChambre;
    private GestionCommodite gCommodite;
    private GestionComChambre gComChambre;
    private GestionReservation gReservation;

    public GestionnaireTransaction(Connexion cx) throws SQLException, IFT287Exception {
        this.cx = cx;

        // Creation des tables
        chambre = new tableChambres(cx);
        client = new tableClients(cx);
        commodite = new tableCommodites(cx);
        reservations = new tableReservations(cx);
        comChambre = new tableComChambre(cx);

        //Creation des gestionnaires
        gChambre = new GestionChambre(chambre,reservations, comChambre);
        gClient = new GestionClient(client, reservations);
        gCommodite = new GestionCommodite(commodite, comChambre);
        gReservation = new GestionReservation(reservations, client, chambre, comChambre, commodite);
        gComChambre = new GestionComChambre(chambre, comChambre, commodite);
    }

    // Getters/setters...

    public GestionClient getGestionClient() {
        return gClient;
    }

    public void setGestionClient(GestionClient gClient) {
        this.gClient = gClient;
    }

    public GestionChambre getGestionChambre() {
        return gChambre;
    }

    public void setGestionChambre(GestionChambre gChambre) {
        this.gChambre = gChambre;
    }

    public GestionCommodite getGestionCommodite() {
        return gCommodite;
    }

    public void setGestionCommodite(GestionCommodite gCommodite) {
        this.gCommodite = gCommodite;
    }

    public GestionComChambre getGestionComChambre() {
        return gComChambre;
    }

    public void setGestionComChambre(GestionComChambre gComChambre) {
        this.gComChambre = gComChambre;
    }

    public GestionReservation getGestionReservation() {
        return gReservation;
    }

    public void setGestionReservation(GestionReservation gReservation) {
        this.gReservation = gReservation;
    }
}
