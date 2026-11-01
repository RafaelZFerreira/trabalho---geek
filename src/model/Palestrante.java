package model;

import exception.DadosInvalidosException;

/** Participante que ministra palestras ou oficinas. */
public class Palestrante extends Participante {

    public Palestrante(String nome, String email, String atividade) throws DadosInvalidosException {
        super(nome, email, PALESTRANTE, atividade);
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Observação: teste o equipamento de apresentação antes da palestra.");
    }
}
