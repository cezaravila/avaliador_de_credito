package br.com.cezaravila.msavaliadorcredito.domain.model;

import java.math.BigDecimal;

public record Cartao (
    Long id,
    String nome,
    String bandeira,
    BigDecimal limiteBasico){
}
