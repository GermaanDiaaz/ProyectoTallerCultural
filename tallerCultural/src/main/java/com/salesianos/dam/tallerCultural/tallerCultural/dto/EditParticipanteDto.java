package com.salesianos.dam.tallerCultural.tallerCultural.dto;

import com.salesianos.dam.tallerCultural.tallerCultural.model.Participante;

public record EditParticipanteDto(
        String nombreCompleto
) {

    public Participante to() {
        return Participante.builder()
                .nombreCompleto(nombreCompleto)
                .build();
    }
}
