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
import net.peyrache.marketplace.model.InscriptionAc;
import net.peyrache.marketplace.tools.SqLiteAccessRequest;

public class InscriptionActivity extends AppCompatActivity {

    private EditText inscUsernameET, inscPasswordET, inscEmail, inscPostalAddress, surname, name, rib;
    private RadioButton man, woman;
    private Button subscribe, provider;
    private CinscriptionActivity inscriptionActivity;
    private Intent intentMain;

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
        rib = findViewById(R.id.ribET);
        man = findViewById(R.id.manRB);
        woman=findViewById(R.id.womanRB);
        subscribe = findViewById(R.id.subscribe);
        provider = findViewById(R.id.provider);
        inscriptionActivity= new CinscriptionActivity();
        ecouteurInscription();
    }
    private void ecouteurInscription(){

        subscribe.setOnClickListener(new Button.OnClickListener(){


            @Override
            public void onClick(View v) {
                String inscUsername = inscUsernameET.getText().toString();
                String inscPassword = inscPasswordET.getText().toString();
                String inscEmailEt = inscEmail.getText().toString();
                String inscPostalAddressEt = inscPostalAddress.getText().toString();
                String inscSurname = surname.getText().toString();
                String inscName = name.getText().toString();
                String inscRib = rib.getText().toString();
                Integer sexe = (man.isChecked())?0:1;

                if (!(inscUsername.isEmpty() || inscPassword.isEmpty() || inscEmailEt.isEmpty() ||
                        inscPostalAddressEt.isEmpty() || inscSurname.isEmpty() ||
                        inscName.isEmpty() || inscRib.isEmpty() || !(man.isChecked()) && !(woman.isChecked()))) {
                    inscriptionActivity.getSqlAccessResquest(InscriptionActivity.this);

                    inscriptionActivity.getInscriptionInstanceAc(inscUsername, inscPassword, inscEmailEt, inscPostalAddressEt, inscSurname, inscName, sexe, inscRib);
                    Toast.makeText(InscriptionActivity.this, "Bravo vous vous êtes enregistré votre sexe est : "+sexe, Toast.LENGTH_SHORT).show();
                    intentMain=new Intent(InscriptionActivity.this, MainActivity.class);
                    startActivity(intentMain);

                } else {
                    Toast.makeText(InscriptionActivity.this, "Veuillez rentrer des informations valides", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
