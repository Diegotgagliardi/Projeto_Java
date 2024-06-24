package br.edu.up;

import br.edu.up.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    private static final String ARQUIVO_VEICULOS = "veiculos.txt";
    private static final String ARQUIVO_VAGAS = "vagas.txt";
    private static final String ARQUIVO_RELATORIOS = "relatorios.txt";
    private static final String ARQUIVO_CONFIGURACAO = "configuracao.txt";

    public Persistencia() {

        criarArquivos();
    }

    private void criarArquivos() {
        criarArquivoSeNaoExistir(ARQUIVO_VEICULOS);
        criarArquivoSeNaoExistir(ARQUIVO_VAGAS);
        criarArquivoSeNaoExistir(ARQUIVO_RELATORIOS);
        criarArquivoSeNaoExistir(ARQUIVO_CONFIGURACAO);
    }

    private void criarArquivoSeNaoExistir(String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        try {
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salvarVeiculo(Veiculo veiculo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_VEICULOS, true))) {
            writer.println(veiculo.getModelo() + ";" + veiculo.getPlaca() + ";" + veiculo.getCor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Veiculo> carregarVeiculos() {
        List<Veiculo> veiculos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_VEICULOS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] dados = line.split(";");
                String modelo = dados[0];
                String placa = dados[1];
                String cor = dados[2];
                Veiculo veiculo = new Veiculo(modelo, placa, cor);
                veiculos.add(veiculo);
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return veiculos;
    }

    public void salvarVaga(Vaga vaga) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_VAGAS, true))) {
            writer.println(vaga.isOcupada() + ";" + (vaga.getVeiculo() != null ? vaga.getVeiculo().getPlaca() : ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Vaga> carregarVagas() {
        List<Vaga> vagas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_VAGAS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] dados = line.split(";");
                boolean ocupada = Boolean.parseBoolean(dados[0]);
                String placa = dados[1];
                Veiculo veiculo = placa.isEmpty() ? null : buscarVeiculoPorPlaca(placa);
                Vaga vaga = new Vaga(ocupada, veiculo);
                vagas.add(vaga);
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return vagas;
    }

    private Veiculo buscarVeiculoPorPlaca(String placa) {
        return null;
    }

    public void salvarRelatorio(Relatorio relatorio) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_RELATORIOS, true))) {
            writer.println(relatorio.getData() + ";" + relatorio.getHora() + ";" + relatorio.getVeiculo().getPlaca() + ";" + relatorio.getValor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Relatorio> carregarRelatorios() {
        List<Relatorio> relatorios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_RELATORIOS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] dados = line.split(";");
                String data = dados[0];
                String hora = dados[1];
                String placa = dados[2];
                double valor = Double.parseDouble(dados[3]);
                Veiculo veiculo = buscarVeiculoPorPlaca(placa);
                Relatorio relatorio = new Relatorio(data, hora, veiculo, valor);
                relatorios.add(relatorio);
            }
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            e.printStackTrace();
        }
        return relatorios;
    }

    public void salvarConfiguracao(Configuracao configuracao) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_CONFIGURACAO))) {
            writer.println(configuracao.getValorHora());
            writer.println(configuracao.getNumeroVagas());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Configuracao carregarConfiguracao() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_CONFIGURACAO))) {
            double valorHora = Double.parseDouble(reader.readLine());
            int numeroVagas = Integer.parseInt(reader.readLine());
            return new Configuracao(valorHora, numeroVagas);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return null; 
        }
    }
}
