package net.peyrache.marketplace.model;

public class article {
    private Integer nArticle;
    private Integer idUtilisateur;
    private String cat;
    private String nomArticle;
    private Float prix;
    private String description;
    private Integer qte;

    public article(Integer idUtilisateur, String cat, String nomArticle,
                   Float prix, String description, Integer qte) {

        this.idUtilisateur = idUtilisateur;
        this.cat = cat;
        this.nomArticle = nomArticle;
        this.prix = prix;
        this.description = description;
        this.qte = qte;
    }

    public String getCat() {
        return cat;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public Float getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQte() {
        return this.qte;
    }

    public Integer setIdUtilisateur(Integer idUtilisateur){
        return this.idUtilisateur = idUtilisateur;
    }
}
