package AubergeInn;

import AubergeInn.Gestionnaires.*;
import AubergeInn.Tables.*;


import java.sql.SQLException;

public class GestionnaireTransaction {

    private Connexion cx;

    private Chambres chambres;
    private Clients clients;
    private Commodites commodites;
    private Reservations reservations;
    private ComChambres comChambres;

    private GestionClient gClient;
    private GestionChambre gChambre;
    private GestionCommodite gCommodite;
    private GestionComChambre gComChambre;
    private GestionReservation gReservation;

    public GestionnaireTransaction(Connexion cx) throws SQLException, IFT287Exception {
        this.cx = cx;

        // Creation des tables
        chambres = new Chambres(this.cx);
        clients = new Clients(this.cx);
        commodites = new Commodites(this.cx);
        reservations = new Reservations(this.cx);
        comChambres = new ComChambres(this.cx);

        //Creation des gestionnaires
        gChambre = new GestionChambre(chambres,reservations, comChambres);
        gClient = new GestionClient(clients, reservations);
        gCommodite = new GestionCommodite(commodites, comChambres);
        gReservation = new GestionReservation(reservations, clients, chambres, comChambres, commodites);
        gComChambre = new GestionComChambre(chambres, comChambres, commodites);
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
