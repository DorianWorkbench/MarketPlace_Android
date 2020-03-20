package net.peyrache.marketplace.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.peyrache.marketplace.R;
import net.peyrache.marketplace.controller.CpageAccueil;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends AppCompatActivity {

    private EditText usernameET;
    private EditText passwordET;
    private Button connexionBT;
    private Button inscriptionBT;
    private static CpageAccueil controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        this.usernameET= findViewById(R.id.usernameET);
        this.passwordET= findViewById(R.id.passwordET);
        this.connexionBT= findViewById(R.id.connexionBT);
        this.inscriptionBT= findViewById(R.id.inscriptionBT);
        this.controle = new CpageAccueil();
        ecouteurBoutonConnexion();
        ecouteurBoutonInscription();
    }

    //Ecoute du bouton de connexion.
    private void ecouteurBoutonConnexion(){
        connexionBT.setOnClickListener(new Button.OnClickListener(){
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
    private void ecouteurBoutonInscription(){
        inscriptionBT.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Vous avez demandé à vous inscrire", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

