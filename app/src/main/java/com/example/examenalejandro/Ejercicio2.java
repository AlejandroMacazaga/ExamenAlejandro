package com.example.examenalejandro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.HashMap;

public class Ejercicio2 extends AppCompatActivity {
    private Spinner spinner;
    private Button btnVolver;
    private HashMap<String, String> mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);

        // declaramos las views
        spinner = (Spinner) findViewById(R.id.spinner);
        btnVolver = (Button) findViewById(R.id.btnVolverTiempo);

        // creamos el adapted del spinner y preparamos el mapa
        String[] list = getResources().getStringArray(R.array.localidades);
        String[] curatedList = new String[list.length];
        mapa = new HashMap<String, String>();
        for(int i = 0; i < list.length; i++) {
            curatedList[i] = list[i].substring(0, list[i].indexOf(";"));
            mapa.put(curatedList[i],list[i].substring(list[i].indexOf(";") + 1));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(Ejercicio2.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, curatedList);
        // aplicamos el adaptador al spinner
        spinner.setAdapter(adapter);
        //declaramos los listeners
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String key = spinner.getSelectedItem().toString();
                String url = mapa.get(key);
                XMLParser parser = new XMLParser(url);
                Tiempo[] tiempos = parser.read();
                loadFragments(newInstance(tiempos[0]), newInstance(tiempos[1]), newInstance(tiempos[2]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadFragments(Fragment fragment, Fragment fragment2, Fragment fragment3) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment1, fragment, null);
        transaction.replace(R.id.fragment2, fragment2, null);
        transaction.replace(R.id.fragment3, fragment3, null);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private static TiempoFragment newInstance(Tiempo t) {
        TiempoFragment f = new TiempoFragment();
        Bundle args = new Bundle();
        args.putString("fecha", t.getFecha());
        args.putInt("minima", t.getMinima());
        args.putInt("maxima", t.getMaxima());
        args.putString("descripcion", t.getDescripcion());
        f.setArguments(args);
        return f;
    }


}