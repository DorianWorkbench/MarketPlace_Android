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
    public String getRaisonSociale(){
        return this.raisonSociale;
    }
}
