package br.edu.up.model;

public class Configuracao {

    private double valorHora;
    private int numeroVagas;

    public Configuracao(double valorHora, int numeroVagas) {
        this.valorHora = valorHora;
        this.numeroVagas = numeroVagas;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public int getNumeroVagas() {
        return numeroVagas;
    }

    public void setNumeroVagas(int numeroVagas) {
        this.numeroVagas = numeroVagas;
    }

    @Override
    public String toString() {
        return "Configuracao{" +
                "valorHora=" + valorHora +
                ", numeroVagas=" + numeroVagas +
                '}';
    }
}
