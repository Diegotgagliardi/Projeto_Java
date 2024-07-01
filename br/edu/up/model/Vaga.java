package br.edu.up.model;

public class Vaga {
    private boolean ocupada;
    private Veiculo veiculo;

    public Vaga(boolean ocupada, Veiculo veiculo) {
        this.ocupada = ocupada;
        this.veiculo = veiculo;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}