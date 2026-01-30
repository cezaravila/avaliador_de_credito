package br.com.cezaravila.mscartoes.api.dto;

import br.com.cezaravila.mscartoes.domain.enums.BandeiraCartao;
import br.com.cezaravila.mscartoes.domain.model.Cartao;

import java.math.BigDecimal;

public record CartaoSaveRequest(
        String nome,
        BandeiraCartao bandeira,
        BigDecimal renda,
        BigDecimal limite) {
    public Cartao toEntity() {
        return new Cartao(nome, bandeira.toString(), renda, limite);
    }
}
