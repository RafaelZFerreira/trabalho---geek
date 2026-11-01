package main;

import exception.DadosInvalidosException;
import model.Cosplayer;
import model.Evento;
import model.Gamer;
import model.Palestrante;
import model.Participante;
import model.Visitante;
import service.MenuConsole;

import java.io.IOException;
import java.util.List;

/**
 * Sistema GeekVerse - gestão de participantes de evento geek.
 *
 * Execução normal: roda a demonstração completa.
 * Execução com o argumento "--menu": abre o menu interativo.
 */
public class Main {

    public static void main(String[] args) {
        try {
            Evento evento = new Evento("Tech Geek Experience", "Auditório Principal");

            if (args.length > 0 && args[0].equals("--menu")) {
                new MenuConsole(evento).iniciar();
                return;
            }

            demonstracao(evento);

        } catch (DadosInvalidosException e) {
            System.out.println("Erro ao criar o evento: " + e.getMessage());
        }
    }

    private static void demonstracao(Evento evento) {
        // 1) Cadastro de participantes (forma 1 da sobrecarga: objeto pronto)
        try {
            evento.adicionarParticipante(
                    new Gamer("Lucas Martins", "lucas@email.com", "Campeonato de E-sports"));
            evento.adicionarParticipante(
                    new Cosplayer("Ana Souza", "ana@email.com", "Concurso de Cosplay"));
            evento.adicionarParticipante(
                    new Palestrante("Marina Costa", "marina@email.com", "IA nos Games"));

            // Forma 2 da sobrecarga: dados soltos + tipo em texto
            evento.adicionarParticipante("João Lima", "joao@email.com", "Visitante", "Visitação livre");
        } catch (DadosInvalidosException e) {
            System.out.println("Erro no cadastro: " + e.getMessage());
        }

        // 2) Tratamento de exceções: dados inválidos não derrubam o programa
        System.out.println("--- Testando dados inválidos ---");
        tentarCadastrar(evento, "Pedro", "pedro.com", "Gamer", "Torneio");        // e-mail inválido
        tentarCadastrar(evento, "   ", "vazio@email.com", "Gamer", "Torneio");    // nome vazio
        tentarCadastrar(evento, "Bia", "lucas@email.com", "Gamer", "Torneio");    // e-mail repetido
        tentarCadastrar(evento, "Caio", "caio@email.com", "Alienígena", "Pouso"); // tipo inexistente
        System.out.println();

        // 3) Exibição final (polimorfismo: cada subclasse mostra sua observação)
        evento.exibirDados();

        // 4) Busca por tipo
        System.out.println();
        System.out.println("--- Busca por tipo: Gamer ---");
        List<Participante> gamers = evento.buscarPorTipo(Participante.GAMER);
        for (Participante p : gamers) {
            System.out.println(p.getNome() + " (" + p.getPerfil() + ")");
        }

        // 5) Exportação para .txt
        try {
            evento.exportarParaArquivo("participantes.txt");
            System.out.println();
            System.out.println("Arquivo participantes.txt gerado com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao exportar: " + e.getMessage());
        }
    }

    private static void tentarCadastrar(Evento evento, String nome, String email,
                                        String tipo, String atividade) {
        try {
            evento.adicionarParticipante(nome, email, tipo, atividade);
            System.out.println("Cadastrado: " + nome);
        } catch (DadosInvalidosException e) {
            System.out.println("Erro tratado -> " + e.getMessage());
        }
    }
}
