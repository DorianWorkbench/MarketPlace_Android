package net.peyrache.marketplace.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqLiteAccessRequest extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Marketplace.db";
    private static final Integer DATABASE_VERSION = 1;

    public SqLiteAccessRequest(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "CREATE TABLE utilisateur("
                            +"idUtilisateur integer primary key autoincrement,"
                            +"username text not null,"
                            +"password text not null,"
                            +"email text not null,"
                            +"adresse text not null,"
                            +"type text not null,"
                            +"paiement text not null,"
                            +"sexe integer,"
                            +"nom text,"
                            +"prenom text,"
                            +"rib text,"
                            +"raisonSociale text"
                        +")";
        db.execSQL(strSql);
        Log.d("Bdd", "onCreate invoke");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void newUser(String username, String password, String email, String postalAddress, String type, String paiement, Integer sexe, String surname){
        username = username.replace("'","''");
        password = password.replace("'","''");
        email = email.replace("'","''");
        postalAddress = postalAddress.replace("'","''");
        type = type.replace("'", "''");
        paiement = paiement.replace("'", "''");
        surname = surname.replace("'", "''");
        String strSql="INSERT INTO utilisateur(username, password, email, adresse, type, paiement, sexe, nom, prenom, rib) VALUES('"+username+"','"+password+"','"+email+"','"+postalAddress+"','"+type+"','"+paiement+"','"+sexe+"','"+surname+"')";
    }
}
