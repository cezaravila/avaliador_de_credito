package br.com.cezaravila.mscartoes.api.dto;

import br.com.cezaravila.mscartoes.domain.model.ClienteCartao;

import java.math.BigDecimal;

public record CartoesPorClienteResponse(
        String nome,
        String bandeira,
        BigDecimal limiteLiberado
) {
    public static CartoesPorClienteResponse fromModel(ClienteCartao model) {
        return new CartoesPorClienteResponse(
                model.getCartao().getNome(),
                model.getCartao().getBandeira(),
                model.getLimiteAprovado()
        );
    }
}