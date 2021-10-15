package AubergeInn.Tuples;

// Permet de représenter un tuple de la table CommoditeChambre.

public class TupleCommoditeChambre {

    private int commoditeId;
    private int chambreId;

    public TupleCommoditeChambre() {}

    public TupleCommoditeChambre(int commoditeId, int chambreId) {
        this.commoditeId = commoditeId;
        this.chambreId = chambreId;
    }

    // Getters/Setters...

    public int getCommoditeId() {
        return commoditeId;
    }

    public void setCommoditeId(int commoditeId) {
        this.commoditeId = commoditeId;
    }

    public int getChambreId() {
        return chambreId;
    }

    public void setChambreId(int chambreId) {
        this.chambreId = chambreId;
    }
}
