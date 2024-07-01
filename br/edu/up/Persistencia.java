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

    private List<Veiculo> veiculos;
    private List<Vaga> vagas;
    private List<Relatorio> relatorios;
    private Configuracao configuracao;

    public Persistencia() {
        this.veiculos = carregarVeiculos();
        this.vagas = carregarVagas();
        this.relatorios = carregarRelatorios();
        this.configuracao = carregarConfiguracao();
        criarArquivos();

        // Criar vagas.txt com o número de vagas da configuração
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_VAGAS))) {
            for (int i = 0; i < configuracao.getNumeroVagas(); i++) {
                writer.println("false;"); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        veiculos.add(veiculo);
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
        vagas.add(vaga);
    }

    public List<Vaga> carregarVagas() {
        List<Vaga> vagas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_VAGAS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] dados = line.split(";");
                boolean ocupada = Boolean.parseBoolean(dados[0]);
                String placa = dados.length > 1 ? dados[1] : ""; 

                Veiculo veiculo = placa.isEmpty() ? null : buscarVeiculoPorPlaca(placa);
                Vaga vaga = new Vaga(ocupada, veiculo);
                vagas.add(vaga);
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return vagas;
    }

    public Veiculo buscarVeiculoPorPlaca(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    public void salvarRelatorio(Relatorio relatorio) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_RELATORIOS, true))) {
            writer.println(relatorio.getData() + ";" + relatorio.getHora() + ";" + relatorio.getVeiculo().getPlaca() + ";" + relatorio.getValor());
        } catch (IOException e) {
            e.printStackTrace();
        }
        relatorios.add(relatorio);
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
        this.configuracao = configuracao;
    }

    public Configuracao carregarConfiguracao() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_CONFIGURACAO))) {
            String valorHoraStr = reader.readLine();
            String numeroVagasStr = reader.readLine();

            // Verificar se as linhas foram lidas corretamente
            if (valorHoraStr == null || numeroVagasStr == null) {
                System.err.println("Arquivo 'configuracao.txt' inválido! Usando valores padrão.");
                return new Configuracao(5.0, 10); 
            }

            double valorHora = Double.parseDouble(valorHoraStr);
            int numeroVagas = Integer.parseInt(numeroVagasStr);
            return new Configuracao(valorHora, numeroVagas);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return null; 
        }
    }

    public Vaga getVagaVeiculo(Veiculo veiculo) {
        for (Vaga vaga : vagas) {
            if (vaga.getVeiculo() != null && vaga.getVeiculo().getPlaca().equals(veiculo.getPlaca())) {
                return vaga;
            }
        }
        return null;
    }

    public Vaga getProximaVagaLivre() {
        for (Vaga vaga : vagas) {
            if (!vaga.isOcupada()) { 
                return vaga;
            }
        }
        return null;
    }

    public void removerVeiculo(Veiculo veiculo) {
        veiculos.remove(veiculo);
    }
}