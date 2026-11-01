package model;

import exception.DadosInvalidosException;

/** Participante que joga em campeonatos. */
public class Gamer extends Participante {

    public Gamer(String nome, String email, String atividade) throws DadosInvalidosException {
        super(nome, email, GAMER, atividade);
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Observação: chegue 30 min antes para o check-in do campeonato.");
    }
}
