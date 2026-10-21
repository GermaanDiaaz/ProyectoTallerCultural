package com.salesianos.dam.tallerCultural.tallerCultural.dto;

import com.salesianos.dam.tallerCultural.tallerCultural.model.Organizador;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "GetOrganizadorList",
        description = "Item de una lista de organizadores"
)
public record GetOrganizadorList(
        @Schema(
                description = "Identificador único del organizador",
                example = "15"
        )
        Long id,

        @Schema(
                description = "Nombre del organizador",
                example = "Luismi"
        )
        String nombre
) {

    public static GetOrganizadorList of(Organizador o) {
        return new GetOrganizadorList(
                o.getId(),
                o.getNombre()
        );
    }

}