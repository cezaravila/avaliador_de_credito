package br.com.cezaravila.msavaliadorcredito.domain.model;

public record DadosCliente (
     Long id,
     String cpf,
     String nome,
     Integer idade){
}
