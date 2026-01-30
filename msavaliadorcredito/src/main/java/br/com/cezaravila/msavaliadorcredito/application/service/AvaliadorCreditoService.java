package br.com.cezaravila.msavaliadorcredito.application.service;

import br.com.cezaravila.msavaliadorcredito.domain.model.*;
import br.com.cezaravila.msavaliadorcredito.domain.model.*;
import feign.FeignException;
import br.com.cezaravila.msavaliadorcredito.domain.exception.DadosClienteNotFoundException;
import br.com.cezaravila.msavaliadorcredito.domain.exception.ErroComunicacaoMicroservicesException;
import br.com.cezaravila.msavaliadorcredito.domain.exception.ErroSolicitacaoCartaoException;
import br.com.cezaravila.msavaliadorcredito.infrastructure.feign.CartoesClient;
import br.com.cezaravila.msavaliadorcredito.infrastructure.feign.ClienteClient;
import br.com.cezaravila.msavaliadorcredito.infrastructure.messaging.producer.SolicitacaoEmissaoCartaoPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteClient clientesClient;
    private final CartoesClient cartoesClient;
    private final SolicitacaoEmissaoCartaoPublisher emissaoCartaoPublisher;

    public SituacaoCliente obterSituacaoCliente(String cpf)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException{
        // obterDadosClientes - msclientes
        // obter cartoes do cliente - mscartoes

        /* Ou seja vou preceisar fazer uma requisição para o serviço de cartões
        *  e uma requisição para o serviço de clientes*/

        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dadosCliente(cpf);
            ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesClient.getCartoesByCliente(cpf);

            return new SituacaoCliente(
                    dadosClienteResponse.getBody(),
                    cartoesResponse.getBody()
            );
        } catch (FeignException.FeignClientException e){
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status){
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }
    }

    public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException{
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dadosCliente(cpf);
            ResponseEntity<List<Cartao>> cartoesResponse = cartoesClient.getCartoesRendaAteh(renda);

            List<Cartao> cartoes = cartoesResponse.getBody();
            var listaCartoesAprovados = cartoes.stream().map(cartao -> {
                DadosCliente dadosCliente = dadosClienteResponse.getBody();

                BigDecimal limiteBasico = cartao.limiteBasico();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.idade());
                var fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                return new CartaoAprovado(
                        cartao.nome(),
                        cartao.bandeira(),
                        limiteAprovado
                );
            }).collect(Collectors.toList());

            return new RetornoAvaliacaoCliente(listaCartoesAprovados);

        } catch (FeignException.FeignClientException e){
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status){
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }
    }

    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados){
        try {
            emissaoCartaoPublisher.solicitarCartao(dados);
            var protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protocolo);
        } catch (Exception e) {
            throw new ErroSolicitacaoCartaoException(e.getMessage());
        }
    }
}
