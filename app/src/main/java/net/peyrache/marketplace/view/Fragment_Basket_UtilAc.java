package net.peyrache.marketplace.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import net.peyrache.marketplace.R;
import net.peyrache.marketplace.controller.CutilAc;
import net.peyrache.marketplace.model.Commande;
import net.peyrache.marketplace.model.UtilisateurAc;

import java.util.ArrayList;

public class Fragment_Basket_UtilAc extends Fragment {
    private ListView listeCommandes;
    private CutilAc cutilAc;
    private UtilisateurAc utilisateur;
    private Commandes_Adapter adapter;
    private FragmentTransaction reload;
    private Button suppression;

    public Fragment_Basket_UtilAc(UtilisateurAc utilisateur){
        this.utilisateur=utilisateur;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basket_utilac, container, false);
        Context context = container.getContext();
        init(context, view);
        return view;
    }

    private void init(Context context, View view) {
        cutilAc = new CutilAc(context);
        suppression = view.findViewById(R.id.Suppression);
        listeCommandes = view.findViewById(R.id.ContainerCommandes);
        ArrayList<Commande> listeCom = cutilAc.listeCommandes(utilisateur.getnUtilisateur());
        adapter = new Commandes_Adapter(utilisateur.getnUtilisateur(), listeCom, context);
        listeCommandes.setAdapter(adapter);
        ecouteurListeCommandes(context);
        ecouteurBtnSuppr(context, listeCom);
    }

    private void ecouteurBtnSuppr(final Context context, final ArrayList<Commande> listeCom) {
        suppression.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                cutilAc.supprToutesCommandes(listeCom, utilisateur.getnUtilisateur());
                Toast.makeText(context, "Vous Avez Annulé votre commande", Toast.LENGTH_SHORT).show();
                reload = getFragmentManager().beginTransaction();
                reload.detach(Fragment_Basket_UtilAc.this).attach(Fragment_Basket_UtilAc.this).commit();
            }
        });
    }

    private void ecouteurListeCommandes(final Context context) {
        listeCommandes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Commande commande = (Commande)listeCommandes.getItemAtPosition(position);
                Log.d("TypeCommande",commande.getnArticle().toString());
                Toast.makeText(context, "Vous avez annulé votre achat de : "+commande.getNomArticle(), Toast.LENGTH_SHORT).show();
                reload = getFragmentManager().beginTransaction();
                cutilAc.supprLigneCommande(utilisateur.getnUtilisateur(), commande.getnArticle(), commande.getnCommande());
                reload.detach(Fragment_Basket_UtilAc.this).attach(Fragment_Basket_UtilAc.this).commit();
            }
        });
    }
}
