package br.com.cezaravila.mscartoes.domain.exception;

public class ErroPermanenteException extends RuntimeException {
    public ErroPermanenteException(String msg) {
        super(msg);
    }
}