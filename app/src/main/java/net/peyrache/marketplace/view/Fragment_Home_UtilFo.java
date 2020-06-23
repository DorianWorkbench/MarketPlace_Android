package net.peyrache.marketplace.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.peyrache.marketplace.R;
import net.peyrache.marketplace.controller.CutilFo;
import net.peyrache.marketplace.model.Article;
import net.peyrache.marketplace.model.UtilisateurFo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fragment_Home_UtilFo extends Fragment {

    private CutilFo cutilFo;
    private UtilisateurFo utilFo;
    private Home_UtilFo_ListAdapter home_utilFo_listAdapter;
    private ListView listViewArticle;
    private Fragment fragmentModifArticle;

    public Fragment_Home_UtilFo(UtilisateurFo utilFo){
        this.utilFo = utilFo;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_fo, container, false);
        Context context = container.getContext();
        init(context, view);
        return view;
    }

    private void init(Context context, View view) {
        listViewArticle = view.findViewById(R.id.listViewArticles);
        cutilFo = new CutilFo(context);
        ArrayList<Article> listeArticle = (ArrayList<Article>)cutilFo.listeArticle(utilFo.getnUtilisateur());

        home_utilFo_listAdapter = new Home_UtilFo_ListAdapter(context, listeArticle);
        listViewArticle.setAdapter(home_utilFo_listAdapter);

        ecouteurListViewArticle(context, view);
    }

    private void ecouteurListViewArticle(final Context context, View view) {
        listViewArticle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Récupération de l'objet article sur l'item cliqué par l'utilisateur

                Article article = (Article) listViewArticle.getItemAtPosition(position);
                fragmentModifArticle = new Fragment_ModifArticle_utilFo(article, utilFo);
                getFragmentManager().beginTransaction().replace(R.id.container_fragment_fo, fragmentModifArticle).commit();
            }
        });
    }
}
