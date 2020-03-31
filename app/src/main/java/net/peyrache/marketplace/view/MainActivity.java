package net.peyrache.marketplace.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.peyrache.marketplace.R;
import net.peyrache.marketplace.controller.CpageAccueil;
import net.peyrache.marketplace.model.UtilisateurAc;
import net.peyrache.marketplace.model.UtilisateurFo;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends AppCompatActivity {

    //Déclaration des variables d'objet graphique
    private EditText usernameET, passwordET;
    private Button connexionBT, inscriptionBT;
    private CpageAccueil controle;
    private Intent intent, intent_utilAc, intent_utilFo;
    private TextView text;

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
        this.text=findViewById(R.id.tvConnexion);
        text.setText("Coucou");
        this.controle = new CpageAccueil(MainActivity.this);
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

                // Initialisation des variables ayant pour valeur les données rentré par l'utilisateur une fois le bouton validé.
                // La valeur récupérée est ici transtypée en type String.

                String username = usernameET.getText().toString();
                String password = passwordET.getText().toString();


                //Vérification du type de l'utilisateur cherchant à se connecter.
                String verif = controle.getTypeUser(username, password);

                //Vérification du type d'utilisateur pour savoir quel Classe utilisateur instancier.
                if (verif.equals("ac")){

                    // Initialisation de la variable connexionAc pour facilité la récupération de données
                    // (moins de choses à tapper)
                    UtilisateurAc connexionAc = controle.getConnexionAC(username, password);

                    // Initialisation des variables surname et name pour mettre la première lettre
                    // de ces deux chaines de caractères en Capitale.
                    String surname = connexionAc.getSurname().substring(0, 1).toUpperCase()+connexionAc.getSurname().substring(1);
                    String name = connexionAc.getName().substring(0, 1).toUpperCase()+connexionAc.getName().substring(1);

                    // Message de bienvenue.
                    Toast.makeText(MainActivity.this, "Bienvenue "+surname+" "+name, Toast.LENGTH_SHORT).show();

                    //Redirection MainActivity utilisateurAc
                    intent_utilAc=new Intent(MainActivity.this, UtilisateurAcPages.class);
                    intent_utilAc.putExtra("utilisateur", connexionAc);
                    startActivity(intent_utilAc);

                }else if(verif.equals("fo")){
                    String usernameFo = usernameET.getText().toString();
                    String passwordFo = passwordET.getText().toString();


                    UtilisateurFo connexionFo = controle.getConnexionFO(username, password);

                    String raisonSociale = connexionFo.getRaisonSociale().substring(0, 1).toUpperCase()+connexionFo.getRaisonSociale().substring(1);
                    Toast.makeText(MainActivity.this, "Bienvenue "+raisonSociale, Toast.LENGTH_SHORT).show();
                    intent_utilFo = new Intent(MainActivity.this, UtilisateurFoPages.class);
                    intent_utilFo.putExtra("utilisateurFo", connexionFo);
                    startActivity(intent_utilFo);
                }else{
                    Toast.makeText(MainActivity.this, "Erreur dans les identifiants", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Permet l'écoute du bouton "inscription" et ainsi la redirection sur la page d'inscription.
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