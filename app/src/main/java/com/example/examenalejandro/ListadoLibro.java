package com.example.examenalejandro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ListadoLibro extends AppCompatActivity {
    private RadioGroup rg;
    private ListView listViewLibros;
    private SQLiteHelper dbsql;
    private Button btnVolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_libro);
        // declaramos el helper
        dbsql = new SQLiteHelper(this, "Biblioteca", null, 3);
        // declaramos las views
        listViewLibros = (ListView) findViewById(R.id.listViewLibros);
        rg = (RadioGroup) findViewById(R.id.radioGroup2);
        btnVolver = (Button) findViewById(R.id.btnVolverListado);
        // declaramos listener
        listViewLibros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Libro libro = (Libro) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(ListadoLibro.this, DetalleLibro.class);
                intent.putExtra("titulo", libro.getTitulo());
                intent.putExtra("autor", libro.getAutor());
                intent.putExtra("ISBN", libro.getISBN());
                intent.putExtra("numPag", libro.getNum_paginas());
                intent.putExtra("leido", libro.isLeido());
                intent.putExtra("editorial", libro.getEditorial());
                startActivityForResult(intent, 1);
            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
               loadListView();
            }
        });
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        loadListView();
    }

    // FUNCION PARA RECOGER LOS LIBROS DE LA BASE DE DATOS
    private ArrayList<Libro> getAllLibros(int option) {
        ArrayList<Libro> lista = new ArrayList<Libro>();
        SQLiteDatabase db = dbsql.getWritableDatabase();
        Cursor c = null;
        if(option == 1) {
            c = db.rawQuery("SELECT titulo, autor, editorial, ISBN, numpaginas FROM libro where leido = 0", null);
            // comprobamos si hay libros
            if(c.moveToFirst()) {
                do {
                   String titulo = c.getString(0);
                   String autor = c.getString(1);
                   String editorial = c.getString(2);
                   String ISBN = c.getString(3);
                   int numPag = c.getInt(4);
                   boolean leido = true;

                   lista.add(new Libro(titulo, autor, numPag, ISBN, leido, editorial));

                } while(c.moveToNext());
            }
        }
        if(option == 2) {
            c = db.rawQuery("SELECT titulo, autor, editorial, ISBN, numpaginas FROM libro where leido = 1", null);
            // comprobamos si hay libros
            if(c.moveToFirst()) {
                do {
                    String titulo = c.getString(0);
                    String autor = c.getString(1);
                    String editorial = c.getString(2);
                    String ISBN = c.getString(3);
                    int numPag = c.getInt(4);
                    boolean leido = false;

                    lista.add(new Libro(titulo, autor, numPag, ISBN, leido, editorial));

                } while(c.moveToNext());
            }
        }
        if(option == 0) {
            c = db.rawQuery("SELECT titulo, autor, editorial, ISBN, numpaginas, leido FROM libro", null);
            // comprobamos si hay libros
            if(c.moveToFirst()) {
                do {
                    String titulo = c.getString(0);
                    String autor = c.getString(1);
                    String editorial = c.getString(2);
                    String ISBN = c.getString(3);
                    int numPag = c.getInt(4);
                    boolean leido = (c.getInt(5) == 0 ? true : false);
                    lista.add(new Libro(titulo, autor, numPag, ISBN, leido, editorial));
                } while(c.moveToNext());
            }
        }
        c.close();
        db.close();
        return lista;
    }

    private void loadListView() {
        RadioButton btnSeleccionado = findViewById(rg.getCheckedRadioButtonId());
        int option = 0;
        // get dato seleccionado
        if(btnSeleccionado != null) {
            String seleccion = btnSeleccionado.getText().toString();
            if(seleccion.equals(getString(R.string.leido))) {
                option = 1;
            }
            if(seleccion.equals(getString(R.string.porleer))) {
                option = 2;
            }
        }
        ArrayList<Libro> lista = getAllLibros(option);
        Libro[] datos = new Libro[lista.size()];
        for(int i = 0; i < datos.length; i++) {
            datos[i] = lista.get(i);
        }
        AdaptadorLibros al = new AdaptadorLibros(this, datos);
        listViewLibros.setAdapter(al);
    }

    private class AdaptadorLibros extends ArrayAdapter<Libro> {
        private Libro[] lista;

        public AdaptadorLibros(Context c, Libro[] lista) {
            super(c, R.layout.listitem_libro, lista);
            this.lista = lista;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_libro, null);
            TextView tituloLibro = (TextView) item.findViewById(R.id.tituloLibroItem);
            TextView autor = (TextView) item.findViewById(R.id.autorLibroItem);
            tituloLibro.setText(lista[position].getTitulo());
            autor.setText(lista[position].getAutor());
            return item;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loadListView();
    }
}