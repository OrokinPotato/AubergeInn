// Travail fait par :
//   Olivier Tremblay-Turcotte - 17078154
//   NomEquipier2 - Matricule

package AubergeInn;

import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Fichier de base pour le TP2 du cours IFT287
 *
 * <pre>
 * 
 * Vincent Ducharme
 * Universite de Sherbrooke
 * Version 1.0 - 7 juillet 2016
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * Ce programme permet d'appeler des transactions d'un systeme
 * de gestion utilisant une base de donnees.
 *
 * Paramètres
 * 0- serveur à utiliser
 * 1- nom de la BD
 * 2- user id pour établir une connexion avec le serveur SQL
 * 3- mot de passe pour le user id
 * 4- fichier de transaction [optionnel]
 *           si non spécifié, les transactions sont lues au
 *           clavier (System.in)
 *
 * Pré-condition
 *   Aucune
 *
 * Post-condition
 *   Le programme effectue les maj associées à chaque
 *   transaction
 * </pre>
 */
public class AubergeInn
{
    private GestionAuberge gestionAub;
    private boolean estFichier;

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        if (args.length < 4)
        {
            System.out.println("Usage: java AubergeInn.AubergeInn <serveur> <bd> <user> <password> [<fichier-transactions>]");
            return;
        }
        
        AubergeInn aubergeInstance = null;
        
