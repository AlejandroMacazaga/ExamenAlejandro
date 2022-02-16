package com.example.examenalejandro;

public class Tiempo {
    private String fecha;
    private int minima;
    private int maxima;
    private String descripcion;

    public Tiempo(String fecha, int minima, int maxima, String descripcion) {
        this.fecha = fecha;
        this.minima = minima;
        this.maxima = maxima;
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getMinima() {
        return minima;
    }

    public void setMinima(int minima) {
        this.minima = minima;
    }

    public int getMaxima() {
        return maxima;
    }

    public void setMaxima(int maxima) {
        this.maxima = maxima;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
