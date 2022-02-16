package com.example.examenalejandro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ejercicio1 extends AppCompatActivity {

    private Button btnNuevoLibro;
    private Button btnListadoLibro;
    private Button btnVolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1);

        // declaramos las views
        btnNuevoLibro = (Button) findViewById(R.id.btnNuevoLibro);
        btnListadoLibro = (Button) findViewById(R.id.btnListadoLibro);
        btnVolver = (Button) findViewById(R.id.btnVolverLibro);

        // declaramos los eventos
        btnNuevoLibro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(Ejercicio1.this, NuevoLibro.class);
                startActivity(i);
            }
        });
        btnListadoLibro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(Ejercicio1.this, ListadoLibro.class);
                startActivity(i);
            }
        });
        btnVolver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}