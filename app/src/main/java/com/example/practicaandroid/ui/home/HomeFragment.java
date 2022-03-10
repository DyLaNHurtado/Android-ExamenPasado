package com.example.practicaandroid.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.practicaandroid.FragmentComunication;
import com.example.practicaandroid.R;
import com.example.practicaandroid.databinding.FragmentHomeBinding;
import com.example.practicaandroid.model.Categoria;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements FragmentComunication {

    private List<Categoria> datoList;
    private RecyclerView recyclerView;
    private AdapterCategoria adapterCategoria;
    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(datoList==null){
            datoList=new ArrayList<>();
        }
        adapterCategoria = new AdapterCategoria(datoList,getContext());



    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        recyclerView= view.findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        AdapterCategoria adapter = new AdapterCategoria(datoList,getContext());
        //recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterCategoria);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAddCategoria dialogAddCategoria = new DialogAddCategoria();
                dialogAddCategoria.show(getFragmentManager(),"FialogoNuevaCategoria");
            }
        });
        return view;
    }

    @Override
    public void onCategoriaInsertItemSelected(String titulo) {
        Categoria categoria = new Categoria();
        categoria.setName(titulo);
        categoria.setImage(R.drawable.ic_launcher_background);
        datoList.add(categoria);
    }
}