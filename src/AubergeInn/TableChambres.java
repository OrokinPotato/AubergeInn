package AubergeInn;

import java.util.List;

import com.mongodb.client.FindIterable;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class TableChambres {

    private Connexion cx;
    private MongoCollection<Document> chambresCollection;

    public TableChambres(Connexion cx) {
        this.cx = cx;
        chambresCollection = cx.getDatabase().getCollection("Chambres");
    }

    public Connexion getConnexion() {
        return cx;
    }

    public TupleChambre getChambre(int idChambre) {
        Document l = chambresCollection.find(eq("idChambre", idChambre)).first();
        if(l != null){
            return new TupleChambre(l);
        }
            return null;
    }

    // TODO: https://docs.mongodb.com/realm/mongodb/actions/collection.find/
    public FindIterable<Document> getAllChambre(){
        FindIterable<Document> chambres = chambresCollection.find(gt("idChambre", 0));
        if (chambres.first() != null)
        {
            return chambres;
        }
        else
        {
            return null;
        }
    }

    public boolean existe(int idChambre) {

        return chambresCollection.find(eq("idChambre", idChambre)).first() != null;
    }

    public void ajouter(int idChambre, String nomChambre, String typeLit, double prix) {
        TupleChambre chambre = new TupleChambre(idChambre, nomChambre, typeLit, prix);

        chambresCollection.insertOne(chambre.toDocument());
    }

    public boolean supprimer(int idChambre) {
        return chambresCollection.deleteOne(eq("idChambre", idChambre)).getDeletedCount() > 0;
    }
}
