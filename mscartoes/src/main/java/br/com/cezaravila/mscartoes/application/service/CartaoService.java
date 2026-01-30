package br.com.cezaravila.mscartoes.application.service;

import br.com.cezaravila.mscartoes.domain.model.Cartao;
import br.com.cezaravila.mscartoes.domain.model.ClienteCartao;
import br.com.cezaravila.mscartoes.infrastructure.persistence.repository.CartaoRepository;
import br.com.cezaravila.mscartoes.infrastructure.persistence.repository.ClienteCartaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartaoService {

    private final CartaoRepository repository;
    private final ClienteCartaoRepository clienteCartaoRepository;

    public CartaoService(CartaoRepository cartaoRepository,
                         ClienteCartaoRepository clienteCartaoRepository) {
        this.repository = cartaoRepository;
        this.clienteCartaoRepository = clienteCartaoRepository;
    }

    @Transactional
    public Cartao save(Cartao cartao){
        return repository.save(cartao);
    }

    public List<Cartao> getCartoesRendaMenorIgual(Long renda){
        var rendaBigDecimal = BigDecimal.valueOf(renda);
        return repository.findByRendaLessThanEqual(rendaBigDecimal);
    }

    public List<ClienteCartao> buscarCartoesDoCliente(String cpf) {
        return clienteCartaoRepository.findByCpf(cpf);
    }
}
