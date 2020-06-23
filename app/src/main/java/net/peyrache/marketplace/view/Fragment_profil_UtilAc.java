package net.peyrache.marketplace.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import net.peyrache.marketplace.R;
import net.peyrache.marketplace.controller.CpageAccueil;
import net.peyrache.marketplace.controller.CutilAc;
import net.peyrache.marketplace.model.UtilisateurAc;

public class Fragment_profil_UtilAc extends Fragment {
    private EditText modif_password_utilAC, modifAdresseUtilAc, modifRibUtilAc,modifEmailUtilAc;
    private Button modifProfilUtilAc;
    private CpageAccueil cpageAccueil;
    private UtilisateurAc utilisateurAc;
    private UtilisateurAc utilisateur;
    private CutilAc cutilAc;
    public Fragment_profil_UtilAc(UtilisateurAc utilisateur){
        this.utilisateurAc=utilisateur;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil_utilac, container, false);
        Context context = container.getContext();
        init(context, view);
        return view;
    }

    private void init(Context context, View view) {
        cpageAccueil = new CpageAccueil(context);
        cutilAc = new CutilAc(context);
        utilisateur = cpageAccueil.getConnexionAC(utilisateurAc.getUsername());
        modif_password_utilAC = view.findViewById(R.id.modif_password_utilAC);
            modif_password_utilAC.setText(utilisateur.getPassword());
        modifAdresseUtilAc = view.findViewById(R.id.modifAdresseUtilAc);
            modifAdresseUtilAc.setText(utilisateur.getPostalAddress());
        modifRibUtilAc = view.findViewById(R.id.modifRibUtilAc);
            modifRibUtilAc.setText(utilisateur.getRib());
        modifEmailUtilAc= view.findViewById(R.id.modifEmailUtilAc);
            modifEmailUtilAc.setText(utilisateur.getEmail());
        modifProfilUtilAc = view.findViewById(R.id.modifProfilUtilAc);

        ecouteurModif(context, utilisateur);
    }

    private void ecouteurModif(final Context context, final UtilisateurAc utilisateur) {
        modifProfilUtilAc.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction reload = getFragmentManager().beginTransaction();

                String modif_password_utilACSTR = modif_password_utilAC.getText().toString();
                String modifAdresseUtilAcSTR = modifAdresseUtilAc.getText().toString();
                String modifRibUtilAcSTR = modifRibUtilAc.getText().toString();
                String modifEmailUtilAcSTR = modifEmailUtilAc.getText().toString();

                if(utilisateur.getPassword().equals(modif_password_utilACSTR)
                    && utilisateur.getRib().equals(modifRibUtilAcSTR)
                    && utilisateur.getPostalAddress().equals(modifAdresseUtilAcSTR) &&
                    utilisateur.getEmail().equals(modifEmailUtilAcSTR)){

                    Toast.makeText(context, "Vous n'avez rien modifié", Toast.LENGTH_SHORT).show();

                }else{
                    cutilAc.updateUtilAc(utilisateurAc.getUsername(), modif_password_utilACSTR, modifEmailUtilAcSTR,
                            modifAdresseUtilAcSTR, modifRibUtilAcSTR);
                    Toast.makeText(context, "Modification/s effectuée/s", Toast.LENGTH_SHORT).show();
                    //Enleve le fragment courrant pour le remplacer par le même
                    reload.detach(Fragment_profil_UtilAc.this).attach(Fragment_profil_UtilAc.this).commit();
                }
            }
        });
    }
}
