package com.salesianos.dam.tallerCultural.tallerCultural.dto;

import com.salesianos.dam.tallerCultural.tallerCultural.model.Taller;

import java.time.LocalDateTime;

public record EditTallerDto(
        String titulo,
        LocalDateTime fechaHoraInicio,
        LocalDateTime fechaHoraFin
) {

    public Taller to() {
        return Taller.builder()
                .titulo(titulo)
                .fechaHoraInicio(fechaHoraInicio)
                .fechaHoraFin(fechaHoraFin)
                .build();
    }
}
