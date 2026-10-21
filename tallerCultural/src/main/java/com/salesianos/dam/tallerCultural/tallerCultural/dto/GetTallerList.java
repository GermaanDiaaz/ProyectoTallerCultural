package com.salesianos.dam.tallerCultural.tallerCultural.dto;

import com.salesianos.dam.tallerCultural.tallerCultural.model.Taller;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "GetTallerList",
        description = "Item de una lista de talleres"
)
public record GetTallerList(

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
                description = "Nombre del organizador",
                example = "Luismi"
        )
        String organizadorNombre

) {
    public static GetTallerList of(Taller t) {
        return new GetTallerList(
                t.getId(),
                t.getTitulo(),
                t.getOrganizador() != null ? t.getOrganizador().getNombre() : null

        );
    }
}

