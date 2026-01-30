package br.com.cezaravila.msavaliadorcredito.domain.exception;

public class ErroSolicitacaoCartaoException extends RuntimeException {
    public ErroSolicitacaoCartaoException(String message) {
        super(message);
    }
}
