package br.edu.up.controller;

import br.edu.up.model.Veiculo;
import br.edu.up.model.Vaga;
import br.edu.up.view.TelaEntrada; 
import br.edu.up.view.TelaSaida; 
import br.edu.up.Persistencia; 
import br.edu.up.model.Relatorio; 

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
        vaga.setOcupada(false); 

        persistencia.removerVeiculo(veiculo);
        persistencia.salvarVaga(vaga);
        persistencia.salvarRelatorio(relatorio);

        telaSaida.exibirMensagemSucesso("Veículo removido com sucesso.");
    }
}