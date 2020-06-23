package net.peyrache.marketplace.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.peyrache.marketplace.R;
import net.peyrache.marketplace.controller.CutilFo;
import net.peyrache.marketplace.model.UtilisateurFo;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Add_UtilFo extends Fragment {

    //Définition des Elements graphiques/objets
    private EditText nomArticle, prix, qteArticle, descArticle;
    private Button ajoutBT;
    private CutilFo cutilFo;
    private Spinner spinnerCat;
    private ArrayAdapter<String> dataAdapter;
    private UtilisateurFo utilFo;

    public Fragment_Add_UtilFo(UtilisateurFo utilisateurFo){
        this.utilFo=utilisateurFo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Récupération de la vue pour pouvoir rechercher les éléments graphiques.
        View view = inflater.inflate(R.layout.fragment_add_fo, container, false);

        //Récupération du contexte pour pouvoir afficher des Toasts sur la page.
        Context context = container.getContext();

        //Ajout de la vue et du contexte dans les paramètres pour les utiliser ultérieurement.
        init(view, context);
        return view;
    }

    private void init(View v, final Context context) {
        //Récuperation des composants
        nomArticle = v.findViewById(R.id.nomArticleET);
        prix = v.findViewById(R.id.prixET);
        qteArticle = v.findViewById(R.id.qteArticle);
        descArticle = v.findViewById(R.id.descArticle);
        ajoutBT = v.findViewById(R.id.ajoutBT);
        spinnerCat = v.findViewById(R.id.spinnerCat);
        cutilFo = new CutilFo(context);
        List<String> cat = cutilFo.getAllLabels();

        //Définition du dataAdapter pour le spinner.
        dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, cat);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCat.setAdapter(dataAdapter);
        
        ecouteurAjout(context);
        
    }

    private void ecouteurAjout(final Context context) {
        ajoutBT.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String nomArticleSTR = nomArticle.getText().toString().toLowerCase();
                    String viewCat = spinnerCat.getSelectedItem().toString();
                    Float prixF = Float.parseFloat(prix.getText().toString());
                    Integer qteArticleINT = Integer.parseInt(qteArticle.getText().toString());
                    String descriptionArticleSTR = descArticle.getText().toString();

                    //Si un des champs suivant est vide, demander à l'utilisateur de saisir ces champs à nouveau.
                    if(nomArticleSTR.isEmpty() || viewCat.isEmpty() || descriptionArticleSTR.isEmpty()){
                        Toast.makeText(context, "Veuillez remplir les champs correctement", Toast.LENGTH_SHORT).show();

                        nomArticle.setText("");
                        prix.setText("");
                        qteArticle.setText("");
                        descArticle.setText("");

                    }else{
                            //Vérification de l'existance de l'article.
                            Boolean articleExist = cutilFo.articleExist(nomArticleSTR, utilFo.getnUtilisateur());

                       if (!articleExist){

                           //Méthode permettant d'ajouter un article.
                           cutilFo.addArticle(utilFo.getnUtilisateur(), viewCat, nomArticleSTR, descriptionArticleSTR, prixF, qteArticleINT, articleExist);

                           //Remet à zéro les champs

                           nomArticle.setText("");
                           prix.setText("");
                           qteArticle.setText("");
                           descArticle.setText("");

                           Toast.makeText(context, "L'article "+nomArticleSTR+" a été ajouté", Toast.LENGTH_SHORT).show();

                           Log.d("Article", "Ajouté");
                       }else{
                           //Erreur si l'article existe déjà
                           Toast.makeText(context, "L'article existe déjà", Toast.LENGTH_SHORT).show();
                       }
                    }

                }catch (Exception e){//Si le parse Int et le parse Float ne sont pas remplis correctement.
                    Toast.makeText(context, "Veuillez remplis les champs correctement", Toast.LENGTH_SHORT).show();
                }
                
            }
        });
    }

}
