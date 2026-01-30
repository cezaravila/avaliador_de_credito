package br.com.cezaravila.mscartoes.application.service;

import br.com.cezaravila.mscartoes.domain.model.ClienteCartao;
import br.com.cezaravila.mscartoes.infrastructure.persistence.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {
    private final ClienteCartaoRepository repository;

    public List<ClienteCartao> listCartoesByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
