package model;

import exception.DadosInvalidosException;

/** Participante que apenas visita o evento. */
public class Visitante extends Participante {

    public Visitante(String nome, String email, String atividade) throws DadosInvalidosException {
        super(nome, email, VISITANTE, atividade);
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Observação: acesso livre às áreas comuns do evento.");
    }
}
