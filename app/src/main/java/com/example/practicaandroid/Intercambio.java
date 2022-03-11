package com.example.practicaandroid;

import com.example.practicaandroid.model.Categoria;
import com.example.practicaandroid.model.Receta;

public class Intercambio {

    private static Intercambio intercambio;
    private  Intercambio(){}

    public static Intercambio getInstance(){
        if(intercambio == null)
            intercambio = new Intercambio();
        return intercambio;
    }

    private Receta receta;
    private Categoria categoria;

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}

