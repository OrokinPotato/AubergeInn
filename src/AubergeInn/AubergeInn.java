// Travail fait par :
//   NomEquipier1 - Matricule
//   NomEquipier2 - Matricule

package AubergeInn;

import java.io.*;
import java.util.StringTokenizer;
import java.sql.*;

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
 * Paramètres du programme
 * 0- site du serveur SQL ("local" ou "dinf")
 * 1- nom de la BD
 * 2- user id pour etablir une connexion avec le serveur SQL
 * 3- mot de passe pour le user id
 * 4- fichier de transaction [optionnel]
 *           si non spécifié, les transactions sont lues au
 *           clavier (System.in)
 *
 * Pré-condition
 *   - La base de donnees doit exister
 *
 * Post-condition
 *   - Le programme effectue les mises à jour associees à chaque
 *     transaction
 * </pre>
 */
public class AubergeInn
{
    private static Connexion cx;

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
        
        cx = null;
        
        try
        {
            // Il est possible que vous ayez à déplacer la connexion ailleurs.
            // N'hésitez pas à le faire!
            cx = new Connexion(args[0], args[1], args[2], args[3]);
            BufferedReader reader = ouvrirFichier(args);
            String transaction = lireTransaction(reader);
            while (!finTransaction(transaction))
            {
                executerTransaction(transaction);
                transaction = lireTransaction(reader);
            }
        }
        finally
        {
            if (cx != null)
                cx.fermer();
        }
    }

    /**
     * Decodage et traitement d'une transaction
     */
    static void executerTransaction(String transaction) throws Exception, IFT287Exception
    {
        try
        {
            // Creer le gestionnaire
            GestionnaireTransaction gestionTransaction = new GestionnaireTransaction(cx);

            System.out.print(transaction);
            // Decoupage de la transaction en mots
            StringTokenizer tokenizer = new StringTokenizer(transaction, " ");
            if (tokenizer.hasMoreTokens())
            {
                String command = tokenizer.nextToken();
                // Vous devez remplacer la chaine "commande1" et "commande2" par
                // les commandes de votre programme. Vous pouvez ajouter autant
                // de else if que necessaire. Vous n'avez pas a traiter la
                // commande "quitter".
                if (command.equals("ajouterClient"))
                {
                    // Lecture des parametres
                    int param1 = readInt(tokenizer);            // idClient
                    String param2 = readString(tokenizer);      // Prenom
                    String param3 = readString(tokenizer);      // Nom
                    int param4 = readInt(tokenizer);            // Age
                    // Appel de la methode des gestionnaires qui traite la transaction specifique
                    gestionTransaction.getGestionClient().ajouter(param1, param2, param3, param4);
                }
                else if (command.equals("supprimerClient"))
                {
                    // Lecture des parametres
                    int param1 = readInt(tokenizer);            // idClient
                    // Appel de la methode des gestionnaires qui traite la transaction specifique
                    gestionTransaction.getGestionClient().retirer(param1);
                }
                else if (command.equals("ajouterChambre"))
                {
                    // Lecture des parametres
                    int param1 = readInt(tokenizer);                            // idChambre
                    String param2 = readString(tokenizer);                      // Nom de la chambre
                    String param3 = readString(tokenizer);                      // Type de lit
                    double param4 = Double.parseDouble(readString(tokenizer));  // Prix de base
                    gestionTransaction.getGestionChambre().ajouter(param1, param2, param3, param4);
                }
                else if (command.equals("supprimerChambre"))
                {
                    // Lecture des parametres
                    int param1 = readInt(tokenizer);            // idChambre
                    gestionTransaction.getGestionChambre().retirer(param1);
                }
                else if (command.equals("ajouterCommodite"))
                {
                    // Lecture des parametres
                    int param1 = readInt(tokenizer);            // idCommodite
                    String param2 = readString(tokenizer);      // Description
                    double param3 = Double.parseDouble(readString(tokenizer));       // Surplux prix
                    gestionTransaction.getGestionCommodite().ajouter(param1, param2, param3);
                }
                else if (command.equals("inclureCommodite"))
                {
                    // Lecture des parametres
                    int param1 = readInt(tokenizer);            // idChambre
                    int param2 = readInt(tokenizer);            // idCommodite
                    gestionTransaction.getGestionComChambre().inclure(param1, param2);
                }
                else if (command.equals("enleverCommodite"))
                {
                    // Lecture des parametres
                    int param1 = readInt(tokenizer);            // idChambre
                    int param2 = readInt(tokenizer);            // idCommodite
                    gestionTransaction.getGestionComChambre().enlever(param1, param2);
                }
                else if (command.equals("afficherChambresLibres"))
                {
                    // Aucun parametres
                    gestionTransaction.getGestionChambre().afficherLibre();
                }
                else if (command.equals("afficherClient"))
                {
                    // Lecture des parametres
                    int param1 = readInt(tokenizer);            // idClient
                    gestionTransaction.getGestionClient().afficher(param1);
                }
                else if (command.equals("afficherChambre"))
                {
                    // Lecture des parametres
                    int param1 = readInt(tokenizer);            // idChambre
                    gestionTransaction.getGestionChambre().afficher(param1);
                }
                else if (command.equals("reserver"))
                {
                    // Lecture des parametres
                    int param1 = readInt(tokenizer);            // idClient
                    int param2 = readInt(tokenizer);            // idChambre
                    Date param3 = readDate(tokenizer);          // dateDebut
                    Date param4 = readDate(tokenizer);          // dateFin
                    gestionTransaction.getGestionReservation().reserver(param1, param2, param3, param4);
                }
                else if (command.equals("quitter"))
                {
                    // Quitter l'application
                    return;
                }
                else
                {
                    System.out.println(" : Transaction non reconnue");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(" " + e.toString());
            // Ce rollback est ici seulement pour vous aider et éviter des problèmes lors de la correction
            // automatique. En théorie, il ne sert à rien et ne devrait pas apparaître ici dans un programme
            // fini et fonctionnel sans bogues.
            cx.rollback();
        }
    }

    
    // ****************************************************************
    // *   Les methodes suivantes n'ont pas besoin d'etre modifiees   *
    // ****************************************************************

    /**
     * Ouvre le fichier de transaction, ou lit à partir de System.in
     */
    public static BufferedReader ouvrirFichier(String[] args) throws FileNotFoundException
    {
        if (args.length < 5)
            // Lecture au clavier
            return new BufferedReader(new InputStreamReader(System.in));
        else
            // Lecture dans le fichier passe en parametre
            return new BufferedReader(new InputStreamReader(new FileInputStream(args[4])));
    }

    /**
     * Lecture d'une transaction
     */
    static String lireTransaction(BufferedReader reader) throws IOException
    {
        return reader.readLine();
    }

    /**
     * Verifie si la fin du traitement des transactions est atteinte.
     */
    static boolean finTransaction(String transaction)
    {
        // fin de fichier atteinte
        return (transaction == null || transaction.equals("quitter"));
    }

    /** Lecture d'une chaine de caracteres de la transaction entree a l'ecran */
    static String readString(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
            return tokenizer.nextToken();
        else
            throw new Exception("Autre parametre attendu");
    }

    /**
     * Lecture d'un int java de la transaction entree a l'ecran
     */
    static int readInt(StringTokenizer tokenizer) throws Exception
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
                throw new Exception("Nombre attendu a la place de \"" + token + "\"");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }

    static Date readDate(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Date.valueOf(token);
            }
            catch (IllegalArgumentException e)
            {
                throw new Exception("Date dans un format invalide - \"" + token + "\"");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }

}
