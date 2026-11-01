package exception;

/**
 * Exceção personalizada (checked) lançada quando os dados de um
 * participante ou evento são inválidos (nome vazio, e-mail inválido,
 * e-mail repetido, tipo de participação desconhecido etc.).
 */
public class DadosInvalidosException extends Exception {

    public DadosInvalidosException(String mensagem) {
        super(mensagem);
    }
}
