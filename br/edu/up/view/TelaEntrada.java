package br.edu.up.view;

import br.edu.up.model.Veiculo;
import java.util.Scanner;

public class TelaEntrada {

    private Scanner scanner;

    public TelaEntrada() {
        this.scanner = new Scanner(System.in);
    }

    public Veiculo obterInformacoesVeiculo() {
        System.out.println("Digite o modelo do veículo:");
        String modelo = scanner.nextLine();
        System.out.println("Digite a placa do veículo:");
        String placa = scanner.nextLine();
        System.out.println("Digite a cor do veículo:");
        String cor = scanner.nextLine();
        
        return new Veiculo(modelo, placa, cor);
    }

    public void exibirMensagemErro(String mensagem) {
        System.out.println("Erro: " + mensagem);
    }

    public void exibirMensagemSucesso(String mensagem) {
        System.out.println("Sucesso: " + mensagem);
    }
}