package net.peyrache.marketplace.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import net.peyrache.marketplace.R;
import net.peyrache.marketplace.controller.CutilAc;
import net.peyrache.marketplace.model.Article;
import net.peyrache.marketplace.model.UtilisateurAc;

import java.util.ArrayList;

public class Fragment_Home_UtilAc extends Fragment {

    private ArrayList<Article> listeArticle;
    private CutilAc cutilAc;
    private UtilisateurAc utilAc;
    private Home_UtilAc_Adapter adapter;
    private ListView listeViewArticles;

    public Fragment_Home_UtilAc(UtilisateurAc utilAc){
        this.utilAc=utilAc;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_utilac, container, false);
        Context context = container.getContext();
        init(context, view);
        return view;
    }

    private void init(Context context, View view) {
        cutilAc = new CutilAc(context);
        listeArticle = cutilAc.listeBoutique();
        listeViewArticles = view.findViewById(R.id.container_Article);

        adapter = new Home_UtilAc_Adapter(listeArticle, context);
        listeViewArticles.setAdapter(adapter);
        ecouteurListeArticles(context, view);
    }

    private void ecouteurListeArticles(final Context context, View view) {
        listeViewArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentTransaction thisFragment = getFragmentManager().beginTransaction();

                Article articlepos = (Article)listeViewArticles.getItemAtPosition(position);

                Log.d("articleposSuffisant", cutilAc.stockSuffisant(articlepos.getnArticle()).toString());
                if (cutilAc.stockSuffisant(articlepos.getnArticle())){
                    cutilAc.ajoutCommande(utilAc.getnUtilisateur(), articlepos.getnArticle(), articlepos.getPrix(), articlepos.getNomArticle());
                    Toast.makeText(context, "Vous avez acheté : "+articlepos.getNomArticle(), Toast.LENGTH_SHORT).show();
                    thisFragment.detach(Fragment_Home_UtilAc.this).attach(Fragment_Home_UtilAc.this).commit();
                }else{
                    Toast.makeText(context, "Pas assez d'article", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

