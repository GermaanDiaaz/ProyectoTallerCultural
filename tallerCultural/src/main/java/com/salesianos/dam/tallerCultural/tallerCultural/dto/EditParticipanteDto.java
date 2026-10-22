package com.salesianos.dam.tallerCultural.tallerCultural.dto;

import com.salesianos.dam.tallerCultural.tallerCultural.model.Participante;
import jakarta.validation.constraints.NotBlank;

public record EditParticipanteDto(
        @NotBlank(message = "El nombre completo no puede estar vacío")
        String nombreCompleto
) {

    public Participante to() {
        return Participante.builder()
                .nombreCompleto(nombreCompleto)
                .build();
    }
}
