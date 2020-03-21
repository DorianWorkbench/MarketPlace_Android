package net.peyrache.marketplace.controller;

import net.peyrache.marketplace.model.InscriptionAc;
import net.peyrache.marketplace.model.InscriptionFo;

public class CinscriptionActivity {
    private InscriptionAc inscriptionAc;
    private InscriptionFo inscriptionFo;

    public CinscriptionActivity() {
        super();
    }

    public void getInscriptionInstanceAc(String username, String password, String email,
                                         String postalAddress, String surname, String name, Integer sexe,
                                         String paiement, String rib){

        inscriptionAc=new InscriptionAc(username, password, email, postalAddress, surname, name, sexe, paiement, rib);

    }
}
