package model;

import exception.DadosInvalidosException;

/**
 * Representa os dados básicos de uma pessoa (nome e e-mail).
 * Herda de Usuario e implementa a interface Exibivel.
 * Os atributos são privados (encapsulamento) e a validação
 * acontece nos setters.
 */
public class Pessoa extends Usuario implements Exibivel {

    private String nome;
    private String email;

    public Pessoa(String nome, String email) throws DadosInvalidosException {
        super();
        setNome(nome);
        setEmail(email);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws DadosInvalidosException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new DadosInvalidosException("O nome não pode ser vazio.");
        }
        this.nome = nome.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws DadosInvalidosException {
        if (!emailValido(email)) {
            throw new DadosInvalidosException("E-mail inválido: \"" + email + "\".");
        }
        this.email = email.trim().toLowerCase();
    }

    // Validação simples: algo@dominio.ext
    private static boolean emailValido(String email) {
        return email != null && email.trim().matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$");
    }

    @Override
    public String getPerfil() {
        return "Pessoa";
    }

    @Override
    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
    }
}
