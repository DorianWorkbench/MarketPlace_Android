package net.peyrache.marketplace.view;

import android.content.Context;
import android.os.Bundle;
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

import net.peyrache.marketplace.R;
import net.peyrache.marketplace.controller.CutilFo;
import net.peyrache.marketplace.model.Article;
import net.peyrache.marketplace.model.UtilisateurFo;

public class Fragment_ModifArticle_utilFo extends Fragment {

    private Article article;
    private Integer idUtilisateur;
    private UtilisateurFo utilFo;
    private CutilFo cutilFo;

    private EditText description, prix, qte;
    private TextView nomArticle;
    private Button annuler, valider;
    private Fragment home;

    public Fragment_ModifArticle_utilFo(Article article, UtilisateurFo utilFo){
        this.article = article;
        this.utilFo = utilFo;
        this.idUtilisateur = utilFo.getnUtilisateur();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modification_article_utilfo, container, false);
        Context context= container.getContext();
        init(context, view);
        return view;
    }

    private void init(Context context, View view) {
        cutilFo = new CutilFo(context);
        nomArticle=view.findViewById(R.id.LblArticle);
            nomArticle.setText(article.getNomArticle());
        description=view.findViewById(R.id.modif_descArticle_utilFo);
            description.setText(article.getDescription());
        prix = view.findViewById(R.id.modif_prixArticle_utilFo);
            prix.setText(article.getPrix().toString());
        qte = view.findViewById(R.id.modif_qteArticle_utilFo);
            qte.setText(article.getQte().toString());
        valider = view.findViewById(R.id.modifier_ModiFo);
        annuler = view.findViewById(R.id.annuler_ModifFo);
        ecouteurValider(context);
        ecouteurAnnuler(context);
    }

    private void ecouteurAnnuler(final Context context) {
        annuler.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                home = new Fragment_Home_UtilFo(utilFo);
                Toast.makeText(context, "Vous avez annulé la modification", Toast.LENGTH_SHORT).show();
                getFragmentManager().beginTransaction().replace(R.id.container_fragment_fo, home).commit();
            }
        });
    }

    private void ecouteurValider(final Context context) {
        valider.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String descriptionSTR = description.getText().toString();
                    Float prixFLOAT = Float.parseFloat(prix.getText().toString());
                    Integer qteINT = Integer.parseInt(qte.getText().toString());

                    cutilFo.articleUpdate(article.getNomArticle(), descriptionSTR,prixFLOAT, qteINT, idUtilisateur);
                    Toast.makeText(context, "Vous avez modifier votre article "+article.getNomArticle(), Toast.LENGTH_SHORT).show();
                    home = new Fragment_Home_UtilFo(utilFo);
                    getFragmentManager().beginTransaction().replace(R.id.container_fragment_fo, home).commit();

                }catch (Exception e){
                    Toast.makeText(context, "Veuillez saisir des informations correctes", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
