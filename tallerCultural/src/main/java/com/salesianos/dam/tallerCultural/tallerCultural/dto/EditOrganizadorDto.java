package com.salesianos.dam.tallerCultural.tallerCultural.dto;

import com.salesianos.dam.tallerCultural.tallerCultural.model.Organizador;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EditOrganizadorDto(
        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email debe ser válido")
        String email
) {

    public Organizador to() {
        return Organizador.builder()
                .nombre(nombre)
                .email(email)
                .build();
    }
}
