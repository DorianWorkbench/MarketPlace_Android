package net.peyrache.marketplace.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import net.peyrache.marketplace.R;
import net.peyrache.marketplace.controller.CinscriptionActivity;
import net.peyrache.marketplace.model.InscriptionAc;

public class InscriptionActivity extends AppCompatActivity {

    private EditText inscUsernameET, inscPasswordET, inscEmail, inscPostalAddress, surname, name;
    private RadioButton man, woman;
    private Button subscribe, provider;
    private CinscriptionActivity inscriptionActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        init();
    }

    private void init(){
        inscUsernameET = findViewById(R.id.inscUsernameET);
        inscPasswordET = findViewById(R.id.inscPasswordET);
        inscEmail = findViewById(R.id.inscEmailET);
        inscPostalAddress = findViewById(R.id.inscPostalAddressET);
        surname = findViewById(R.id.surname);
        name = findViewById(R.id.name);
        man = findViewById(R.id.manRB);
        woman=findViewById(R.id.womanRB);
        subscribe = findViewById(R.id.subscribe);
        provider = findViewById(R.id.provider);
        inscriptionActivity= new CinscriptionActivity();
    }
    private void ecouteurInscription(){

        subscribe.setOnClickListener(new Button.OnClickListener(){


            @Override
            public void onClick(View v) {
                String inscUsername=inscUsernameET.getText().toString();
                String inscPassword=inscPasswordET.getText().toString();
                String inscEmailEt=inscEmail.getText().toString();
                String inscPostalAddressEt=inscPostalAddress.getText().toString();
                String inscSurname=surname.getText().toString();
                String inscName=name.getText().toString();
                Integer sexe=(man.isChecked())?0:1;

                //Création de l'instance d'inscription en vu de lier une requête d'update
                //inscriptionActivity.getInscriptionInstanceAc(inscUsername, inscPassword,inscEmailEt,inscPostalAddressEt,inscSurname,inscName,sexe);

            }
        });
    }
}
