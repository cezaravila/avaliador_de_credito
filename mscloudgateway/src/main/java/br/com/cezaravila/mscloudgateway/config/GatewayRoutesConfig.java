package br.com.cezaravila.mscloudgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

    /**
     * Rotas do API Gateway usando service discovery (Eureka) via lb://
     */
    //    Esse método vai ser injetado pelo spring, ele vai fazer o roteamento
    //    das minhas rotas direto para o discoveryserver
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        /* Vou difinir minha primeira rota com '.route()', dentro
         * dele coloco uma expressão lambida 'r' de rota, toda vez que eu
         * acessar o gateway em sua porta fixa.
         * Quando eu for usar essa url 'r.path("/clientes/**")' vou redirecionar
         * para o loadbalance 'lb' com o nome do microservice, para pegar esse nome vou
         * nele e no '.yml' e pego o nome que esta em 'spring: application: name:' no meu
         * caso é msclientes, mscartoes e msavaliadorcredito*/
        return builder.routes()
                .route(r -> r.path("/clientes/**").uri("lb://msclientes"))
                .route(r -> r.path("/cartoes/**").uri("lb://mscartoes"))
                .route(r -> r.path("/avaliacoes-credito/**").uri("lb://msavaliadorcredito"))
                .build();
    }
}