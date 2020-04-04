package net.peyrache.marketplace.controller;

import android.content.Context;
import android.util.Log;

import net.peyrache.marketplace.model.Article;
import net.peyrache.marketplace.model.UtilisateurFo;
import net.peyrache.marketplace.tools.SqLiteAccessRequest;

import java.util.ArrayList;
import java.util.List;

public class CutilFo {

//Définition des variables de classe.
private SqLiteAccessRequest sqLiteAccessRequest;
private Context context;

    //Constructeur de la classe CutilFo récupérant le context permettant l'instantiation de sqLiteAccessRequest.
    public CutilFo(Context context){
        this.context = context;
    }

    //Permet l'update du profil utilisateur.
    public void modifUserFo(String username, String password, String email, String adresse, String rib, String raisonSociale){
        sqLiteAccessRequest = new SqLiteAccessRequest(context);
        sqLiteAccessRequest.updateProfilFo(username, password, email, adresse, rib, raisonSociale);
        sqLiteAccessRequest.close();
    }

    //Récupération des différentes catégories enregistré dans la base de données.
    public List<String> getAllLabels(){
        sqLiteAccessRequest = new SqLiteAccessRequest(context);
        return sqLiteAccessRequest.getAllLabels();
    }

    //Permet de récupérer une valeur booléenne pour savoir si l'article existe déjà ou non.
    /*public Boolean articleExistUneFoisModif(String nomArticle, Integer nUtilisateur){
        sqLiteAccessRequest = new SqLiteAccessRequest(context);
        Boolean exist = sqLiteAccessRequest.articleExistUneFoisModif(nomArticle, nUtilisateur);
        sqLiteAccessRequest.close();
        return exist;
    }*/
    public Boolean articleExist(String nomArticle, Integer nUtilisateur){
        sqLiteAccessRequest = new SqLiteAccessRequest(context);
        Boolean exist = sqLiteAccessRequest.articleExist(nomArticle, nUtilisateur);
        sqLiteAccessRequest.close();
        return exist;
    }

    //Permet l'ajout de l'article. Prend en paramètre articleExist pour éviter les erreurs lors de l'utilisation de cette méthode.
    public void addArticle(Integer nUtilisateur, String cat, String nomArticle, String description, Float prix, Integer Qte, Boolean articleExist){
        sqLiteAccessRequest = new SqLiteAccessRequest(context);
        sqLiteAccessRequest.articleAdd(nUtilisateur, cat, nomArticle, prix,  description, Qte, articleExist);
        sqLiteAccessRequest.close();
    }

    public List<Article> listeArticle(Integer idUtilisateur){
        sqLiteAccessRequest = new SqLiteAccessRequest(context);
        List<Article> listeArticle = sqLiteAccessRequest.listeArticleAjoutUtilFo(idUtilisateur);
        sqLiteAccessRequest.close();
        return listeArticle;
    }
    /*public Article rechercheArticle(String nomArticle, Integer idUtilisateur){
        sqLiteAccessRequest = new SqLiteAccessRequest(context);
        Article article = sqLiteAccessRequest.rechercheArticle(nomArticle, idUtilisateur);
        sqLiteAccessRequest.close();
        return article;
    }*/
    public void articleUpdate(String ancienNomArticle, String descriptionArticle, Float prix, Integer qte, Integer idUtilisateur){
        sqLiteAccessRequest = new SqLiteAccessRequest(context);
        sqLiteAccessRequest.articleUpdate(ancienNomArticle, descriptionArticle, prix, qte, idUtilisateur);
        sqLiteAccessRequest.close();
    }
}
