package br.com.cezaravila.msavaliadorcredito.domain.model;

import java.util.List;

public record RetornoAvaliacaoCliente (
    List<CartaoAprovado> cartoes){
}
