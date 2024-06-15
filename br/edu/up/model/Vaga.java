package br.edu.up.model;

public class Vaga {
  private int vagas;
  private double valorHora = 5.0;
  private double valorTotal;

  public Vaga (int vagas) {
    this.vagas = vagas;
  }

  public int getVagas () {
    return vagas;
  }

  public void setVagas (int vagas) {
    this.vagas = vagas;
  }

  public double getValorHora () {
    return valorHora;
  }

  public double getValorTotal () {
    return valorTotal;
  }

  public void setValorTotal () {
    this.valorTotal = valorTotal;
  }

  @Override
  public String toString() {
    return "Vaga [vagas=" + vagas + ", valorHora=" + valorHora + ", valorTotal=" + valorTotal + ", toString()="
        + super.toString() + "]";
  }
}