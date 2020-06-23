package net.peyrache.marketplace.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {
    private Integer nArticle;
    private Integer idUtilisateur;
    private String cat;
    private String nomArticle;
    private Float prix;
    private String description;
    private Integer qte;

    public Article(Integer nArticle, Integer idUtilisateur, String cat, String nomArticle,
                   Float prix, String description, Integer qte) {

        this.idUtilisateur = idUtilisateur;
        this.cat = cat;
        this.nomArticle = nomArticle;
        this.prix = prix;
        this.description = description;
        this.qte = qte;
        this.nArticle = nArticle;
    }

    //region getters/setters
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

    public Integer getnArticle() {
        return nArticle;
    }

    public Integer setIdUtilisateur(Integer idUtilisateur){
        return this.idUtilisateur = idUtilisateur;
    }
    //endregion

    //region Parcelable Article
    protected Article(Parcel in) {
        if (in.readByte() == 0) {
            nArticle = null;
        } else {
            nArticle = in.readInt();
        }
        if (in.readByte() == 0) {
            idUtilisateur = null;
        } else {
            idUtilisateur = in.readInt();
        }
        cat = in.readString();
        nomArticle = in.readString();
        if (in.readByte() == 0) {
            prix = null;
        } else {
            prix = in.readFloat();
        }
        description = in.readString();
        if (in.readByte() == 0) {
            qte = null;
        } else {
            qte = in.readInt();
        }
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.nArticle);
        dest.writeInt(this.idUtilisateur);
        dest.writeString(this.cat);
        dest.writeString(this.nomArticle);
        dest.writeFloat(this.prix);
        dest.writeString(this.description);
        dest.writeInt(this.qte);
    }
    //endregion
}
