package br.com.cezaravila.mscartoes.infrastructure.persistence.repository;

import br.com.cezaravila.mscartoes.domain.model.ClienteCartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Long> {
    List<ClienteCartao> findByCpf(String cpf);
}
