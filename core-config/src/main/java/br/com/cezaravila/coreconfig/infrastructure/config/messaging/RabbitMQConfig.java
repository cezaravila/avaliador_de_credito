package br.com.cezaravila.coreconfig.infrastructure.config.messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class RabbitMQConfig {

    // nomes padronizados
    public static final String EXCHANGE = "cartoes.exchange";
    public static final String ROUTING_KEY = "cartoes.emissao";
    public static final String QUEUE = "cartoes.emissao.queue";

    public static final String DLX = "cartoes.dlx";
    public static final String DLQ = "cartoes.emissao.dlq";

    // Retry (fila de espera) — opcional, mas bem “mercado”
    public static final String RETRY_QUEUE = "cartoes.emissao.retry.queue";
    public static final String RETRY_ROUTING_KEY = "cartoes.emissao.retry";

    // TTL do retry (quanto tempo espera antes de tentar de novo)
    private static final int RETRY_TTL_MS = 15_000; // 15s (dev). Em prod você pode aumentar via property depois.

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory cf, Jackson2JsonMessageConverter conv) {
        RabbitTemplate template = new RabbitTemplate(cf);
        template.setMessageConverter(conv);
        return template;
    }

    // Exchange principal
    @Bean
    public DirectExchange cartoesExchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    // DLX
    @Bean
    public DirectExchange cartoesDlx() {
        return new DirectExchange(DLX, true, false);
    }

    /**
     * Fila principal:
     * - quando você REJEITA/NACK sem requeue, ela vai pro DLX com routing retry
     */
    @Bean
    public Queue cartoesEmissaoQueue() {
        return QueueBuilder.durable(QUEUE)
                .withArguments(Map.of(
                        "x-dead-letter-exchange", DLX,
                        "x-dead-letter-routing-key", RETRY_ROUTING_KEY
                ))
                .build();
    }

    /**
     * Fila retry:
     * - segura a msg por TTL
     * - depois manda de volta para a fila principal (exchange principal)
     */
    @Bean
    public Queue cartoesRetryQueue() {
        return QueueBuilder.durable(RETRY_QUEUE)
                .withArguments(Map.of(
                        "x-message-ttl", RETRY_TTL_MS,
                        "x-dead-letter-exchange", EXCHANGE,
                        "x-dead-letter-routing-key", ROUTING_KEY
                ))
                .build();
    }

    /**
     * DLQ final:
     * - você manda pra cá quando for “erro permanente”
     */
    @Bean
    public Queue cartoesDlq() {
        return QueueBuilder.durable(DLQ).build();
    }

    @Bean
    public Binding bindQueuePrincipal(Queue cartoesEmissaoQueue, DirectExchange cartoesExchange) {
        return BindingBuilder.bind(cartoesEmissaoQueue).to(cartoesExchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding bindRetry(Queue cartoesRetryQueue, DirectExchange cartoesDlx) {
        return BindingBuilder.bind(cartoesRetryQueue).to(cartoesDlx).with(RETRY_ROUTING_KEY);
    }

    @Bean
    public Binding bindDlq(Queue cartoesDlq, DirectExchange cartoesDlx) {
        return BindingBuilder.bind(cartoesDlq).to(cartoesDlx).with(DLQ);
    }
}
