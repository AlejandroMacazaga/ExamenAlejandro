package com.example.examenalejandro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NuevoLibro extends AppCompatActivity {
    private Button btnVolver, btnInsertar, btnCancelar;
    private EditText inputTitulo, inputAutor, inputISBN, inputEditorial, inputNumPaginas;
    private CheckBox chkLeido;
    private SQLiteHelper sqldb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_libro);
        // declaramos helper sql
        sqldb = new SQLiteHelper(this, "Biblioteca", null, 3);
        // declaramos los views button
        btnVolver = (Button) findViewById(R.id.btnVolver);
        btnInsertar = (Button) findViewById(R.id.btnInsertar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        // declaramos los views textview
        inputTitulo = (EditText) findViewById(R.id.inputTitulo);
        inputAutor = (EditText) findViewById(R.id.inputAutor);
        inputISBN = (EditText) findViewById(R.id.inputISBN);
        inputEditorial = (EditText) findViewById(R.id.inputEditorial);
        inputNumPaginas = (EditText) findViewById(R.id.inputNumPaginas);

        // declaramos el checkbox
        chkLeido = (CheckBox) findViewById(R.id.checkboxleido);
        // declaramos los listeners
        btnVolver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                limpiar();
            }
        });
        btnInsertar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SQLiteDatabase db = sqldb.getWritableDatabase();
                if(db != null) {
                    try {
                        String titulo = inputTitulo.getText().toString();
                        if(titulo.equals("")) {
                            throw new Exception("Titulo vacio");
                        }
                        String autor = inputAutor.getText().toString();
                        if(autor.equals("")) {
                            throw new Exception("Autor vacio");
                        }
                        String editorial = inputEditorial.getText().toString();
                        if(editorial.equals("")) {
                            throw new Exception("Editorial vacio");
                        }
                        String isbn = inputISBN.getText().toString();
                        if(isbn.equals("")) {
                            throw new Exception("ISBN vacio");
                        }
                        int numPag = Integer.parseInt(inputNumPaginas.getText().toString());
                        boolean leido = chkLeido.isChecked();
                        ContentValues libro = new ContentValues();
                        libro.put("titulo", titulo);
                        libro.put("autor", autor);
                        libro.put("editorial", editorial);
                        libro.put("ISBN", isbn);
                        libro.put("numpaginas", numPag);
                        libro.put("leido", leido);
                        db.insert("Libro", null, libro);
                    }
                    catch(Exception e) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(NuevoLibro.this);
                        builder.setTitle("ERROR");
                        builder.show();
                    }
                }
                db.close();
                limpiar();

            }
        });
    }

    private void limpiar() {
        inputTitulo.setText("");
        inputAutor.setText("");
        inputISBN.setText("");
        inputEditorial.setText("");
        inputNumPaginas.setText("");
        chkLeido.setChecked(false);
    }
}