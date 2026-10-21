package com.salesianos.dam.tallerCultural.tallerCultural.dto;

import com.salesianos.dam.tallerCultural.tallerCultural.model.Organizador;

public record EditOrganizadorDto(
        String nombre,
        String email
) {

    public Organizador to() {
        return Organizador.builder()
                .nombre(nombre)
                .email(email)
                .build();
    }
}
