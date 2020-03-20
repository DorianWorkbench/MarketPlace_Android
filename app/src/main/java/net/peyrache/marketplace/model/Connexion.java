package net.peyrache.marketplace.model;

public class Connexion {
    private String username;
    private String password;

    public Connexion(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public Boolean verifUser(){
        if(username.equals("toto") && password.equals("toto")){
            return true;
        }
        return false;
    }
}
