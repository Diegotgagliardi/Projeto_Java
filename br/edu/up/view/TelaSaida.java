package br.edu.up.view;

import br.edu.up.model.Veiculo;
import java.util.Scanner;

public class TelaSaida {
    private Scanner scanner;

    public TelaSaida () {
        this.scanner = new Scanner(System.in);
    }
    
    public Veiculo obterInformacoesVeiculo() {
        System.out.println("Informe a placa do veículo que está saindo: ");
        String placa = scanner.nextLine();
        return new Veiculo(null, placa, null); 
    }

    public void exibirMensagemErro(String mensagem) {
        System.out.println("Erro: " + mensagem);
    }

    public void exibirMensagemSucesso(String mensagem) {
        System.out.println("Sucesso: " + mensagem);
    }
}