package AubergeInn.Tuples;

// Permet de représenter un tuple de la table chambre.

public class oldChambre {

    private int id;
    private String nom;
    private String type_lit;
    private double prix;

    public oldChambre() {}
    public oldChambre(int id, String nom, String type_lit, double prix) {
        this.id = id;
        this.nom = nom;
        this.type_lit = type_lit;
        this.prix = prix;
    }

    // Getters/Setters...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType_lit() {
        return type_lit;
    }

    public void setType_lit(String type_lit) {
        this.type_lit = type_lit;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
