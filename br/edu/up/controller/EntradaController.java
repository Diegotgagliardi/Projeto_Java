package br.edu.up.controller;

import br.edu.up.model.Relatorio;
import br.edu.up.model.Veiculo;
import br.edu.up.model.Vaga;
import br.edu.up.view.TelaEntrada;
import br.edu.up.Persistencia;

public class EntradaController {

    private TelaEntrada telaEntrada;
    private Persistencia persistencia;

    public EntradaController(TelaEntrada telaEntrada, Persistencia persistencia) {
        this.telaEntrada = telaEntrada;
        this.persistencia = persistencia;
    }

    public void entradaVeiculo() {
        // Obter informações do veículo da TelaEntrada
        Veiculo veiculo = telaEntrada.obterInformacoesVeiculo();

        // Verificar disponibilidade de vagas
        Vaga vaga = persistencia.getProximaVagaLivre();
        if (vaga == null) {
            // Exibir mensagem de erro na TelaEntrada
            telaEntrada.exibirMensagemErro("Não há vagas disponíveis.");
            return;
        }

        // Criar objeto Relatorio
        Relatorio relatorio = new Relatorio(veiculo, "Entrada");

        // Associar veículo à vaga
        vaga.setVeiculo(veiculo);

        // Salvar informações em arquivos
        persistencia.salvarVeiculo(veiculo);
        persistencia.salvarVaga(vaga);
        persistencia.salvarRelatorio(relatorio);

        // Exibir mensagem de sucesso na TelaEntrada
        telaEntrada.exibirMensagemSucesso("Veículo registrado com sucesso!");
    }
}