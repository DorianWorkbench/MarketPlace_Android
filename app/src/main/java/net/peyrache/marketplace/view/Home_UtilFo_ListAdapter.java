package net.peyrache.marketplace.view;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.peyrache.marketplace.R;
import net.peyrache.marketplace.model.Article;

import java.util.ArrayList;

public class Home_UtilFo_ListAdapter extends BaseAdapter {

    private ArrayList<Article> listeArticle;
    private Context context;
    private LayoutInflater inflater;

    public Home_UtilFo_ListAdapter(Context context, ArrayList<Article> listeArticle){
        this.context = context;
        this.listeArticle = listeArticle;
        inflater= LayoutInflater.from(context);
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

            convertView = inflater.inflate(R.layout.row_listviewadapter, null);

            holder.lblArticle=convertView.findViewById(R.id.LblArticle);
            holder.lblPrix = convertView.findViewById(R.id.LblPrix);
            holder.lblQte = convertView.findViewById(R.id.LblQte);

            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }

        holder.lblArticle.setText(listeArticle.get(position).getNomArticle());
        holder.lblQte.setText("Qte : "+listeArticle.get(position).getQte().toString());
        holder.lblPrix.setText("Prix : "+listeArticle.get(position).getPrix().toString()+" €");

        return convertView;
    }
    private class ViewHolder{
        TextView lblArticle;
        TextView lblQte;
        TextView lblPrix;
    }
}
