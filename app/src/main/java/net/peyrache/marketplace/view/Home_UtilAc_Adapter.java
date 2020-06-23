package net.peyrache.marketplace.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.peyrache.marketplace.R;
import net.peyrache.marketplace.controller.CutilAc;
import net.peyrache.marketplace.model.Article;


import java.util.ArrayList;


public class Home_UtilAc_Adapter extends BaseAdapter {

    private ArrayList<Article> listeArticle;
    private LayoutInflater inflater;
    private CutilAc cutilAc;

    public Home_UtilAc_Adapter(ArrayList<Article> mesArticles, Context context){
        this.listeArticle = mesArticles;
        this.inflater = LayoutInflater.from(context);
        cutilAc = new CutilAc(context);
    }

    @Override
    public int getCount() {
        return listeArticle.size();
    }

    @Override
    public Object getItem(int position) {
        return listeArticle.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.row_listviewadapter_utilac, null);

            holder.row_Article = (TextView)convertView.findViewById(R.id.row_Article);
            holder.row_Prix = (TextView)convertView.findViewById(R.id.row_Prix);
            holder.row_Qte = (TextView)convertView.findViewById(R.id.row_Qte);
            holder.row_Fournisseur = convertView.findViewById(R.id.row_Fournisseur);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.row_Article.setText(listeArticle.get(position).getNomArticle());
        holder.row_Prix.setText("Prix : "+listeArticle.get(position).getPrix().toString()+" €");
        holder.row_Qte.setText("qte : "+listeArticle.get(position).getQte().toString());
        holder.row_Fournisseur.setText(cutilAc.nomFournisseur(listeArticle.get(position).getnArticle()));

        return convertView;
    }
    private class ViewHolder{
        TextView row_Article;
        TextView row_Qte;
        TextView row_Prix;
        TextView row_Fournisseur;
    }
}
