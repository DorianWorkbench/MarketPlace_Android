package net.peyrache.marketplace.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.peyrache.marketplace.R;
import net.peyrache.marketplace.controller.CinscriptionActivity;
import net.peyrache.marketplace.model.InscriptionFo;

public class InscriptionFoActivity extends AppCompatActivity {

    private EditText inscfoUsername, inscfoPassword, inscfoEmail,
                     inscfoPostalAddress, inscfoRib, inscfoRaisonSociale;
    private Button inscfoSubscribe;
    private CinscriptionActivity inscriptionActivity;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_fo);
        init();
    }

    private void init(){
        inscfoUsername=findViewById(R.id.inscfoUsernameET);
        inscfoPassword=findViewById(R.id.inscfoPasswordET);
        inscfoEmail=findViewById(R.id.inscfoEmailET);
        inscfoPostalAddress=findViewById(R.id.inscfoPostalAddressET);
        inscfoRib=findViewById(R.id.inscfoRibET);
        inscfoRaisonSociale=findViewById(R.id.inscfoRaisonSocialeET);
        inscfoSubscribe=findViewById(R.id.inscfoSubscribeBT);
        inscriptionActivity= new CinscriptionActivity();
        ecouteurSubscribeBT();
    }

    private void ecouteurSubscribeBT(){
        inscfoSubscribe.setOnClickListener(new Button.OnClickListener(){


            @Override
            public void onClick(View v) {

                String inscfoUsernameSTR = inscfoUsername.getText().toString();
                String inscfoPasswordSTR = inscfoPassword.getText().toString();
                String inscfoEmailSTR = inscfoEmail.getText().toString();
                String inscfoPostalAddressSTR = inscfoPostalAddress.getText().toString();
                String inscfoRibSTR = inscfoRib.getText().toString();
                String inscfoRaisonSocialeSTR = inscfoRaisonSociale.getText().toString();


                if(!(inscfoUsernameSTR.isEmpty() || inscfoPasswordSTR.isEmpty() ||
                        inscfoEmailSTR.isEmpty() || inscfoPostalAddressSTR.isEmpty() ||
                        inscfoRibSTR.isEmpty() || inscfoRaisonSocialeSTR.isEmpty())){

                    inscriptionActivity.getSqlAccessResquest(InscriptionFoActivity.this);
                    inscriptionActivity.getInscriptionInstanceFo(inscfoUsernameSTR, inscfoPasswordSTR, inscfoEmailSTR, inscfoPostalAddressSTR, inscfoRibSTR, inscfoRaisonSocialeSTR);
                    Toast.makeText(InscriptionFoActivity.this, "Vous vous êtes bien enregistré", Toast.LENGTH_SHORT).show();
                    intent= new Intent(InscriptionFoActivity.this, MainActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(InscriptionFoActivity.this, "Veuillez rentrer des informations valides", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
