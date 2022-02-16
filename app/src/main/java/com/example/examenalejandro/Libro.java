package com.example.examenalejandro;

public class Libro {
    private String titulo;
    private String autor;
    private int num_paginas;
    private String ISBN;
    private boolean leido;
    private String editorial;

    public Libro(String titulo, String autor, int num_paginas, String ISBN, boolean leido, String editorial) {
        this.titulo = titulo;
        this.autor = autor;
        this.num_paginas = num_paginas;
        this.ISBN = ISBN;
        this.leido = leido;
        this.editorial = editorial;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNum_paginas() {
        return num_paginas;
    }

    public void setNum_paginas(int num_paginas) {
        this.num_paginas = num_paginas;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
}
