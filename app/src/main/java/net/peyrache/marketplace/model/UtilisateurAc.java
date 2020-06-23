package net.peyrache.marketplace.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UtilisateurAc extends Utilisateur implements Parcelable {

    private String surname;
    private String name;
    private Integer sexe;
    private String paiement;

    public UtilisateurAc(String username, String password, String email, String postalAddress, String rib,String surname, String name, Integer sexe, String paiement, Integer nUtilisateur) {
        this.surname = surname;
        this.name = name;
        this.sexe = sexe;
        this.paiement = paiement;
        this.username = username;
        this.password = password;
        this.email = email;
        this.postalAddress=postalAddress;
        this.rib = rib;
        this.type="ac";
        this.nUtilisateur = nUtilisateur;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getEmail(){
        return this.email;
    }
    public String getPostalAddress(){
        return this.postalAddress;
    }
    public String getRib(){
        return this.rib;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getName() {
        return this.name;
    }

    public Integer getSexe() {
        return this.sexe;
    }

    public String getPaiement() {
        return this.paiement;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPaiement(String paiement) {
        this.paiement = paiement;
    }

    public Integer getnUtilisateur() {
        return this.nUtilisateur;
    }

    protected UtilisateurAc(Parcel in) {
        username = in.readString();
        password = in.readString();
        email = in.readString();
        postalAddress = in.readString();
        rib = in.readString();
        surname = in.readString();
        name = in.readString();
        if (in.readByte() == 0) {
            sexe = null;
        } else {
            sexe = in.readInt();
        }
        paiement = in.readString();
        nUtilisateur = in.readInt();
    }

    public static final Creator<UtilisateurAc> CREATOR = new Creator<UtilisateurAc>() {
        @Override
        public UtilisateurAc createFromParcel(Parcel in) {
            return new UtilisateurAc(in);
        }

        @Override
        public UtilisateurAc[] newArray(int size) {
            return new UtilisateurAc[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.email);
        dest.writeString(this.postalAddress);
        dest.writeString(this.rib);
        dest.writeString(this.surname);
        dest.writeString(this.name);
        dest.writeInt(this.sexe);
        dest.writeString(this.paiement);
        dest.writeInt(this.nUtilisateur);
    }
}
