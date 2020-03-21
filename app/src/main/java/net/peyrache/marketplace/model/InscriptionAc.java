package net.peyrache.marketplace.model;

public class InscriptionAc extends Inscription{
    private String surname;
    private String name;
    private Integer sexe;
    private String paiement;

    public InscriptionAc(String inscUsername, String inscPassword, String inscEmail, String inscPostalAddress, String surname, String name, Integer sexe, String paiement, String rib) {
        this.inscUsername = inscUsername;
        this.inscPassword = inscPassword;
        this.inscEmail = inscEmail;
        this.inscPostalAddress = inscPostalAddress;
        this.surname = surname;
        this.name = name;
        this.sexe = sexe;
        this.type = "ac";
        this.paiement = paiement;
        this.rib = rib;
        this.paiement="Carte bancaire";
    }
}
