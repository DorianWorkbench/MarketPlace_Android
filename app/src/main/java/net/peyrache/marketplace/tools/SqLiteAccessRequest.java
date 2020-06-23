package net.peyrache.marketplace.tools;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import net.peyrache.marketplace.model.Commande;
import net.peyrache.marketplace.model.UtilisateurAc;
import net.peyrache.marketplace.model.UtilisateurFo;
import net.peyrache.marketplace.model.Article;

import java.util.ArrayList;
import java.util.List;

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
                +"prix REAL NOT NULL,"
                +"description TEXT,"
                +"stock INTEGER NOT NULL,"
                +"FOREIGN KEY(idUtilisateur) REFERENCES utilisateur(idUtilisateur)"
                +")";

        String strSql6="CREATE TABLE commandes(" +
                        "nCommande INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "idUtilisateur INTEGER NOT NULL," +
                        "nArticle Integer NOT NULL," +
                        "prixArticle REAL NOT NULL," +
                        "nomArticle TEXT NOT NULL,"+
                        "FOREIGN KEY(idUtilisateur) REFERENCES utilisateur(idUtilisateur)," +
                        "FOREIGN KEY(nArticle) REFERENCES article(nArticle))";

        String strSql3 = "CREATE TABLE categorie(" +
                         "categorie TEXT NOT NULL PRIMARY KEY)";

        String strSql4 = "INSERT INTO categorie(categorie) VALUES('Boisson')";
        String strSql5 = "INSERT INTO categorie(categorie) VALUES('Manger')";


        db.execSQL(strSql);
        db.execSQL(strSql2);
        db.execSQL(strSql3);
        db.execSQL(strSql6);
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
     * @param rib
     */
    public void updateProfilAc(String username, String password, String adresse, String rib, String email){

        //Mise en forme du champs texte pour la requête.
        username = username.replace("'","''");
        password = password.replace("'","''");
        adresse = adresse.replace("'","''");
        rib = rib.replace("'","''");
        email = email.replace("'","''");

        //Requête d'update de l'acheteur.
        String strSql = "UPDATE utilisateur " +
                        "SET password = '"+password+"', " +
                        "adresse='"+adresse+"', " +
                        "rib='"+rib+"', " +
                        "email='"+email+"' " +
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
                             "AND raisonSociale = (SELECT raisonSociale " +
                             "FROM utilisateur " +
                             "WHERE idUtilisateur = '"+nUtilisateur+"')";

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

    /**
     * Permet d'ajouter un article à la liste d'article.
     * @param idUtilisateur
     * @return
     */
    public List<Article> listeArticleAjoutUtilFo(Integer idUtilisateur){

        List<Article> listeArticle = new ArrayList<Article>();
        Article article;

        String strSql = "SELECT article.idUtilisateur, cat, nomArticle, prix, description, stock, nArticle " +
                        "FROM article, utilisateur " +
                        "WHERE utilisateur.idUtilisateur=article.idUtilisateur " +
                        "AND utilisateur.raisonSociale = (SELECT raisonSociale " +
                                                          "FROM utilisateur " +
                                                          "WHERE utilisateur.idUtilisateur = "+idUtilisateur+")"
                        +"";

            Cursor sqlCursorArt = this.getReadableDatabase().rawQuery(strSql, null);
            sqlCursorArt.moveToFirst();

            if(sqlCursorArt.moveToFirst()){
                do {
                    article = new Article(sqlCursorArt.getInt(6), sqlCursorArt.getInt(0), sqlCursorArt.getString(1),
                            sqlCursorArt.getString(2), sqlCursorArt.getFloat(3),
                            sqlCursorArt.getString(4), sqlCursorArt.getInt(5));
                    listeArticle.add(article);
                    Log.d("ListeArticle", "Ajout d'un article");
                }while (sqlCursorArt.moveToNext());
            }

            sqlCursorArt.close();
            this.getReadableDatabase().close();
        return listeArticle;
    }

    /**
     * Méthode faisant une requête d'update pour l'article modifié
     * @param ancienNomArticle
     * @param descriptionArticle
     * @param prix
     * @param qte
     * @param idUtilisateur
     */
    public void articleUpdate(String ancienNomArticle, String descriptionArticle, Float prix, Integer qte, Integer idUtilisateur){

        ancienNomArticle = ancienNomArticle.replace("'", "''");
        descriptionArticle = descriptionArticle.replace("'", "''");

        String strSqlUpdate = "UPDATE article " +
                              "SET description ='"+descriptionArticle+"'," +
                              "prix ="+prix+"," +
                              "stock = "+qte+" " +
                              "WHERE nomArticle='"+ancienNomArticle+"'";
        try{
            this.getWritableDatabase().execSQL(strSqlUpdate);
            this.getWritableDatabase().close();
            Log.d("UpdateArticle", "L'update a bien été pris en compte");
        }catch(Exception e){
            Log.d("UpdateArticle", "Erreur dans la requête");
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

    //region Boutique/Acheteur

    /**
     * Fait une liste de tous les articles
     * @return
     */
    public List<Article> affichageBoutique(){
            List<Article> listeBoutique = new ArrayList<Article>();

            String strSqlAffich = "SELECT nArticle, idUtilisateur, cat, nomArticle, prix, description, stock FROM article WHERE stock>0";
            Cursor cursorAffich = this.getReadableDatabase().rawQuery(strSqlAffich, null);
            cursorAffich.moveToFirst();

            if(cursorAffich.moveToFirst()){
                do{
                    Article articleAffiche = new Article(cursorAffich.getInt(0), cursorAffich.getInt(1),cursorAffich.getString(2),
                                                         cursorAffich.getString(3),cursorAffich.getFloat(4),cursorAffich.getString(5),
                                                         cursorAffich.getInt(6));
                    listeBoutique.add(articleAffiche);
                }while(cursorAffich.moveToNext());
            }
            return listeBoutique;
        }

    /**
     * Permet de savoir si il y a suffisement d'article en stock
     * @param nArticle
     * @return
     */
    public Boolean stockSuffisant(Integer nArticle){

        String strSqlStock = "SELECT stock " +
                             "FROM article " +
                             "WHERE nArticle = "+nArticle+"";

        Cursor cursorStock = this.getReadableDatabase().rawQuery(strSqlStock, null);
        cursorStock.moveToFirst();
        if(cursorStock.moveToFirst()){
            do {
                if(cursorStock.getInt(0)>0){
                    this.getReadableDatabase().close();
                    Log.d("stockSuffisant", "Le stock est suffisant");
                    return true;
                }
                else{
                    this.getReadableDatabase().close();
                    Log.d("stockSuffisant", "Le stock est insuffisant");
                    return false;
                }
            }while(cursorStock.moveToNext());
        }
        this.getReadableDatabase().close();
        return false;
        }
        /**
     * Ajoute une commande à la base de données (Table commandes)
     * @param idUtilisateur
     * @param nArticle
     * @param prixArticle
     */
        public void ajoutCommande(Integer idUtilisateur, Integer nArticle, Float prixArticle, String nomArticle){
            String strSql = "INSERT INTO commandes(idUtilisateur, nArticle, prixArticle, nomArticle) " +
                            "VALUES("+idUtilisateur+", "+nArticle+", "+prixArticle+", '"+nomArticle+"')";
            
            try{
                this.getWritableDatabase().execSQL(strSql);
                this.getWritableDatabase().close();
                Log.d("AjoutCommande", "Commande Ajoutée");
                String strSqlDeleteStock = "UPDATE article SET stock = stock-1 WHERE nArticle = "+nArticle+"";
                try{
                    this.getWritableDatabase().execSQL(strSqlDeleteStock);
                    this.getWritableDatabase().close();
                    Log.d("DeleteArticle", "L'article a été soustrait de 1 dans son stock");
                }catch (Exception e){
                    Log.d("DeleteArticle", "Echec de la requête");
                }
            }catch (Exception e){
                Log.d("AjoutCommande", "Erreur sql");
            }
        }

    /**
     * Création de la liste répertoriant les commandes de l'utilisateur courrant
     * @param idUtilisateur
     * @return
     */
    public List<Commande> listeCommandes(Integer idUtilisateur){

            List<Commande> listeCommandes = new ArrayList<Commande>();

            String strSql ="SELECT nCommande, commandes.idUtilisateur, nArticle, prixArticle, nomArticle " +
                           "FROM commandes "+
                           "WHERE commandes.idUtilisateur="+idUtilisateur+"";

            Cursor cursorListeCommandes = this.getReadableDatabase().rawQuery(strSql, null);
            cursorListeCommandes.moveToFirst();

            if (cursorListeCommandes.moveToFirst()){
                do{
                    Commande commande = new Commande(cursorListeCommandes.getInt(0), cursorListeCommandes.getInt(1),
                                                     cursorListeCommandes.getInt(2), cursorListeCommandes.getFloat(3),
                                                     cursorListeCommandes.getString(4));
                    listeCommandes.add(commande);
                    Log.d("AjoutLigneCommandeStr", "Ligne ajoutée");
                }while(cursorListeCommandes.moveToNext());
            }
            this.getReadableDatabase().close();
            return listeCommandes;
        }

    /**
     * Permet de trouver le fournisseur pour l'article sélectionné.
     * @param nArticle
     * @return
     */
    public String fournisseurArticle(Integer nArticle){
        String fournisseur="";
        String strSqlNomFournisseur = "SELECT raisonSociale " +
                                      "FROM utilisateur, article " +
                                      "WHERE utilisateur.idUtilisateur=article.idUtilisateur " +
                                      "AND nArticle = "+nArticle+"";

        Cursor cursorRaisonSociale = this.getReadableDatabase().rawQuery(strSqlNomFournisseur, null);
        cursorRaisonSociale.moveToFirst();
        if(cursorRaisonSociale.moveToFirst()){
            do{
                this.getReadableDatabase().close();
                Log.d("RaisonSociale", "La raison Sociale à été trouvée"+ cursorRaisonSociale.getString(0));
                return fournisseur=cursorRaisonSociale.getString(0);
            }while(cursorRaisonSociale.moveToNext());
        }
        return null;
        }
    /**
     * Supprime la ligne commande sélectionnée et le rajoute au stock de l'article.
     * Doit être utilisé avec la méthode d'ajout d'article par rapport au nArticle.
     * @param idUtilisateur
     * @param nArticle
     */
    public void supprLigneCommande(Integer idUtilisateur, Integer nArticle, Integer nCommande){

            String strSupprLigneCommande = "DELETE FROM commandes " +
                                      "WHERE idUtilisateur = "+idUtilisateur+" " +
                                      "AND nArticle = "+nArticle+" " +
                                      "AND nCommande = "+nCommande+"";
            String updateArticle = "UPDATE article " +
                                   "SET stock = stock+1 " +
                                   "WHERE nArticle = "+nArticle+"";

            try{
                this.getWritableDatabase().execSQL(updateArticle);
                this.getWritableDatabase().close();
                this.getWritableDatabase().execSQL(strSupprLigneCommande);
                this.getWritableDatabase().close();
                Log.d("SupprLigneCommande", "L'article a été enlevé et la mise à jour à été faite");
            }catch (Exception e){
                Log.d("SupprLigneCommande", "Erreur Sql");
            }
        }
        public void supprToutesCommandes(ArrayList<Commande> listeCommande, Integer nUtilisateur){

        for(int i = 0; i<listeCommande.size(); i++){

            String strSqlUpdate ="UPDATE article " +
                                 "SET stock = stock+1 " +
                                 "WHERE nArticle = "+listeCommande.get(i).getnArticle()+"";

            try{
                this.getWritableDatabase().execSQL(strSqlUpdate);
                Log.d("Suppression", "Suppression et rajout du stock dans article");
            }catch (Exception e){
                Log.d("Suppression", "Echec");
            }
        }

        String strSqlDelete = "DELETE FROM commandes " +
                              "WHERE idUtilisateur = "+nUtilisateur+"";

            this.getWritableDatabase().close();
            this.getWritableDatabase().execSQL(strSqlDelete);
            this.getWritableDatabase().close();
    }

    //endregion
}