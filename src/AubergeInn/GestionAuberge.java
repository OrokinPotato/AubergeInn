package AubergeInn;

public class GestionAuberge {

    private Connexion cx;
    private Chambres chambres;
    private Clients clients;
    private Commodites commodites;
    private Reservations reservations;

    private GestionChambre gestionChambres;
    private GestionClient gestionClients;
    private GestionReservation gestionReservations;
    private GestionCommodite gestionCommodites;
    private GestionComChambre gestionComChambre;

    
    public GestionAuberge(String serveur, String bd, String user, String password)
        throws IFT287Exception
    {
        // allocation des objets pour le traitement des transactions
        cx = new Connexion(serveur, bd, user, password);
        chambres = new Chambres(cx);
        clients = new Clients(cx);
        reservations = new Reservations(cx);
        commodites = new Commodites(cx);

        setGestionChambres(new GestionChambre(chambres, reservations));
        setGestionClients(new GestionClient(clients, reservations));
        setGestionCommodites(new GestionCommodite(commodites));
        setGestionReservations(new GestionReservation(reservations, clients, chambres));
        setGestionComChambre(new GestionComChambre(commodites, chambres));
    }

    public void fermer()
    {
        // fermeture de la connexion
        cx.fermer();
    }

    public GestionChambre getGestionChambres() {
        return gestionChambres;
    }

    public void setGestionChambres(GestionChambre gestionChambres) {
        this.gestionChambres = gestionChambres;
    }

    public GestionClient getGestionClients() {
        return gestionClients;
    }

    public void setGestionClients(GestionClient gestionClients) {
        this.gestionClients = gestionClients;
    }

    public GestionReservation getGestionReservations() {
        return gestionReservations;
    }

    public void setGestionReservations(GestionReservation gestionReservations) {
        this.gestionReservations = gestionReservations;
    }

    public GestionCommodite getGestionCommodites() {
        return gestionCommodites;
    }

    public void setGestionCommodites(GestionCommodite gestionCommodites) {
        this.gestionCommodites = gestionCommodites;
    }

    public GestionComChambre getGestionComChambre() {
        return gestionComChambre;
    }

    public void setGestionComChambre(GestionComChambre gestionComChambre) {
        this.gestionComChambre = gestionComChambre;
    }
}
