package br.com.cezaravila.mscartoes.api.dto;

import br.com.cezaravila.mscartoes.domain.model.Cartao;

import java.math.BigDecimal;

public record CartaoResponse(
        Long id,
        String nome,
        String bandeira,
        BigDecimal renda,
        BigDecimal limiteBasico
) {
    public static CartaoResponse fromModel(Cartao c) {
        return new CartaoResponse(c.getId(), c.getNome(), c.getBandeira(), c.getRenda(), c.getLimiteBasico());
    }
}