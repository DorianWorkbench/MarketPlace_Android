package net.peyrache.marketplace.model;

public class UtilisateurAc extends Utilisateur {

    private String surname;
    private String name;
    private Integer sexe;
    private String paiement;

    public UtilisateurAc(String username, String password, String email, String postalAddress, String rib,String surname, String name, Integer sexe, String paiement) {
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
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public Integer getSexe() {
        return sexe;
    }

    public String getPaiement() {
        return paiement;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPaiement(String paiement) {
        this.paiement = paiement;
    }


}
