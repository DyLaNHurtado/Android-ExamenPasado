package com.example.practicaandroid.ui.slideshow;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.practicaandroid.R;
import com.example.practicaandroid.database.RoomDatabase;
import com.example.practicaandroid.databinding.ActivityNuevaReservaBinding;
import com.example.practicaandroid.mapper.UriMapper;
import com.example.practicaandroid.model.Categoria;
import com.example.practicaandroid.model.Receta;

import org.jetbrains.annotations.NotNull;

public class NuevaReserva extends AppCompatActivity {
    private ActivityNuevaReservaBinding binding;
    private Uri imageUri = Uri.EMPTY;
    private SacarFotoAction sacarFotoAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNuevaReservaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sacarFotoAction = new SacarFotoAction(this,this,this);

        setSpinner();
        onClickLIstener();
    }

    private void onClickLIstener(){
        binding.nuevaReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkFields()){
                    guardarReceta();
                }else{
                    Toast.makeText(NuevaReserva.this, "Hay campos sin rellenar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.sacarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageUri = sacarFotoAction.sacarFoto();
            }
        });
    }
    private boolean checkFields(){
        return !binding.newDuracion.getText().toString().equalsIgnoreCase("") &&
                !binding.newPreparacion.getText().toString().equalsIgnoreCase("") &&
                !binding.nuevoNombre.getText().toString().equalsIgnoreCase("") &&
                !binding.newIngredientes.getText().toString().equalsIgnoreCase("") &&
                !imageUri.equals(Uri.EMPTY);
    }
    private void setSpinner(){
        ArrayAdapter adapter = new ArrayAdapter(
                this.getApplicationContext(),android.R.layout.simple_spinner_item,
                RoomDatabase.getInstance(this).CateogoriaDao().selectAll());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.categoriaSpinner.setAdapter(adapter);
    }

    private int getDificultad(){
        if(binding.dif1.isChecked()){
            return 1;
        }
        else if(binding.dif2.isChecked()){
            return 2;
        }
        else{
            return 3;
        }
    }

    public ActivityResultLauncher setLauncher() {
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                //result.getData().getExtras(MediaStore.)
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==3333){
            sacarFotoAction.openCamera();
        }else{
            Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardarReceta(){
        Receta receta = new Receta();
        receta.setNombre(binding.nuevoNombre.getText().toString());
        receta.setCategoria(getCategoria((Categoria)binding.categoriaSpinner.getSelectedItem()));
        receta.setDificultad(getDificultad());
        receta.setElavoracion(binding.newPreparacion.getText().toString());
        receta.setIngredientes(binding.newIngredientes.getText().toString());
        receta.setFoto(UriMapper.getInstance().fromUriToString(imageUri));
        receta.setTiempoDeCoccion(binding.newDuracion.getText().toString());

        RoomDatabase.getInstance(this).recetaDAO().insertReceta(receta);
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.verImagen.setImageURI(imageUri);
    }


    private String getCategoria(Categoria c){
        return c.getName();
    }
}