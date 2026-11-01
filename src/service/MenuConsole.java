package service;

import exception.DadosInvalidosException;
import model.Evento;
import model.Participante;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/** Desafio criativo: menu interativo no console. */
public class MenuConsole {

    private final Evento evento;
    private final Scanner teclado = new Scanner(System.in);

    public MenuConsole(Evento evento) {
        this.evento = evento;
    }

    public void iniciar() {
        int opcao;
        do {
            System.out.println();
            System.out.println("=== " + Evento.NOME_SISTEMA + " | " + evento.getNomeEvento() + " ===");
            System.out.println("1 - Cadastrar participante");
            System.out.println("2 - Listar participantes");
            System.out.println("3 - Buscar por tipo");
            System.out.println("4 - Exportar para arquivo .txt");
            System.out.println("5 - Total de inscritos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = lerInteiro();
            switch (opcao) {
                case 1:
                    cadastrar();
                    break;
                case 2:
                    evento.exibirDados();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    exportar();
                    break;
                case 5:
                    System.out.println("Total de inscritos: " + evento.getTotalParticipantes());
                    break;
                case 0:
                    System.out.println("Até a próxima!");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrar() {
        System.out.print("Nome: ");
        String nome = teclado.nextLine();
        System.out.print("Email: ");
        String email = teclado.nextLine();
        System.out.print("Tipo (Gamer, Cosplayer, Palestrante, Visitante): ");
        String tipo = teclado.nextLine();
        System.out.print("Atividade: ");
        String atividade = teclado.nextLine();
        try {
            evento.adicionarParticipante(nome, email, tipo, atividade);
            System.out.println("Participante cadastrado com sucesso!");
        } catch (DadosInvalidosException e) {
            System.out.println("Não foi possível cadastrar: " + e.getMessage());
        }
    }

    private void buscar() {
        System.out.print("Tipo a buscar: ");
        String tipo = teclado.nextLine();
        List<Participante> lista = evento.buscarPorTipo(tipo);
        if (lista.isEmpty()) {
            System.out.println("Nenhum participante do tipo \"" + tipo + "\".");
            return;
        }
        System.out.println(lista.size() + " participante(s) encontrado(s):");
        for (Participante p : lista) {
            System.out.println();
            p.exibirDados();
        }
    }

    private void exportar() {
        System.out.print("Nome do arquivo (ex.: participantes.txt): ");
        String arquivo = teclado.nextLine();
        try {
            evento.exportarParaArquivo(arquivo);
            System.out.println("Arquivo salvo: " + arquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    private int lerInteiro() {
        try {
            return Integer.parseInt(teclado.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // cai no "Opção inválida"
        }
    }
}
