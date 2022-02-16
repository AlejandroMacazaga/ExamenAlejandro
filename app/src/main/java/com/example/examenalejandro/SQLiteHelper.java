package com.example.examenalejandro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    private final String sqlCreate = "CREATE TABLE Libro (" +
                            "titulo TEXT NOT NULL," +
                            "autor TEXT," +
                            "editorial TEXT," +
                            "ISBN TEXT NOT NULL," +
                            "numpaginas INT," +
                            "leido BOOLEAN," +
                            "PRIMARY KEY (ISBN))";

    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Libro");
        onCreate(db);
    }
}
