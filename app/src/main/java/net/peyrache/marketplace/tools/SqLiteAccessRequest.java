package net.peyrache.marketplace.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.peyrache.marketplace.model.UtilisateurAc;
import net.peyrache.marketplace.model.UtilisateurFo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SqLiteAccessRequest extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Marketplace.db";
    private static final Integer DATABASE_VERSION = 2;

    public SqLiteAccessRequest(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "CREATE TABLE utilisateur("
                            +"idUtilisateur integer primary key autoincrement,"
                            +"username text not null,"
                            +"password text not null,"
                            +"email text not null,"
                            +"adresse text not null,"
                            +"type text not null,"
                            +"paiement text,"
                            +"sexe integer,"
                            +"nom text,"
                            +"prenom text,"
                            +"rib text,"
                            +"raisonSociale text"
                        +")";

        String strSql2 = "CREATE TABLE article("
                +"nArticle INTEGER primary key autoincrement,"
                +"idUtilisateur INTEGER NOT NULL,"
                +"cat TEXT NOT NULL,"
                +"nomArticle TEXT NOT NULL,"
                +"prix INTEGER NOT NULL,"
                +"description TEXT,"
                +"stock INTEGER NOT NULL,"
                +"FOREIGN KEY(idUtilisateur) REFERENCES utilisateur(idUtilisateur)"
                +")";

        String strSql3 = "CREATE TABLE categorie(" +
                         "categorie TEXT NOT NULL PRIMARY KEY)";

        String strSql4 = "INSERT INTO categorie(categorie) VALUES('Boisson')";
        String strSql5 = "INSERT INTO categorie(categorie) VALUES('Manger')";
        /*
        String strSql6 = "INSERT INTO article(idUtilisateur, cat, nomArticle, prix, description, stock) " +
                                 "VALUES(1, 'Boisson', 'petibulle', 35, 'ddd',20)";

         */

        db.execSQL(strSql);
        db.execSQL(strSql2);
        db.execSQL(strSql3);
        try{
            db.execSQL(strSql4);
            db.execSQL(strSql5);
            Log.d("Ajout", "Boisson done");
        }catch (Exception e){
            Log.d("Ajout", "Echec");
        }

        Log.d("Bdd", "onCreate invoke");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //region Utilisateur

    //region Inscritpion utilisateur
    /**
     * Création d'un nouvel utilisateur de type Acheteur, cette méthode sera appelée dans le controller Cinscription
     * @param username
     * @param password
     * @param email
     * @param postalAddress
     * @param type
     * @param paiement
     * @param sexe
     * @param surname
     * @param name
     * @param rib
     */
    public void newUserAC(String username, String password, String email, String postalAddress,
                          String type, String paiement, Integer sexe, String surname,
                          String name, String rib){
        //Permet de mettre sous le bon format pour les requêtes sql les propriétées rentrées précédement.
        username = username.replace("'","''");
        password = password.replace("'","''");
        email = email.replace("'","''");
        postalAddress = postalAddress.replace("'","''");
        type = type.replace("'", "''");
        paiement = paiement.replace("'", "''");
        surname = surname.replace("'", "''");

        //Définition de la requête d'insertion pour la création d'un nouvel utilisateur de type Acheteur.
        String strSql="INSERT INTO utilisateur(username, password, email, adresse, type, paiement, sexe, nom, prenom, rib) " +
                        "VALUES('"+username+"','"+password+"','"+email+"','"+postalAddress+"','"+type+"','"
                        +paiement+"','"+sexe+"','"+surname+"','"+name+"','"+rib+"')";
        try{
            //Test si la requête peut bien s'exécuter

            this.getWritableDatabase().execSQL(strSql);
            Log.d("newUserAC", "Création du nouvel utilisateur réussite ! ");
        }catch (Exception e){

            //Erreur renvoyé en cas de problème lors de l'exécution de la requête.
            Log.d("newUserAC", "Erreur lors de l'execution de la requête");
        }
    }

    /**
     * Création d'un nouvel utilisateur de type Fournisseur, cette méthode sera appelée dans le controller Cinscription
     * @param username
     * @param password
     * @param email
     * @param postalAddress
     * @param type
     * @param rib
     * @param raisonSociale
     */
    public void newUserFO(String username, String password, String email, String postalAddress,
                          String type, String rib, String raisonSociale){

        //Permet de mettre sous le bon format pour les requêtes sql les propriétées rentrées précédement.
        username = username.replace("'","''");
        password = password.replace("'","''");
        email = email.replace("'","''");
        postalAddress = postalAddress.replace("'","''");
        type = type.replace("'", "''");
        rib = rib.replace("'", "''");
        raisonSociale = raisonSociale.replace("'", "''");

        //Définition de la requête d'insertion pour la création d'un nouvel utilisateur de type Fournisseur.
        String strSql="INSERT INTO utilisateur(username, password, email, adresse, type, rib, raisonSociale) " +
                        "VALUES('"+username+"','"+password+"','"+email+"','"+postalAddress+"','"+type+"','"+rib+"','"+raisonSociale+"')";

        try{
            //Test si la requête peut bien s'exécuter
            this.getWritableDatabase().execSQL(strSql);
            Log.d("newUserFO", "Création d'un nouveau fournisseur effectuée ! ");
        }catch (Exception e){
            //Erreur renvoyé en cas de problème lors de l'exécution de la requête.
            Log.d("newUserFO", "Erreur lors de l'execution de la requête");
        }
    }
    /**
     * Permet de vérifier si l'utilisateur existe avant de créer un utilisateur.
     * @param username
     * @return
     */
    public boolean utilExist(String username){
        Boolean result=true;
        String strSql = "SELECT username FROM utilisateur WHERE username='"+username+"'";

        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();

        while(!(cursor.isAfterLast())){
            if(cursor.getString(0).isEmpty()){

                Log.d("Existe", "L'utilisateur n'existe pas");

                result = true;
                return result;
            }else{

                Log.d("Existe", "L'utilisateur existe");

                result = false;
                return result;
            }
        }
        return result;
    }
    //endregion

    //region Connexion utilisateur

    /**
     * Permet de vérifier le type d'utilisateur suivant le nom d'utilisateur et le mot de passe.
     * Cette méthode sera appelé dans le controller CpageAccueil.
     * @param username
     * @param password
     * @return
     */
    public String verifTypeUser(String username, String password){
        String strSql = "SELECT type FROM utilisateur WHERE username = '"+username+"' AND password = '"+password+"'";
        String result="L'utilisateur n'existe pas";
        Cursor cursor =this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();

        while(!(cursor.isAfterLast())){
            return cursor.getString(0);
        }
        return result;
    }

    /**
     * Permet la création d'un utilisateur de type acheteur.
     * L'objet de connexion sera envoyé en putExtra à l'activity d'après.
     * @param username
     * @return L'objet utilisateur(acheteur)
     */
    public UtilisateurAc connexionAC(String username){
        UtilisateurAc utilisateur;
        String strSql = "SELECT username, password, email, adresse, rib, nom, prenom, sexe, paiement, idUtilisateur " +
                        "FROM utilisateur WHERE username ='"+username+"'";

        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();
        while(!(cursor.isAfterLast())){

            utilisateur = new UtilisateurAc(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6), cursor.getInt(7),
                        cursor.getString(8), cursor.getInt(9));

            return utilisateur;
        }
        Log.d("UtilisateurCoAC", "Pas créé");
        return null;
    }

    /**
     * Permet la création d'un utilisateur de type fournisseur.
     * L'objet de connexion sera envoyé en putExtra à l'activity d'après.
     * @param username
     * @return L'objet utilisateur(fournisseur)
     */
    public UtilisateurFo connexionFo(String username){

        // Création d'une variable de type UtilisateurFo
        UtilisateurFo utilisateur;

        //Requête de selection des informations nécessaire à la création d'un objet utilisateurFo.
        String strSql = "SELECT idUtilisateur, username, password, email, adresse, rib, raisonSociale FROM utilisateur WHERE username = '"+username+"'";

        //Lecture des informations reçu par la requête.
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();

        while(!(cursor.isAfterLast())){
            //Création de l'utilisateur.
            utilisateur = new UtilisateurFo(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                                            cursor.getString(3),cursor.getString(4),cursor.getString(5), cursor.getString(6));
            Log.d("UtilisateurFoConnexion", "Connecté");
            //Retour de l'utilisateur.
            return utilisateur;
        }
        Log.d("UtilisateurFoConnexion", "Pas connecté");

        //Retour par défaut.
        return null;
    }
    //endregion

    //region Update profil utilisateur
    /**
     * Permet Update du profil acheteur
     * @param username
     * @param password
     * @param email
     * @param adresse
     * @param sexe
     * @param nom
     * @param prenom
     * @param rib
     */
    public void updateProfilAc(String username, String password, String email, String adresse, Integer sexe,
                               String nom, String prenom, String rib){

        //Mise en forme du champs texte pour la requête.
        password = password.replace("'","''");
        email = email.replace("'","''");
        adresse = adresse.replace("'","''");
        nom = nom.replace("'","''");
        prenom = prenom.replace("'","''");
        rib = rib.replace("'","''");
        username = username.replace("'","''");

        //Requête d'update de l'acheteur.
        String strSql = "UPDATE utilisateur" +
                "SET password = '"+password+"'," +
                "email = '"+email+"'," +
                "adresse = '"+adresse+"'," +
                "sexe = "+sexe+"," +
                "nom = '"+nom+"'," +
                "prenom = '"+prenom+"'," +
                "rib = '"+rib+"'" +
                "WHERE username = '"+username+"'";

        //Test de l'exécution de la requête.
        try{
            this.getWritableDatabase().execSQL(strSql);
            Log.d("modifUtilAc", "Effectué");
        }catch (Exception e){
            Log.d("modifUtilAc", "Echec de requête");
        }
    }

    /**
     * Permet l'update du profil Fournisseur.
     * @param username
     * @param password
     * @param email
     * @param adresse
     * @param rib
     * @param raisonSociale
     */
    public void updateProfilFo(String username, String password, String email, String adresse, String rib, String raisonSociale){

        //Mise en forme du champs texte pour la requête.
        password = password.replace("'","''");
        email = email.replace("'","''");
        adresse = adresse.replace("'","''");
        rib = rib.replace("'","''");
        username = username.replace("'","''");
        raisonSociale = raisonSociale.replace("'","''");

        //Requête d'update de l'utilisateur par rapport à son username
        // (le username est unique car dans InscriptionFoActivity,
        // la condition pour l'ajout d'un utilisateur est de savoir si il esxiste ou non.
        String strSql = "UPDATE utilisateur SET password ='"+password+"', email='"+email+"', adresse='"+adresse+"'" +
                        ", rib='"+rib+"', raisonSociale='"+raisonSociale+"' WHERE username='"+username+"'";

        //Test d'exécution de la requête d'update.
        try{
            this.getWritableDatabase().execSQL(strSql);
            Log.d("modifUtilFo", "Effectué");
        }catch (Exception e){
            Log.d("modifUtilFo", "Echec de requête");
        }
    }
    //endregion

    //endregion

    //region Article

    /**
     * Test effectué pour voir si l'article existe suivant le nUtilisateur.
     * (un utilisateur ne peut enregistrer qu'une seule fois la même ressource.)
     * @param nomArticle
     * @param nUtilisateur
     * @return
     */
    public Boolean articleExist(String nomArticle, Integer nUtilisateur){

        //Mise en forme du champs texte pour la requête.
        nomArticle=nomArticle.replace("'", "''");

        //Requête permettant de voir le nomArticle suivant l'id utilisateur renseigné dans le constructeur.
        String strSqlVerif = "SELECT nomArticle " +
                             "FROM article, utilisateur " +
                             "WHERE article.idUtilisateur = utilisateur.idUtilisateur " +
                             "AND nomArticle = '"+nomArticle+"'" +
                             "AND article.idUtilisateur = '"+nUtilisateur+"'";

        //Essaie d'exécution de la requête.
        try{
            Cursor cursor = this.getReadableDatabase().rawQuery(strSqlVerif, null);
            cursor.moveToFirst();

            //Tant que le cursor n'arrive pas après le dernier champ, tester le premier champ.
            while(!(cursor.isAfterLast())){
                if(cursor.getString(0).equals(nomArticle)){
                    this.getReadableDatabase().close();
                    return true;
                }
            }
        }catch (Exception e){
            Log.d("ArticleVerif", "Erreur sql");
            this.getReadableDatabase().close();
        }
        return false;
    }

    /**
     * Méthode permettant d'ajouter un article.
     * @param nUtilisateur idUtilisateur de la base de données.
     * @param cat La catégorie ajouté au produit. (parmis le choix des autres catégories contenues dans la bdd(table categorie).
     * @param nomArticle
     * @param prix En Float.
     * @param description
     * @param quantite
     * @param articleExist Booléen de la fonction ArticleExist.
     */
    public void articleAdd( Integer nUtilisateur, String cat, String nomArticle, Float prix,
                            String description, Integer quantite, Boolean articleExist){

        //Mise en forme des champs texte pour la requête.
        cat = cat.replace("'", "''");
        nomArticle = nomArticle.replace("'", "''");
        description = description.replace("'", "''");

        //Test pour voir si l'article existe ou non.

        if(!articleExist) {//Si il n'existe pas :

            Log.d("ArticleAdd", "L'article n'existe pas");
            String strSqlAdd = "INSERT INTO article(cat, description, idUtilisateur, nomArticle, prix, stock) " +
                               "VALUES('"+cat+"', '"+description+"', "+nUtilisateur+", '"+nomArticle+"', "+prix+", "+quantite+")";

            //Essaie d'exécuter la requête d'ajoute d'article.
            try {
                this.getWritableDatabase().execSQL(strSqlAdd);
                this.getWritableDatabase().close();
                Log.d("ArticleAdd", "L'article a été ajouté");

            }catch (Exception e){//Si l'exécution échoue,
                Log.d("ArticleAdd", "Erreur sql");
            }
        }
    }



    public void articleSuppr(String nomArticle){
        nomArticle = nomArticle.replace("'","''");

        String strSql = "DELETE FROM article WHERE nomArticle='"+nomArticle+"'";
        try{
            this.getWritableDatabase().execSQL(strSql);
            Log.d("supprArticle", "Suppression effectuée");
        }catch (Exception e){
            Log.d("supprArticle", "Echec lors de l'exécution de la requête sql");
        }
    }

    /**
     * Permet de récuperer le nom des catégories sous forme de tableau.
     * @return Le tableau des catégories de la table categorie.
     */
    public List<String> getAllLabels(){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT categorie FROM categorie";


        Cursor cursor = this.getReadableDatabase().rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        this.getReadableDatabase().close();
        // returning lables
        return labels;
    }
    //endregion


}