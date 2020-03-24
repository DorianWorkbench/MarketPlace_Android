package net.peyrache.marketplace.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.peyrache.marketplace.R;
import net.peyrache.marketplace.controller.CpageAccueil;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends AppCompatActivity {

    //Déclaration des variables d'objet graphique
    private EditText usernameET, passwordET;
    private Button connexionBT, inscriptionBT;
    private CpageAccueil controle;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * Methode permettant plus de lisibilité dans le onCreate du MainActivity.
     * Assignation des objets pour les variable objets créée précedement.
     */
    private void init(){
        this.usernameET= findViewById(R.id.usernameET);
        this.passwordET= findViewById(R.id.passwordET);
        this.connexionBT= findViewById(R.id.connexionBT);
        this.inscriptionBT= findViewById(R.id.inscriptionBT);
        this.controle = new CpageAccueil();
        ecouteurBoutonConnexion();
        ecouteurBoutonInscription();
    }

    /**
     * Ecoute du bouton de connexion.
     */
    private void ecouteurBoutonConnexion(){
        connexionBT.setOnClickListener(new Button.OnClickListener(){

            /**
             * lors du click, une vérification sur les données de connexion est faite.
             * @param v
             */
            @Override
            public void onClick(View v) {
                String username = MainActivity.this.usernameET.getText().toString();
                String password= MainActivity.this.passwordET.getText().toString();
                controle.getConnection(username, password);

                if(controle.getVerifUser()){
                    Toast.makeText(MainActivity.this, "Vous êtes connecté avec vos identifiant\nUtilisateur : "+controle.getUsername()+"\nMot de passe : "+controle.getPassword(), Toast.LENGTH_SHORT).show();
                }else if (isEmpty(username) && isEmpty(password)|| isEmpty(username)||isEmpty(password)){
                    Toast.makeText(MainActivity.this, "Remplissez correctement le formulaire", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Erreur dans vos identifiants\nUtilisateur : " +controle.getUsername()+"\nMot de passe : "+controle.getPassword()+"\nVous êtes vous inscrit ?", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Permet l'écoute du bouton inscription.
     * Surcharge de la méthode onClick pour ouvrir l'activity InscriptionActivity
     */
    private void ecouteurBoutonInscription(){
        inscriptionBT.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this, InscriptionActivity.class);
                startActivity(intent);
            }
        });
    }
}

