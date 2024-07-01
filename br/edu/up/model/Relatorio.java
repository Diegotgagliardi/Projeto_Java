package br.edu.up.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Relatorio {

    private LocalDateTime dataHora;
    private Veiculo veiculo;
    private double valor;
    private String tipo; 

    public Relatorio(Veiculo veiculo, String tipo) {
        this.dataHora = LocalDateTime.now(); 
        this.veiculo = veiculo;
        this.valor = 0.0; 
        this.tipo = tipo;
    }

    public Relatorio(String data, String hora, Veiculo veiculo2, double valor2) {
        //TODO Auto-generated constructor stub
    }

    public String getData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataHora.format(formatter);
    }

    public String getHora() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return dataHora.format(formatter);
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }
}