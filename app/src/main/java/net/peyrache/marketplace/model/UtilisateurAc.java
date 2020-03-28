package net.peyrache.marketplace.model;

public class UtilisateurAc extends Utilisateur {

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

    public Integer getIdUtilisateur(){
        return this.nUtilisateur;
    }



}
