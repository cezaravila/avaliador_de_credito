package br.com.cezaravila.msavaliadorcredito.api.controller;

import br.com.cezaravila.msavaliadorcredito.application.service.AvaliadorCreditoService;
import br.com.cezaravila.msavaliadorcredito.domain.exception.DadosClienteNotFoundException;
import br.com.cezaravila.msavaliadorcredito.domain.exception.ErroComunicacaoMicroservicesException;
import br.com.cezaravila.msavaliadorcredito.domain.exception.ErroSolicitacaoCartaoException;
import br.com.cezaravila.msavaliadorcredito.domain.model.*;
import br.com.cezaravila.msavaliadorcredito.domain.model.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("avaliacoes-credito")
@Tag(name = "Avaliação de Crédito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping("testes-status")
    @Operation(summary = "Teste do status")
    public String status(){
        return "Ok";
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    @Operation(summary = "Realiza a consulta da situação do cliente")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf){
        try {
            SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping("realiza-avaliacao")
    @Operation(summary = "Realiza a avaliação do cliente.")
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dados){
        try {
            RetornoAvaliacaoCliente retornoAvaliacaoCliente = avaliadorCreditoService.realizarAvaliacao(dados.cpf(), dados.renda());
            return ResponseEntity.ok(retornoAvaliacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping("solicitacoes-cartao")
    @Operation(summary = "Faz a solicitação do cartão do cliente")
    public ResponseEntity solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartao dados){
        try {
            ProtocoloSolicitacaoCartao protocoloSolicitacaoCartao = avaliadorCreditoService
                    .solicitarEmissaoCartao(dados);
            return ResponseEntity.accepted().body(protocoloSolicitacaoCartao);
        } catch (ErroSolicitacaoCartaoException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
