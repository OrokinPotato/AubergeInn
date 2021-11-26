package AubergeInn;

import java.util.LinkedList;
import java.util.List;

import com.mongodb.client.FindIterable;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import static com.mongodb.client.model.Filters.*;

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
        Document l = chambresCollection.find(eq("m_idChambre", idChambre)).first();
        if(l != null){
            return new TupleChambre(l);
        }
            return null;
    }

    public List<TupleChambre> getAllChambre(){
        List<TupleChambre> liste = new LinkedList<TupleChambre>();
        MongoCursor<Document> chambres = chambresCollection.find().iterator();
        try
        {
            while (chambres.hasNext())
            {
                liste.add(new TupleChambre(chambres.next()));
            }
        }
        finally
        {
            chambres.close();
        }

        return liste;
    }

    public boolean existe(int idChambre) {

        return chambresCollection.find(eq("m_idChambre", idChambre)).first() != null;
    }

    public void ajouter(int idChambre, String nomChambre, String typeLit, double prix) {
        TupleChambre chambre = new TupleChambre(idChambre, nomChambre, typeLit, prix);

        chambresCollection.insertOne(chambre.toDocument());
    }

    public boolean supprimer(int idChambre) {
        return chambresCollection.deleteOne(eq("m_idChambre", idChambre)).getDeletedCount() > 0;
    }
}
