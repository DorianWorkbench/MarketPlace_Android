package net.peyrache.marketplace.model;

public class Commande {
    private Integer nCommande;
    private Integer idUtilisateur;
    private Integer nArticle;
    private Float prixArticle;
    private String nomArticle;

    public Commande(Integer nCommande, Integer idUtilisateur, Integer nArticle, Float prixArticle, String nomArticle){
        this.idUtilisateur=idUtilisateur;
        this.nArticle=nArticle;
        this.prixArticle=prixArticle;
        this.nCommande = nCommande;
        this.nomArticle=nomArticle;
    }

    //region getters

    public String getNomArticle() {
        return nomArticle;
    }

    public Integer getnCommande() {
        return nCommande;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public Integer getnArticle() {
        return nArticle;
    }

    public Float getPrixArticle() {
        return prixArticle;
    }
    //endregion getters
}
