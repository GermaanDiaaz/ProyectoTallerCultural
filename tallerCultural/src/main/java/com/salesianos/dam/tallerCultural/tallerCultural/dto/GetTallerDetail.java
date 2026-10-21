package com.salesianos.dam.tallerCultural.tallerCultural.dto;

import com.salesianos.dam.tallerCultural.tallerCultural.model.Taller;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Schema(
        name = "GetTallerDetail",
        description = "Detalles de un taller"
)
public record GetTallerDetail(

        @Schema(
                description = "Identificador único del taller",
                example = "15"
        )
        Long id,

        @Schema(
                description = "Título del taller",
                example = "Taller Python"
        )
        String titulo,

        @Schema(
                description = "Hora de inicio del taller"
        )
        LocalDateTime fechaHoraInicio,

        @Schema(
                description = "Hora de fin del taller"
        )
        LocalDateTime fechaHoraFin,

        @Schema(
                description = "Identificador único del organizador",
                example = "15"
        )
        Long organizadorId,

        @Schema(
                description = "Nombre del organizador",
                example = "Luismi"
        )
        String organizadorNombre,

        @Schema(
                description = "Participantes inscritos en el taller"
        )
        Set<GetParticipanteDetail> participantes

) {
    public static GetTallerDetail of(Taller t) {
        return new GetTallerDetail(
                t.getId(),
                t.getTitulo(),
                t.getFechaHoraInicio(),
                t.getFechaHoraFin(),
                t.getOrganizador() != null ? t.getOrganizador().getId() : null,
                t.getOrganizador() != null ? t.getOrganizador().getNombre() : null,

                //-----------------Pregunatar-----------------
                t.getParticipantes() != null ? t.getParticipantes().stream()
                        .map(GetParticipanteDetail::of)
                        .collect(Collectors.toSet())
                        : Set.of()


        );
    }
}
