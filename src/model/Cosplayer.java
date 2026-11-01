package model;

import exception.DadosInvalidosException;

/** Participante que se apresenta fantasiado. */
public class Cosplayer extends Participante {

    public Cosplayer(String nome, String email, String atividade) throws DadosInvalidosException {
        super(nome, email, COSPLAYER, atividade);
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Observação: registre seu personagem para o desfile.");
    }
}
