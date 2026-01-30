package br.com.cezaravila.msclientes.api.controller;

import br.com.cezaravila.msclientes.api.dto.ClienteResponse;
import br.com.cezaravila.msclientes.application.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import br.com.cezaravila.msclientes.api.dto.ClienteSaveRequest;
import br.com.cezaravila.msclientes.domain.model.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clientes")
@Tag(name = "Clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService service;

    @GetMapping("testes-status")
    @Operation(summary = "Teste do status")
    public String status(){
        log.info("Obtendo o status do microservice de cliens");
        return "ok";
    }

    @PostMapping("salvar-novo-usuario")
    @Operation(summary = "Método que vai salvar um novo usuário")
    public ResponseEntity save(@RequestBody ClienteSaveRequest request){
        Cliente cliente = request.toEntity();
        service.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(value = "dados-cliente", params = "cpf")
    @Operation(summary = "Retorna os dados do cliente")
    public ResponseEntity<ClienteResponse> dadosCliente(@RequestParam("cpf") String cpf){
        return service.getByCPF(cpf)
                .map(cliente -> ResponseEntity.ok(
                        new ClienteResponse(
                                cliente.getId(),
                                cliente.getCpf(),
                                cliente.getNome(),
                                cliente.getIdade()
                        )
                ))
                .orElse(ResponseEntity.notFound().build());
    }
}
