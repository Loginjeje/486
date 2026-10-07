package pt3;

import java.io.Serializable;

public class Videojoc implements Serializable {
    private static final long serialVersionUID = 1L;

    private String titol;
    private String genere;
    private int anyLlançament;
    private String plataforma;
    private double preu;

    public Videojoc(String titol, String genere, int anyLlançament, String plataforma, double preu) {
        this.titol = titol;
        this.genere = genere;
        this.anyLlançament = anyLlançament;
        this.plataforma = plataforma;
        this.preu = preu;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public int getAnyLlançament() {
        return anyLlançament;
    }

    public void setAnyLlançament(int anyLlançament) {
        this.anyLlançament = anyLlançament;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    @Override
    public String toString() {
        return "Títol: " + titol + ", Gènere: " + genere + ", Any: " + anyLlançament
                + ", Plataforma: " + plataforma + ", Preu: " + preu + " €";
    }
}