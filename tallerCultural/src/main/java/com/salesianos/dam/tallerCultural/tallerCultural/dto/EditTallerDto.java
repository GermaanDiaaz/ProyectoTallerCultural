package com.salesianos.dam.tallerCultural.tallerCultural.dto;

import com.salesianos.dam.tallerCultural.tallerCultural.model.Taller;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EditTallerDto(
        @NotBlank(message = "El título no puede estar vacío")
        String titulo,

        @NotNull(message = "La fecha y hora de inicio no pueden estar vacías")
        LocalDateTime fechaHoraInicio,

        @NotNull(message = "La fecha y hora de fin no pueden estar vacías")
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
