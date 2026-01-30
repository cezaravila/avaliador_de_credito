package br.com.cezaravila.msclientes.api.dto;

import br.com.cezaravila.msclientes.domain.model.Cliente;

public record ClienteSaveRequest(Long id, String cpf, String nome, Integer idade) {
    public Cliente toEntity() {
        return new Cliente(id, cpf, nome, idade);
    }
}
