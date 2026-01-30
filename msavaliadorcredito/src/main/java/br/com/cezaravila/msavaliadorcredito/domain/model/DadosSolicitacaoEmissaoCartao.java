package br.com.cezaravila.msavaliadorcredito.domain.model;

import java.math.BigDecimal;

public record DadosSolicitacaoEmissaoCartao (
     Long idCartao,
     String cpf,
     String endereco,
     BigDecimal limiteLiberado){
}
