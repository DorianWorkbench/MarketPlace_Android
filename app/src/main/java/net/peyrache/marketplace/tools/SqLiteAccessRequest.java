package net.peyrache.marketplace.tools;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import net.peyrache.marketplace.model.UtilisateurAc;
import net.peyrache.marketplace.model.UtilisateurFo;

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
                +"ean INTEGER NOT NULL,"
                +"prix INTEGER NOT NULL,"
                +"description TEXT,"
                +"stock INTEGER NOT NULL,"
                +"FOREIGN KEY(idUtilisateur) REFERENCES utilisateur(idUtilisateur)"
                +")";

        db.execSQL(strSql);
        db.execSQL(strSql2);
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
    public UtilisateurAc connexionAC(String username, String password){
        UtilisateurAc utilisateur;
        String strSql = "SELECT username, password, email, adresse, rib, nom, prenom, sexe, paiement, idUtilisateur " +
                        "FROM utilisateur WHERE username ='"+username+"' AND password = '"+password+"'";

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
    public UtilisateurFo connexionFo(String username, String password){

        UtilisateurFo utilisateur;

        String strSql = "SELECT username, password, email, adresse, rib, raisonSociale FROM utilisateur WHERE username = '"+username+"'" +
                        "AND password = '"+password+"'";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();

        while(!(cursor.isAfterLast())){

            utilisateur = new UtilisateurFo(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                                            cursor.getString(3),cursor.getString(4),cursor.getString(5));
            Log.d("UtilisateurFoConnexion", "Connecté");
            return utilisateur;
        }
        Log.d("UtilisateurFoConnexion", "Pas connecté");
        return null;
    }
    //endregion

    //region Update profil utilisateur
    /**
     * Permet l'update d'un profil utilisateur de type "Acheteur".
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

        password = password.replace("'","''");
        email = email.replace("'","''");
        adresse = adresse.replace("'","''");
        nom = nom.replace("'","''");
        prenom = prenom.replace("'","''");
        rib = rib.replace("'","''");
        username = username.replace("'","''");

        String strSql = "UPDATE utilisateur" +
                "SET password = '"+password+"'," +
                "email = '"+email+"'," +
                "adresse = '"+adresse+"'," +
                "sexe = "+sexe+"," +
                "nom = '"+nom+"'," +
                "prenom = '"+prenom+"'," +
                "rib = '"+rib+"'" +
                "WHERE username = '"+username+"'";
        try{
            this.getWritableDatabase().execSQL(strSql);
            Log.d("modifUtilAc", "Effectué");
        }catch (Exception e){
            Log.d("modifUtilAc", "Echec de requête");
        }
    }
    public void updateProfilFo(String username, String password, String email, String adresse, String rib, String raisonSociale){

        password = password.replace("'","''");
        email = email.replace("'","''");
        adresse = adresse.replace("'","''");
        rib = rib.replace("'","''");
        username = username.replace("'","''");

        String strSql = "UPDATE utilisateur" +
                "SET password = '"+password+"'," +
                "email = '"+email+"'," +
                "adresse = '"+adresse+"'," +
                "rib = '"+rib+"'" +
                "raisonSociale ='"+raisonSociale+"',"+
                "WHERE username = '"+username+"'";
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
     * Permet d'update si l'article existe sinon insert.
     * A tester.
     * @param idUtilisateur
     * @param cat
     * @param nomArticle
     * @param ean
     * @param prix
     * @param description
     * @param quantite
     */
    public void articleAdd( Integer idUtilisateur, String cat, String nomArticle, Integer ean, Integer prix,
                            String description, Integer quantite){

        cat = cat.replace("'","''");
        nomArticle = nomArticle.replace("'","''");
        description = description.replace("'", "''");

        String strSqlVerif = "SELECT nomArticle FROM article WHERE nomArticle = '"+nomArticle+"'";

        Cursor cursor = this.getWritableDatabase().rawQuery(strSqlVerif, null);
        cursor.moveToFirst();

        while(!(cursor.isAfterLast())){

            if(cursor.getString(0).equals(nomArticle)){

                try{

                    String strSqlAdd = "UPDATE article SET stock="+quantite+" WHERE nomArticle='"+nomArticle+"'";
                    this.getWritableDatabase().execSQL(strSqlAdd);
                    Log.d("remplArticle", "Vous avez ajouté à votre stock");

                }catch(Exception e){

                    Log.d("remplArticle", "Erreur lors de l'execution de la requête");

                }
            }else{

                String strSql="INSERT INTO article(cat, description, ean, idUtilisateur, nomArticle, prix, stock) " +
                        "VALUES('"+cat+"','"+description+"',"+ean+","+idUtilisateur+",'"+nomArticle+"',"+prix+","+quantite+")";
                try{
                    //Test si la requête peut bien s'exécuter
                    this.getWritableDatabase().execSQL(strSql);
                    Log.d("articleAdd", "Création d'un nouvel article effectué !");
                }catch (Exception e){
                    //Erreur renvoyé en cas de problème lors de l'exécution de la requête.
                    Log.d("articleAdd", "Erreur lors de l'execution de la requête");
                }

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

    public void articleAjout(String nomArticle){

        nomArticle=nomArticle.replace("'","''");

    }
    //endregion
}