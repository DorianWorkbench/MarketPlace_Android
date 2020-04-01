package net.peyrache.marketplace.controller;

import android.content.Context;

import net.peyrache.marketplace.model.UtilisateurAc;
import net.peyrache.marketplace.model.UtilisateurFo;
import net.peyrache.marketplace.tools.SqLiteAccessRequest;

public class CpageAccueil {
    private SqLiteAccessRequest sqLiteAccessRequest;
    private UtilisateurAc utilisateur;
    private Context context;

    //Constructeur CpageAccueil retournant le contenu du constructeur Object.
    public CpageAccueil(Context context) {
        this.context=context;
    }

    /**
     * Permet de tester le type de l'utilisateur pour les conditions du MainActivity
     * (utilisé dans le main activity pour ses conditions)
     * @param username
     * @param password
     * @return
     */
    public String getTypeUser(String username, String password){
        sqLiteAccessRequest= new SqLiteAccessRequest(context);
        String verif = sqLiteAccessRequest.verifTypeUser(username, password);
        sqLiteAccessRequest.close();
        return verif;
    }

    /**
     * Récupération de l'objet utilisateur de type "acheteur" permettant l'accès à ses attributs.
     * @param username
     * @param password
     */
    public UtilisateurAc getConnexionAC(String username, String password){

        //Ouverture d'une connexion à la base de données.
        sqLiteAccessRequest= new SqLiteAccessRequest(context);

        //Récupération de l'objet utilisateur de la méthode connexionAC de l'outil SqLiteAccessRequest
        UtilisateurAc utilisateur = sqLiteAccessRequest.connexionAC(username, password);
        sqLiteAccessRequest.close();
        return utilisateur;
    }
    /**
     * Récupération de l'objet utilisateur de type "fournisseur" permettant l'accès à ses attributs.
     * @param username
     */
    public UtilisateurFo getConnexionFO(String username){
        //Ouverture d'une connexion à la base de données.
        sqLiteAccessRequest= new SqLiteAccessRequest(context);

        UtilisateurFo user = sqLiteAccessRequest.connexionFo(username);
        sqLiteAccessRequest.close();
        return user;
    }

    /**
     * Création d'un nouvel article.
     * @param idUtilisateur
     * @param cat
     * @param nomArticle
     * @param ean
     * @param prix
     * @param description
     * @param stock
     */
    public void newArticle(Integer idUtilisateur, String cat, String nomArticle, Integer ean,
                           Integer prix, String description, Integer stock){
        //Ouverture d'une connexion à la base de données.
        sqLiteAccessRequest= new SqLiteAccessRequest(context);
        //Ajout d'un article.
        sqLiteAccessRequest.articleAdd(idUtilisateur,cat, nomArticle, ean, prix,
                            description, stock);
        sqLiteAccessRequest.close();
    }
}

