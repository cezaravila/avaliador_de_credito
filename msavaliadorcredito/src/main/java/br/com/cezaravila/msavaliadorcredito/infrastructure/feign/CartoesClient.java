package br.com.cezaravila.msavaliadorcredito.infrastructure.feign;

import br.com.cezaravila.msavaliadorcredito.domain.model.Cartao;
import br.com.cezaravila.msavaliadorcredito.domain.model.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartoesClient {

    @GetMapping(value = "lista-catoes-cliente", params = "cpf")
    ResponseEntity<List<CartaoCliente>> getCartoesByCliente(
            @RequestParam("cpf") String cpf);

    @GetMapping(value = "consulta-renda", params = "renda")
    ResponseEntity<List<Cartao>> getCartoesRendaAteh(@RequestParam("renda") Long renda);
}
