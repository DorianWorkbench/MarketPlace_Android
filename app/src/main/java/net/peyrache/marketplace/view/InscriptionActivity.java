package net.peyrache.marketplace.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import net.peyrache.marketplace.R;
import net.peyrache.marketplace.controller.CinscriptionActivity;

public class InscriptionActivity extends AppCompatActivity {

    //Définition des variables utilisées dans la classe
    private EditText inscUsernameET, inscPasswordET, inscEmail, inscPostalAddress, surname, name, rib;
    private RadioButton man, woman;
    private Button subscribe, provider;
    private CinscriptionActivity inscriptionActivity;
    private Intent intentMain, intentFournisseur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        //Utilisation de la méthode init() à la création de l'activity
        init();
    }

    /**
     * Permet de dégager plus de lisibilité dans le onCreate
     */
    private void init(){
        inscUsernameET = findViewById(R.id.inscUsernameET);
        inscPasswordET = findViewById(R.id.inscPasswordET);
        inscEmail = findViewById(R.id.inscEmailET);
        inscPostalAddress = findViewById(R.id.inscPostalAddressET);
        surname = findViewById(R.id.surname);
        name = findViewById(R.id.name);
        rib = findViewById(R.id.ribET);
        man = findViewById(R.id.manRB);
        woman=findViewById(R.id.womanRB);
        subscribe = findViewById(R.id.subscribe);
        provider = findViewById(R.id.provider);

        //Insctanciation du controller
        inscriptionActivity= new CinscriptionActivity(InscriptionActivity.this);

        //Récupération des méthodes d'écoute des boutons.
        ecouteurInscription();
        ecouteurProviderButton();
    }

    /**
     * Permet de savoir quand le bouton "Inscription" est appuyé.
     * Permet le traitement de la méthode abstraite onClick de onClickListener().
     */
    private void ecouteurInscription(){

        subscribe.setOnClickListener(new Button.OnClickListener(){


            @Override
            public void onClick(View v) {
                // Initialisation des Objets de présentation et Transtypage en
                // chaine de caractère pour pouvoir récupérer les données utilisateur.
                String inscUsername = inscUsernameET.getText().toString();
                String inscPassword = inscPasswordET.getText().toString();
                String inscEmailEt = inscEmail.getText().toString();
                String inscPostalAddressEt = inscPostalAddress.getText().toString();
                String inscSurname = surname.getText().toString();
                String inscName = name.getText().toString();
                String inscRib = rib.getText().toString();
                Integer sexe = (man.isChecked())?0:1;

                // Si tous les champs sont remplis, Création de l'utilisateur.
                if (!(inscUsername.isEmpty() || inscPassword.isEmpty() || inscEmailEt.isEmpty() ||
                        inscPostalAddressEt.isEmpty() || inscSurname.isEmpty() ||
                        inscName.isEmpty() || inscRib.isEmpty() || !(man.isChecked()) && !(woman.isChecked()))) {
                    //Si l'utilisateur n'existe pas permettre.
                    if(inscriptionActivity.utilExist(inscUsername.toLowerCase())){

                        // Utilisation de la méthode d'inscription d'utilisateur de type Acheteur.
                        inscriptionActivity.getInscriptionInstanceAc(inscUsername.toLowerCase(), inscPassword, inscEmailEt, inscPostalAddressEt, inscSurname, inscName, sexe, inscRib);
                        Toast.makeText(InscriptionActivity.this, "Bravo vous vous êtes enregistré", Toast.LENGTH_SHORT).show();

                        // Renvoie vers la page de connexion.
                        intentMain=new Intent(InscriptionActivity.this, MainActivity.class);
                        startActivity(intentMain);
                    }else{
                        Toast.makeText(InscriptionActivity.this, "Le nom d'utilisateur existe déjà.", Toast.LENGTH_SHORT).show();
                    }

                } else { // Sinon, Affichage d'un toast demandant de saisir des informations valides.
                    Toast.makeText(InscriptionActivity.this, "Veuillez rentrer des informations valides", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Récupération de l'event de click sur le bouton.
     */
    private void ecouteurProviderButton(){
        provider.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Renvoie sur la page d'inscription de type Fournisseur.
                intentFournisseur = new Intent(InscriptionActivity.this, InscriptionFoActivity.class);
                startActivity(intentFournisseur);
            }
        });
    }
}
