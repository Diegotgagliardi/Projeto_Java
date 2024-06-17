package br.edu.up.controller;

import br.edu.up.model.Veiculo;
import br.edu.up.model.Vaga;
import br.edu.up.view.TelaEntrada; // Importação correta
import br.edu.up.view.TelaSaida; // Importação correta
import br.edu.up.model.Persistencia; // Certifique-se de que Persistencia esteja no pacote correto
import br.edu.up.model.Relatorio; // Certifique-se de que Relatorio esteja no pacote correto

public class SaidaController {
    private TelaEntrada telaEntrada;
    private TelaSaida telaSaida;
    private Persistencia persistencia;

    public SaidaController(TelaEntrada telaEntrada, TelaSaida telaSaida, Persistencia persistencia) {
        this.telaEntrada = telaEntrada;
        this.telaSaida = telaSaida;
        this.persistencia = persistencia;
    }

    public void saidaVeiculo() {
        Veiculo veiculo = telaSaida.obterInformacoesVeiculo();

        Vaga vaga = persistencia.getVagaVeiculo(veiculo);
        if (vaga == null) {
            telaSaida.exibirMensagemErro("Veículo não encontrado;");
            return;
        }

        Relatorio relatorio = new Relatorio(veiculo, "Saída");

        vaga.setVeiculo(null);

        persistencia.removerVeiculo(veiculo);
        persistencia.salvarVaga(vaga);
        persistencia.salvarRelatorio(relatorio);

        telaSaida.exibirMensagemSucesso("Veículo removido com sucesso.");
    }
}
