package model;

public class Articulo {
    private String doi;
    private String titulo;
    private String autor;
    private int año;
    private int numPags;
    private boolean estado;



    // Constructor con parámetros
    public Articulo(String doi, String titulo, String autor, int año, int numPags, boolean estado) {
        this.doi = doi;
        this.titulo = titulo;
        this.autor = autor;
        this.año = año;
        this.numPags = numPags;
        this.estado = estado;
    }

    // Getters y Setters
    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
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

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getNumPags() {
        return numPags;
    }

    public void setNumPags(int numPags) {
        this.numPags = numPags;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
