package net.peyrache.marketplace.controller;

import net.peyrache.marketplace.model.Connexion;

public class CpageAccueil {
    private Connexion connexion;

    //Constructeur CpageAccueil
    public CpageAccueil() {
        super();
    }

    /**
     * Création de la méthode getConnexion pour permettre à l'utilisateur de créer un object connexion.
     * @param username
     * @param password
     */
    public void getConnection(String username, String password){
        connexion = new Connexion(username, password);
    }

    /**
     * Création d'un getter pour avoir accès au nom d'utilisateur de la classe (récupère les valeurs des attributs,
     * ayant reçus une valeur dans le constructeur.
     * @return
     */
    public String getUsername(){
        return connexion.getUsername();
    }

    /**
     * Création d'un getter pour avoir accès au mot de passe de la classe (récupère les valeurs des attributs,
     * ayant reçus une valeur dans le constructeur.
     * @return
     */
    public String getPassword(){
        return connexion.getPassword();
    }

    /**
     * Récupération du résultat de la méthode verifUser de la classe Connexion.
     * @return
     */
    public Boolean getVerifUser(){
        return connexion.verifUser();
    }
}
