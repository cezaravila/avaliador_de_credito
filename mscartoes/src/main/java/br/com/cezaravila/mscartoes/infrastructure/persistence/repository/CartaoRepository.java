package br.com.cezaravila.mscartoes.infrastructure.persistence.repository;

import br.com.cezaravila.mscartoes.domain.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    List<Cartao> findByRendaLessThanEqual(BigDecimal renda);
}
