package com.example.examenalejandro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnEj1;
    private Button btnEj2;
    private Button btnSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declaracion de views
        btnEj1 = (Button) findViewById(R.id.btnEj1);
        btnEj2 = (Button) findViewById(R.id.btnEj2);
        btnSalir = (Button) findViewById(R.id.btnSalir);

        // declaracion de listeners
        btnEj1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Ejercicio1.class);
                startActivity(i);
            }
        });
        btnEj2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Ejercicio2.class);
                startActivity(i);
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Creamos el builder del Alert Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.salir_title);
                builder.setMessage(R.string.salir_pregunta);
                // Evento al pulsar SI
                builder.setPositiveButton(R.string.SI, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        dialog.dismiss();
                        System.exit(0);
                    }
                });
                // Evento al pulsar NO
                builder.setNegativeButton(R.string.NO, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });


    }
}