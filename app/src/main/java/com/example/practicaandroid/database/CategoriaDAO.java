package com.example.practicaandroid.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.practicaandroid.model.Categoria;

import java.util.List;

@Dao
public interface CategoriaDAO {
    @Insert
    public long insert(Categoria categoria);
    @Query("SELECT * FROM categoria")
    List<Categoria> selectAll();
}
