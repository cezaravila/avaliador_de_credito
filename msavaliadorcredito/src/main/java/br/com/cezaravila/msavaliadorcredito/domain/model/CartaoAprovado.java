package br.com.cezaravila.msavaliadorcredito.domain.model;

import java.math.BigDecimal;

public record CartaoAprovado (
     String cartao,
     String bandeira,
     BigDecimal limiteAprovado){
}
