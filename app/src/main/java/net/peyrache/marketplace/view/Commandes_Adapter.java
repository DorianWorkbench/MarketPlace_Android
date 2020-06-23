package net.peyrache.marketplace.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import net.peyrache.marketplace.R;
import net.peyrache.marketplace.controller.CutilAc;
import net.peyrache.marketplace.model.Commande;

import java.util.ArrayList;

public class Commandes_Adapter extends BaseAdapter {
    private ArrayList<Commande> listeCommandes;
    private Context context;
    private CutilAc cutilAc;
    private Integer idUtilisateur;
    private LayoutInflater inflater;

    public Commandes_Adapter(Integer idUtilisateur, ArrayList<Commande> listeCommandes, Context context){
        this.listeCommandes = listeCommandes;
        this.idUtilisateur=idUtilisateur;
        this.context=context;
        this.cutilAc = new CutilAc(context);
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return listeCommandes.size();
    }

    @Override
    public Object getItem(int position) {
        return listeCommandes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.row_lignecommande, null);

            holder.LblComArticle = convertView.findViewById(R.id.LblComArticle);
            holder.LblComFournisseur = convertView.findViewById(R.id.LblComFournisseur);
            holder.LblComPrixArticle = convertView.findViewById(R.id.LblComPrixArticle);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.LblComArticle.setText(listeCommandes.get(position).getNomArticle());
        holder.LblComFournisseur.setText(cutilAc.nomFournisseur(listeCommandes.get(position).getnArticle()));
        holder.LblComPrixArticle.setText(listeCommandes.get(position).getPrixArticle().toString()+" €");

        return convertView;
    }

    private class ViewHolder{
        TextView LblComArticle;
        TextView LblComFournisseur;
        TextView LblComPrixArticle;
    }
}
