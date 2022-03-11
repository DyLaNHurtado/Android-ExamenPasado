package com.example.practicaandroid.ui.slideshow;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicaandroid.Intercambio;
import com.example.practicaandroid.R;
import com.example.practicaandroid.database.RoomDatabase;
import com.example.practicaandroid.mapper.UriMapper;
import com.example.practicaandroid.model.Receta;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.ViewHolder> {

    List<Receta> recetas;
    Context context;

    public RecetaAdapter(Context context){
        if(Intercambio.getInstance().getReceta()==null){
            recetas = RoomDatabase.getInstance(context).recetaDAO().getAllRecetas();
        }else{
            recetas = RoomDatabase.getInstance(context).recetaDAO().selectByCategoria(Intercambio.getInstance().getCategoria().getName());
        }
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public RecetaAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.receta_preview,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecetaAdapter.ViewHolder holder, int i) {
        holder.dificultad.setText("Dificultad nivel: "+recetas.get(i).getDificultad());
        holder.imagen.setImageURI(UriMapper.getInstance().fromStringToUri(recetas.get(i).getFoto()));
        holder.nombre.setText(recetas.get(i).getNombre());
    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imagen;
        TextView nombre,dificultad;
        ConstraintLayout layout;
        public ViewHolder(@NonNull @NotNull View v) {
            super(v);
            imagen = v.findViewById(R.id.recetaImagen);
            nombre = v.findViewById(R.id.nombreReceta);
            dificultad = v.findViewById(R.id.dificultadReceta);
            layout = v.findViewById(R.id.layoutPreview);

            onClick();
        }

        private void onClick(){
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int posicion = getAdapterPosition();
                    Intercambio.getInstance().setReceta(recetas.get(posicion));
                    context.startActivity(new Intent(context,VerReserva.class));
                }
            });
        }
    }


}
