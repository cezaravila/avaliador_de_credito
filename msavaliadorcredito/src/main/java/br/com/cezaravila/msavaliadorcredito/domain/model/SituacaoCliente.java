package br.com.cezaravila.msavaliadorcredito.domain.model;

import java.util.List;

public record SituacaoCliente (
     DadosCliente cliente,
     List<CartaoCliente> cartoes){
}
