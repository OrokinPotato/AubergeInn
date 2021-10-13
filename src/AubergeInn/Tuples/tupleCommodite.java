package AubergeInn.Tuples;

// Permet de représenter un tuple de la table commodité.

public class tupleCommodite {

    private int id;
    private String description;
    private double prix;

    public tupleCommodite() {}
    public tupleCommodite(int id, String description, double prix) {
        this.id = id;
        this.description = description;
        this.prix = prix;
    }

    // Getters/Setters...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
