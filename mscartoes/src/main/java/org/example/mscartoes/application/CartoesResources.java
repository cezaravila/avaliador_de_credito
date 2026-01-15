package org.example.mscartoes.application;

import org.example.mscartoes.application.representation.CartaoSaveRequest;
import org.example.mscartoes.application.representation.CartoesPorClienteResponse;
import org.example.mscartoes.domain.Cartao;
import org.example.mscartoes.domain.ClienteCartao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@Tag(name = "Cartões de Crédito")
@RequiredArgsConstructor
public class CartoesResources {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    @GetMapping("testes-status")
    @Operation(summary = "Teste do status")
    public String status() {
        return "ok";
    }

    @PostMapping("cadastro-cartao")
    @Operation(summary = "Cadastro de cartões")
    public ResponseEntity cadastra(@RequestBody CartaoSaveRequest request) {
        Cartao cartao = request.toModel();
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "consulta-renda", params = "renda")
    @Operation(summary = "Consulta a renda do cliente")
    public ResponseEntity<List<Cartao>> getCartoesRendaAteh(@RequestParam("renda") Long renda) {
        List<Cartao> list = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "lista-catoes-cliente", params = "cpf")
    @Operation(summary = "Retorna todos os cartões dos cliente")
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(
            @RequestParam("cpf") String cpf) {
        List<ClienteCartao> lista = clienteCartaoService.listCartoesByCpf(cpf);
        List<CartoesPorClienteResponse> resultList = lista.stream()
                .map(CartoesPorClienteResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}
