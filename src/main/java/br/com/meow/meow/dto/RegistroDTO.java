package br.com.meow.meow.dto;

import br.com.meow.meow.model.Role;

public record RegistroDTO(String login, String password, Role role) {
}