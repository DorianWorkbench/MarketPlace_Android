package net.peyrache.marketplace.controller;

import net.peyrache.marketplace.model.Connexion;

public class CpageAccueil {
    private Connexion connexion;

    public CpageAccueil() {
        super();
    }

    public void getConnection(String username, String password){
        connexion = new Connexion(username, password);
    }
    public String getUsername(){
        return connexion.getUsername();
    }

    public String getPassword(){
        return connexion.getPassword();
    }

    public Boolean getVerifUser(){
        return connexion.verifUser();
    }
}
