package com.example.examenalejandro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetalleLibro extends AppCompatActivity {
    private TextView txtTitulo, txtAutor, txtISBN, txtLeido, txtPaginas, txtEditorial;
    private Button btnCancelar, btnEliminar;
    private SQLiteHelper bdsql;
    private Libro l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_libro);
        // declaramos los views;
        btnCancelar = (Button) findViewById(R.id.btnCancelarDetalle);
        btnEliminar = (Button) findViewById(R.id.btnEliminarDetalle);
        txtTitulo = (TextView) findViewById(R.id.txtTituloDetalle);
        txtAutor = (TextView) findViewById(R.id.txtAutorDetalle);
        txtISBN = (TextView) findViewById(R.id.txtISBNDetalle);
        txtLeido = (TextView) findViewById(R.id.txtLeidoDetalle);
        txtEditorial = (TextView) findViewById(R.id.txtEditorialDetalle);
        txtPaginas = (TextView) findViewById(R.id.txtPaginasDetalle);

        // declaramos los listeners
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra("borrado", false);
                setResult(RESULT_OK, i);
                finish();
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = bdsql.getWritableDatabase();
                if(db!=null) {
                    db.execSQL("delete from libro where isbn = '" + l.getISBN() + "'");
                }
                db.close();
                Intent i = new Intent();
                i.putExtra("borrado", true);
                setResult(RESULT_OK, i);
                finish();
            }
        });
        bdsql = new SQLiteHelper(this, "Biblioteca", null, 3);
        loadData();
    }

    private void loadData() {
        Bundle extras = getIntent().getExtras();
        String titulo = extras.getString("titulo");
        String autor = extras.getString("autor");
        String isbn = extras.getString("ISBN");
        int paginas = extras.getInt("numPag");
        boolean leido = extras.getBoolean("leido");
        String editorial = extras.getString("editorial");
        txtTitulo.setText(titulo);
        txtAutor.setText(autor);
        txtISBN.setText(isbn);
        txtEditorial.setText(editorial);
        txtPaginas.setText(paginas + " Paginas");
        txtLeido.setText((leido ? "Libro Leido" : "Libro sin leer"));
        l = new Libro(titulo, autor, paginas, isbn, leido, editorial);
    }
}