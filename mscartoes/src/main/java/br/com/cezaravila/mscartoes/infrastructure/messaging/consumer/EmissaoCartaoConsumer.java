package br.com.cezaravila.mscartoes.infrastructure.messaging.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.cezaravila.mscartoes.domain.model.Cartao;
import br.com.cezaravila.mscartoes.domain.model.ClienteCartao;
import br.com.cezaravila.mscartoes.domain.dto.DadosSolicitacaoEmissaoCartao;
import br.com.cezaravila.mscartoes.infrastructure.persistence.repository.CartaoRepository;
import br.com.cezaravila.mscartoes.infrastructure.persistence.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmissaoCartaoConsumer {

    private final CartaoRepository cartaoRepository;

    //public void receberSolicitacaoEmissao(@Payload String payload) {
    @RabbitListener(queues = "cartoes.emissao.queue")
    public void receberSolicitacaoEmissao(DadosSolicitacaoEmissaoCartao dados) {
        try {
           // var mapper = new ObjectMapper();
           // DadosSolicitacaoEmissaoCartao dados = mapper.readValue(payload, DadosSolicitacaoEmissaoCartao.class);
            Cartao cartao = cartaoRepository.findById(dados.idCartao()).orElseThrow();
            ClienteCartao clienteCartao = new ClienteCartao();
            clienteCartao.setCartao(cartao);
            clienteCartao.setCpf(dados.cpf());
            clienteCartao.setLimiteAprovado(dados.limiteLiberado());

            clienteCartaoRepository.save(clienteCartao);

        }catch (Exception e){
            log.error("Erro ao receber solicitacao de emissao de cartão : {}", e.getMessage());
        }
    }

    private final ClienteCartaoRepository clienteCartaoRepository;
}
