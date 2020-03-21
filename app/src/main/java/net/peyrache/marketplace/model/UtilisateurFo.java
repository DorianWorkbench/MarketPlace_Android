package net.peyrache.marketplace.model;

public class UtilisateurFo extends Utilisateur{

    private String raisonSociale;

    public UtilisateurFo(String username, String password, String email, String postalAddress, String rib, String raisonSociale) {
        this.username=username;
        this.password=password;
        this.email=email;
        this.postalAddress=postalAddress;
        this.type="fo";
        this.rib=rib;
        this.raisonSociale=raisonSociale;
    }

}
