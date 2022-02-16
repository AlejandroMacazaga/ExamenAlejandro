package com.example.examenalejandro;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class TiempoFragment extends Fragment {

    private View v;
    private TextView txtFecha, txtTemp, txtDescripcion;

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        OnBackPressedCallback callback = new OnBackPressedCallback(
                true // default to enabled
        ) {
            @Override
            public void handleOnBackPressed() {
                Ejercicio2 activity = (Ejercicio2) getActivity();
                activity.finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(
                this, // LifecycleOwner
                callback);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tiempo, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        v = getView();
        Tiempo t = new Tiempo(getArguments().getString("fecha"), getArguments().getInt("maxima"), getArguments().getInt("minima"), getArguments().getString("descripcion"));
        txtFecha = (TextView) v.findViewById(R.id.txtFecha);
        txtTemp = (TextView) v.findViewById(R.id.txtMinMax);
        txtDescripcion = (TextView) v.findViewById(R.id.txtDescripcion);
        txtFecha.setText(t.getFecha());
        txtTemp.setText("MIN:" + t.getMinima() + " / MAX:" +t.getMaxima());
        txtDescripcion.setText(t.getDescripcion());




    }
}