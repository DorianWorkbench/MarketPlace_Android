package net.peyrache.marketplace.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import net.peyrache.marketplace.R;
import net.peyrache.marketplace.controller.CpageAccueil;
import net.peyrache.marketplace.controller.CutilFo;
import net.peyrache.marketplace.model.UtilisateurFo;

public class Fragment_Account_UtilFo extends Fragment {

    private UtilisateurFo utilFo, initUserFo;
    private EditText rs_modif_utilFo,rib_modif_utilFo,
                     addr_modif_utilFo,pass_modif_utilFo,mail_modif_utilFo;
    private Button modif_utilFo;
    private CutilFo cutil;
    private Context context;
    private CpageAccueil cpageAccueil;

    public Fragment_Account_UtilFo(UtilisateurFo utilisateurFo){
        this.utilFo=utilisateurFo;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_fo, container, false);
        this.context=container.getContext();
        init(view);
        return view;
    }

    private void init(View view) {
        cpageAccueil = new CpageAccueil(context);
        UtilisateurFo utilisateur = cpageAccueil.getConnexionFO(utilFo.getUsername());
        initUserFo=utilFo;
        rs_modif_utilFo = view.findViewById(R.id.rs_modif_utilFo);
            rs_modif_utilFo.setText(utilisateur.getRaisonSociale());
        rib_modif_utilFo = view.findViewById(R.id.rib_modif_utilFo);
            rib_modif_utilFo.setText(utilisateur.getRib());
        addr_modif_utilFo = view.findViewById(R.id.addr_modif_utilFo);
            addr_modif_utilFo.setText(utilisateur.getPostalAddress());
        pass_modif_utilFo = view.findViewById(R.id.pass_modif_utilFo);
            pass_modif_utilFo.setText(utilisateur.getPassword());
        mail_modif_utilFo = view.findViewById(R.id.mail_modif_utilFo);
            mail_modif_utilFo.setText(utilisateur.getEmail());
        modif_utilFo = view.findViewById(R.id.modif_utilFo);
        cutil = new CutilFo(context);

        ecouteurModif(utilisateur);
    }

    private void ecouteurModif(final UtilisateurFo utilisateur) {
        modif_utilFo.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Initialisation de la variable reload
                FragmentTransaction reload = getFragmentManager().beginTransaction();

               String rs_modif_utilFoSTR = rs_modif_utilFo.getText().toString();
               String rib_modif_utilFoSTR = rib_modif_utilFo.getText().toString();
               String addr_modif_utilFoSTR = addr_modif_utilFo.getText().toString();
               String pass_modif_utilFoSTR = pass_modif_utilFo.getText().toString();
               String mail_modif_utilFoSTR = mail_modif_utilFo.getText().toString();

                if(utilisateur.getRaisonSociale().equals(rs_modif_utilFoSTR) && utilisateur.getRib().equals(rib_modif_utilFoSTR)
                        && utilisateur.getPostalAddress().equals(addr_modif_utilFoSTR) && utilisateur.getPassword().equals(pass_modif_utilFoSTR)
                        && utilisateur.getEmail().equals(mail_modif_utilFoSTR)){

                    Toast.makeText(context, "Vous n'avez rien modifié", Toast.LENGTH_SHORT).show();

                }else{
                        cutil.modifUserFo(utilFo.getUsername(), pass_modif_utilFoSTR, mail_modif_utilFoSTR,
                                addr_modif_utilFoSTR, rib_modif_utilFoSTR, rs_modif_utilFoSTR);
                        Toast.makeText(context, "Modification/s effectuée/s", Toast.LENGTH_SHORT).show();
                        //Enleve le fragment courrant pour le remplacer par le même
                        reload.detach(Fragment_Account_UtilFo.this).attach(Fragment_Account_UtilFo.this).commit();
                }
            }
        });
    }
}
