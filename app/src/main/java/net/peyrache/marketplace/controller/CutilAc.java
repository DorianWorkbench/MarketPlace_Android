package net.peyrache.marketplace.controller;

import android.content.Context;

import net.peyrache.marketplace.model.Article;
import net.peyrache.marketplace.model.Commande;
import net.peyrache.marketplace.tools.SqLiteAccessRequest;

import java.util.ArrayList;

public class CutilAc {

    private Context context;
    private SqLiteAccessRequest sqLiteAccessRequest;

    public CutilAc(Context context){
        this.context=context;
    }

    public ArrayList<Article> listeBoutique(){
        sqLiteAccessRequest = new SqLiteAccessRequest(context);
        ArrayList<Article> listArticles =(ArrayList<Article>)sqLiteAccessRequest.affichageBoutique();
        sqLiteAccessRequest.close();
        return listArticles;
    }

    public void ajoutCommande(Integer idUtilisateur, Integer nArticle, Float prixArticle, String nomArticle){
        sqLiteAccessRequest = new SqLiteAccessRequest(context);
        sqLiteAccessRequest.ajoutCommande(idUtilisateur,nArticle,prixArticle, nomArticle);
        sqLiteAccessRequest.close();
    }
    public Boolean stockSuffisant(Integer nArticle){
        Boolean stockSuffisant;
        sqLiteAccessRequest = new SqLiteAccessRequest(context);
        stockSuffisant = sqLiteAccessRequest.stockSuffisant(nArticle);
        sqLiteAccessRequest.close();
        return stockSuffisant;
    }

    public ArrayList<Commande> listeCommandes(Integer idUtilisateur){
        ArrayList<Commande> listeCommande;
        sqLiteAccessRequest = new SqLiteAccessRequest(context);
        listeCommande = (ArrayList<Commande>) sqLiteAccessRequest.listeCommandes(idUtilisateur);
        sqLiteAccessRequest.close();
        return  listeCommande;
    }
    public void supprLigneCommande(Integer idUtilisateur, Integer nArticle, Integer nCommande){
        sqLiteAccessRequest = new SqLiteAccessRequest(context);
        sqLiteAccessRequest.supprLigneCommande(idUtilisateur, nArticle, nCommande);
        sqLiteAccessRequest.close();
    }

    public String nomFournisseur(Integer nArticle){
        String nomFournisseur="";
        sqLiteAccessRequest = new SqLiteAccessRequest(context);
        nomFournisseur=sqLiteAccessRequest.fournisseurArticle(nArticle);
        sqLiteAccessRequest.close();
        return nomFournisseur;
    }
    public void supprToutesCommandes(ArrayList<Commande> listeCommande, Integer nUtilisateur){
        sqLiteAccessRequest = new SqLiteAccessRequest(context);
        sqLiteAccessRequest.supprToutesCommandes(listeCommande, nUtilisateur);
        sqLiteAccessRequest.close();
    }
    public void updateUtilAc(String username, String password, String adresse, String rib, String email){
        sqLiteAccessRequest = new SqLiteAccessRequest(context);
        sqLiteAccessRequest.updateProfilAc(username,password,adresse,rib,email);
        sqLiteAccessRequest.close();
    }
}
