package br.com.cezaravila.mscartoes.infrastructure.messaging.consumer;

import br.com.cezaravila.mscartoes.domain.dto.DadosSolicitacaoEmissaoCartao;
import br.com.cezaravila.mscartoes.domain.exception.ErroPermanenteException;
import br.com.cezaravila.mscartoes.domain.model.Cartao;
import br.com.cezaravila.mscartoes.domain.model.ClienteCartao;
import br.com.cezaravila.mscartoes.infrastructure.persistence.repository.CartaoRepository;
import br.com.cezaravila.mscartoes.infrastructure.persistence.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmissaoCartaoConsumer {

    private final CartaoRepository cartaoRepository;
    private final ClienteCartaoRepository clienteCartaoRepository;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "cartoes.emissao.queue")
    public void receberSolicitacaoEmissao(DadosSolicitacaoEmissaoCartao dados) {
        try {
            Cartao cartao = cartaoRepository.findById(dados.idCartao())
                    .orElseThrow(() -> new ErroPermanenteException("Cartão não encontrado"));
            ClienteCartao clienteCartao = new ClienteCartao();
            clienteCartao.setCartao(cartao);
            clienteCartao.setCpf(dados.cpf());
            clienteCartao.setLimiteAprovado(dados.limiteLiberado());

            clienteCartaoRepository.save(clienteCartao);

        } catch (ErroPermanenteException e) {
            log.error("Erro permanente → DLQ: {}", e.getMessage());
            rabbitTemplate.convertAndSend(
                    "cartoes.dlx",
                    "cartoes.emissao.dlq",
                    dados
            );

        } catch (Exception e) {
            log.error("Erro temporário → retry: {}", e.getMessage());
            throw e; // vai para retry automaticamente
        }
    }

}
