package br.edu.up;

import br.edu.up.controller.EntradaController;
import br.edu.up.controller.RelatorioController;
import br.edu.up.controller.SaidaController;
import br.edu.up.model.Configuracao;
import br.edu.up.view.TelaEntrada;
import br.edu.up.view.TelaRelatorio;
import br.edu.up.view.TelaSaida;

import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Persistencia persistencia = new Persistencia();
        TelaEntrada telaEntrada = new TelaEntrada();
        TelaSaida telaSaida = new TelaSaida();
        TelaRelatorio telaRelatorio = new TelaRelatorio(); 
        EntradaController entradaController = new EntradaController(telaEntrada, persistencia);
        SaidaController saidaController = new SaidaController(telaEntrada, telaSaida, persistencia);
        RelatorioController relatorioController = new RelatorioController(telaRelatorio, persistencia); 

        // Carregar a configuração do sistema
        Configuracao configuracao = persistencia.carregarConfiguracao();
        if (configuracao == null) {
            System.out.println("Configuração não encontrada! Definindo valores padrão...");
            configuracao = new Configuracao(5.0, 10);
            persistencia.salvarConfiguracao(configuracao);
        }

        // Loop do menu principal
        while (true) {
            System.out.println("\n--- Menu do Estacionamento ---");
            System.out.println("1 - Entrada de Veículo");
            System.out.println("2 - Saída de Veículo");
            System.out.println("3 - Relatório");
            System.out.println("4 - Sair");
            System.out.print("Digite a opção desejada: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    entradaController.entradaVeiculo();
                    break;
                case 2:
                    saidaController.saidaVeiculo();
                    break;
                case 3:
                    relatorioController.exibirRelatorio();
                    break;
                case 4:
                    System.out.println("Saindo do sistema...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}