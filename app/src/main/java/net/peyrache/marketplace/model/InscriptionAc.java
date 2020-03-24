package net.peyrache.marketplace.model;

public class InscriptionAc extends Inscription{
    private String surname;
    private String name;
    private Integer sexe;
    private String paiement;
    private static final String type="ac";

    public InscriptionAc(String inscUsername, String inscPassword, String inscEmail,
                         String inscPostalAddress, String surname, String name, Integer sexe, String rib) {
        this.inscUsername = inscUsername;
        this.inscPassword = inscPassword;
        this.inscEmail = inscEmail;
        this.inscPostalAddress = inscPostalAddress;
        this.surname = surname;
        this.name = name;
        this.sexe = sexe;
        this.rib = rib;
        this.paiement="Carte bancaire";
    }

    public String getType() {
        return type;
    }

    public String getPaiement() {
        return paiement;
    }

}


