package br.com.cezaravila.mscartoes.domain.dto;

import java.math.BigDecimal;

public record DadosSolicitacaoEmissaoCartao (
    Long idCartao,
    String cpf,
    String endereco,
    BigDecimal limiteLiberado){
}