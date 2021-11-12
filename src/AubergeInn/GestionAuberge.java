package AubergeInn;

public class GestionAuberge {

    private Connexion cx;
    private TableChambres tableChambres;
    private TableClients tableClients;
    private TableCommodites tableCommodites;
    private TableReservations tableReservations;

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
        tableChambres = new TableChambres(cx);
        tableClients = new TableClients(cx);
        tableReservations = new TableReservations(cx);
        tableCommodites = new TableCommodites(cx);

        setGestionChambres(new GestionChambre(tableChambres, tableReservations));
        setGestionClients(new GestionClient(tableClients, tableReservations));
        setGestionCommodites(new GestionCommodite(tableCommodites));
        setGestionReservations(new GestionReservation(tableReservations, tableClients, tableChambres));
        setGestionComChambre(new GestionComChambre(tableCommodites, tableChambres));
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
