package br.edu.up.view;

import br.edu.up.model.Relatorio;

import java.util.List;

public class TelaRelatorio {

    public void exibirRelatorio(List<Relatorio> relatorios) {
        System.out.println("\n--- Relatório de Entrada e Saída ---");
        if (relatorios.isEmpty()) {
            System.out.println("Não há relatórios para exibir.");
            return;
        }

        for (Relatorio relatorio : relatorios) {
            System.out.println("Data: " + relatorio.getData() + " Hora: " + relatorio.getHora());
            System.out.println("Veículo: " + relatorio.getVeiculo().getPlaca());
            System.out.println("Tipo: " + relatorio.getTipo());
            System.out.println("Valor: R$ " + relatorio.getValor());
            System.out.println("-------------------------------------");
        }
    }
}