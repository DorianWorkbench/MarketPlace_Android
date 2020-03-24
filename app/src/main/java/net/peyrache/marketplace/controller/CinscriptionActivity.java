package net.peyrache.marketplace.controller;

import android.content.Context;
import android.util.Log;

import net.peyrache.marketplace.model.InscriptionAc;
import net.peyrache.marketplace.model.InscriptionFo;
import net.peyrache.marketplace.tools.SqLiteAccessRequest;

public class CinscriptionActivity {

    private InscriptionAc inscriptionAc;
    private InscriptionFo inscriptionFo;
    private SqLiteAccessRequest sqLiteAccessRequest;

    public CinscriptionActivity() {
        super();
    }

    public void getInscriptionInstanceAc(String username, String password, String email,
                                         String postalAddress, String surname, String name, Integer sexe, String rib){
        inscriptionAc= new InscriptionAc(username,password,email,postalAddress,surname,name,sexe,rib);
        sqLiteAccessRequest.newUserAC(username, password, email, postalAddress,inscriptionAc.getType(), inscriptionAc.getPaiement(), sexe, surname, name, rib);
        sqLiteAccessRequest.close();
    }

    public void getInscriptionInstanceFo(String username, String password, String email,
                                         String postalAddress, String rib, String raisonSociale){
        inscriptionFo= new InscriptionFo(username,password,email,postalAddress,rib, raisonSociale);
        Log.d("inscriptionFO type : ", "Username : "+username+" Password : "+password+" email : "+email+" Postal Address : "+postalAddress+" Type : "+inscriptionFo.getType()+" rib : "+rib+" Raison Sociale : "+raisonSociale);
        sqLiteAccessRequest.newUserFO(username, password, email, postalAddress,inscriptionFo.getType(), rib, raisonSociale);
        sqLiteAccessRequest.close();
    }

    public void getSqlAccessResquest(Context context){
        sqLiteAccessRequest= new SqLiteAccessRequest(context);
    }
}
