package net.peyrache.marketplace.model;

public class Connexion {
    private String username;
    private String password;

    //Constructeur
    public Connexion(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Méthode de vérification de connexion
     * @return Boolean pour les tests dans le MainActivity
     */
    public Boolean verifUser(){
        return username.equals("toto") && password.equals("toto");
    }
}
