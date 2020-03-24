package net.peyrache.marketplace.model;

public class InscriptionFo extends Inscription {

    private String raisonSociale;
    private static final String type = "fo";

    public InscriptionFo(String inscUsername, String inscPassword, String inscEmail, String inscPostalAddress, String rib, String raisonSociale) {
        this.inscUsername = inscUsername;
        this.inscPassword = inscPassword;
        this.inscEmail = inscEmail;
        this.inscPostalAddress = inscPostalAddress;
        this.rib = rib;
        this.raisonSociale=raisonSociale;
    }

    public String getType(){
        return this.type;
    }
}
