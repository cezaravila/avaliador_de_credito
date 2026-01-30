package br.com.cezaravila.msavaliadorcredito.domain.exception;

public class DadosClienteNotFoundException extends Exception{
    public DadosClienteNotFoundException(){
        super("Dados do cliente não encontrado para o CPF informado.");
    }
}
