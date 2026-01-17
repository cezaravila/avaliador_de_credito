package org.example.mscartoes.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cartao_cliente")
public class ClienteCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf_cliente", nullable = false, length = 11)
    private String cpf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cartao", nullable = false)
    private Cartao cartao;

    @Column(name = "limite_aprovado", nullable = false, precision = 15, scale = 2)
    private BigDecimal limiteAprovado;

    public ClienteCartao() {
    }

    public ClienteCartao(String cpf, Cartao cartao, BigDecimal limiteAprovado) {
        this.cpf = cpf;
        this.cartao = cartao;
        this.limiteAprovado = limiteAprovado;
    }

    // getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public BigDecimal getLimiteAprovado() {
        return limiteAprovado;
    }

    public void setLimiteAprovado(BigDecimal limiteAprovado) {
        this.limiteAprovado = limiteAprovado;
    }
}
