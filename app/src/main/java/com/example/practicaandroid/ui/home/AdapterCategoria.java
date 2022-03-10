package com.example.practicaandroid.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.practicaandroid.R;
import com.example.practicaandroid.model.Categoria;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterCategoria extends RecyclerView.Adapter<AdapterCategoria.ViewHolder> {

    List<Categoria> datoLista;
    Context context;


    public AdapterCategoria(List<Categoria> datoLista, Context context) {

        this.datoLista = datoLista;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Categoria categoria = new Categoria();
        categoria.setName(datoLista.get(position).getName());
        categoria.setImage(R.drawable.ic_launcher_background);
    }

    @Override
    public int getItemCount() {
        return datoLista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemTV;
        ImageView itemIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTV = itemView.findViewById(R.id.itemText);
            itemIV = itemView.findViewById(R.id.imageItem);
        }
    }
}
