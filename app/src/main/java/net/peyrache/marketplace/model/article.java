package net.peyrache.marketplace.model;

public class article {
    private Integer nArticle;
    private Integer idUtilisateur;
    private String cat;
    private String nomArticle;
    private Integer ean;
    private Integer prix;
    private String description;
    private Integer stock;

    public article(Integer idUtilisateur, String cat, String nomArticle, Integer ean,
                   Integer prix, String description, Integer stock) {

        this.idUtilisateur = idUtilisateur;
        this.cat = cat;
        this.nomArticle = nomArticle;
        this.ean = ean;
        this.prix = prix;
        this.description = description;
        this.stock = stock;
    }

    public String getCat() {
        return cat;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public Integer getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStock() {
        return stock;
    }
}
