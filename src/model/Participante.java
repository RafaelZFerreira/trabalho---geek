package model;

import exception.DadosInvalidosException;

/**
 * Pessoa inscrita em um evento. Herda nome e e-mail de Pessoa e
 * acrescenta o tipo de participação e a atividade.
 * As subclasses (Gamer, Cosplayer, Palestrante, Visitante) sobrescrevem
 * exibirDados() para mostrar uma observação própria (polimorfismo).
 */
public class Participante extends Pessoa {

    // Constantes com os tipos fixos de participação
    public static final String GAMER = "Gamer";
    public static final String COSPLAYER = "Cosplayer";
    public static final String PALESTRANTE = "Palestrante";
    public static final String VISITANTE = "Visitante";

    private final String tipoParticipacao;
    private String atividade;

    public Participante(String nome, String email, String tipoParticipacao, String atividade)
            throws DadosInvalidosException {
        super(nome, email); // chama o construtor de Pessoa
        if (tipoParticipacao == null || tipoParticipacao.trim().isEmpty()) {
            throw new DadosInvalidosException("O tipo de participação não pode ser vazio.");
        }
        this.tipoParticipacao = tipoParticipacao;
        setAtividade(atividade);
    }

    // Método final: o tipo não pode ser alterado nem sobrescrito
    public final String getTipoParticipacao() {
        return tipoParticipacao;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) throws DadosInvalidosException {
        if (atividade == null || atividade.trim().isEmpty()) {
            throw new DadosInvalidosException("A atividade não pode ser vazia.");
        }
        this.atividade = atividade.trim();
    }

    @Override
    public String getPerfil() {
        return "Participante (" + tipoParticipacao + ")";
    }

    @Override
    public void exibirDados() {
        super.exibirDados(); // reaproveita Nome e Email de Pessoa
        System.out.println("Tipo: " + tipoParticipacao);
        System.out.println("Atividade: " + atividade);
    }
}
