package AubergeInn;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoCollection;
import org.bson.Document;


public class TableClients {

    private Connexion cx;
    private MongoCollection<Document> clientsCollections;

    public TableClients(Connexion cx) {
        this.cx = cx;
        clientsCollections = cx.getDatabase().getCollection("TableClients");
    }

    public Connexion getConnexion() {
        return cx;
    }

    public boolean existe(int idClient) {
        return clientsCollections.find(eq("m_idClient", idClient)).first() != null;
    }

    /**
     * Lecture d'un client.
     */
    public TupleClient getClient(int idClient) {
        Document doc = clientsCollections.find(eq("m_idClient", idClient)).first();
        if (doc != null)
        {
            return new TupleClient(doc);
        }
        return null;
    }

    /**
     * Ajout d'un nouveau tupleClient.
     */
    public void ajouter(TupleClient tupleClient) {
        clientsCollections.insertOne(tupleClient.toDocument());
    }

    /**
     * Suppression d'un tupleClient.
     */
    public boolean supprimer(TupleClient tupleClient) {
        return clientsCollections.deleteOne(eq("m_idClient", tupleClient.getM_idClient())).getDeletedCount() > 0;
    }
}