        try
        {
            // Ouverture du fichier de transactions
            // s'il est spécifié comme argument
            boolean lectureAuClavier = true;
            InputStream sourceTransaction = System.in;
            if (args.length > 4)
            {
                sourceTransaction = new FileInputStream(args[4]);
                lectureAuClavier = false;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(sourceTransaction));

            aubergeInstance = new AubergeInn(args[0], args[1], args[2], args[3]);
            aubergeInstance.setFaireEcho(!lectureAuClavier);
            aubergeInstance.traiterTransactions(reader);
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
        }
        finally
        {
            if(aubergeInstance != null)
                aubergeInstance.fermer();
        }
    }
    public AubergeInn(String server, String bd, String user, String pass) throws Exception
    {
        gestionAub = new GestionAuberge(server, bd, user, pass);
    }

    public void setFaireEcho(boolean echo)
    {
        this.estFichier = echo;
    }

    public void fermer() throws Exception
    {
        gestionAub.fermer();
    }

    /**
     * Traitement des transactions de la bibliothèque
     */
    public void traiterTransactions(BufferedReader reader) throws Exception
    {
        afficherAide();
        String transaction = lireTransaction(reader);
        while (!finTransaction(transaction))
        {
            // Découpage de la transaction en mots
            StringTokenizer tokenizer = new StringTokenizer(transaction, " ");
            if (tokenizer.hasMoreTokens())
                executerTransaction(tokenizer);
            transaction = lireTransaction(reader);
        }
    }

    /**
     * Lecture d'une transaction
     */
    private String lireTransaction(BufferedReader reader) throws IOException
    {
        System.out.print("> ");
        String transaction = reader.readLine();
        // Echo si lecture dans un fichier
        if (estFichier)
            System.out.println(transaction);
        return transaction;
    }

    /**
     * Décodage et traitement d'une transaction
     */
    private void executerTransaction(StringTokenizer tokenizer) throws Exception
    {
        String command = tokenizer.nextToken();

        // *******************
        // HELP
        // *******************
        if (command.equals("aide"))
        {
            afficherAide();
        }
        // *******************
        // ajouterClient
        // *******************
        else if (command.equals("ajouterClient"))
        {
            int idClient = readInt(tokenizer);
            String prenom = readString(tokenizer);
            String nom = readString(tokenizer);
            int age = readInt(tokenizer);
            gestionAub.getGestionClients().ajouter(idClient, prenom, nom, age);
        }
        // *******************
        // supprimerClient
        // *******************
        else if (command.equals("supprimerClient"))
        {
            int idClient = readInt(tokenizer);
            gestionAub.getGestionClients().supprimer(idClient);
        }
        // *******************
        // ajouterChambre
        // *******************
        else if (command.equals("ajouterChambre"))
        {
            int idChambre = readInt(tokenizer);
            String nomChambre = readString(tokenizer);
            String typeLit = readString(tokenizer);
            double prix = Double.parseDouble(readString(tokenizer));
            gestionAub.getGestionChambres().ajouter(idChambre, nomChambre, typeLit, prix);
        }
        // *******************
        // supprimerChambre
        // *******************
        else if (command.equals("supprimerChambre"))
        {
            int idChambre = readInt(tokenizer);
            gestionAub.getGestionChambres().supprimer(idChambre);
        }
        // *******************
        // ajouterCommodite
        // *******************
        else if (command.equals("ajouterCommodite"))
        {
            int idCom = readInt(tokenizer);
            String description = readString(tokenizer);
            double prix = Double.parseDouble(readString(tokenizer));
            gestionAub.getGestionCommodites().ajouter(idCom, description, prix);
        }
        // *******************
        // inclureCommodite
        // *******************
        else if (command.equals("inclureCommodite"))
        {
            int idChambre = readInt(tokenizer);
            int idCom = readInt(tokenizer);
            gestionAub.getGestionComChambre().inclure(idChambre, idCom);
        }
        // *******************
        // enleverCommodite
        // *******************
        else if (command.equals("enleverCommodite"))
        {
            int idChambre = readInt(tokenizer);
            int idCom = readInt(tokenizer);
            gestionAub.getGestionComChambre().enlever(idChambre, idCom);
        }
        // *******************
        // reserver
        // *******************
        else if (command.equals("reserver"))
        {
            int idClient = readInt(tokenizer);
            int idChambre = readInt(tokenizer);
            Date dateDebut = readDate(tokenizer);
            Date dateFin = readDate(tokenizer);
            gestionAub.getGestionReservations().reserver(idClient, idChambre, dateDebut, dateFin);
        }
        // *******************
        // afficherChambresLibres
        // *******************
        else if (command.equals("afficherChambresLibres"))
        {
            gestionAub.getGestionChambres().afficherChambresLibres();
        }
        // *******************
        // afficherClient
        // *******************
        else if (command.equals("afficherClient"))
        {
            int idClient = readInt(tokenizer);
            gestionAub.getGestionClients().afficherClient(idClient);
        }
        // *******************
        // afficherChambre
        // *******************
        else if (command.equals("afficherChambre"))
        {
            int idChambre = readInt(tokenizer);
            gestionAub.getGestionChambres().afficherChambre(idChambre);
        }
        // *********************
        // Commentaire : ligne d�butant par --
        // *********************
        else if (command.equals("--"))
        {
            // Ne rien faire, c'est un commentaire
        }
        // ***********************
        // TRANSACTION INCONNUE
        // ***********************
        else
        {
            System.out.println("  Transactions non reconnue.  Essayer \"aide\"");
        }
    }

    /** Affiche le menu des transactions acceptées par le système */
    private static void afficherAide()
    {
        System.out.println();
        System.out.println("Chaque transaction comporte un nom et une liste d'arguments");
        System.out.println("separes par des espaces. La liste peut etre vide.");
        System.out.println(" Les dates sont en format yyyy-mm-dd.");
        System.out.println("");
        System.out.println("Les transactions sont:");
        System.out.println("  aide");
        System.out.println("  exit");
        System.out.println("  ajouterClient <idClient> <prenom> <nom> <age>");
        System.out.println("  supprimerClient <idClient>");
        System.out.println("  ajouterChambre <idChambre> <nom de la chambre> <type de lit> <prix de base>");
        System.out.println("  supprimerChambre <idChambre>");
        System.out.println("  ajouterCommodite <idCommodite> <description> <surplus prix>");
        System.out.println("  inclureCommodite <idChambre> <idCommodite>");
        System.out.println("  enleverCommodite <idChambre> <idCommodite>");
        System.out.println("  afficherChambresLibres");
        System.out.println("  afficherClient <idClient>");
        System.out.println("  afficherChambre <idChambre>");
        System.out.println("  reserver <idClient> <idChambre> <dateDebut> <dateFin>");
    }

    /**
     * Vérifie si la fin du traitement des transactions est atteinte.
     */
    private boolean finTransaction(String transaction)
    {
        // fin de fichier atteinte
        if (transaction == null)
            return true;

        StringTokenizer tokenizer = new StringTokenizer(transaction, " ");

        // ligne ne contenant que des espaces
        if (!tokenizer.hasMoreTokens())
            return false;

        // commande "exit"
        String commande = tokenizer.nextToken();
        if (commande.equals("exit"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /** lecture d'une chaine de caractères de la transaction entrée à l'écran */
    private String readString(StringTokenizer tokenizer) throws IFT287Exception
    {
        if (tokenizer.hasMoreElements())
        {
            return tokenizer.nextToken();
        }
        else
        {
            throw new IFT287Exception("Autre paramètre attendu");
        }
    }

    /**
     * lecture d'un int java de la transaction entrée à l'écran
     */
    private int readInt(StringTokenizer tokenizer) throws IFT287Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Integer.valueOf(token).intValue();
            }
            catch (NumberFormatException e)
            {
                throw new IFT287Exception("Nombre attendu à la place de \"" + token + "\"");
            }
        }
        else
        {
            throw new IFT287Exception("Autre paramètre attendu");
        }
    }

    /**
     * lecture d'un long java de la transaction entrée à l'écran
     */
    private long readLong(StringTokenizer tokenizer) throws IFT287Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Long.valueOf(token).longValue();
            }
            catch (NumberFormatException e)
            {
                throw new IFT287Exception("Nombre attendu à la place de \"" + token + "\"");
            }
        }
        else
        {
            throw new IFT287Exception("Autre paramètre attendu");
        }
    }

    /**
     * lecture d'une date en format YYYY-MM-DD
     */
    private java.util.Date readDate(StringTokenizer tokenizer) throws IFT287Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                Date d = FormatDate.convertirDate(token);
                return d;
            }
            catch (ParseException e)
            {
                throw new IFT287Exception("Date en format YYYY-MM-DD attendue à la place de \"" + token + "\"");
            }
        }
        else
        {
            throw new IFT287Exception("Autre paramètre attendu");
        }
    }
}// class
