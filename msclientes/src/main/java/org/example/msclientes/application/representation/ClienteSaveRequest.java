package org.example.msclientes.application.representation;

import org.example.msclientes.domain.Cliente;
import lombok.Data;

@Data
public class ClienteSaveRequest {
    private Long id;
    private String cpf;
    private String nome;
    private Integer idade;

    public Cliente toModel(){
        return new Cliente(id, cpf, nome, idade);
    }
}
