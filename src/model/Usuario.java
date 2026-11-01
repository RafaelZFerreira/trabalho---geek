package model;

/**
 * Classe abstrata que representa um usuário genérico do sistema.
 * Não pode ser instanciada: serve de base para Pessoa.
 */
public abstract class Usuario {

    private static int proximoId = 1;

    private final int id; // atributo final: o id nunca muda depois de criado

    protected Usuario() {
        this.id = proximoId++;
    }

    public final int getId() { // método final: não pode ser sobrescrito
        return id;
    }

    // Método abstrato: cada subclasse define o seu perfil
    public abstract String getPerfil();
}
