package model;

import exception.DadosInvalidosException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa o evento geek. Um Evento possui vários Participantes
 * (relacionamento 1 para N) guardados em uma ArrayList.
 */
public class Evento implements Exibivel {

    public static final String NOME_SISTEMA = "GEEKVERSE EVENTOS";

    private String nomeEvento;
    private String local;
    private final List<Participante> listaParticipantes = new ArrayList<>();

    public Evento(String nomeEvento, String local) throws DadosInvalidosException {
        if (nomeEvento == null || nomeEvento.trim().isEmpty()) {
            throw new DadosInvalidosException("O nome do evento não pode ser vazio.");
        }
        if (local == null || local.trim().isEmpty()) {
            throw new DadosInvalidosException("O local do evento não pode ser vazio.");
        }
        this.nomeEvento = nomeEvento.trim();
        this.local = local.trim();
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public String getLocal() {
        return local;
    }

    public List<Participante> getListaParticipantes() {
        return new ArrayList<>(listaParticipantes); // cópia: protege a lista interna
    }

    // ---------- Sobrecarga: duas formas de cadastrar participante ----------

    /** Forma 1: recebe um objeto Participante já criado. */
    public void adicionarParticipante(Participante participante) throws DadosInvalidosException {
        if (participante == null) {
            throw new DadosInvalidosException("Participante inválido (nulo).");
        }
        for (Participante p : listaParticipantes) {
            if (p.getEmail().equalsIgnoreCase(participante.getEmail())) {
                throw new DadosInvalidosException(
                        "Já existe um participante com o e-mail " + participante.getEmail() + ".");
            }
        }
        listaParticipantes.add(participante);
    }

    /** Forma 2: recebe os dados soltos e cria a subclasse certa. */
    public void adicionarParticipante(String nome, String email, String tipo, String atividade)
            throws DadosInvalidosException {
        Participante novo;
        switch (tipo == null ? "" : tipo.trim().toLowerCase()) {
            case "gamer":
                novo = new Gamer(nome, email, atividade);
                break;
            case "cosplayer":
                novo = new Cosplayer(nome, email, atividade);
                break;
            case "palestrante":
                novo = new Palestrante(nome, email, atividade);
                break;
            case "visitante":
                novo = new Visitante(nome, email, atividade);
                break;
            default:
                throw new DadosInvalidosException(
                        "Tipo de participação desconhecido: \"" + tipo
                                + "\" (use Gamer, Cosplayer, Palestrante ou Visitante).");
        }
        adicionarParticipante(novo);
    }

    // ---------- Consultas ----------

    public List<Participante> buscarPorTipo(String tipo) {
        List<Participante> encontrados = new ArrayList<>();
        for (Participante p : listaParticipantes) {
            if (p.getTipoParticipacao().equalsIgnoreCase(tipo)) {
                encontrados.add(p);
            }
        }
        return encontrados;
    }

    public int getTotalParticipantes() {
        return listaParticipantes.size();
    }

    // ---------- Exibição ----------

    @Override
    public void exibirDados() {
        System.out.println("=== " + NOME_SISTEMA + " ===");
        System.out.println("Evento: " + nomeEvento);
        System.out.println("Local: " + local);
        System.out.println();

        int numero = 1;
        // Polimorfismo: a lista guarda Participante, mas cada objeto
        // executa o exibirDados() da sua própria subclasse.
        for (Participante p : listaParticipantes) {
            System.out.println("Participante " + numero++);
            p.exibirDados();
            System.out.println();
        }
        System.out.println("Total de participantes cadastrados: " + getTotalParticipantes());
    }

    // ---------- Exportação ----------

    public void exportarParaArquivo(String caminho) throws IOException {
        try (PrintWriter out = new PrintWriter(caminho, "UTF-8")) {
            out.println("=== " + NOME_SISTEMA + " ===");
            out.println("Evento: " + nomeEvento);
            out.println("Local: " + local);
            out.println();
            int numero = 1;
            for (Participante p : listaParticipantes) {
                out.println("Participante " + numero++);
                out.println("Nome: " + p.getNome());
                out.println("Email: " + p.getEmail());
                out.println("Tipo: " + p.getTipoParticipacao());
                out.println("Atividade: " + p.getAtividade());
                out.println();
            }
            out.println("Total de participantes cadastrados: " + getTotalParticipantes());
        }
    }
}
