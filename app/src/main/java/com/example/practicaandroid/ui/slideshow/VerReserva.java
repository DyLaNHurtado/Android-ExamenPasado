package com.example.practicaandroid.ui.slideshow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.practicaandroid.Intercambio;
import com.example.practicaandroid.R;
import com.example.practicaandroid.databinding.ActivityVerReservaBinding;
import com.example.practicaandroid.mapper.UriMapper;
import com.example.practicaandroid.model.Receta;

public class VerReserva extends AppCompatActivity {

    private ActivityVerReservaBinding binding;
    private Receta r = Intercambio.getInstance().getReceta();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVerReservaBinding.inflate(getLayoutInflater());

        binding.dificultadView.setText(setDificultad());
        binding.imagenView.setImageURI(UriMapper.getInstance().fromStringToUri(r.getFoto()));
        binding.nombreView.setText(r.getNombre());
        Log.i("tiempo",""+r.getTiempoDeCoccion());
        binding.tiempoView.setText("Tiempo de preparacion: "+r.getTiempoDeCoccion()+"min");
        binding.preparacionVIew.setText(r.getElavoracion());
        binding.ingredientesView.setText(r.getIngredientes());
        setContentView(binding.getRoot());
    }

    private String setDificultad(){
        String ret = "Dificultad: ";

        if(r.getDificultad()==1){
            ret+="facil";
        }
        else if(r.getDificultad()==2){
            ret+="medio";
        }
        else{
            ret+="dificil";
        }
        return ret;
    }
}
