package com.example.practicaandroid.database;


import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.practicaandroid.model.Categoria;
import org.jetbrains.annotations.NotNull;

@Database(entities = {Categoria.class},version = 1)
public abstract class CategoriaRoomDatabase extends androidx.room.RoomDatabase {

    private static String DATABASE_NAME="basededatos";
    public abstract CategoriaDAO CateogoriaDao();
    private static volatile CategoriaRoomDatabase INSTANCE;



    public synchronized static CategoriaRoomDatabase getInstance(final Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                    CategoriaRoomDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
