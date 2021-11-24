package AubergeInn;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoCollection;
import org.bson.Document;


public class TableCommodites {

    private MongoCollection<Document> commoditesCollection;
    private Connexion cx;

    public TableCommodites(Connexion cx) {
        this.cx = cx;
        commoditesCollection = cx.getDatabase().getCollection("TableCommodites");
    }

    public Connexion getConnexion() {
        return cx;
    }

    public boolean existe(int idCom) {
        return commoditesCollection.find(eq("m_idCom", idCom)).first() != null;

    }

    /**
     * Lecture d'une commodite.
     */
    public TupleCommodite getCommodite(int idCom) {
        Document doc = commoditesCollection.find(eq("m_idCom", idCom)).first();
        if (doc != null)
        {
            return new TupleCommodite(doc);
        }
        return null;
    }

    /**
     * Ajout d'une nouvelle commodite.
     */
    public void ajouter(TupleCommodite com) {
        commoditesCollection.insertOne(com.toDocument());
    }
}
