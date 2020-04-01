package net.peyrache.marketplace.controller;

import android.content.Context;

import net.peyrache.marketplace.model.UtilisateurFo;
import net.peyrache.marketplace.tools.SqLiteAccessRequest;

public class CutilFo {

private SqLiteAccessRequest sqLiteAccessRequest;
private Context context;

    public CutilFo(Context context){
        this.context = context;
    }
//    public void updateProfilFo(String username, String password, String email, String adresse, String rib, String raisonSociale){

        public void modifUserFo(String username, String password, String email, String adresse, String rib, String raisonSociale){
        sqLiteAccessRequest = new SqLiteAccessRequest(context);
        sqLiteAccessRequest.updateProfilFo(username, password, email, adresse, rib, raisonSociale);
        sqLiteAccessRequest.close();
    }
    public UtilisateurFo verifUtilisateurFo(String username, String password, String email, String adresse, String rib, String raisonSociale){
        UtilisateurFo utilisateurFo = new UtilisateurFo(username, password, email, adresse, rib, raisonSociale);
        return utilisateurFo;
    }
}
