package br.edu.up.controller;

import br.edu.up.Persistencia;
import br.edu.up.model.Relatorio;
import br.edu.up.view.TelaRelatorio;

import java.util.List;

public class RelatorioController {
    private TelaRelatorio telaRelatorio;
    private Persistencia persistencia;

    public RelatorioController(TelaRelatorio telaRelatorio, Persistencia persistencia) {
        this.telaRelatorio = telaRelatorio;
        this.persistencia = persistencia;
    }

    public void exibirRelatorio() {
        List<Relatorio> relatorios = persistencia.carregarRelatorios();
        telaRelatorio.exibirRelatorio(relatorios);
    }
}