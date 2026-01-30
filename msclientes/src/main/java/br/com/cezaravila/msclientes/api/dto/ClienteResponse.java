package br.com.cezaravila.msclientes.api.dto;

public record ClienteResponse(Long id, String cpf, String nome, Integer idade) {}