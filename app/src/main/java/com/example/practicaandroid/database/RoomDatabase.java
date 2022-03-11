package com.example.practicaandroid.database;


import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.practicaandroid.model.Categoria;
import com.example.practicaandroid.model.Receta;

import org.jetbrains.annotations.NotNull;

@Database(entities = {Categoria.class, Receta.class},version = 1)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {
    private static String DATABASE_NAME="databasee";

    public abstract CategoriaDAO CateogoriaDao();
    public abstract RecetaDAO recetaDAO();
    private static volatile RoomDatabase INSTANCE;


    public synchronized static RoomDatabase getInstance(final Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                    RoomDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}